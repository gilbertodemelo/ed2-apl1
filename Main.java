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
        String expressao = "";
        ExpressionTree etree = null;
        BinaryTree arvore = null;
        VeryBasicTokenizer vbt = null;
        List<String> tokens = null;
        Validator validador = null;

        // Loop de uso do programa
        while(option != 5){

            // Apresentacao do menu e escolha de opcao
            System.out.println("1. Entrada da expressao aritmetica.");
            System.out.println("2. Criacao da árvore binaria de expressao aritmetica.");
            System.out.println("3. Exibição da árvore binaria de expressao aritmetica.");
            System.out.println("4. Calculo da expressao.");
            System.out.println("5. Encerramento do programa.");
            System.out.printf("   Escolha opcao desejada: ");
            option = inputUser.nextInt();

            // Caso escolha opcao invalida
            if(option < 1 || option > 5) System.out.println("\nPor favor, selecionar entre 1 e 5.\n");

            // Input de expressao
            if(option == 1){
                // vamos ler uma nova exp, transfromar arvore em null para não "sobrar" em memoria
                etree = null;
                arvore = null;

                // ler expressão
                System.out.printf("\nDigitar expressao: ");
                expressao = inputExpressao.nextLine();

                // validar
                validador = new Validator(expressao);
                validador.validate();

                if (validador.getValidStatus()) {
                    // se é valida, tokenizar
                    vbt = new VeryBasicTokenizer(expressao);
                    tokens = vbt.tokenize();
                    System.out.printf("Expressao digitada: %s\n\n", expressao); // TODO versão deploy
                    // System.out.println("Expressão digitada: " + tokens); // versão debug
                } 
                else {
                    System.out.println("Expressão inválida. Tente novamente.\n");
                }
            }

            // Geracao da arvore
            if (option == 2){
                if(validador == null || !validador.getValidStatus() || tokens.isEmpty()){
                    System.out.println("\nFavor gerar uma expressão antes de selecionar esta opcao.\n");
                } else{
                    etree = new ExpressionTree();
                    arvore = etree.expressionTreeBuilder(tokens);
                    System.out.println("\nArvore construida!!!\n");
                }
            }

            // Impressao da arvore nos 3 modos
            if(option == 3){
                if(arvore == null || etree == null){
                    System.out.println("\nFavor gerar uma arvore antes de selecionar esta opção.\n");
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
                if(arvore == null || etree == null){
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