public class RedBlackTree {
  private Node root;

  // Métodos de inserção, remoção e busca aqui
  public void insert(int data) {
    root = insert(root, data);
    root.isRed = false; // A raiz sempre será preta
  }

  private Node insert(Node node, int data) {
    if (node == null) {
      return new Node(data);
    }

    if (data < node.data) {
      node.left = insert(node.left, data);
    } else if (data > node.data) {
      node.right = insert(node.right, data);
    } else {
      // Dados iguais não são permitidos (depende do seu requisito)
      return node;
    }

    // Correção das propriedades da árvore Rubro-Negra
    if (isRed(node.right) && !isRed(node.left)) {
      node = rotateLeft(node);
    }
    if (isRed(node.left) && isRed(node.left.left)) {
      node = rotateRight(node);
    }
    if (isRed(node.left) && isRed(node.right)) {
      flipColors(node);
    }

    // Atualiza a altura do nó
    node.height = Math.max(height(node.left), height(node.right)) + 1;

    return node;
  }

  public void remove(int data) {
    root = remove(root, data);
    if (root != null) {
      root.isRed = false;
    }
  }

  private Node remove(Node node, int data) {
    if (node == null) {
      return null;
    }

    if (data < node.data) {
      node.left = remove(node.left, data);
    } else if (data > node.data) {
      node.right = remove(node.right, data);
    } else {
      // Nó a ser removido encontrado

      // Caso 1: Nó com pelo menos um filho é marcado para remoção
      if (node.left == null) {
        return node.right;
      } else if (node.right == null) {
        return node.left;
      }

      // Caso 2: Nó com dois filhos, encontra o sucessor e o remove
      node.data = findMin(node.right).data;
      node.right = remove(node.right, node.data);
    }

    // Correção das propriedades da árvore Rubro-Negra após a remoção
    if (isRed(node.right) && !isRed(node.left)) {
      node = rotateLeft(node);
    }
    if (isRed(node.left) && isRed(node.left.left)) {
      node = rotateRight(node);
    }
    if (isRed(node.left) && isRed(node.right)) {
      flipColors(node);
    }

    // Atualiza a altura do nó
    node.height = Math.max(height(node.left), height(node.right)) + 1;

    return node;
  }

  private Node findMin(Node node) {
    while (node.left != null) {
      node = node.left;
    }
    return node;
  }

  public boolean search(int data) {
    return search(root, data);
  }

  private boolean search(Node node, int data) {
    if (node == null) {
      return false;
    }

    if (data < node.data) {
      return search(node.left, data);
    } else if (data > node.data) {
      return search(node.right, data);
    } else {
      return true;
    }
  }

  public int countOccurrences(int number) {
    return countOccurrences(root, number);
  }

  private int countOccurrences(Node node, int number) {
    if (node == null) {
      return 0;
    }

    int count = 0;
    if (node.data == number) {
      count++;
    }

    count += countOccurrences(node.left, number);
    count += countOccurrences(node.right, number);

    return count;
  }

  private boolean isRed(Node node) {
    if (node == null) {
      return false;
    }
    return node.isRed;
  }

  private Node rotateLeft(Node h) {
    Node x = h.right;
    h.right = x.left;
    x.left = h;
    x.isRed = h.isRed;
    h.isRed = true;
    h.height = Math.max(height(h.left), height(h.right)) + 1;
    x.height = Math.max(height(x.left), height(x.right)) + 1;
    return x;
  }

  private Node rotateRight(Node h) {
    Node x = h.left;
    h.left = x.right;
    x.right = h;
    x.isRed = h.isRed;
    h.isRed = true;
    h.height = Math.max(height(h.left), height(h.right)) + 1;
    x.height = Math.max(height(x.left), height(x.right)) + 1;
    return x;
  }

  private void flipColors(Node h) {
    h.isRed = !h.isRed;
    h.left.isRed = !h.left.isRed;
    h.right.isRed = !h.right.isRed;
  }

  private int height(Node node) {
    return (node == null) ? 0 : node.height;
  }
}
