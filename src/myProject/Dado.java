package myProject;
import java.util.Random;
public class Dado {
    private int cara;
    private String imagen;
    private String estado;
    private String nombre;
    private String instruccion;

    public Dado(String estadoInicial)
    {
        estado = estadoInicial;
    }

    public int tirarDado() {
        Random aleatorio = new Random();
        cara = aleatorio.nextInt(6)+1;
        this.asignarImagen();
        return cara;
    }

    public int getCara()
    {
        return cara;
    }

    public void asignarImagen()
    {
        switch (cara)
        {
            case 1:
                imagen = "meeple.png";
                nombre = "Meeple";
                instruccion = "Relanza uno de tus dados activos.";
                break;

            case 2:
                imagen = "nave_espacial.png";
                nombre = "Nave espacial";
                instruccion = "Envía un dado no usado a la selección de inactivos";
                break;

            case 3:
                imagen = "super_herue.png";
                nombre = "Super Heroe";
                instruccion = "Voltea un dado de la selección de activos.";
                break;

            case 4:
                imagen = "corazon.png";
                nombre = "Corazon";
                instruccion = "Puedes traer al juego un dado del grupo de los inactivos.";
                break;

            case 5:
                imagen = "dragon.png";
                nombre = "Dragon";
                instruccion = "Desaste de ese dragon!!.";
                break;

            case 6:
                imagen = "42.png";
                nombre = "42";
                instruccion = "El 42 te suma puntos!!!.";
                break;

        }
    }

    public String getImagen()
    {
        return imagen;
    }


    public String getEstado()
    {
        return estado;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getInstruccion()
    {

        return estado;
    }

    public void setEstado(String nuevoEstado)
    {
        estado = nuevoEstado;
    }
    public void setCara(int nuevoCara)
    {
        cara = nuevoCara;
        asignarImagen();
    }

}
