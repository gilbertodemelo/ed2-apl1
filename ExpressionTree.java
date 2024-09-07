/**
 * ESTRUTURA DE DADOS II
 * TURMA 04P11
 * APL 1
 * ALAN MENIUK GLEIZER - 10416804
 * CAIO VINICIUS CORSINI FILHO - 10342005
 * GILBERTO DE MELO JÚNIOR - 10419275
 * **/

 /* REFERÊNCIAS
  * Vídeo sobre expression trees: https://www.youtube.com/watch?v=qPoS1iM2JXY
  * Exemplos para construção posfixa: https://www.geeksforgeeks.org/expression-tree/ 
  * Discussão de problema similar no LeetCode: https://leetcode.ca/2020-04-14-1597-Build-Binary-Expression-Tree-From-Infix-Expression/#
  * Discussão 2: https://algo.monster/liteproblems/1597
  */

import java.util.List;
import java.util.Stack;

public class ExpressionTree {
    // attributes
    // private List<String> input; // sera que é necessário? acho que posso simplesmente passar pra função....
    private Stack<OperatorNode> operatorStack;
    private Stack<Node> nodeStack;
    private BinaryTree expressionTree;

    // constructors
    public ExpressionTree() {
        operatorStack = new Stack<>();
        nodeStack = new Stack<>();
    }
    
    // retorna a prioridade de cada operador
    int priority (char operator) {
        if (operator == '(' || operator == ')') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 2;
        } else if (operator == '*' || operator == '/') {
            return 3;
        } else {
            return 0;
        }
    }

    // função será chamada sempre que for necessário criar uma subárvore COMPLETA (um operador root com dois operandos leaf)
    // função sem parâmetros ou retorno. elementos são desempilhados dos stacks e referência para a raíz da subárvore é reempilhada
    private void createSubtree() {
        Node tempNode1 = nodeStack.pop();
        Node tempNode2 = nodeStack.pop();
        Node tempNodeRoot = operatorStack.pop();

        tempNodeRoot.setRight(tempNode1);
        tempNodeRoot.setLeft(tempNode2);

        // empilhar a raíz da nova subárvore
        nodeStack.push(tempNodeRoot);
    }

    // função responsável pela construção da árvore de expressão a partir de uma lista de Strings (retornada pelo VeryBasicTokenizer)
    public BinaryTree expressionTreeBuilder(List<String> input) {
        // percorrer a expressao
        for (int i = 0; i < input.size(); i++) {
            // pegar elemento i da lista (acesso com .get(), não com notação de array [i]!)
            String currElementStr = input.get(i);
            
            // transformar elemento em array de char para análise fácil
            char[] currElement = currElementStr.toCharArray();

            // avaliar do que se trata o elemento atual
            if (Character.isDigit(currElement[0])) {
                // se o primeiro elemento de currElement for um numero, então sabemos que currElementStr todo será um numero (int ou float, de tamanho n)
                // converter currelementStr para float, criar um novo Node com o dado...
                NumberNode tempNumNode = new NumberNode(Float.parseFloat(currElementStr), null, null, null);
                //  ... e empilhar no nodeStack
                nodeStack.push(tempNumNode);
            } else {
                // se não for um número, então é um operador. criar um NodeOperator...
                OperatorNode tempOpNode = new OperatorNode(currElementStr, null, null, null);

                // ... e empilhar
                if (currElement[0] == '(') operatorStack.push(tempOpNode);

                else if (currElement[0] == ')') {
                    // se for um fechamento de parenteses, precisamos criar uma subárvore que comporte todas as operaçoẽs, já lidas, dentro desse grupo de parenteses
                    while (!operatorStack.peek().getData().equals("(")) { // enquanto o elemento no topo da stack não é a abertura do ()
                        // desempilhar dois Nodes e um operador e criar uma subárvore
                        createSubtree();
                    }

                    // quando sairmos do while loop, o top da stack é a abertura do parenteses, podemos pop e descartar
                    operatorStack.pop();
                }

                else {
                    // se current não for parênteses ou número, é operador
                    // comparar prioridade
                    while (!operatorStack.isEmpty() && priority(currElement[0]) <= priority((operatorStack.peek().getData()).charAt(0))) {
                        // se current tiver prioridade <= topo da stack, criar subarvore
                        createSubtree();
                    }
                    // empilhar o operador após as subárvores serem criadas, se necessário
                    operatorStack.push(tempOpNode);
                }

            }
        }
        // após a leitura da lista, remover o que restar nas stacks
        while (!operatorStack.isEmpty()) {
            createSubtree();
        }

        // só deve restar um Node no stack, que será a raíz da árvore final
        expressionTree = new BinaryTree(nodeStack.pop());

        return expressionTree;
    }
}
