
package Arbol;
import java.util.Scanner;
public class Tester {
    public static void main(String []args){
        Arbol a = new Arbol();
        Scanner i = new Scanner(System.in);
        int opc = -1, n = 0;
        do{
            menu();
            opc = i.nextInt();
            switch(opc){
                case 1:
                    System.out.println("\tNumero::");
                    n = i.nextInt();
                    a.insertarNodo(n);
                    break;
                case 2:
                    System.out.println("\tNumero::");
                    n = i.nextInt();
                    n = a.busqueda(n);
                    if(n==-1)
                        System.out.println("No existe en el arbol");
                    else
                        System.out.println(n);
                    break;
                case 3:
                    System.out.println("\tNumero::");
                    n = i.nextInt();
                    a.eliminar(n);
                    break;
                case 4:
                    System.out.println("\tArbol::");
                    a.preorden(a.raiz);
                    break;
                case 5:
                    System.out.println("\tArbol::");
                    a.inorden(a.raiz);
                    break;
                case 6:
                    System.out.println("\tArbol::");
                    a.postorden(a.raiz);
                    break;
                default:
                    System.out.println("\tOpcion erronea");
                    break;
            }
        }while(opc!=-1);
    }
    public static void menu(){
        System.out.println("-----ABB-------");
        System.out.println("1)Insertar un nodo");
        System.out.println("2)Buscar un nodo");
        System.out.println("3)Elimiar un nodo");
        System.out.println("4)Preorden");
        System.out.println("5)Inorden");
        System.out.println("6)Postorden");
        System.out.println("-1)Salir");
    }
}
