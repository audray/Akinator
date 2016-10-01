
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.ImageIcon;

/**
 *
 * @author Ari
 */
public class Akinator {
    protected ArbolBinario arbol;
    protected NodoArbol<String> nodoActual;
    protected Reproductor reproductor;
    protected Image[] imagenes;
    protected Image ReinaM, villanos, cruela, facilier, garfio, gaston,gothel, hades, hans, jafar, lady, malefica, scar, shereK, ursula, yzma;
    protected String[] nombres;
    
    public Akinator(Reproductor reproductor){
        this.reproductor= reproductor;
        this.arbol= new ArbolBinario();
        this.nodoActual= null;
    }
    
    public void imageLoader(){
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
		nombres[4]= "Gast�n";
		nombres[5]= "Madre Gothel";
		nombres[6]= "Hades";
		nombres[7]= "Hans Westergard";
		nombres[8]= "Jafar";
		nombres[9]= "Lady Tremaine";
		nombres[10]= "Mal�fica";
		nombres[11]= "Scar";
		nombres[12]= "Shere Khan";
		nombres[13]= "�rsula";
		nombres[14]= "Yzma";
            
        }
        
        public void loadGame(){
            this.reproductor.play();
            cargaArchivoJuego();
            imageLoader();
        }

	/**
	 * AbreArchivo - metodo que lee desde un archivo de texto y agrega al arbol el dato
	 */
	public void cargaArchivoJuego(){
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
					this.arbol.add(linea, p, n);
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
			if(!this.arbol.isEmpty()){
				pw.println(this.arbol.raiz.getNum());
				pw.println(this.arbol.raiz.getPref());
				pw.println(this.arbol.raiz.getDato());
				if(arbol.altura()>0){
					Queue<NodoArbol> aux= new Queue<NodoArbol>();
					aux.enqueue(this.arbol.raiz);
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
}
