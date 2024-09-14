import java.util.Stack;

public class Validator {
    // attributes 
    String input;
    char[] inputArr;
    Boolean validExp;
    Stack<Character> parenthesisStack;

    public Validator(String expression) {
        this.input = expression;
        this.inputArr = input.toCharArray();
        this.validExp = false;
        this.parenthesisStack = new Stack<>();
    }

    public Boolean getValidStatus() {
        return validExp;
    }

    private Boolean isValidOperator(char current) {
        if (current == '+' || current == '-' || current == '*' || current == '/') return true;
        else return false;
    }

    public Boolean validate() {
        for (int i = 0; i < input.length(); i++) {
            // validar operadores seguidos
            // exceção é se o segundo operador for um '-', que é válido!!!
            if ((i != 0) && isValidOperator(inputArr[i]) && isValidOperator(inputArr[i-1]) && (inputArr[i] != '-')) {
                System.out.println("Operadores seguidos detectados!");
                return false;
            };
            // validar pontos seguidos
            if ((i != 0) && (inputArr[i] == '.') && (inputArr[i-1] == '.')) {
                System.out.println("Pontos decimais seguidos detectados!");
                return false;
            };
            // validar abertura e fechamento de parenteses
            if (inputArr[i] == '(') parenthesisStack.add(inputArr[i]); // empilhar abertura
            else if (inputArr[i] == ')') {
                if (parenthesisStack.isEmpty()) {
                    System.out.println("Parênteses desbalenceados!");
                    return false; // se encontramos fechamento e stack está vazio, inválido
                }
                else parenthesisStack.pop(); // senão, pop!
            }
        }
        if (!parenthesisStack.isEmpty()) {
            System.out.println("Parênteses desbalenceados!");
            return false;

        }
        this.validExp = true;
        return true;
    }

}