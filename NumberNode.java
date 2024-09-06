/**
 * ESTRUTURA DE DADOS II
 * TURMA 04P11
 * APL 1
 * ALAN MENIUK GLEIZER - 10416804
 * CAIO VINICIUS CORSINI FILHO - 10342005
 * GILBERTO DE MELO JÚNIOR - 10419275
 * **/

// era public class NumberNode<T extends Number> extends Node<T>
// vou trocar para tipo float para eliminar alguns warnings... -Alan
public class NumberNode extends Node<Float> {

    // atributo(s)
    private Float data;
    private Node parent;
    private Node left;
    private Node right;

    // construtor(es)
    public NumberNode(Float data, Node parent, Node left, Node right){
        super(data, parent, left, right);
    }

    // setters
    public void setData(Float data) {
        this.data = data;
    }

} // OperatorNode