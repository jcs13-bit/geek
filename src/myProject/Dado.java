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

    /**
     * Tira el dado.
     * @autor Johan, Jose y Jhonatan edier.castro@correounivalle.edu.co
     * @version v.1.0.0 date:21/03/2023
     */
    public int tirarDado() {
        Random aleatorio = new Random();
        cara = aleatorio.nextInt(6)+1;
        this.asignarImagen();
        return cara;
    }

    /**
     * Obtiene la cara en la que cayo el dado.
     * @autor Johan, Jose y Jhonatan edier.castro@correounivalle.edu.co
     * @version v.1.0.0 date:21/03/2023
     */
    public int getCara()
    {
        return cara;
    }

    /**
     * Asigna una imagen al dado segun la cara en la que cayo.
     * @autor Johan, Jose y Jhonatan edier.castro@correounivalle.edu.co
     * @version v.1.0.0 date:21/03/2023
     */
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
                imagen = "super_heroe.png";
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

    /**
     * Obtiene la imagen asignada al dado.
     * @autor Johan, Jose y Jhonatan edier.castro@correounivalle.edu.co
     * @version v.1.0.0 date:21/03/2023
     */
    public String getImagen()
    {
        return imagen;
    }


    /**
     * Obtiene el estado del dado.
     * @autor Johan, Jose y Jhonatan edier.castro@correounivalle.edu.co
     * @version v.1.0.0 date:21/03/2023
     */
    public String getEstado()
    {
        return estado;
    }

    /**
     * Obtiene el nombre del poder del dado, ejemplo : Meeple.
     * @autor Johan, Jose y Jhonatan edier.castro@correounivalle.edu.co
     * @version v.1.0.0 date:21/03/2023
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Asigna un estado nuevo al dado.
     * @autor Johan, Jose y Jhonatan edier.castro@correounivalle.edu.co
     * @version v.1.0.0 date:21/03/2023
     */
    public void setEstado(String nuevoEstado)
    {
        estado = nuevoEstado;
    }

    /**
     * Asigna una nueva cara al dado.
     * @autor Johan, Jose y Jhonatan edier.castro@correounivalle.edu.co
     * @version v.1.0.0 date:21/03/2023
     */
    public void setCara(int nuevoCara)
    {
        cara = nuevoCara;
        asignarImagen();
    }

}
