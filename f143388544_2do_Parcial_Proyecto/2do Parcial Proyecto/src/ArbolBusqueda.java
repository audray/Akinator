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
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Clase para el adivinador.
 * @author Ariadna N. Hernández Rivero -A01228363
 * @author AriAlfredo Zárate Buenrostro - A01222814
 *
 */
public class ArbolBusqueda extends JFrame implements ActionListener{
	private NodoB<String> raiz, nodoAct;
	private JLabel texto, nuevoPj, nuevaPreg;
	private JTextField texto2;
	private JButton botonS,
	botonN,reset,guardar, song;
	private ImageIcon resetI;
	private String nuevo, nuevoV;
	private boolean save, rep;
	private PanelImg fondo;
	private Clip sonido;
	private Image[] imagenes;
	private Image ReinaM, villanos, cruela, facilier, garfio, gaston,gothel, hades, hans, jafar, lady, malefica, scar, shereK, ursula, yzma;
	private String[] nombres;

	public ArbolBusqueda(){
		super();
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(600,450));
		JPanel panelBoton=new JPanel();
		
		this.song= new JButton("Música");
		panelBoton.add(song);
		song.addActionListener(this);

		this.botonS= new JButton("Si");
		panelBoton.add(botonS);
		botonS.addActionListener(this);

		this.botonN=new JButton("No");
		botonN.addActionListener(this);
		panelBoton.add(botonN);

		this.reset= new JButton("Reiniciar");
		panelBoton.add(reset);
		reset.addActionListener(this);

		this.add(panelBoton, BorderLayout.SOUTH);

		JPanel panelTexto= new JPanel();
		panelTexto.setPreferredSize(new Dimension(450,100));

		this.texto= new JLabel("¿Quieres jugar? Piensa en un villano de Disney y yo lo adivino.");
		panelTexto.add(texto);
		this.nuevoPj= new JLabel("¿QuÈ personaje estabas buscando?");
		this.nuevoPj.setVisible(false);
		panelTexto.add(nuevoPj);
		this.texto2= new JTextField(20);
		this.texto2.setVisible(false);

		this.guardar=new JButton("Guardar");
		panelTexto.add(this.guardar);
		this.guardar.addActionListener(this);
		this.guardar.setVisible(false);
		panelBoton.setBackground(Color.BLACK);
		panelTexto.add(texto2);
		panelTexto.setBackground(Color.RED);
		this.add(panelTexto, BorderLayout.NORTH);
		
		this.fondo= new PanelImg();
		this.add(fondo, BorderLayout.CENTER);
	
		this.pack();
		this.setVisible(true);
		this.nodoAct= null;
		//this.setLayout(null);
		this.rep=true;
		try {
            
            // Se obtiene un Clip de sonido
            sonido = AudioSystem.getClip();
            
            // Se carga con un fichero wav
            sonido.open(AudioSystem.getAudioInputStream(new File("OnceUponaDream.wav")));
            
				
					// Comienza la reproducción
					sonido.start();
					sonido.loop(10);
			
						//Espera mientras se esté reproduciendo.
					/*while (sonido.isRunning()){
							Thread.sleep(5000);
							//sonido.loop(1);
						// Se cierra el clip.
						sonido.close();*/
					
						
						
        } catch (Exception f) {
            System.out.println("" + f);
        }
		ReinaM= new ImageIcon("Reina malvada.png").getImage();
		villanos= new ImageIcon("DisneyV.png").getImage();
		facilier= new ImageIcon("Dr-Facilier.png").getImage();
		garfio= new ImageIcon("Garfio.png").getImage();
		gaston= new ImageIcon("gaston.png").getImage();
		gothel= new ImageIcon("gothel.png").getImage();
		hades= new ImageIcon("hades.png").getImage();
		hans= new ImageIcon("Hans.png").getImage();
		jafar= new ImageIcon("Jafar.png").getImage();
		lady= new ImageIcon("lady.png").getImage();
		malefica= new ImageIcon("malefica.png").getImage();
		scar= new ImageIcon("scar.png").getImage();
		shereK= new ImageIcon("shere khan.png").getImage();
		ursula= new ImageIcon("ursula.png").getImage();
		cruela= new ImageIcon("cruella.png").getImage();
		yzma= new ImageIcon("yzma.png").getImage();
		imagenes= new Image[15];
		imagenes[0]= ReinaM;
		imagenes[1]= cruela;
		imagenes[2]= facilier;
		imagenes[3]= garfio;
		imagenes[4]= gaston;
		imagenes[5]= gothel;
		imagenes[6]= hades;
		imagenes[7]= hans;
		imagenes[8]= jafar;
		imagenes[9]= lady;
		imagenes[10]= malefica;
		imagenes[11]= scar;
		imagenes[12]= shereK;
		imagenes[13]= ursula;
		imagenes[14]= yzma;
		nombres= new String[15];
		nombres[0]= "La reina malvada";
		nombres[1]= "Cruella de Vil";
		nombres[2]= "Doctor Facilier";
		nombres[3]= "Garfio";
		nombres[4]= "Gastón";
		nombres[5]= "Madre Gothel";
		nombres[6]= "Hades";
		nombres[7]= "Hans Westergard";
		nombres[8]= "Jafar";
		nombres[9]= "Lady Tremaine";
		nombres[10]= "Maléfica";
		nombres[11]= "Scar";
		nombres[12]= "Shere Khan";
		nombres[13]= "Úrsula";
		nombres[14]= "Yzma";
		

	}
	
	
	
	/**
	 * reiniciar - Metodo que reinicia en valores predeterminados para comenzar de nuevo
	 */
	public void reiniciar(){
		this.nodoAct= null;
		this.fondo.fondoP= villanos;
		this.fondo.repaint();
		this.nodoAct= next(nodoAct, true);
		this.texto.setText(nodoAct.getDato());
	}

	/**
	 * isEmpty - Comprueba si el arbol esta vacio
	 * @return True si el arbol esta vacio, false en caso contrario
	 */
	public boolean isEmpty(){
		return this.raiz==null;
	}

	/**
	 * AbreArchivo - metodo que lee desde un archivo de texto y agrega al arbol el dato
	 */
	public void AbreArchivo(){
		try {
			BufferedReader br= new BufferedReader(new FileReader("DV.txt"));
			String linea;
			String p;
			int n;
			while((linea=br.readLine())!=null){
				if (!linea.equals("")) {
					n = Integer.parseInt(linea);
					p = br.readLine();
					linea = br.readLine();
					//System.out.println(linea);
					add(linea, p, n);
				}
			}
			br.close();

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Guardar - metodo que escribe en un archivo de texto 
	 */
	public void Guardar(){
		try {
			PrintWriter pw= new PrintWriter(new FileWriter("DV.txt"));
			if(!isEmpty()){
				pw.println(this.raiz.getNum());
				pw.println(this.raiz.getPref());
				pw.println(this.raiz.getDato());
				if(altura()>0){
					Queue<NodoB> aux= new Queue<NodoB>();
					aux.enqueue(this.raiz);
					while(!aux.isEmpty()){
						if(aux.getFirst().getIzquierdo()!= null){
							pw.println(aux.getFirst().getIzquierdo().getNum());
							pw.println(aux.getFirst().getIzquierdo().getPref());
							pw.println(aux.getFirst().getIzquierdo().getDato());
							aux.enqueue(aux.getFirst().getIzquierdo());
						}
						if(aux.getFirst().getDerecho()!= null){
							pw.println(aux.getFirst().getDerecho().getNum());
							pw.println(aux.getFirst().getDerecho().getPref());
							pw.println(aux.getFirst().getDerecho().getDato());
							aux.enqueue(aux.getFirst().getDerecho());
						}
						aux.dequeue();
					}
				}
				pw.close();
			}

		}catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * add - metodo que agrega datos con su respectivo numero asignado
	 * @param dato - dato a agregar
	 * @param pref - checar si es pregunta
	 * @param num - asigna el numero destinado para cada dato
	 */
	public void add(String dato, String pref, int num){
		if(isEmpty()){
			this.raiz= new NodoB<String>(dato, pref, num);
		}else{
			add(this.raiz, dato, pref, num);
		}
	}

	/**
	 * add - metodo que agrega nodos con su respectivo dato y numero asignado
	 * @param act - nodo a agregar
	 * @param dato - dato a agregar
	 * @param pref - checar si es pregunta
	 * @param num - asigna el numero destinado para cada dato
	 */
	private void add(NodoB<String> act, String dato, String pref, int num){
		if(act.getNum()>num){
			if(act.getIzquierdo()==null){
				act.setIzquierdo(new NodoB<String>(dato, pref, num));
			}else{
				add(act.getIzquierdo(), dato, pref, num);
			}
		}else if(act.getNum()<num){
			if(act.getDerecho()==null){
				act.setDerecho(new NodoB<String>(dato, pref, num));

			}else{
				add(act.getDerecho(), dato, pref, num);
			}
		}

	}

	/**
	 * next - metodo que devuelve el siguiente nodo
	 * @param act - nodo a checar
	 * @param flag - para saber a que lado se dirige del arbol, dependiendo de si es si==true, o no==false
	 * @return act - siguiente nodo
	 */
	public NodoB<String> next(NodoB<String> act, boolean flag){
		if(act==null){
			AbreArchivo();
			return act=this.raiz;
		}else if(flag==true){
			if (act.getIzquierdo()!= null) {
				return act.getIzquierdo();
			}else{
				return act;
			}
		}else{
			if (act.getDerecho()!=null) {
				return act.getDerecho();
			}else{
				return act;
			}
		}
	}
/**
 * Método para verificar si un elemento ya existe dentro del árbol.
 * Hace el recorrido por niveles y usando un Queue como auxiliar.
 * @param dato - el elemento a buscar
 * @return boolean
 */

	public boolean contains(String dato){
		if(altura()>0){
			Queue<NodoB> aux= new Queue<NodoB>();
			aux.enqueue(this.raiz);
			while(!aux.isEmpty()){
				if(aux.getFirst().getIzquierdo()!= null){
					aux.enqueue(aux.getFirst().getIzquierdo());
				}else if(aux.getFirst().getIzquierdo()== null){
					if(((java.lang.String) aux.getFirst().getDato()).equalsIgnoreCase(dato)){
						return true;
					}	
				}
				if(aux.getFirst().getDerecho()!= null){
					aux.enqueue(aux.getFirst().getDerecho());
				}
				aux.dequeue();
			}
			return false;
		}
		return false;
	}

	/**
	 * Método para obtener la altura de un nodo.
	 * @param nodo
	 * @return
	 */
	private int alturaNodo(NodoB<String> nodo) {
		if(nodo.getDerecho()==null && nodo.getIzquierdo()==null){
			return 0;
		}else{
			int izq=0;
			int der=0;
			if(nodo.getIzquierdo()!= null){
				izq= 1+ (alturaNodo(nodo.getIzquierdo()));
			}
			if(nodo.getDerecho() != null){
				der= 1+ (alturaNodo(nodo.getDerecho()));
			}
			if(izq>der){
				return izq;
			}else{
				return der;
			}
			// Otro estilo de if(ternario) serÌa "return izq>der? izq:der;"
		}

	}

	/**
	 * MÈtodo para llamar al mÈtodo recursivoalturaNodo y determinar la altura.
	 * Oculta variables y verifica si el ·rbol es vacÌo o no.
	 * @return integer altura.
	 */

	public int altura(){
		if(!this.isEmpty()){
			return alturaNodo(this.raiz);
		}
		return -1;
	}


/**
 * Inner class NodoB.
 *
 * @param <String>
 */
	private class NodoB<String>{
		private String dato, pref;
		private NodoB<String> izq, der;
		private int num;

		/**
		 * Constructor de clase.
		 * @param d
		 * @param p
		 * @param n
		 */
		public NodoB(String d, String p, int n){
			this(d, p, null, null,n);
		}
		
		/**
		 * Constructor de clase con parámetros completos.
		 * @param d
		 * @param p
		 * @param izq
		 * @param der
		 * @param n
		 */

		public NodoB(String d, String p, NodoB<String> izq, NodoB<String> der, int n){
			this.pref= p;
			this.dato= d;
			this.num=n;
			this.izq= izq;
			this.der= der;
		}

		/**
		 * Getter de Dato.
		 * @return dato.
		 */
		public String getDato(){
			return dato;
		}
		
		/**
		 * Getter de pref.
		 * @return pref.
		 */

		public String getPref(){
			return pref;
		}
		
		/**
		 * Getter de num.
		 * @return num
		 */

		public int getNum(){
			return num;
		}
		/**
		 *Getter para obtener el nodo de la Izquierda. 
		 * @return nodo izquierdo.
		 */

		public NodoB<String> getIzquierdo() {
			return this.izq;
		}

		/**
		 * Setter del nodo de la izquierda.
		 * @param izq 
		 */
		public void setIzquierdo(NodoB<String> izq) {
			this.izq = izq;
		}

		/**
		 * Getter del nodo derecho.
		 * @return nodo derecho.
		 */

		public NodoB<String> getDerecho() {
			return this.der;
		}

		/**
		 * Setter del nodo de la derecha.
		 * @param der 
		 */

		public void setDerecho(NodoB<String> der) {
			this.der = der;
		}
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
		 * Método paint.
		 * @param g
		 */
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(this.fondoP, 0,0,this.getWidth(),this.getHeight(),this);
		}
		
	}
/**
 * Método para generar un numero auxiliar para crear el num de nuevos nodos. 
 * Se hace de manera semi-aleatoria.
 * @param act - nodo actual.
 * @return int
 */
	private int generaNum(NodoB<String> act){
		int n1= act.getNum();
		int n2= act.getIzquierdo().getNum();
		return (n1-n2)/2;
	}
	public static void main(String[] args){
		ArbolBusqueda arbol= new ArbolBusqueda();
		//System.out.println(arbol.porNiveles());
		//arbol.Guardar();
		//System.out.println(Random());
	}

	
	/**
	 * Action Listener de los botones..
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==botonS){
			nodoAct= next(nodoAct, true);
			if (nodoAct.getPref().equals("pre")) {
				texto.setText(nodoAct.getDato());
			}else{
				texto.setText("Estabas pensando en "+nodoAct.getDato());
				for(int i=0; i<15; i++){
					System.out.println(nombres[i]);
					if(nombres[i].equals(nodoAct.getDato())){
						fondo.fondoP= imagenes[i];
						fondo.repaint();
						i=15;
					}
				}
			}
		}
		else if(e.getSource()==reset){
			reiniciar();
		}
		else if(e.getSource()==botonN){
			if (nodoAct != null) {
				String comp= nodoAct.getDato();
				nodoAct = next(nodoAct, false);
				if (!nodoAct.getDato().equals(comp)) {
					texto.setText(nodoAct.getDato());
				}else{
					this.reset.setVisible(false);
					texto.setText("No se que personaje sea. ¿Qué personaje estabas buscando?");
					this.guardar.setVisible(true);
					this.texto2.setVisible(true);
				}
				
			}else{
				System.exit(0);
			}
			
		}else if(e.getSource()==guardar){
			nuevo= this.texto2.getText();
			this.texto2.setText(" ");
			if (!save) {
				if (contains(nuevo)) {
					texto.setText("Me parece que te has equivocado al responder una pregunta. Presiona el botón reiniciar si quieres intentarlo de nuevo");
					this.texto2.setVisible(false);
					this.guardar.setVisible(false);
					this.reset.setVisible(true);
				} else {
					nuevoV=nuevo;
					this.texto.setText("¿Con qué pregunta podría haberlo encontrado?");
					save = true;
				}
			}else{
				int num= generaNum(nodoAct);
				this.nodoAct.setDerecho(new NodoB<String>(nuevo, "pre", nodoAct.getNum()+num));
				this.nodoAct.getDerecho().setIzquierdo(new NodoB<String>(nuevoV, "resp", nodoAct.getDerecho().getNum()-(num/2)));
				this.save=false;
				this.texto.setText("¿Quieres jugar de nuevo? Piensa en otro villano y presiona el botón reiniciar.");
				this.texto2.setVisible(false);
				this.guardar.setVisible(false);
				this.reset.setVisible(true);
				Guardar();
			}
		}else if(e.getSource()==song){
			if(rep){
				this.rep=false;
				sonido.close();
			}else{
				this.rep=true;
				try {
		            
		            // Se obtiene un Clip de sonido
		            sonido = AudioSystem.getClip();
		            
		            // Se carga con un fichero wav
		            sonido.open(AudioSystem.getAudioInputStream(new File("OnceUponaDream.wav")));
		            
						
							// Comienza la reproducción
							sonido.start();
							sonido.loop(10);
					
								//Espera mientras se esté reproduciendo.
							/*while (sonido.isRunning()){
									Thread.sleep(5000);
									//sonido.loop(1);
								// Se cierra el clip.
								sonido.close();*/
							
								
								
		        } catch (Exception f) {
		            System.out.println("" + f);
		            
		        }
			}
		}
	}

}