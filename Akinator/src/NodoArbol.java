/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Inner class NodoB.
 *
 * @param <String>
 */
public class NodoArbol<String> {
    private String dato;
    private String pref;
    private NodoArbol<String> izq, der;
    private int num;

    /**
     * Constructor de clase.
     * @param d
     * @param p
     * @param n
     */
    public NodoArbol(String d, String p, int n) {
        this(d, p, null, null, n);
    }

    /**
     * Constructor de clase con par�metros completos.
     * @param d
     * @param p
     * @param izq
     * @param der
     * @param n
     */
    public NodoArbol(String d, String p, NodoArbol<String> izq, NodoArbol<String> der, int n) {
        this.pref = p;
        this.dato = d;
        this.num = n;
        this.izq = izq;
        this.der = der;
    }

    /**
     * Getter de Dato.
     * @return dato.
     */
    public String getDato() {
        return dato;
    }

    /**
     * Getter de pref.
     * @return pref.
     */
    public String getPref() {
        return pref;
    }

    /**
     * Getter de num.
     * @return num
     */
    public int getNum() {
        return num;
    }
    /**
     *Getter para obtener el nodo de la Izquierda.
     * @return nodo izquierdo.
     */

    public NodoArbol<String> getIzquierdo() {
        return this.izq;
    }

    /**
     * Setter del nodo de la izquierda.
     * @param izq
     */
    public void setIzquierdo(NodoArbol<String> izq) {
        this.izq = izq;
    }

    /**
     * Getter del nodo derecho.
     * @return nodo derecho.
     */
    public NodoArbol<String> getDerecho() {
        return this.der;
    }

    /**
     * Setter del nodo de la derecha.
     * @param der
     */
    public void setDerecho(NodoArbol<String> der) {
        this.der = der;
    }
    
}
