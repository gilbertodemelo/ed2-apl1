/**
 * ESTRUTURA DE DADOS II
 * TURMA 04P11
 * APL 1
 * ALAN MENIUK GLEIZER - 10416804
 * CAIO VINICIUS CORSINI FILHO - 10342005
 * GILBERTO DE MELO JÚNIOR - 10419275
 * **/

public class BinaryTree<T> {


    // atributos
    private Node<T> root;

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

    //public int getDegree();
    //public int getHeight();

    public void insert(T value) {
        root = inserir(root, value);
    }


    // método auxiliar
    private Node<T> inserir(Node<T> node, T value) {
        if (node == null) {
            return new Node<>(value);
        }


        // comparacao para BST
        // TODO: precisa ser ajustado para colocar de acordo com a
        // expressao matematica
        if (((Comparable<T>) value).compareTo(node.getData()) < 0) {
            node.setLeft(insertRec(node.getLeft(), value));
        } else if (((Comparable<T>) value).compareTo(node.getData()) > 0) {
            node.setRight(insertRec(node.getRight(), value));
        }

        return node;

    }



    public void inOrderTraversal() {
        traverseInOrder(root);
    }

    public void preOrderTraversal() {
        traversePreOrder(root);
    }

    public void postOrderTraversal() {
        traversePostOrder(root);
    }

    // método auxiliar - inOrder
    private void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.getLeft());
            System.out.printf("%s ", node.getData());
            traverseInOrder(node.getRight());
        }
    }

    // método auxiliar - preOrder
    private void traversePreOrder(Node node) {
        if(node != null) {
            System.out.printf("%s ", node.getData());
            traversePreOrder(node.getLeft());
            traversePreOrder(node.getRight());
        }
    }

    // método auxiliar - postOrder
    private void traversePostOrder(Node node) {
        if(node != null) {
            traversePostOrder(node.getLeft());
            traversePostOrder(node.getRight());
            System.out.printf("%s ", node.getData());
        }
    }





} // BinaryTree