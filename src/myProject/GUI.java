package myProject;

import javax.imageio.ImageIO;
import javax.management.modelmbean.ModelMBean;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

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
    private int dadoSeleccionado;
    private MoldelGeek modelGeek;


    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panelMarcadorPuntaje;

    private JButton botonComenzar;

    private int ronda;

    private AccionMeeple accionMeeple;
    private AccionCohete accionCohete;
    private AccionCorazon accionCorazon;

    private DefaultTableModel modeloTabla;
    private AccionHeroe accionHeroe;

    public GUI()
    {

        /* Inicia la primera ronda */
        ronda = 1;
        accionMeeple = new AccionMeeple();
        accionCohete = new AccionCohete();
        accionHeroe = new AccionHeroe();
        accionCorazon = new AccionCorazon();
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
        panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createTitledBorder("Dados Inactivos"));
        panel3 = new JPanel(new BorderLayout());

        panel3.setBorder(BorderFactory.createTitledBorder("Tarjeta Puntuación"));
        panel4 = new JPanel();
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

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Ronda");
        modeloTabla.addColumn("Puntuación.");
        JTable tabla = new JTable(modeloTabla);
        tabla.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(tabla);
        modeloTabla.addRow(new Object[]{ronda, "0", "0"});

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
        botonComenzar = new JButton("Lanzar dados");
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
        //ventana de ayuda
        /*JPanel panelAyuda = new JPanel();
        getContentPane().add(panelAyuda, BorderLayout.CENTER);
        panelAyuda.setVisible(false);*/

        //mostrar ayuda
        botonAyuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon imageAyuda = new ImageIcon(getClass().getResource("/resources/caras/ayuda.png"));
                JOptionPane.showMessageDialog(rootPane, " ", "Ayuda", JOptionPane.PLAIN_MESSAGE, imageAyuda);
            }
        });
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
            botonComenzar.setEnabled(false);
            if (ronda > 1) {
                escuchaDados.reubicarDados();
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
                    dadoSeleccionado = i;
                    if (dados[i].getEstado() == "activo")
                    {

                        dadosLabel[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
                        headerProjec.setText(dados[i].getNombre());

                    }else{
                        headerProjec.setText("Para usar un dado debes escogerlo de entre los activos.");
                    }
                }
            }
            if (dados[dadoSeleccionado].getEstado() == "activo")
            {
                if(dados[dadoSeleccionado].getCara() == 5 || dados[dadoSeleccionado].getCara() == 6)
                {
                    headerProjec.setText("El dado seleccionado no tiene funcionalidad especial");
                    return;
                }
                Object[] options = {"Aceptar", "Cancelar"};
                int option = JOptionPane.showOptionDialog(null,
                        "¿Seguro que quíeres utilizar  "+ dados[dadoSeleccionado].getNombre(),"GEET OUT MASTER",
                        JOptionPane.PLAIN_MESSAGE,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (option == 0) {

                    switch (dados[dadoSeleccionado].getCara()) {
                        /* ACCION MEEPLE */
                        case 1:
                            headerProjec.setText("vuelve a Lanzar uno de los dados activos");
                            for (int i = 0; i < dadosLabel.length; i++) {
                                dadosLabel[i].removeMouseListener(this);
                                dadosLabel[i].addMouseListener(accionMeeple);
                            }
                            break;
                        /* ACCION COHETE */
                        case 2:
                            headerProjec.setText("Elimina uno de los dados activos enviándolo a la sección inactivos.");
                            for (int i = 0; i < dadosLabel.length; i++) {
                                dadosLabel[i].removeMouseListener(this);
                                dadosLabel[i].addMouseListener(accionCohete);
                            }
                            break;
                        /* ACCION HEROE */
                    case 3:
                        headerProjec.setText("voltea el dado a su cara opuesta");
                        for (int i = 0 ; i < dadosLabel.length ; i++)
                        {
                            dadosLabel[i].removeMouseListener(this);
                            dadosLabel[i].addMouseListener(accionHeroe);
                        }
                        break;
                        /* ACCION CORAZON */
                        case 4:
                            headerProjec.setText("Elimina uno de los dados inactivos enviándolo a la sección de dados activos.");
                            for (int i = 0; i < dadosLabel.length; i++) {
                                dadosLabel[i].removeMouseListener(this);
                                dadosLabel[i].addMouseListener(accionCorazon);
                            }
                            break;

                    }
                    if (dados[dadoSeleccionado].getCara() != 6 || dados[dadoSeleccionado].getCara() != 5) {
                        dados[dadoSeleccionado].setEstado("usado");
                        panel1.removeAll();
                        panel2.removeAll();
                        panel4.removeAll();
                        repaintDados();

                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

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

        public void repaintDados()
        {
            for (int i = 0; i < dados.length ; i++)
            {
                if (dados[i].getEstado() == "activo")
                {
                    panel1.add(dadosLabel[i]);
                }
                if (dados[i].getEstado() == "inactivo")
                {
                    panel2.add(dadosLabel[i]);
                }
                if (dados[i].getEstado() == "usado")
                {
                    dadosLabel[i].setEnabled(false);
                    panel4.add(dadosLabel[i]);
                }
            }
            revalidate();
            repaint();

        }

        public void validarEstado()
        {
            String estado = modelGeek.validarEstado();
            if (estado == "sin acciones")
            {
                int puntuacion = modelGeek.validarPuntuacion();
                ronda++;
                modeloTabla.setValueAt(puntuacion, 0, 1);
                modeloTabla.setValueAt(ronda, 0, 0);
                botonComenzar.setEnabled(true);
                headerProjec.setText("Terminaste la ronda no quedan mas acciones . Vuelve a tirar los dados.");
                for (int i = 0; i < 10; i++) {
                    dadosLabel[i].removeMouseListener(this);
                }
            }
        }

        public void reubicarDados(){
            panel1.removeAll();
            panel2.removeAll();
            panel4.removeAll();
            headerProjec.setText("Usa alguna de tus acciones.");
            for (int i = 0; i < 10; i++) {
                dadosLabel[i].setEnabled(true);
                dadosLabel[i].addMouseListener(escuchaDados);
                if (i < 7) {
                    panel1.add(dadosLabel[i]);
                    dados[i].setEstado("activo");
                } else {
                    panel2.add(dadosLabel[i]);
                    dados[i].setEstado("inactivo");
                }
            }
            revalidate();
            repaint();
        }


    }

    private class AccionMeeple implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            boolean dadoActivo = false;
            for (int i = 0 ; i < dadosLabel.length ; i++)
            {
                if (dadosLabel[i] == e.getSource())
                {
                    dadoSeleccionado = i;
                    if (dados[i].getEstado() == "activo") {
                        dadoActivo = true;
                        modelGeek.accionMeeple(i);
                        dadosLabel[i].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/caras/" + dados[i].getImagen())).getImage().getScaledInstance(70, 70, 1)));
                        headerProjec.setText("Ahora sigue jugando!!");
                    }
                }
            }
            if (dadoActivo == true)
            {
                for (int i = 0 ; i < dadosLabel.length ; i++) {
                    dadosLabel[i].removeMouseListener(this);
                    dadosLabel[i].addMouseListener(escuchaDados);
                }
                escuchaDados.validarEstado();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class AccionCohete implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            boolean dadoActivo = false;
            for (int i = 0 ; i < dadosLabel.length ; i++)
            {
                if (dadosLabel[i] == e.getSource())
                {
                    dadoSeleccionado = i;
                    if (dados[i].getEstado() == "activo")
                    {
                        dadoActivo = true;
                        dados[i].setEstado("inactivo");
                        headerProjec.setText("Ahora sigue jugando!!");
                    }


                }
            }
            if (dadoActivo == true) {
                for (int i = 0 ; i < dadosLabel.length ; i++) {
                    dadosLabel[i].removeMouseListener(this);
                    dadosLabel[i].addMouseListener(escuchaDados);
                }
                escuchaDados.repaintDados();
                escuchaDados.validarEstado();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    private class AccionHeroe implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {
            boolean dadoActivo = false;
            for (int i = 0 ; i < dadosLabel.length ; i++)
            {
                if (dadosLabel[i] == e.getSource())
                {
                        dadoSeleccionado = i;
                    if (dados[i].getEstado() == "activo" ) {
                        if (dados[i].getCara() == 3)
                        {
                            headerProjec.setText("No puedes voltear otro heroe");
                            return;
                        }
                        dadoActivo = true;
                        modelGeek.accionHeroe(dadoSeleccionado);
                        dadosLabel[i].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/caras/" + dados[i].getImagen())).getImage().getScaledInstance(70, 70, 1)));
                        headerProjec.setText("El dado ahora está en su opuesto");
                    }
                }
            }
            if (dadoActivo == true)
            {
                for (int i = 0 ; i < dadosLabel.length ; i++) {
                    dadosLabel[i].removeMouseListener(this);
                    dadosLabel[i].addMouseListener(escuchaDados);
                }
                revalidate();
                repaint();
                escuchaDados.validarEstado();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class AccionCorazon implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            for (int i = 0 ; i < dadosLabel.length ; i++)
            {
                if (dadosLabel[i] == e.getSource())
                {
                    dadoSeleccionado = i;
                    dados[i].setEstado("activo");
                    headerProjec.setText("Ahora sigue tirando!!");
                }
                dadosLabel[i].removeMouseListener(this);
                dadosLabel[i].addMouseListener(escuchaDados);


            }
            escuchaDados.repaintDados();
            escuchaDados.validarEstado();

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }


}
