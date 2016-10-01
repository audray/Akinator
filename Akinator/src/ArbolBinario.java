/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ari
 */
public class ArbolBinario {
    protected NodoArbol<String> raiz;
    
    public ArbolBinario(){
        this.raiz=null;
    }
    
    /**
	 * isEmpty - Comprueba si el arbol esta vacio
	 * @return True si el arbol esta vacio, false en caso contrario
	 */
	public boolean isEmpty(){
		return this.raiz==null;
	}
        
    /**
	 * add - metodo que agrega datos con su respectivo numero asignado
	 * @param dato - dato a agregar
	 * @param pref - checar si es pregunta
	 * @param num - asigna el numero destinado para cada dato
	 */
	public void add(String dato, String pref, int num){
		if(isEmpty()){
			this.raiz= new NodoArbol<String>(dato, pref, num);
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
	private void add(NodoArbol<String> act, String dato, String pref, int num){
		if(act.getNum()>num){
			if(act.getIzquierdo()==null){
				act.setIzquierdo(new NodoArbol<String>(dato, pref, num));
			}else{
				add(act.getIzquierdo(), dato, pref, num);
			}
		}else if(act.getNum()<num){
			if(act.getDerecho()==null){
				act.setDerecho(new NodoArbol<String>(dato, pref, num));

			}else{
				add(act.getDerecho(), dato, pref, num);
			}
		}

	}

	/**
	 * next - metodo que devuelve el siguiente nodo
	 * @param actual - nodo a checar
	 * @param flag - para saber a que lado se dirige del arbol, dependiendo de si es si==true, o no==false
	 * @return act - siguiente nodo
	 */
	public NodoArbol<String> next(NodoArbol<String> actual, boolean flag){
		if(actual==null){
			return actual=this.raiz;
		}else if(flag==true){
			if (actual.getIzquierdo()!= null) {
				return actual.getIzquierdo();
			}else{
				return actual;
			}
		}else{
			if (actual.getDerecho()!=null) {
				return actual.getDerecho();
			}else{
				return actual;
			}
		}
	}
        
        /**
	 * M�todo para obtener la altura de un nodo.
	 * @param nodo
	 * @return
	 */
	private int alturaNodo(NodoArbol<String> nodo) {
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
			// Otro estilo de if(ternario) ser�a "return izq>der? izq:der;"
		}

	}

	/**
	 * M�todo para llamar al m�todo recursivoalturaNodo y determinar la altura.
	 * Oculta variables y verifica si el �rbol es vac�o o no.
	 * @return integer altura.
	 */
        
        public int altura(){
		if(!this.isEmpty()){
			return alturaNodo(this.raiz);
		}
		return -1;
	}
        
        /**
 * M�todo para verificar si un elemento ya existe dentro del �rbol.
 * Hace el recorrido por niveles y usando un Queue como auxiliar.
 * @param dato - el elemento a buscar
 * @return boolean
 */

	public boolean contains(String dato){
		if(altura()>0){
			Queue<NodoArbol> aux= new Queue<NodoArbol>();
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
}
