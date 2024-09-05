/**
 * ESTRUTURA DE DADOS II
 * TURMA 04P11
 * APL 1
 * ALAN MENIUK GLEIZER -
 * CAIO VINICIUS CORSINI FILHO - 10342005
 * GILBERTO DE MELO JÚNIOR - 10419275
 * **/

public class BinaryTree {


    // atributos
    private Node root;

    // construtor(es)
    public BinaryTree(Node root) {
        this.root = root;
    }

    // setters
    public void setRoot(Node root) {
        this.root = root;
    }

    // getters
    public Node getRoot() {
        return root;
    }

    // outros métodos
    /**
     * boolean isEmpty - Verifica se a árvore está fazia
     */
    public boolean isEmpty() { return root == null; }



} // BinaryTree