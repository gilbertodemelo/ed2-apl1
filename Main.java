/**
 * ESTRUTURA DE DADOS II
 * TURMA 04P11
 * APL 1
 * ALAN MENIUK GLEIZER - 10416804
 * CAIO VINICIUS CORSINI FILHO - 10342005
 * GILBERTO DE MELO JÚNIOR - 10419275
 * **/

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("---CALCULADORA DE EXPRESSOES---\n");

        // Declaracao das variaveis
        int option = 0;
        Scanner inputUser = new Scanner(System.in);
        Scanner inputExpressao = new Scanner(System.in);
        String expressao = "nada";
        ExpressionTree etree = new ExpressionTree();
        BinaryTree arvore = null;
        
        // Loop de uso do programa
        while(option != 5){

            // Apresentacao do menu e escolha de opcao
            System.out.println("1. Entrada da expressao aritmetica.");
            System.out.println("2. Criacao da árvore binaria de expressao aritmetica.");
            System.out.println("3. Exibição da árvore binaria de expressao aritmetica.");
            System.out.println("4. Calculo da expressao.");
            System.out.println("5. Encerramento do programa.");
            System.out.printf("Escolha opcao desejada: ");
            option = inputUser.nextInt();

            // Caso escolha opcao invalida
            if(option < 1 || option > 5) System.out.println("\nPor favor, selecionar entre 1 e 5.\n");

            // Input de expressao
            if(option == 1){
                System.out.printf("\nDigitar expressao: ");
                expressao = inputExpressao.nextLine();
                System.out.printf("Expressao digitada: %s\n\n", expressao);
            }

            // Geracao da arvore
            if(option == 2){
                if(expressao == "nada"){
                    System.out.println("\nFavor gerar uma expressao antes de selecionar esta opcao.\n");
                } else{
                    VeryBasicTokenizer vbt = new VeryBasicTokenizer(expressao);
                    List<String> tokens = vbt.tokenize();
                    arvore = etree.expressionTreeBuilder(tokens);
                    System.out.println("\nArvore construida!!!\n");
                }
            }

            // Impressao da arvore nos 3 modos
            if(option == 3){
                if(arvore == null){
                    System.out.println("\nFavor gerar uma arvore antes de selecionar esta opcao.\n");
                } else{
                    System.out.printf("\nPre ordem: ");
                    arvore.preOrderTraversal();
                    System.out.printf("\nEm ordem: ");
                    arvore.inOrderTraversal();
                    System.out.printf("\nPos ordem: ");
                    arvore.postOrderTraversal();
                    System.out.printf("\n\n");
                }
            }

            // Calculo da arvore
            if(option == 4){
                if(arvore == null){
                    System.out.println("\nFavor gerar uma arvore antes de selecionar esta opcao.\n");
                } else{
                    System.out.printf("\nResultado: %.2f\n\n", evaluateTree.evaluate(arvore));
                }
            }
        }

        // Fechando os scanners
        inputUser.close();
        inputExpressao.close();
    }
}