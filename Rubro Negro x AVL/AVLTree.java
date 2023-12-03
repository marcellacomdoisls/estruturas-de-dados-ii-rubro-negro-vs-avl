public class AVLTree {
  private Node root;

  // Métodos de inserção, remoção e busca aqui
  public void insert(int data) {
    root = insert(root, data);
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

    // Atualiza a altura do nó atual
    node.height = Math.max(height(node.left), height(node.right)) + 1;

    // Verifica o balanceamento do nó
    int balance = getBalance(node);

    // Casos de desequilíbrio

    // Caso de desequilíbrio à esquerda
    if (balance > 1 && data < node.left.data) {
      return rotateRight(node);
    }

    // Caso de desequilíbrio à direita
    if (balance < -1 && data > node.right.data) {
      return rotateLeft(node);
    }

    // Caso de desequilíbrio à esquerda-direita
    if (balance > 1 && data > node.left.data) {
      node.left = rotateLeft(node.left);
      return rotateRight(node);
    }

    // Caso de desequilíbrio à direita-esquerda
    if (balance < -1 && data < node.right.data) {
      node.right = rotateRight(node.right);
      return rotateLeft(node);
    }

    return node;
  }

  public void remove(int data) {
    root = remove(root, data);
  }

  private Node remove(Node node, int data) {
    // Caso base: nó nulo
    if (node == null) {
      return null;
    }

    // Procura o nó a ser removido
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

    // Atualiza a altura do nó atual
    node.height = Math.max(height(node.left), height(node.right)) + 1;

    // Verifica o balanceamento do nó
    int balance = getBalance(node);

    // Casos de desequilíbrio

    // Caso de desequilíbrio à esquerda
    if (balance > 1 && getBalance(node.left) >= 0) {
      return rotateRight(node);
    }

    // Caso de desequilíbrio à direita
    if (balance < -1 && getBalance(node.right) <= 0) {
      return rotateLeft(node);
    }

    // Caso de desequilíbrio à esquerda-direita
    if (balance > 1 && getBalance(node.left) < 0) {
      node.left = rotateLeft(node.left);
      return rotateRight(node);
    }

    // Caso de desequilíbrio à direita-esquerda
    if (balance < -1 && getBalance(node.right) > 0) {
      node.right = rotateRight(node.right);
      return rotateLeft(node);
    }

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

  // Métodos auxiliares para balanceamento
  private int height(Node node) {
    return (node == null) ? 0 : node.height;
  }

  private int getBalance(Node node) {
    return (node == null) ? 0 : height(node.left) - height(node.right);
  }

  private Node rotateLeft(Node x) {
    Node y = x.right;
    Node T2 = y.left;

    y.left = x;
    x.right = T2;

    // Atualiza alturas
    x.height = Math.max(height(x.left), height(x.right)) + 1;
    y.height = Math.max(height(y.left), height(y.right)) + 1;

    return y;
  }

  private Node rotateRight(Node y) {
    Node x = y.left;
    Node T2 = x.right;

    x.right = y;
    y.left = T2;

    // Atualiza alturas
    y.height = Math.max(height(y.left), height(y.right)) + 1;
    x.height = Math.max(height(x.left), height(x.right)) + 1;

    return x;
  }
}
