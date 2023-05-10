package myProject;

import java.util.Map;

public class MoldelGeek {
    private Dado[] dados = new Dado[10];

    private int tiro, puntos = 0, estado;
    private String estadoString;


    /**
     * Constructor , crea los 10 dados.
     * @autor Johan, Jose y Jhonatan edier.castro@correounivalle.edu.co
     * @version v.1.0.0 date:21/03/2023
     */
    public MoldelGeek()
    {
        dados[0] = new Dado("activo");
        dados[1] = new Dado("activo");
        dados[2] = new Dado("activo");
        dados[3] = new Dado("activo");
        dados[4] = new Dado("activo");
        dados[5] = new Dado("activo");
        dados[6] = new Dado("activo");
        dados[7] = new Dado("inactivo");
        dados[8] = new Dado("inactivo");
        dados[9] = new Dado("inactivo");
    }

    /**
     * realiza un tiro de todos los dados.
     * @autor Johan, Jose y Jhonatan edier.castro@correounivalle.edu.co
     * @version v.1.0.0 date:21/03/2023
     */
    public void tiroInicial()
    {
        for (int i = 0; i < 10; i++)
        {
            dados[i].tirarDado();
        }
    }

    /**
     * Obtiene todos los dados.
     * @autor Johan, Jose y Jhonatan edier.castro@correounivalle.edu.co
     * @version v.1.0.0 date:21/03/2023
     */
    public Dado[] getDados()
    {
        return dados;
    }

    /**
     * tira un dado especifico de la zona activos.
     * @autor Johan, Jose y Jhonatan edier.castro@correounivalle.edu.co
     * @version v.1.0.0 date:21/03/2023
     */
    public void accionMeeple(int dadoSeleccionado)
    {
        dados[dadoSeleccionado].tirarDado();
    }

    /**
     * Tira un dado especifico de la zona inactivos
     * @autor Johan, Jose y Jhonatan edier.castro@correounivalle.edu.co
     * @version v.1.0.0 date:21/03/2023
     */
    public void accionCorazon(int dadoSeleccionado)
    {
        dados[dadoSeleccionado].tirarDado();
    }

    /**
     * gira un dado a su cara contraria.
     * @autor Johan, Jose y Jhonatan edier.castro@correounivalle.edu.co
     * @version v.1.0.0 date:21/03/2023
     */
    public void accionHeroe(int dadoSeleccionado) {
        switch (dados[dadoSeleccionado].getCara()){
            case 1:
                dados[dadoSeleccionado].setCara(2);
                break;
            case 2:
                dados[dadoSeleccionado].setCara(1);
                break;
            case 3:
                dados[dadoSeleccionado].setCara(5);
                break;
            case 5:
                dados[dadoSeleccionado].setCara(3);
                break;
            case 4:
                dados[dadoSeleccionado].setCara(6);
                break;
            case 6:
                dados[dadoSeleccionado].setCara(4);
                break;
        }
    }

    /**
     * valida en que estado se encuentra el juego.
     * @autor Johan, Jose y Jhonatan edier.castro@correounivalle.edu.co
     * @version v.1.0.0 date:21/03/2023
     */
    public String validarEstado()
    {
        int acciones = 0;
        for (int i = 0; i < 10; i++)
        {
            if (dados[i].getCara() > 0 &&  dados[i].getCara() < 5 && dados[i].getEstado() == "activo")
                acciones++;

        }
        if (acciones > 1)
            return "con acciones";

        return "sin acciones";
    }

    /**
     * Obtiene la puntuación al acabar la ronda.
     * @autor Johan, Jose y Jhonatan edier.castro@correounivalle.edu.co
     * @version v.1.0.0 date:21/03/2023
     */
    public int validarPuntuacion()
    {
        int cantidadDragones = 0;
        int cantidad42 = 0;
        int cantidadOtros = 0;
        for (int i = 0; i < 10; i++)
        {
            if (dados[i].getCara() == 6 && dados[i].getEstado() == "estado")
            {
                cantidad42++;
            } else if (dados[i].getCara() == 5 && dados[i].getEstado() == "estado") {
                cantidadDragones++;
            }else{
                if (dados[i].getEstado() == "estado")
                    cantidadOtros++;
            }
        }
        if (cantidadDragones > 0)
        {
            puntos = 0;
        } else if (cantidadOtros > 0) {
            puntos += 0;
            return 0;
        }else if (cantidad42 > 0){
            for(int i = 1; i <= cantidad42; i++) {
                puntos += i;
            }
        }
        return puntos;

    }




}
