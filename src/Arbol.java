package Arbol;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
class Arbol extends JPanel {
    Nodo raiz;
    int m;
    boolean estadoBalance = true;
    public Arbol() {
        raiz = null;
        this.setPreferredSize(new Dimension(1000, 1000));
    }
    public String insertarNodo(int dato) {
        /*

             INICIA INSERCION NORMAL

        */
        String ret;
        Nodo aux1 = new Nodo(dato);
        Nodo aux2 = raiz;
        Nodo aux3 = null;
        boolean status = true;
        while (aux2 != null) {
            // A la izquierda de
            aux3 = aux2;
            if (aux1.id < aux2.id ) {
                aux2 = aux2.izquierda;
            }
            // A la derecha de
            else if (aux1.id > aux2.id ) {
                aux2 = aux2.derecha;
            }
            // Es igual, no podemos insertar igual
            else {
                status = false;
                break;
            }
        }
        if (status) {
            if (aux3 == null)
                raiz = aux1;
            else {
                if (aux1.id < aux3.id ) {
                    aux3.izquierda = aux1;
                } else
                    aux3.derecha = aux1;
            }
        }
        /*

             FIN INSERCION NORMAL

        */
        fb(raiz);
        ret = String.format("\n\nAntes de revision" + "\n");
        preorden (raiz);
        if (estadoBalance)
            ret += String.format(aux1 + "Insercion comun y corriente" + "\n");
        else {
            ret += String.format(aux1 + "A ocurrido un desbalanceo, obteniendo tipo de rotacion..." + "\n");
            ret += administraRotacion(aux1);
            fb(raiz) ;
            ret += String.format(aux1 + "Despues de revision" + preorden(raiz) + "\n");
        }
        return ret;
    }
    public String administraRotacion(Nodo aux1) {
        int nuevo = aux1.id;
        String ret = "";
        Nodo A = null;
        Nodo aux = raiz;
        Nodo B = raiz;
        Nodo restoArbol = raiz;
        while (aux != null) {
            if (!aux.balanceado())
                B = aux;
            if (nuevo < aux.id) {
                if (!aux.izquierda.balanceado())
                    restoArbol = aux;
                aux = aux.izquierda;
            } else if (nuevo > aux.id) {
                if (!aux.derecha.balanceado())
                    restoArbol = aux;
                aux = aux.derecha;
            } else {
                aux = null;
            }

        }
        
        if (nuevo > B.id) {
            A = B.derecha;
            if (nuevo > A.id) {
                rotacionSimpleIzquierda(A, B, restoArbol);
                ret+= String.format(aux1 + "provoco una rotacion simple izquierda, su b es" + B  + "\n");
            } else if (nuevo < A.id) {
                rotacionDobleIzquierda(A, B, restoArbol);
                ret+= String.format(aux1 + "provoco una rotacion doble izquierda" + "\n");
            }
        } else if (nuevo < B.id) {
            A = B.izquierda;
            if (nuevo < A.id) {
                rotacionSimpleDerecha(A, B, restoArbol);
                ret+= String.format(aux1 + "provoco una rotacion simple derecha" + "\n");
            } else if (nuevo > A.id) {
                rotacionDobleDerecha(A, B, restoArbol);
                ret+= String.format(aux1 + "provoco una rotacion doble derecha" + "\n");
            }
        }
        estadoBalance = true; // Validar balanceo
        return ret;
    }
    public void rotacionSimpleIzquierda(Nodo A, Nodo B, Nodo restoArbol) {
        Nodo SA1 = B.izquierda;
        Nodo SA2 = A.izquierda;
        Nodo SA3 = A.derecha;
        if (B == raiz)
            raiz = A;
        else if (B.id > restoArbol.id)
            restoArbol.derecha = A;
        else if (B.id < restoArbol.id)
            restoArbol.izquierda = A;
        A.izquierda = B;
        A.derecha = SA3;
        B.izquierda = SA1;
        B.derecha = SA2;
    }
    public void rotacionDobleIzquierda(Nodo A, Nodo B, Nodo restoArbol) {
        Nodo SA1 = B.izquierda;
        Nodo C   = A.izquierda;
        Nodo SA2 = C.izquierda;
        Nodo SA3 = C.derecha;
        Nodo SA4 = A.derecha;
        if (B == raiz)
            raiz = C;
        else if (B.id > restoArbol.id)
            restoArbol.derecha = C;
        else if (B.id < restoArbol.id)
            restoArbol.izquierda = C;
        C.izquierda = B;
        C.derecha   = A;
        B.izquierda = SA1;
        B.derecha   = SA2;
        A.izquierda = SA3;
        A.derecha   = SA4;
    }
    public void rotacionSimpleDerecha(Nodo A, Nodo B, Nodo restoArbol) {
        Nodo SA1 = A.izquierda;
        Nodo SA2 = A.derecha;
        Nodo SA3 = B.derecha;
        if (B == raiz)
            raiz = A;
        else if (B.id > restoArbol.id)
            restoArbol.derecha = A;
        else if (B.id < restoArbol.id)
            restoArbol.izquierda = A;
        A.izquierda = SA1;
        A.derecha = B;
        B.izquierda = SA2;
        B.derecha = SA3;
    }
    public void rotacionDobleDerecha(Nodo A, Nodo B, Nodo restoArbol) {
        Nodo SA1 = A.izquierda;
        Nodo C   = A.derecha;
        Nodo SA2 = C.izquierda;
        Nodo SA3 = C.derecha;
        Nodo SA4 = B.derecha;
        if (B == raiz)
            raiz = C;
        else if (B.id > restoArbol.id)
            restoArbol.derecha = C;
        else if (B.id < restoArbol.id)
            restoArbol.izquierda = C;
        C.izquierda = A;
        C.derecha   = B;
        A.izquierda = SA1;
        A.derecha   = SA2;
        B.izquierda = SA3;
        B.derecha   = SA4;

    }

    /*

            ACTUALIZAR FACTOR BALANCE

    */
    public int fb(Nodo nodo) {
        if (nodo == null)
            return 0;
        int a, b;
        a = fb(nodo.izquierda);
        b = fb(nodo.derecha);
        if (nodo.derecha != null)
            nodo.hDerecha = 1 + b;
        else
            nodo.hDerecha = 0;
        if (nodo.izquierda != null)
            nodo.hIzquierda = 1 + a;
        else
            nodo.hIzquierda = 0;
        nodo.fb = nodo.hDerecha -  nodo.hIzquierda;
        if (nodo.fb < -1 || nodo.fb > 1)
            estadoBalance = false;
        if ( nodo.hDerecha >= nodo.hIzquierda )
            return nodo.hDerecha;
        return  nodo.hIzquierda;
    }

    /*

            OBTENER PIVOTE Y SU PADRE

    */




    public void pivote(Nodo nodo, int nuevo, Nodo piv, Nodo ppiv) {
        if (!nodo.balanceado())
            piv = nodo;
        if (nuevo < nodo.id) {
            if (!nodo.izquierda.balanceado())
                ppiv = nodo;
            pivote(nodo.izquierda, nuevo, piv, ppiv);
        } else if (nuevo > nodo.id) {
            if (!nodo.derecha.balanceado())
                ppiv = nodo;
            pivote(nodo.derecha, nuevo, piv, ppiv);
        }
    }

    public int busqueda(int b) {
        boolean status = false;
        Nodo aux = raiz;
        while (aux != null && status == false) {
            if (aux.id > b)
                aux = aux.derecha;
            else if (aux.id < b)
                aux = aux.izquierda;
            else
                status = true;
        }
        if (status == false)
            return -1;
        return aux.id;
    }
    public boolean eliminar(int a) {
        Nodo aux1 = raiz;
        Nodo aux2 = raiz;
        Nodo temp;
        boolean status = false;
        if (busqueda(a) != -1) {
            while (a != aux1.id) {
                aux2 = aux1;
                if (aux1.id > a) {
                    aux1 = aux1.derecha;
                } else if (aux1.id < a) {
                    aux1 = aux1.izquierda;
                }
            }
            temp = aux1;
            //NODO RAIZ
            if (aux1 == raiz && esHoja(raiz)) {
                raiz = null;
            }
            //EL NODO A ELIMINAR ES UNA HOJA
            else if (esHoja(aux1)) {
                if (aux2.id < aux1.id)
                    aux2.izquierda = null;
                if (aux2.id > aux1.id)
                    aux2.derecha = null;
            }
            /*NODO CON HIJO DERECHO O IZQUIERDO*/
            else if (aux1.derecha != null && aux1.izquierda == null) {
                if (aux1 == raiz)
                    raiz = raiz.derecha;
                if (aux2.id < aux1.id) {

                    aux2.izquierda = aux1.derecha;
                }
                if (aux2.id > aux1.id) {
                    aux2.derecha = aux1.derecha;
                }
            } else if (aux1.izquierda != null && aux1.derecha == null) {
                if (aux1 == raiz)
                    raiz = raiz.izquierda;
                if (aux2.id < aux1.id)
                    aux2.izquierda = aux1.izquierda;
                if (aux2.id > aux1.id)
                    aux2.derecha = aux1.izquierda;
            }
            /*                                  */
            //NODO CON DOS HIJOS
            else if (aux1.izquierda != null && aux1.derecha != null) {
                temp = aux1.izquierda;
                Nodo fTemp = aux1;
                while (temp.derecha != null) {
                    fTemp = temp;
                    temp = temp.derecha;
                }
                aux1.id = temp.id;
                if (esHoja(temp)) {
                    if (fTemp.izquierda == temp)
                        fTemp.izquierda = null;
                    else
                        fTemp.derecha = null;
                } else {
                    fTemp.derecha = temp.izquierda;
                }
            }
            return true;
        }
        return false;
    }
    public String preorden(Nodo a) {
        String ret = "";
        if (a == raiz)
            ret = String.format("Estado del arbol" + "\n");
        if (a == null) return ret;
        ret+=String.format(a.id + "  su fb=" + a.fb + "\n");
        ret+=preorden(a.izquierda);
        ret+=preorden(a.derecha);
        return ret;
    }
    public int preordenConverso(Nodo a) {
        if (a == null) return 0;
        System.out.println(a.id);
        preorden(a.derecha);
        preorden(a.izquierda);
        return 0;
    }
    public int inorden(Nodo a) {
        if (a == null) return 0;
        inorden(a.izquierda);
        System.out.println(a.id);
        inorden(a.derecha);
        return 0;
    }
    public int inordenConverso(Nodo a) {
        if (a == null) return 0;
        inorden(a.derecha);
        System.out.println(a.id);
        inorden(a.izquierda);
        return 0;
    }
    public int postorden(Nodo a) {
        if (a == null) return 0;
        postorden(a.izquierda);
        postorden(a.derecha);
        System.out.println(a.id);
        return 0;
    }
    public int postordenConverso(Nodo a) {
        if (a == null) return 0;
        postorden(a.derecha);
        postorden(a.izquierda);
        System.out.println(a.id);
        return 0;
    }
    public boolean esHoja(Nodo aux1) {
        if (aux1.derecha == null && aux1.izquierda == null)
            return true;
        else
            return false;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int x, y;
        m = this.getWidth() / 4;
        x = m;
        y = 40;
        Nodo aux = raiz;
        pintarArbol(g, raiz, x, y, 0, 0);
    }
    public int pintarArbol(Graphics g, Nodo n, int x, int y, int pX, int pY) {
        if (n == null) return 0;
        int r;
        if (x > m) {
            pintarArbol(g, n.izquierda, x - ((Math.abs(pX - x)) / 2), y + 50, x, y);
            pintarArbol(g, n.derecha, x + ((Math.abs(pX - x)) / 2), y + 50, x, y);
            r = pX + x / 2;
        } else {
            pintarArbol(g, n.izquierda, x - (x / 2), y + 50, x, y);
            pintarArbol(g, n.derecha, x + (x / 2), y + 50, x, y);
        }
        g.fillOval(x - 5, y - 5, 15, 15);
        g.drawString(n.id + ", " + n.fb,  x + 5, y - 15);
        if (n != raiz)
            g.drawLine(x, y, pX, pY);
        return 0;
    }
}
