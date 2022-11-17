package Arbol;
class Nodo {
    int hDerecha;
    int hIzquierda;
    int fb;
    Nodo derecha;
    Nodo izquierda;
    int id;
    public Nodo(){
        derecha = null;
        izquierda = null; 
        hDerecha = 0;
        hIzquierda = 0;
        fb = 0;
    }
    public Nodo(int id){
        derecha = null;
        izquierda = null;
        this.id = id;
        hDerecha = 0;
        hIzquierda = 0;
        fb = 0;
    }
    public boolean balanceado(){
        if(fb < -1 || fb > 1)
            return false;
        return true;
    }
    public String toString(){
        return "["+id+"]";
    }
}