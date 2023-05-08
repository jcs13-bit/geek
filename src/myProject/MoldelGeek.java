package myProject;

import java.util.Map;

public class MoldelGeek {
    private Dado[] dados = new Dado[10];

    private int tiro, punto, estado;
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


}
