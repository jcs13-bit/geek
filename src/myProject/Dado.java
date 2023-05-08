package myProject;
import java.util.Random;
public class Dado {
    private int cara;
    private String imagen;
    private String estado;

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
                break;

            case 2:
                imagen = "nave_espacial.png";
                break;

            case 3:
                imagen = "super_herue.png";
                break;

            case 4:
                imagen = "corazon.png";
                break;

            case 5:
                imagen = "dragon.png";
                break;

            case 6:
                imagen = "42.png";
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


}
