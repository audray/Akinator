import java.util.NoSuchElementException;
/**
 * Clase Queue.
 * @author Ariadna N. Hernández Rivero - A01228363
 * @Fabiola Uribe
 *
 * @param <E>
 */
public class Queue <E>{
	
		private NodoDG<E> first;
		private NodoDG<E> last;
		private int size;

	/**
	 * Constructor que inicializa la lista a vacia
	 * 
	 */
		public Queue(){
			this.first=this.last=null;
			this.size=0;
		}
		/**
		 * size Nos da información sobre el número de elementos de la lista	
		 * @return size - numero de elementos de la lista
		 */
		public int size(){
			return this.size;
		}
		/**
		 * isEmpty - Comprueba si es lista vacia
		 * @return True si lista vacia, false en caso contrario
		 */
		public boolean isEmpty(){
			return this.size==0;
		}
		/**
		 * enqueue - Agrega un elemento al final de la fila
		 * @param e Elemento a insertar
		 */
		public void enqueue(E e){
			NodoDG<E> nuevo=new NodoDG<>(e, null);
			if(this.last==null){
				this.last=nuevo;
				this.first=this.last;
			}
			else{
				this.last.next=nuevo;
				this.last=nuevo;
			}
			this.size++;
		}
		
		/**
		 * Quita el elemento primer elemento de la fila.
		 * @return dato que se quitó.
		 */
		
		public E dequeue(){
			if(isEmpty()){
				throw new NoSuchElementException();
			}
			NodoDG<E> nuevo= this.first;
			this.first=this.first.next;
			if(this.first==null){
				this.last=null;
			}
			this.size--;
			return nuevo.data;
		}
		
		/**
		 * Regresa el primer elemento de la fila.
		 * @return 
		 */
		public E getFirst(){
			if(isEmpty()){
				throw new NoSuchElementException();
			}else{
				return first.data;
			}
		}
		
		/**
		 * Método queinvierto los elementos del Queue.
		 */
		
		public void reverseQueue(){
			E[] aux= (E[]) new Object[size];
			for(int i=0; i<size; i++){
				aux[i]= (E) this.getFirst();
				this.dequeue();
			}
			for(int j=size; j>-1; j--){
				this.enqueue(aux[j]);
			}
		}
		
		/**
		 * Método toString
		 */

		public String toString(){
			StringBuilder s=new StringBuilder("[");
			NodoDG<E> aux=this.first;
			for (int i = 0; i < this.size; i++){
				s.append(aux.data+ " ");
				aux= aux.next;
			}
	        s.append("]");
			return s.toString();
		}
		/**
		 * Inner class Nodo
		 * @author Ari
		 *
		 * @param <E>
		 */
		private static class NodoDG<E>{
			E data;
			NodoDG<E> next;
			
			/**
			 * Constructor de clase.
			 * @param data
			 * @param next
			 */
			
			public NodoDG(E data, NodoDG<E> next){
				this.data=data;
				this.next=next;

			}
		}
		
}
