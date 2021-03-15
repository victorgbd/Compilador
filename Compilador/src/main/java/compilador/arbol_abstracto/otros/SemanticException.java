/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package compilador.arbol_abstracto.otros;


public class SemanticException extends Exception {

    /**
     * Creates a new instance of <code>SemanticException</code> without detail message.
     */
    public SemanticException() {
    }


    /**
     * Constructs an instance of <code>SemanticException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public SemanticException(String msg) {
        super(msg);
    }
}
