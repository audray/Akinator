import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Clase para el adivinador.
 * @author Ariadna N. Hern�ndez Rivero -A01228363
 * @author Alfredo Z�rate Buenrostro - A01222814
 *
 */
public class GameInterface extends JFrame implements ActionListener{
        //private ArbolBinario arbol;
        private NodoArbol<String> nodoActual;
	private JLabel texto, nuevoPj, nuevaPreg;
	private JTextField textfield;
	private JButton botonSi,
	botonNo,botonReset,botonGuardar, botonMusica;
	private String nuevo, nuevoV;
	private boolean save, reproduccion;
	private PanelImg fondo;
        Akinator juego;

	public GameInterface(Reproductor reproductor){
		super();
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(600,450));
		JPanel panelBoton=new JPanel();
                
                this.botonMusica= addButton(panelBoton, "Musica");
		this.botonMusica.addActionListener(this);

		this.botonSi= addButton(panelBoton, "Si");
		botonSi.addActionListener(this);

		this.botonNo=addButton(panelBoton, "No");
		botonNo.addActionListener(this);

		this.botonReset= addButton(panelBoton, "Reiniciar");
		botonReset.addActionListener(this);

		this.add(panelBoton, BorderLayout.SOUTH);

		JPanel panelTexto= new JPanel();
		panelTexto.setPreferredSize(new Dimension(450,100));

		this.texto= addText(panelTexto, "�Quieres jugar? Piensa en un villano de Disney y yo lo adivino.", true);
		this.nuevoPj= addText(panelTexto, "�Qu� personaje estabas buscando?", false);
		this.textfield= new JTextField(20);
		this.textfield.setVisible(false);
                panelTexto.add(textfield);

		this.botonGuardar=addButton(panelTexto, "Guardar");
		this.botonGuardar.addActionListener(this);
		this.botonGuardar.setVisible(false);
		panelBoton.setBackground(Color.BLACK);
		panelTexto.setBackground(Color.RED);
		this.add(panelTexto, BorderLayout.NORTH);
		
		this.fondo= new PanelImg();
		this.add(fondo, BorderLayout.CENTER);
                
                this.juego= new Akinator(reproductor);
                this.juego.loadGame();
	
		this.pack();
		this.setVisible(true);
		this.reproduccion=true;

	}
        
        public JButton addButton(JPanel button_panel, String button_label){
            JButton new_button = new JButton(button_label);
            button_panel.add(new_button);
            return new_button;
        }
        
        public JLabel addText(JPanel text_panel, String text, boolean visible){
            JLabel text_label= new JLabel(text);
            text_label.setVisible(visible);
            text_panel.add(text_label);
            return text_label;
            
        }
	
	/**
	 * reiniciar - Metodo que reinicia en valores predeterminados para comenzar de nuevo
	 */
	public void reiniciar(){
		this.juego.nodoActual= null;
		this.fondo.fondoP= juego.villanos;
		this.fondo.repaint();
		this.juego.nodoActual= juego.arbol.next(this.juego.nodoActual, true);
		this.texto.setText(this.juego.nodoActual.getDato());
	}

/**
 * Inner class PanelImg.
 * @author Ari
 *
 */
	private class PanelImg extends JPanel{
		private Image fondoP;
		
		private PanelImg(){
			this.fondoP= new ImageIcon("DisneyV.png").getImage();
			this.setPreferredSize(new Dimension(450,450));
		}
		
		/**
		 * M�todo paint.
		 * @param g
		 */
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(this.fondoP, 0,0,this.getWidth(),this.getHeight(),this);
		}
		
	}
/**
 * M�todo para generar un numero auxiliar para crear el num de nuevos nodos. 
 * @param act - nodo actual.
 * @return int
 */
	private int generaNum(NodoArbol<String> act){
		int n1= act.getNum();
		int n2= act.getIzquierdo().getNum();
		return (n1-n2)/2;
	}
	
        /**
	 * Action Listener de los botones..
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
            this.nodoActual= this.juego.nodoActual;
            ArbolBinario arbol= this.juego.arbol;
		if(e.getSource()==botonSi){
			nodoActual= arbol.next(nodoActual, true);
			if (nodoActual.getPref().equals("pre")) {
				texto.setText(nodoActual.getDato());
			}else{
				texto.setText("Estabas pensando en "+nodoActual.getDato());
				for(int i=0; i<15; i++){
					System.out.println(this.juego.nombres[i]);
					if(this.juego.nombres[i].equals(nodoActual.getDato())){
						fondo.fondoP= this.juego.imagenes[i];
						fondo.repaint();
						i=15;
					}
				}
			}
		}
		else if(e.getSource()==botonReset){
			reiniciar();
		}
		else if(e.getSource()==botonNo){
			if (nodoActual != null) {
				String comp= nodoActual.getDato();
				nodoActual = arbol.next(nodoActual, false);
				if (!nodoActual.getDato().equals(comp)) {
					texto.setText(nodoActual.getDato());
				}else{
					this.botonReset.setVisible(false);
					texto.setText("No se que personaje sea. �Qu� personaje estabas buscando?");
					this.botonGuardar.setVisible(true);
					this.textfield.setVisible(true);
				}
				
			}else{
				System.exit(0);
			}
			
		}else if(e.getSource()==botonGuardar){
			nuevo= this.textfield.getText();
			this.textfield.setText(" ");
			if (!save) {
				if (arbol.contains(nuevo)) {
					texto.setText("Me parece que te has equivocado al responder una pregunta. Presiona el bot�n reiniciar si quieres intentarlo de nuevo");
					this.textfield.setVisible(false);
					this.botonGuardar.setVisible(false);
					this.botonReset.setVisible(true);
				} else {
					nuevoV=nuevo;
					this.texto.setText("�Con qu� pregunta podr�a haberlo encontrado?");
					save = true;
				}
			}else{
				int num= generaNum(nodoActual);
				this.nodoActual.setDerecho(new NodoArbol<String>(nuevo, "pre", nodoActual.getNum()+num));
				this.nodoActual.getDerecho().setIzquierdo(new NodoArbol<String>(nuevoV, "resp", nodoActual.getDerecho().getNum()-(num/2)));
				this.save=false;
				this.texto.setText("�Quieres jugar de nuevo? Piensa en otro villano y presiona el bot�n reiniciar.");
				this.textfield.setVisible(false);
				this.botonGuardar.setVisible(false);
				this.botonReset.setVisible(true);
				this.juego.Guardar();
			}
		}else if(e.getSource()==botonMusica){
			if(reproduccion){
				this.reproduccion=false;
				this.juego.reproductor.pause();
			}else{
				this.reproduccion=true;
                                this.juego.reproductor.play();
			}
		}
                this.juego.nodoActual= this.nodoActual;
	}

        public static void main(String[] args){
                Reproductor reproductor= new Reproductor();
                GameInterface juego= new GameInterface(reproductor);
	}
}