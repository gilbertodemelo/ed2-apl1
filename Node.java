/**
 * ESTRUTURA DE DADOS II
 * TURMA 04P11
 * APL 1
 * ALAN MENIUK GLEIZER - 10416804
 * CAIO VINICIUS CORSINI FILHO - 10342005
 * GILBERTO DE MELO JÚNIOR - 10419275
 * *
 */

public class Node<T> {

  // atributos
  private T data;
  private Node<T> parent;
  private Node<T> left;
  private Node<T> right;

  // construtores
  public Node(T data, Node<T> parent, Node<T> left, Node<T> right) {
    this.data = data;
    this.parent = parent;
    this.left = left;
    this.right = right;
  }

  // setters
  public void setData(T data) { this.data = data; }
  public void setParent(Node<T> parent) { this.parent = parent; }
  public void setLeft(Node<T> left) { this.left = left; }
  public void setRight(Node<T> right) { this.right = right; }

  // getters
  public T getData() { return data; }
  public Node<T> getParent() { return parent; }
  public Node<T> getLeft() { return left; }
  public Node<T> getRight() { return right; }

  // outros métodos
  public boolean isRoot() { return parent == null; }
  public boolean isLeaf() { return (left == null) && (right == null); }

  /**
   * Returna o grau do nó
   * @return grau (int)
   */
  public int getDegree() {
    int grau = 0;
    if (!isLeaf()) {
      if (left != null)
        grau += 1;
      if (right != null)
        grau += 1;
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
    Node<T> currentNode = this;
    while (currentNode.getParent() != null) {
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
