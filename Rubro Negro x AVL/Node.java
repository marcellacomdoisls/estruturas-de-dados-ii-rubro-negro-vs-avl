public class Node {
  int data;
  Node left, right;
  int height;
  boolean isRed;

  Node(int data) {
    this.data = data;
    this.height = 1;
    this.isRed = true; // Para árvore Rubro-Negra
    this.left = this.right = null;
  }
}