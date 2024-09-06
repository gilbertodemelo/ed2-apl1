/**
 * ESTRUTURA DE DADOS II
 * TURMA 04P11
 * APL 1
 * ALAN MENIUK GLEIZER - 10416804
 * CAIO VINICIUS CORSINI FILHO - 10342005
 * GILBERTO DE MELO JÚNIOR - 10419275
 * **/


public class OperatorNode extends Node<String> {

    // atributo(s)
    private String operador;
    private Node parent;
    private Node left;
    private Node right;

    // construtor(es)
    public OperatorNode(String operador, Node parent, Node left, Node right) throws IllegalArgumentException {
        super(operador, parent, left, right);
    }

    // setters
    public void setData(String operador) {
        if (operador == "+" || operador == "-" || operador == "*" || operador == "/" || operador == "(" || operador == ")")
            this.operador = operador;
        else throw new IllegalArgumentException("Operador inválido.");
    }

} // OperatorNode