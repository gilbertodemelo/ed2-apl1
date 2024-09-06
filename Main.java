public class Main {

    public static void main(String[] args) {

        OperatorNode oNode = new OperatorNode("+", null, null, null);
        System.out.println(oNode.getData());

        NumberNode nNode = new NumberNode(5, null, null, null);
        System.out.println(nNode.getData());
    }

} // Main