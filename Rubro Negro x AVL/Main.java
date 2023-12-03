import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Main {
  public static void main(String[] args) {
    // Leitura do arquivo e inserção de dados nas árvores
    RedBlackTree redBlackTree = new RedBlackTree();
    AVLTree avlTree = new AVLTree();

    // Marcar o tempo de execução para inserção dos dados
    long startTime = System.currentTimeMillis();

    // Adicione aqui a leitura do arquivo e inserção nas árvores
    try {
      BufferedReader reader = new BufferedReader(new FileReader("E:\\Faculdade\\4º Período\\Estruturas de Dados II\\Rubro Negro x AVL\\dados100_mil.txt"));
      String line;

      while ((line = reader.readLine()) != null) {
        int number = Integer.parseInt(line.trim());
        redBlackTree.insert(number);
        avlTree.insert(number);
      }

      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    long endTime = System.currentTimeMillis();
    System.out.println("Tempo de inserção: " + (endTime - startTime) + " ms");

    // Sorteio aleatório de 50.000 números
    Random random = new Random();
    for (int i = 0; i < 50000; i++) {
      int randomNumber = random.nextInt(19999) - 9999; // Entre -9999 e 9999

      if (randomNumber % 3 == 0) {
        // Inserir na árvore
        redBlackTree.insert(randomNumber);
        avlTree.insert(randomNumber);
      } else if (randomNumber % 5 == 0) {
        // Remover da árvore
        redBlackTree.remove(randomNumber);
        avlTree.remove(randomNumber);
      } else {
        // Contar a ocorrência na árvore
        redBlackTree.countOccurrences(randomNumber);
        avlTree.countOccurrences(randomNumber);
      }
    }

    // Marcar o tempo de execução para as operações acima
    startTime = System.currentTimeMillis();

    // Adicione aqui as operações de sorteio aleatório

    endTime = System.currentTimeMillis();
    System.out.println("Tempo das operações aleatórias: " + (endTime - startTime) + " ms");

    // Chamada para comparar o desempenho
    comparePerformance(redBlackTree, avlTree);
  }

  private static void comparePerformance(RedBlackTree redBlackTree, AVLTree avlTree) {
    // Comparar o desempenho em operações de busca
    int searchNumber = 42; // Número a ser buscado
    long redBlackSearchTime = measureSearchTime(redBlackTree, searchNumber);
    long avlSearchTime = measureSearchTime(avlTree, searchNumber);

    System.out.println("Tempo de busca na árvore Rubro-Negra: " + redBlackSearchTime + " ms");
    System.out.println("Tempo de busca na árvore AVL: " + avlSearchTime + " ms");

    // Comparar o desempenho em operações de contar ocorrências
    int countOccurrencesNumber = 42; // Número para contar ocorrências
    long redBlackCountTime = measureCountTime(redBlackTree, countOccurrencesNumber);
    long avlCountTime = measureCountTime(avlTree, countOccurrencesNumber);

    System.out.println("Tempo de contar ocorrências na árvore Rubro-Negra: " + redBlackCountTime + " ms");
    System.out.println("Tempo de contar ocorrências na árvore AVL: " + avlCountTime + " ms");
  }

  private static long measureSearchTime(RedBlackTree tree, int number) {
    long startTime = System.currentTimeMillis();
    tree.search(number);
    long endTime = System.currentTimeMillis();
    return endTime - startTime;
  }

  private static long measureCountTime(RedBlackTree tree, int number) {
    long startTime = System.currentTimeMillis();
    tree.countOccurrences(number);
    long endTime = System.currentTimeMillis();
    return endTime - startTime;
  }

  private static long measureSearchTime(AVLTree tree, int number) {
    long startTime = System.currentTimeMillis();
    tree.search(number);
    long endTime = System.currentTimeMillis();
    return endTime - startTime;
  }

  private static long measureCountTime(AVLTree tree, int number) {
    long startTime = System.currentTimeMillis();
    tree.countOccurrences(number);
    long endTime = System.currentTimeMillis();
    return endTime - startTime;
  }
}
