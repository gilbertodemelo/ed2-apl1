/**
 * ESTRUTURA DE DADOS II
 * TURMA 04P11
 * APL 1
 * ALAN MENIUK GLEIZER - 10416804
 * CAIO VINICIUS CORSINI FILHO - 10342005
 * GILBERTO DE MELO JÚNIOR - 10419275
 * *
 */

public class BinaryTree<T> {

  // atributos
  private Node<T> root;

  // construtor(es)
  public BinaryTree(Node<T> root) { this.root = root; }

  // setters
  public void setRoot(Node<T> root) { this.root = root; }

  // getters
  public Node<T> getRoot() { return root; }

  // outros métodos
  /**
   * boolean isEmpty - Verifica se a árvore está fazia
   */
  public boolean isEmpty() { return root == null; }

  public void insert(T value) { root = inserir(root, value); }

  // método auxiliar
  private Node<T> inserir(Node<T> node, T value) {
    if (node == null) {
      // When creating a new node, pass null for the parent, left, and right
      // pointers
      return new Node<>(value, null, null, null);
    }

    // comparacao para BST
    if (value instanceof Comparable) {
      Comparable<T> comparableValue = (Comparable<T>)value;

      if (comparableValue.compareTo(node.getData()) < 0) {
        node.setLeft(inserir(node.getLeft(), value));
      } else if (comparableValue.compareTo(node.getData()) > 0) {
        node.setRight(inserir(node.getRight(), value));
      }
    } else {
      throw new IllegalArgumentException("Value must be comparable");
    }

    return node;
  }

  public void inOrderTraversal() { traverseInOrder(root); }

  public void preOrderTraversal() { traversePreOrder(root); }

  public void postOrderTraversal() { traversePostOrder(root); }

  // método auxiliar - inOrder
  private void traverseInOrder(Node<T> node) {
    if (node != null) {
      traverseInOrder(node.getLeft());
      System.out.printf("%s ", node.getData());
      traverseInOrder(node.getRight());
    }
  }

  // método auxiliar - preOrder
  private void traversePreOrder(Node<T> node) {
    if (node != null) {
      System.out.printf("%s ", node.getData());
      traversePreOrder(node.getLeft());
      traversePreOrder(node.getRight());
    }
  }

  // método auxiliar - postOrder
  private void traversePostOrder(Node<T> node) {
    if (node != null) {
      traversePostOrder(node.getLeft());
      traversePostOrder(node.getRight());
      System.out.printf("%s ", node.getData());
    }
  }

} // BinaryTree
