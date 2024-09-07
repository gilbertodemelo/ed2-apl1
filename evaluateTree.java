/**
 * ESTRUTURA DE DADOS II
 * TURMA 04P11
 * APL 1
 * ALAN MENIUK GLEIZER - 10416804
 * CAIO VINICIUS CORSINI FILHO - 10342005
 * GILBERTO DE MELO JÚNIOR - 10419275
 * **/

import java.util.Stack;

public class evaluateTree{

    // Metodo auxiliar para o calculo durante a travessia em pos ordem
    static void traversePostOrderEva2(Node currentNode, Stack operatorStack){

        if(currentNode == null) return; // Caso base

        // Vai para proximo no ou a esquerda ou a direita
        traversePostOrderEva2(currentNode.getLeft(), operatorStack);
        traversePostOrderEva2(currentNode.getRight(), operatorStack);

        // No caso do no atual ser um operador, dois numeros são retirados da pilha e passam pela respectiva operacao
        if(currentNode instanceof OperatorNode){
            String currentData = (String) currentNode.getData();
            float resultado = 0;
            if (currentData.equals("+")){
                resultado = (float)operatorStack.pop() + (float)operatorStack.pop();
            }
            if (currentData.equals("*")){
                resultado = (float)operatorStack.pop() * (float)operatorStack.pop();
            }
            if (currentData.equals("-")){
                float aux = (float)operatorStack.pop(); // Precisa do aux para a subtracao pois a ordem importa
                resultado = (float)operatorStack.pop() - aux;
            }
            if (currentData.equals("/")){
                float aux = (float)operatorStack.pop(); // Precisa do aux para a divisao pois a ordem importa
                resultado = (float)operatorStack.pop() / aux;
            }
            operatorStack.push(resultado); // Resultado da operacao eh colocado no topo da pilha

        // Se o dado do no atual for apenas um numero ele eh apenas colocado no topo da pilha
        } else if (currentNode instanceof NumberNode){
            float currentData = (float) currentNode.getData();
            operatorStack.push(currentData);
        }
    }


    // Metodo para atravessar pela arvore em pos ordem e fazer o calculo
    static void traversePostOrderEva1(BinaryTree treeToTraverse, Stack operatorStack){
        traversePostOrderEva2(treeToTraverse.getRoot(), operatorStack);
    }

    // Utiliza a arvore de expressao e uma pilha para fazer o calculo da expressao
    // A pilha eh generica para poder armazenar tanto Strings quanto floats
    // O que sobra no final, no topo da pilha, eh o resultado e eh retornado ao usuario
    static double evaluate(BinaryTree treeToEvaluate){
        Stack expressionStack = new Stack<>();
        traversePostOrderEva1(treeToEvaluate, expressionStack);
        return (float)expressionStack.pop();
    }
}