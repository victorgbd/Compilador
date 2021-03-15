/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package compilador.arbol_abstracto.listas;

import java.util.ArrayList;
import java.util.List;
import compilador.arbol_abstracto.sentencias.Statement;

public class StatementList extends Lista{
    private List<Statement> listaStatement = new ArrayList<Statement>();

    public boolean empty(){
        return listaStatement.isEmpty();
    }

    public void addStatement(Statement statement){
        listaStatement.add(statement);
    }

    public int size(){
        return listaStatement.size();
    }

    public boolean findStatement(Statement statement){
        return listaStatement.contains(statement);
    }
    public void clear(){
        listaStatement.clear();
    }
}
