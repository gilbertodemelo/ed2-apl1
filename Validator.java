import java.util.Stack;

public class Validator {
    // attributes 
    String input;
    char[] inputArr;
    Boolean validExp;
    Stack<Character> parenthesisStack;

    // constructor
    // String de input é convertida em charArray
    public Validator(String expression) {
        this.input = expression;
        this.inputArr = input.toCharArray();
        this.validExp = false;
        this.parenthesisStack = new Stack<>();
    }

    // getters
    public Boolean getValidStatus() {
        return validExp;
    }

    // methods

    private Boolean isValidOperator(char current) {
        if (current == '+' || current == '-' || current == '*' || current == '/') return true;
        else return false;
    }

    private Boolean isValidOther(char current) {
        if (current == '(' || current == ')' || current == ' ' || current == '/' || current == '\n' || current == '\0') return true;
        else return false;
    }

    // validar se não existem dois numeros separados por um espaço (não aceitaremos)
    // lógica é complexa então separei da função principal
    private Boolean checkForNumbersSeparatedBySpaces() {
        // control variables
        Boolean lastCharWasNumber = false;
        Boolean lastCharWasOperator = false;
        Boolean hasSpaceBetween = false;

        for (int i = 0; i < input.length(); i++) {
            char currChar = inputArr[i];
        
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
        }
        
        // se for valido, agora sim, vamos tirar os espaços para facilitar na tokenizaçao e detecção de operadores seguidos
        input = input.replaceAll(" ", "");
        // e gerar char array novamente, sem espaços!
        this.inputArr = input.toCharArray();
        return true;
    }

    public Boolean validate() {
        
        if (!checkForNumbersSeparatedBySpaces()) {
            return false;
        }

        // percorrer a array
        for (int i = 0; i < input.length(); i++) {
            char currChar = inputArr[i];

            // validar se é um caractere valido
            if (!isValidOperator(currChar) && !isValidOther(currChar) && !Character.isDigit(currChar)) {
                System.out.println("Caractére inválido detectado!");
                return false;
            }

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

            // validar numero antes de parenteses (TODO transformar em multiplicação!)
            if (i != 0 && currChar == '(' && Character.isDigit(inputArr[i-1])) {
                System.out.println("Por favor, use * para multiplicações.\nNumeros adjacentes a parênteses não são suportados.\n");
                return false;
            }

            // validar operador antes de fechamento de parenteses
            if (currChar == ')' && isValidOperator(inputArr[i-1])) {
                System.out.println("Sequencia inválida detectada: operador seguido por fechamento de parênteses.\n");
                return false;
            }

            // validar operador como ultimo caractere
            if (i == (input.length()-1) && isValidOperator(inputArr[i])) {
                System.out.println("A expressão não pode terminar com um operador.\n");
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
        // System.out.println(inputArr); // PARA DEBUG
        return true;
    }

}