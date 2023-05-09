package myProject;

import java.util.Map;

public class MoldelGeek {
    private Dado[] dados = new Dado[10];

    private int tiro, puntos = 0, estado;
    private String estadoString;


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

    public void tiroInicial()
    {
        for (int i = 0; i < 10; i++)
        {
            dados[i].tirarDado();
        }
    }


    public Dado[] getDados()
    {
        return dados;
    }

    public void accionMeeple(int dadoSeleccionado)
    {
        dados[dadoSeleccionado].tirarDado();
    }

    public String validarEstado()
    {
        int acciones = 0;
        for (int i = 0; i < 10; i++)
        {
            if (dados[i].getCara() > 0 &&  dados[i].getCara() < 5 && dados[i].getEstado() == "activo")
                acciones++;

        }
        if (acciones > 0)
            return "con acciones";

        return "sin acciones";
    }

    public int validarPuntuacion()
    {
        int cantidadDragones = 0;
        int cantidad42 = 0;
        int cantidadOtros = 0;
        for (int i = 0; i < 10; i++)
        {
            if (dados[i].getCara() == 6)
            {
                cantidad42++;
            } else if (dados[i].getCara() == 5) {
                cantidadDragones++;
            }else{
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
