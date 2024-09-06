public class OperatorNode extends Node<String> {

    // atributo(s)
    private String operador;
    private Node parent;
    private Node left;
    private Node right;

    // construtor(es)
    public OperatorNode(String operador, Node parent, Node left, Node right) throws IllegalArgumentException {
        if (operador == "+" || operador == "-" || operator == "*" || operador == "/" || operador == "(" || operador == ")")
            super(operador, parent, left, right);
        else throw new IllegalArgumentException("Operador inválido.");
    }

    // setters
    public String setData(String operador) {
        if (operador == "+" || operador == "-" || operator == "*" || operador == "/" || operador == "(" || operador == ")")
            this.operador = operador;
        else throw new IllegalArgumentException("Operador inválido.");
    }

} // OperatorNode