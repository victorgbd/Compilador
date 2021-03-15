/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package compilador.arbol_abstracto.expresiones;

public class True extends Exp{
    @Override
    public int eval(){
        return 1;
    }
}
