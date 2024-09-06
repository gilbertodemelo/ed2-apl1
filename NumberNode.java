/**
 * ESTRUTURA DE DADOS II
 * TURMA 04P11
 * APL 1
 * ALAN MENIUK GLEIZER - 10416804
 * CAIO VINICIUS CORSINI FILHO - 10342005
 * GILBERTO DE MELO JÚNIOR - 10419275
 * **/


public class NumberNode<T extends Number> extends Node<T> {

    // atributo(s)
    private T data;
    private Node parent;
    private Node left;
    private Node right;

    // construtor(es)
    public NumberNode(T data, Node parent, Node left, Node right){
        super(data, parent, left, right);
    }

    // setters
    public void setData(T data) {
        this.data = data;
    }

} // OperatorNode