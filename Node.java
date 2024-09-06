/**
 * ESTRUTURA DE DADOS II
 * TURMA 04P11
 * APL 1
 * ALAN MENIUK GLEIZER -
 * CAIO VINICIUS CORSINI FILHO - 10342005
 * GILBERTO DE MELO JÚNIOR - 10419275
 * **/


public class Node<T> {

    // atributos
    private <T> data;
    private Node parent;
    private Node left;
    private Node right;

    // construtores

    /**
     * Construtor para nó que é um operador
     * @param data
     * @param parent
     * @param left
     * @param right
     */
    // TODO: Restringir o tipo de operadores aqui?
    public Node(T data, Node parent, Node left, Node right) {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }


    // setters
    public void setda(T data) { this.data = data; }
    public void setParent(Node parent) { this.parent = parent; }
    public void setLeft(Node left) { this.left = left; }
    public void serRight(Node right) { this.right = right; }

    // getters
    public T getData() { return data; }
    public Node getParent() { return parent; }
    public Node getLeft() { return left; }
    public Node getRight() { return right; }

    // outros métodos
    public boolean isRoot() { return parent == null; }
    public boolean isLeaf() { return (left == null) && (right == null); }

    /**
     * Returna o grau do nó
     * @return grau (int)
     */
    public int getDegree() {
        int grau = 0;
        if(!isLeaf()) {
            if(left != null) grau += 1;
            if(right != null) grau += 1;
        }
        return grau;
    }

    // The level of a node is defined as the number of edges
    // from the root to the node.
    /**
     * Return o nível do nó
     * @return nível (int)
     */
    public int getLevel() {
        int nivel = 0;
        Node currentNode = this;
        while(currentNode.getParent() != null) {
            nivel += 1;
            currentNode = currentNode.getParent();
        }
        return nivel;
    }

    // The height of a node in a tree is the length of the longest
    // path from that node to a leaf
    /**
     * Retorna a altura do nó
     * @return altura(int)
     */
    public int getHeight() {
        if (isLeaf())
            return 0;

        int leftHeight = (left != null) ? left.getHeight() : 0;
        int rightHeight = (right != null) ? right.getHeight() : 0;

        return 1 + Math.max(leftHeight, rightHeight);
    }

} // Node
