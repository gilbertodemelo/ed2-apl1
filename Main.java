import java.util.List;

/**
 * ESTRUTURA DE DADOS II
 * TURMA 04P11
 * APL 1
 * ALAN MENIUK GLEIZER - 10416804
 * CAIO VINICIUS CORSINI FILHO - 10342005
 * GILBERTO DE MELO JÚNIOR - 10419275
 * **/

public class Main {

    public static void main(String[] args) {

        /* testes Gilberto
        OperatorNode oNode = new OperatorNode("+", null, null, null);
        System.out.println(oNode.getData());

        NumberNode nNode = new NumberNode(5, null, null, null);
        System.out.println(nNode.getData());

        NumberNode nNode2 = new NumberNode(3.14, null, null, null);
        System.out.println(nNode2.getData());
         */

         // testes Alan tokenizer
         
        String teste = "(3 + 6)*(4.5 -1)+  5";
        VeryBasicTokenizer vbt = new VeryBasicTokenizer(teste);
        List<String> tokens = vbt.tokenize();
        for (int i = 0; i < tokens.size(); ++i) {
           //System.out.println("Token[" + i + "]: " + tokens.get(i));
        }

        ExpressionTree etree = new ExpressionTree();
        BinaryTree arvore = etree.expressionTreeBuilder(tokens);
        if (arvore != null) {
            arvore.inOrderTraversal();
        } else {
            System.out.println("Failed to build the expression tree.");
        }
    

}
} // Main