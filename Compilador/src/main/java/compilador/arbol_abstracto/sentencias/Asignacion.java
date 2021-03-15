/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package compilador.arbol_abstracto.sentencias;

import compilador.arbol_abstracto.listas.VarDecList;
import compilador.arbol_abstracto.expresiones.Exp;
import compilador.arbol_abstracto.expresiones.False;
import compilador.arbol_abstracto.expresiones.IdentificadorExp;
import compilador.arbol_abstracto.expresiones.Plus;
import compilador.arbol_abstracto.expresiones.True;
import compilador.arbol_abstracto.otros.Identificador;
import compilador.arbol_abstracto.otros.Programa;
import compilador.arbol_abstracto.otros.VarDeclaracion;
import compilador.arbol_abstracto.tipos.BooleanTipo;
import compilador.arbol_abstracto.tipos.IntegerTipo;
import compilador.arbol_abstracto.tipos.Tipo;

/**
 *
 * @author OSCAR
 */
public class Asignacion extends Statement{
    private Identificador identificador;
    private Exp exprecion;

    public Asignacion(Identificador identificador, Exp exprecion) {
        this.identificador = identificador;
        this.exprecion = exprecion;
    }
    
    public int eval(){
        Tipo tipo= Programa.getListaVariables().getVar(identificador.getId()).getTipo();
        if(tipo instanceof BooleanTipo){
            if(exprecion instanceof True | exprecion instanceof False){
                return 1;
            }
        }else if(tipo instanceof IntegerTipo){
            if(exprecion instanceof Plus){
                return 1;
            }
        }
        else if(exprecion instanceof IdentificadorExp){
            IdentificadorExp id = (IdentificadorExp) exprecion;
            VarDeclaracion var = Programa.getListaVariables().getVar(id.getId());
            if(tipo instanceof BooleanTipo & var.getTipo() instanceof BooleanTipo){
                return 1;
            }else if(tipo instanceof IntegerTipo & var.getTipo() instanceof IntegerTipo){
                return 1;
            }
        }
        return 0;
    }

    public Exp getEmprecion() {
        return exprecion;
    }

    public void setEmprecion(Exp emprecion) {
        this.exprecion = emprecion;
    }

    public Identificador getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Identificador identificador) {
        this.identificador = identificador;
    }

    @Override
    public String toString(){
        return "Asignacion( )\n"+identificador.toString()+exprecion.toString()+"\n";
    }
}
