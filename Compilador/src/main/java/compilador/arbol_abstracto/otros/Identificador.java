/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package compilador.arbol_abstracto.otros;

public class Identificador {
    private String id;

    public Identificador(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "Identificador\n";
    }
}
