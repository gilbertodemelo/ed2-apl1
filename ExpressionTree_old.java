import java.util.List;
import java.util.Stack;

public class ExpressionTree {
    // attributes
    // private List<String> input; // sera que é necessário? acho que posso simplesmente passar pra função....
    private Stack<OperatorNode> operatorStack;
    private Stack<NumberNode> numberStack;
    private Stack<Node> nodeStack;
    private BinaryTree expressionTree;

    // construtores
    public ExpressionTree() {
        operatorStack = new Stack<>();
        numberStack = new Stack<>();
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

    private void createSubtree() {
        Node tempNode1 = nodeStack.pop();
        Node tempNode2 = nodeStack.pop();
        Node tempNodeRoot = operatorStack.pop();

        tempNodeRoot.setRight(tempNode1);
        tempNodeRoot.setLeft(tempNode2);

        // empilhar a raíz da nova subárvore
        nodeStack.push(tempNodeRoot);
    }

    public BinaryTree expressionTreeBuilder(List<String> input) {
        // percorrer a expressao
        for (int i = 0; i < input.size(); i++) {
            // pegar elemento i da lista
            String currElementStr = input.get(i);
            
            // transformar elemento em array de char
            char[] currElement = currElementStr.toCharArray();

            // avaliar do que se trata o elemento atual
            if (Character.isDigit(currElement[0])) {
                // se o primeiro elemento de currElement for um numero, então currElementStr todo será um numero (de tamanho n)
                // converter currelementStr para float
                NumberNode tempNumNode = new NumberNode(Float.parseFloat(currElementStr), null, null, null);

                //  e empilhar no nodeStack
                nodeStack.push(tempNumNode);
            } else {
                // se não for um número, então é um operador
                OperatorNode tempOpNode = new OperatorNode(currElementStr, null, null, null);

                if (currElement[0] == '(') operatorStack.push(tempOpNode);

                else if (currElement[0] == ')') {
                    // se for um fechamento de parenteses, precisamos criar uma subárvore que comporte todas as operaçoẽs, já lidas, dentro desse grupo de parenteses
                    while (!operatorStack.peek().getData().equals("(")) { // enquanto o elemento no topo da stack não é a abertura do ()
                        // desempilhar dois Nodes e um operador e criar uma subárvore
                        createSubtree();
                    }

                    // quando sairmos do while loop, o top da steck é a abertura do parenteses, podemos pop e descartar
                    operatorStack.pop();
                }

                else {
                    // se current não for parênteses ou número, é operador
                    // comparar prioridade
                    if (!operatorStack.isEmpty() && priority(currElement[0]) <= priority((operatorStack.peek().getData()).charAt(0))) {
                        // se current tiver prioridade <= topo da stack, criar subarvore
                        createSubtree();
                    } else {
                        // caso contrário, empilhar
                        operatorStack.push(tempOpNode);  // <---- PROBLEMA PARECE ESTAR AQUI !!!! (será que precisa do elso? testar)
                    }
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