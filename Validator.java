import java.util.Stack;

public class Validator {
    // attributes 
    String input;
    char[] inputArr;
    Boolean validExp;
    Stack<Character> parenthesisStack;

    public Validator(String expression) {
        this.input = expression;
        // input.replaceAll("\\s+",""); // remover espacos
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

    private Boolean isValidOther(char current) {
        if (current == '(' || current == ')' || current == ' ' || current == '/' || current == '\n' || current == '\0') return true;
        else return false;
    }

    public Boolean validate() {
        // control variables
        Boolean lastCharWasNumber = false;
        Boolean lastCharWasOperator = false;
        Boolean hasSpaceBetween = false;

        // loop through the char array
        for (int i = 0; i < input.length(); i++) {
            char currChar = inputArr[i];
            // validar se é um caractere valido
            if (!isValidOperator(currChar) && !isValidOther(currChar) && !Character.isDigit(currChar)) {
                System.out.println("Caractére inválido detectado!");
                return false;
            }
            
            // validar se não existem dois numeros separados por um espaço (não aceitaremos)
            if (currChar ==  ' ' && lastCharWasNumber) {
                hasSpaceBetween = true;
            }
            
            if (Character.isDigit(currChar)) {
                if (hasSpaceBetween) {
                    System.out.println("Números não podem ser separados por espaços.");
                    return false;
                }
                lastCharWasNumber = true;
                lastCharWasOperator = false;
                hasSpaceBetween = false;
            } else lastCharWasNumber = false;

            if (isValidOperator(currChar)) {
                lastCharWasOperator = true;
                lastCharWasNumber = false;
                hasSpaceBetween = false;
            } else lastCharWasOperator = false;

            // validar se não existem operadores seguidos
            // exceção é se o segundo operador for um '-', que é válido agora que suportamos operador unário!!!
            if ((i != 0) && isValidOperator(currChar) && isValidOperator(inputArr[i-1]) && (currChar != '-')) {
                System.out.println("Operadores seguidos detectados!");
                return false;
            };
            
            // validar se não existem pontos seguidos
            if ((i != 0) && (currChar == '.') && (inputArr[i-1] == '.')) {
                System.out.println("Pontos decimais seguidos detectados!");
                return false;
            };

            // validar numero antes de parenteses (transformar em multiplicação!)
            if (i != 0 && currChar == '(' && Character.isDigit(inputArr[i-1])) {
                System.out.println("Por favor, use * para multiplicações.\nNumeros adjacentes a parênteses não são suportados.\n");
                return false;
            }
            
            // validar balanceamento de parenteses
            if (currChar == '(') parenthesisStack.add(currChar); // empilhar abertura
            else if (currChar == ')') {
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