package edu.upc.login;


import edu.upc.login.Entidades.Item;
import edu.upc.login.Entidades.Ranking;


public interface iComunicaFragments {

    public void enviarObjeto(Item item);
    public void consultarTop ();
}
