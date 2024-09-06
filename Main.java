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

        OperatorNode oNode = new OperatorNode("+", null, null, null);
        System.out.println(oNode.getData());

        NumberNode nNode = new NumberNode(5, null, null, null);
        System.out.println(nNode.getData());

        NumberNode nNode2 = new NumberNode(3.14, null, null, null);
        System.out.println(nNode2.getData());

    }

} // Main