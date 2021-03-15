/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package compilador.arbol_abstracto.listas;

import java.util.ArrayList;
import java.util.List;
import compilador.arbol_abstracto.expresiones.Exp;



public class ExpList extends Lista{
    private List<Exp> listaExpreciones = new ArrayList<Exp>();

    public boolean empty(){
        return listaExpreciones.isEmpty();
    }

    public void addStatement(Exp exprecion){
        listaExpreciones.add(exprecion);
    }

    public int size(){
        return listaExpreciones.size();
    }

    public boolean findStatement(Exp exprecion){
        return listaExpreciones.contains(exprecion);
    }

    public void clear(){
        listaExpreciones.clear();
    }
}
