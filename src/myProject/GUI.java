package myProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class is used for ...
 * @autor Carlos Felipe Montoya carlos.felipe.montoya@correounivalle.edu.co
 * @version v.1.0.0 date:21/03/2023
 */
public class GUI extends JFrame {
    private Header headerProjec;
    private JLabel[] dadosActivos = new JLabel[10];
    private JLabel[] dadosInactivos = new JLabel[10];

    private ImageIcon imagenDadoPorDefecto;

    private BufferedImage img;
    private JPanel panelDadosActivos;
    private JPanel panelDadosInactivos;
    private JPanel panelDadosUtilizados;
    private JPanel panelMarcadorPuntaje;

    public GUI()
    {
        // Configurar la ventana principal
        headerProjec = new Header("Geets Out Master -- Game", Color.black);
        this.add(headerProjec, BorderLayout.NORTH);
        setTitle("Geek Masters");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //dado2 = new JLabel(imagenDado);
        //dado1.setPreferredSize(new Dimension(70,70));
        //dado2.setPreferredSize(new Dimension(70,70));
        //dado1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        //dado2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));


        JPanel panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createTitledBorder("Dados Activos"));
        JPanel panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createTitledBorder("Dados Inactivos"));
        JPanel panel3 = new JPanel();
        panel3.setBorder(BorderFactory.createTitledBorder("Tarjeta Puntuación"));
        JPanel panel4 = new JPanel();
        panel4.setBorder(BorderFactory.createTitledBorder("Dados Utilizados"));
        JPanel panel5 = new JPanel();

        // Dados por defecto.
        // ACTIVOS
        imagenDadoPorDefecto = new ImageIcon(new ImageIcon(getClass().getResource("/resources/caras/interrogante.png")).getImage().getScaledInstance(70,70, 1));
        for (int i = 0; i < 7 ; i++)
        {
            dadosActivos[i] = new JLabel(imagenDadoPorDefecto);
            dadosActivos[i].setPreferredSize(new Dimension(70,70));
            dadosActivos[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            panel1.add(dadosActivos[i]);
        }

        //INACTIVOS
        for (int i = 0; i < 3 ; i++)
        {
            dadosInactivos[i] = new JLabel(imagenDadoPorDefecto);
            dadosInactivos[i].setPreferredSize(new Dimension(70,70));
            dadosInactivos[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            panel2.add(dadosInactivos[i]);
        }

        // Añadir componentes gráficos a cada panel
        //panel1.add(dado1);
        //panel1.add(dado2);

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

    public static void main(String[] args)
    {

        GUI juego = new GUI();
        MoldelGeek model = new MoldelGeek();
    }
}
