package Arbol;
import static java.awt.Frame.ICONIFIED;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.Color;
import java.awt.Point;
import javax.swing.*;
public class Frame extends JFrame {
    private int xa,ya;
    private Arbol g1;
    JScrollPane scrollConsol;
    JTextArea consolMessaje;
    String messaje;
    

    JScrollPane scrollPanel;
    JButton  minimizar, salir;
    JButton eliminar, eliminarNodo;
    JButton insertar, insertarNodo;
    JButton reiniciarConsola, mostrar;
    JLabel pestania;
    JTextField nodo, extraer;
    JTextField EverticeA, EverticeB;
    JPanel eliminarPt, insertarPt, espacioBotones;
    public Frame() {

    }
    public void createAndShowGUI() {
        g1 = new Arbol();
        float ancho;
        float alto;
        interfazGrafica();
        ancho = getSize().width;
        alto = getSize().height;
        scrollPanel = new JScrollPane();
        scrollPanel.setBounds((int)((alto * .03f) + 270), (int)(ancho * .09f) , (int)ancho - (int)((alto * .06f) + 260) , (int)alto - (int)(alto * .06f * 2) - (int)(alto * .07f) - (int)(ancho * .09f) - 120);
        scrollPanel.setViewportView(g1);
        add(scrollPanel);
        eliminar = new JButton("Eliminar (No disponible)");
        eliminar.setBounds((int)(alto * .03f), (int)(ancho * .09f), (int)(alto * .4f), 60);
        eliminar.setBackground(new Color(0, 0, 51));
        eliminar.setForeground(Color.white);

        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        eliminar.setFocusable(false);
        getContentPane().add(eliminar);
        espacioBotones = new JPanel();
        espacioBotones.setBounds((int)((alto * .03f) + 270),  (int)alto - 230 , (int)ancho - (int)((alto * .06f) + 260) , 200);
        g1.setBackground(new Color(255, 255, 255));
        espacioBotones.setBackground(new Color(255, 255, 255));
        g1.setVisible(true);
        espacioBotones.setVisible(true);
        this.add(espacioBotones);
        //Seccion d la consola.
        consolMessaje = new JTextArea(12, 65);
        scrollConsol = new JScrollPane(consolMessaje);
        consolMessaje.setLineWrap(true);
        consolMessaje.setWrapStyleWord(true);
        messaje = "";
        consolMessaje.setText(messaje);

        espacioBotones.add(scrollConsol);
        
        insertar = new JButton("insertar");
        insertar.setBounds((int)(alto * .03f), (int)(ancho * .09f) + 70, (int)(alto * .4f), 60);
        insertar.setBackground(new Color(0, 0, 51));
        insertar.setForeground(Color.white);
        insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarActionPerformed(evt);
            }
        });
        insertar.setFocusable(false);
        getContentPane().add(insertar);
        insertarPt = new JPanel();
        insertarPt.setBounds((int)(alto * .03f), 325, (int)(alto * .4f), ((int)alto - (int)(alto * .07f)) / 2 - 15);
        insertarPt.setBackground(new Color(255, 255, 255));
        insertarPt.setVisible(false);
        insertarNodo = new JButton("Insertar Nodo");
        nodo = new JTextField(10);
        insertarPt.add(nodo);
        insertarPt.add(insertarNodo);
        insertarNodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messaje+= g1.insertarNodo(Integer.parseInt(nodo.getText()));
                consolMessaje.setText(messaje);
                actualizar(10);
            }
        });
        getContentPane().add(insertarPt);
        eliminarPt = new JPanel();
        eliminarPt.setBounds((int)(alto * .04f), (int)(ancho * .2f) + (70 * 2), (int)(alto * .4f), ((int)alto - (int)(alto * .07f)) / 2 - 15);
        eliminarPt.setBackground(new Color(255, 255, 255));
        eliminarPt.setVisible(false);
        eliminarNodo = new JButton("Eliminar Nodo");
        extraer = new JTextField(10);
        eliminarPt.add(extraer);
        eliminarPt.add(eliminarNodo);
        eliminarNodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g1.eliminar(Integer.parseInt(extraer.getText()));
                actualizar(10);
            }
        });
        getContentPane().add(eliminarPt);
        reiniciarConsola = new JButton("Reiniciar Consola");
        reiniciarConsola.setBounds((int)(alto * .03f), (int)(ancho * .09f) + (70 * 2), (int)(alto * .4f), 60);
        reiniciarConsola.setBackground(new Color(0, 0, 51));
        reiniciarConsola.setForeground(Color.white);

        reiniciarConsola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciarConsolaActionPerformed(evt);
            }
        });
        reiniciarConsola.setFocusable(false);
        getContentPane().add(reiniciarConsola);
        mostrar = new JButton("Recorrido ");
        mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g1.preorden(g1.raiz);
                actualizar(10);
            }
        });
    }

    private void interfazGrafica() {
        float aux2, aux1, aux;
        float ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        float alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        int r, g, b;
        r = 0;
        g = 153;
        b = 128;
        setLayout(null);
        setPreferredSize(new Dimension((int)(ancho - (ancho * .10f * 2)), (int)(alto - (alto * .10f * 2))));
        setBounds((int)(ancho * .10f), (int)(alto * .10f), (int)((ancho - (ancho * .10f * 2))), (int)(alto - (alto * .10f * 2)));
        setResizable(false);
        getContentPane().setBackground(Color.gray);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setBackground(new Color(0, 8, 27, 100));
        Shape forma = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 30, 30);
        aux = ancho * .30f;
        pestania = new JLabel();
        pestania.setBounds(0, 0, (int)ancho - (int)aux, (int)(alto * .07f));
        add(pestania);
        pestania.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pestaniaMousePressed(evt);
            }
        });
        pestania.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pestaniaMouseDragged(evt);
            }
        });
        aux2 = (float)aux / 3;
        aux1 = (float)aux / 3 * 2;
        salir = new JButton("x");
        salir.setBackground(new Color(r, g, b, 200));
        salir.setBounds(((int)(ancho - (int)aux)) + (int)aux2 / 2, 0, (int)aux2 / 2, (int)(alto * .07f));
        add(salir);
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        minimizar = new JButton("_");
        minimizar.setBackground(new Color(r, g, b, 180));
        minimizar.setBounds((int)ancho - (int)aux, 0, (int) aux2 / 2, (int)(alto * .07f));
        add(minimizar);
        minimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizarActionPerformed(evt);
            }
        });
        pestania.setBackground(new Color(r, g, b, 180));
        pestania.setOpaque(true);
        minimizar.setOpaque(true);
    }
    private void pestaniaMousePressed(java.awt.event.MouseEvent evt) {
        xa = evt.getX   ()  ;
        ya = evt.getY   ()  ;
    }
    private void pestaniaMouseDragged(java.awt.event.MouseEvent evt) {
        Point point = MouseInfo.getPointerInfo().getLocation() ;
        setLocation(point.x - xa, point.y - ya)  ;
    }
    private void salirActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
    private void minimizarActionPerformed(java.awt.event.ActionEvent evt) {
        setExtendedState(ICONIFIED);
    }
    public void actualizar(int a) {
        this.g1.repaint();
    }
    private void reiniciarConsolaActionPerformed(java.awt.event.ActionEvent evt) {
        
        
    }
    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {
        this.eliminarPt.setVisible(true);
        this.insertarPt.setVisible(false);
        
    }
    private void insertarActionPerformed(java.awt.event.ActionEvent evt) {
        this.eliminarPt.setVisible(false);
        this.insertarPt.setVisible(true);
        
    }
    public static void main(String[] args) {
        final Frame app = new Frame();
        SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                app.createAndShowGUI();
            }
        });
    }
}
