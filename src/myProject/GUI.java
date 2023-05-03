package myProject;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used for ...
 * @autor Carlos Felipe Montoya carlos.felipe.montoya@correounivalle.edu.co
 * @version v.1.0.0 date:21/03/2023
 */
public class GUI extends JFrame {
    private JPanel panelDadosActivos;
    private JPanel panelDadosInactivos;
    private JPanel panelDadosUtilizados;
    private JPanel panelMarcadorPuntaje;

    public GUI() {
        // Configurar la ventana principal
        setTitle("Geek Masters");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear los cuatro paneles y asignarles un título
        JPanel panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createTitledBorder("Dados Activos"));
        JPanel panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createTitledBorder("Dados Inactivos"));
        JPanel panel3 = new JPanel();
        panel3.setBorder(BorderFactory.createTitledBorder("Tarjeta Puntuación"));
        JPanel panel4 = new JPanel();
        panel4.setBorder(BorderFactory.createTitledBorder("Dados Utilizados"));
        JPanel panel5 = new JPanel();


        // Añadir componentes gráficos a cada panel
        panel1.add(new JLabel("Panel 1"));

        // Añadir los cuatro paneles a la ventana principal
        JPanel panel_juego = new JPanel();
        JPanel panel_botones = new JPanel();
        panel_juego.setLayout(new GridLayout(2, 2));
        panel_juego.add(panel1);
        panel_juego.add(panel2);
        panel_juego.add(panel3);
        panel_juego.add(panel4);
        panel_botones.add(new JButton("Ayuda"));
        panel_botones.add(new JButton("Comenzar"));


        add(panel_botones, BorderLayout.SOUTH);

        add(panel_juego, BorderLayout.CENTER);




        // Mostrar la ventana principal
        setVisible(true);
    }

    public static void main(String[] args) {
        GUI juego = new GUI();
    }
}
