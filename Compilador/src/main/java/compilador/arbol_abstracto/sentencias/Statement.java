/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package compilador.arbol_abstracto.sentencias;

import compilador.arbol_abstracto.otros.Estatuto;

/**
 *
 * @author OSCAR
 */
public abstract class Statement extends Estatuto{
    public abstract int eval();
}
