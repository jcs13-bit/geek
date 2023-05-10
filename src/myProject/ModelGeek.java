package myProject;

public class ModelGeek {
    private Dado[] dados = new Dado[10];

    private int tiro, puntos = 0, estado;
    private String estadoString;


    public ModelGeek()
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
    public void accionCorazon(int dadoSeleccionado)
    {
        dados[dadoSeleccionado].tirarDado();
    }
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

    public String validarEstado()
    {
        int acciones = 0;
        int dadosActivos = 0;
        int dadoSuperHeroe = 0;
        int corazon = 0;
        int dadosInactivos = 0;
        for (int i = 0; i < 10; i++)
        {
            if (dados[i].getCara() > 0 &&  dados[i].getCara() < 5 && dados[i].getEstado() == "activo")
                acciones++;
            if ( dados[i].getEstado() == "activo")
                dadosActivos++;
            if ( dados[i].getCara() == 3 && dados[i].getEstado() == "activo") {
                dadoSuperHeroe++;
            }

            if ( dados[i].getCara() == 4 && dados[i].getEstado() == "activo");
                corazon++;
            if (dados[i].getEstado() == "inactivo")
                dadosInactivos++;
        }
        if (dadosActivos == 0)
            return "sin acciones";
        else if (acciones <= 1 && dadosActivos == 1)
            return "sin acciones";
        else if (acciones > 1 && dadoSuperHeroe == dadosActivos)
            return "sin acciones";
        else if (corazon>0 && dadosInactivos == 0) {
            return "sin acciones";
        }
        else if (acciones == 0) {
            return "sin acciones";
        }

        return "con acciones";


    }

    public int validarPuntuacion()
    {
        int cantidadDragones = 0;
        int cantidad42 = 0;
        int cantidadOtros = 0;
        for (int i = 0; i < 10; i++)
        {
            if (dados[i].getCara() == 6 && dados[i].getEstado() == "activo")
            {
                cantidad42++;
            } else if (dados[i].getCara() == 5 && dados[i].getEstado() == "activo") {
                cantidadDragones++;
            }else{
                if (dados[i].getEstado() == "activo")
                    cantidadOtros++;
            }
        }
        if (cantidadDragones > 0)
        {
            puntos = 0;
        } else if (cantidadOtros > 0) {
            puntos += 0;

        }else if (cantidad42 > 0){
            for(int i = 1; i <= cantidad42; i++) {
                puntos += i;
            }
        }
        return puntos;

    }


    public int getDadosInactivos() {
        int dadosInactivos = 0;
        for (int i = 0; i < 10; i++)
        {
            if (dados[i].getEstado() == "inactivo")
                dadosInactivos++;
        }
        return dadosInactivos;

    }
}
