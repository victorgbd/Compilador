/*
 * and open the template in the editor.
 */
package compilador.codigo_objeto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JTextArea;
import compilador.arbol_abstracto.expresiones.Comparacion;
import compilador.arbol_abstracto.expresiones.Exp;
import compilador.arbol_abstracto.expresiones.IdentificadorExp;
import compilador.arbol_abstracto.expresiones.Plus;
import compilador.arbol_abstracto.otros.Estatuto;
import compilador.arbol_abstracto.otros.Programa;
import compilador.arbol_abstracto.otros.VarDeclaracion;
import compilador.arbol_abstracto.sentencias.Asignacion;
import compilador.arbol_abstracto.sentencias.If;
import compilador.arbol_abstracto.sentencias.Print;
import compilador.arbol_abstracto.sentencias.Statement;
import compilador.arbol_abstracto.tipos.BooleanTipo;
import compilador.arbol_abstracto.tipos.IntegerTipo;
import compilador.arbol_abstracto.tipos.Tipo;


public class GeneracionCodigoObjeto {

    private File fileOut;
    private BufferedWriter out;
    private List<Estatuto> arbolAbstracto;
    private String salida = "";
    private JTextArea areaTexto;
    private int countLavel = 0;
    private int FP = 0;
    private int SP = 0;
    private final int TAMAÑO_INT = 4;
    private final int TAMAÑO_BOOLEAN = 1;

    public GeneracionCodigoObjeto(File file, JTextArea areaTexto, List<Estatuto> arbolAbstracto) {
        fileOut = file;
        this.arbolAbstracto = arbolAbstracto;
        this.areaTexto = areaTexto;
    }

    public void start() throws IOException {
        crearFichero();
        abrirArchivo();

        for (Estatuto estatuto : arbolAbstracto) {
            if (estatuto instanceof VarDeclaracion) {
                VarDeclaration((VarDeclaracion) estatuto);
                continue;
            }
            if (estatuto instanceof Statement) {
                Statement((Statement) estatuto);
            }
        }
        guardarArchivo();
        areaTexto.setText(salida.trim());
    }

    private void Statement(Statement statuto) {
        if (statuto instanceof Print) {
            Print((Print) statuto);
        } else if (statuto instanceof If) {
            IF((If) statuto);
        } else if (statuto instanceof Asignacion) {
            Asignacion((Asignacion) statuto);
        }
    }

    private void Print(Print print) {
        salida+="\n// Imprimir Linea";
        Exp(print.getEmprecion());
        salida += "\nPRINT $T1";
        salida += "\n";
    }

    private void Asignacion(Asignacion asignacion) {
        salida+="\n// Statement Asignacion";
        Exp(asignacion.getEmprecion());
        VarDeclaracion var1 = Programa.getListaVariables().getVar(asignacion.getIdentificador().getId());
        int index1 = Programa.getListaVariables().getVarIndex(var1);
        index1 = index1 * 4;
        salida += "\nSW $T1, -" + index1 + "($FP)";

        salida += "\n";
    }

    private void IF(If IF) {
        salida+="\n// Statement IF";
        Exp(IF.getExprecion());
        salida += "\nADDI $T2, $ZERO, $ZERO";
        salida += "\nBEQ $T1, $T2, ETIQUETA" + countLavel;
        Statement(IF.getInstruccion1());
        salida += "\nJ ETIQUETA" + (countLavel + 1);
        salida += "\nETIQUETA" + countLavel + ": ";
        Statement(IF.getInstruccion2());
        salida += "\nETIQUETA" + (countLavel + 1) + ": ";

        salida+="\n";
        countLavel += 2;
    }

    private void Exp(Exp exp) {
        if (exp instanceof Plus) {
            Plus((Plus) exp);
        } else if (exp instanceof Comparacion) {
            Comparacion((Comparacion) exp);
        } else if (exp instanceof IdentificadorExp) {
            IdentificadorExp((IdentificadorExp) exp);
        }
    }

    private void IdentificadorExp(IdentificadorExp id) {
        VarDeclaracion var1 = Programa.getListaVariables().getVar(id.getId());
        int index1 = Programa.getListaVariables().getVarIndex(var1);
        index1 = index1 * 4;

        salida += "\nLW $T1, -" + index1 + "($FP)";
    }

    /**
     * Almacena en T1 el resultado de la suma
     */
    private void Plus(Plus plus) {
        IdentificadorExp id1 = (IdentificadorExp) plus.getExprecion1();
        VarDeclaracion var1 = Programa.getListaVariables().getVar(id1.getId());
        int index1 = Programa.getListaVariables().getVarIndex(var1);
        index1 = index1 * 4;

        IdentificadorExp id2 = (IdentificadorExp) plus.getExprecion2();
        VarDeclaracion var2 = Programa.getListaVariables().getVar(id2.getId());
        int index2 = Programa.getListaVariables().getVarIndex(var2);
        index2 = index2 * 4;

        salida += "\nLW $T1, -" + index1 + "($FP)";
        salida += "\nLW $T2, -" + index2 + "($FP)";
        salida += "\nADD $T1, $T1, $T2";
    }

    private void Comparacion(Comparacion comparacion) {
        IdentificadorExp id1 = (IdentificadorExp) comparacion.getExprecion1();
        VarDeclaracion var1 = Programa.getListaVariables().getVar(id1.getId());
        int index1 = Programa.getListaVariables().getVarIndex(var1);
        index1 = index1 * 4;

        IdentificadorExp id2 = (IdentificadorExp) comparacion.getExprecion2();
        VarDeclaracion var2 = Programa.getListaVariables().getVar(id2.getId());
        int index2 = Programa.getListaVariables().getVarIndex(var2);
        index2 = index2 * 4;

        salida += "\nLW $T1, -" + index1 + "($FP)";
        salida += "\nLW $T2, -" + index2 + "($FP)";
        salida += "\nBEQ $T1, $T2, ETIQUETA" + countLavel;
        salida += "\nADDI $T1, $ZERO, 0";
        salida += "\nJ ETIQUETA" + (countLavel + 1);
        salida += "\nETIQUETA" + countLavel + ":";
        salida += "\nADDI $T1, $ZERO, 1";
        salida += "\nETIQUETA" + (countLavel + 1) + ":";

        countLavel += 2;
    }

    private void VarDeclaration(VarDeclaracion var) {
        salida+="\n// Declaracion de Variable";
        Tipo p = var.getTipo();
        if (p instanceof IntegerTipo) {
            salida += "\nADDI $T1, $ZERO, $ZERO";
            salida += "\nSW $T1, 0($ESP)";
            salida += "\nADDI $ESP, $ESP, -4";
        } else if (p instanceof BooleanTipo) {
            salida += "\nADDI $T1, $ZERO, $ZERO";
            salida += "\nSW $T1, 0($ESP)";
            salida += "\nADDI $ESP, $ESP, -4";
        }
        salida += "\n";
    }

    private void abrirArchivo() throws IOException {
        out = new BufferedWriter(new FileWriter(fileOut));
    }

    private void crearFichero() throws IOException {
        if (!fileOut.exists()) {
            fileOut.createNewFile();
        }
    }

    private void guardarArchivo() throws IOException {
        char[] cadena = salida.toCharArray();
        for (int i = 0; i < cadena.length; i++) {
            char c = cadena[i];
            if (c == '$') {
                out.newLine();
            } else {
                out.write(c);
            }
        }
        out.flush();
        out.close();
    }
}
