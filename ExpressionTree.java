import java.util.ArrayList;
import java.util.List;

public class ExpressionTree {

    // cria uma lista ligada de Nodes ao receber uma string
    public void stringToNodes(String expression) {
        
    }

    // retorna a prioridade de cada operador
    int priority (char operador) {
        if (operador == '+' || operador == '-') {
            return 1;
        } else if (operador == '*' || operador == '/') {
            return 2;
        } else {
            return 0;
        }
    }


}