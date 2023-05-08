package myProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 * This class is used for ...
 * @autor Carlos Felipe Montoya carlos.felipe.montoya@correounivalle.edu.co
 * @version v.1.0.0 date:21/03/2023
 */
public class GUI extends JFrame {
    private Header headerProjec;
    private JLabel[] dadosLabel = new JLabel[10];

    private ImageIcon imagenDadoPorDefecto;

    private Dado[] dados = new Dado[10];

    private Escucha escucha;

    private EscuchaDados escuchaDados;

    private MoldelGeek modelGeek;


    private JPanel panel1;
    private JPanel panelDadosInactivos;
    private JPanel panelDadosUtilizados;
    private JPanel panelMarcadorPuntaje;

    public GUI()
    {
        modelGeek = new MoldelGeek();
        // Configurar la ventana principal
        headerProjec = new Header("Geets Out Master -- Game", Color.black);
        this.add(headerProjec, BorderLayout.NORTH);
        setTitle("Geek Masters");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        escucha = new Escucha();
        escuchaDados = new EscuchaDados();

        panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createTitledBorder("Dados Activos"));
        JPanel panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createTitledBorder("Dados Inactivos"));
        JPanel panel3 = new JPanel(new BorderLayout());

        panel3.setBorder(BorderFactory.createTitledBorder("Tarjeta Puntuación"));
        JPanel panel4 = new JPanel();
        panel4.setBorder(BorderFactory.createTitledBorder("Dados Utilizados"));
        JPanel panel5 = new JPanel();

        // Dados por defecto.
        // ACTIVOS
        imagenDadoPorDefecto = new ImageIcon(new ImageIcon(getClass().getResource("/resources/caras/interrogante.png")).getImage().getScaledInstance(70,70, 1));
        for (int i = 0; i < 10 ; i++)
        {
            dadosLabel[i] = new JLabel(imagenDadoPorDefecto);
            dadosLabel[i].setPreferredSize(new Dimension(70,70));
            dadosLabel[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            if (i < 7)
            {
                panel1.add(dadosLabel[i]);
            }else
            {
                panel2.add(dadosLabel[i]);
            }
            dadosLabel[i].addMouseMotionListener(escuchaDados);
            dadosLabel[i].addMouseListener(escuchaDados);

        }
        panel1.setFocusable(true);
        panel2.setFocusable(true);

        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Ronda");
        modeloTabla.addColumn("Jugador 1");
        modeloTabla.addColumn("Jugador 2");
        JTable tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        modeloTabla.addRow(new Object[]{"1", "5", "0"});

        panel3.add(scrollPane, BorderLayout.CENTER);
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
        JButton botonComenzar = new JButton("Lanzar dados");
        JButton botonAyuda = new JButton("Ayuda");
        panel_botones.add(botonAyuda);
        panel_botones.add(botonComenzar);
        botonComenzar.addActionListener(escucha);
        botonComenzar.setFocusable(false);
        botonAyuda.setFocusable(false);



        add(panel_botones, BorderLayout.SOUTH);

        add(panel_juego, BorderLayout.CENTER);




        // Mostrar la ventana principal
        setVisible(true);
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
                GUI juego = new GUI();
        });

    }

    private  class Escucha implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            modelGeek.tiroInicial();
            dados = modelGeek.getDados();
            for (int i = 0; i < 10 ; i++)
            {
                dadosLabel[i].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/caras/" + dados[i].getImagen())).getImage().getScaledInstance(70,70, 1)));
            }

        }
    }

    private  class EscuchaDados implements MouseListener, MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
            panel1.setFocusable(true);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (dados[0] == null)
            {
                headerProjec.setText("Tira los dados antes de intentar usar uno :)" );
                return;
            }
            for (int i = 0 ; i < dadosLabel.length ; i++)
            {
                dadosLabel[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
                if (dadosLabel[i] == e.getSource())
                {
                    if (dados[i].getEstado() == "activo")
                    {
                        dadosLabel[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
                        headerProjec.setText(dados[i].getNombre() + " :" + dados[i].getInstruccion() );
                    }else{
                        headerProjec.setText("Para usar un dado debes escogerlo de entre los activos.");
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("aja men");
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {
            e.getComponent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }


}
