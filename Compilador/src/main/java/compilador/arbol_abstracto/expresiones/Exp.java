/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package compilador.arbol_abstracto.expresiones;

import compilador.arbol_abstracto.otros.Estatuto;


public abstract class Exp extends Estatuto{
    public abstract int eval();
}
