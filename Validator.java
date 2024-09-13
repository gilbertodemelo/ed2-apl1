public class Validator {
    // attributes 
    String input;
    char[] inputArr;
    Boolean validExp;

    public Validator(String expression) {
        this.input = expression;
        this.inputArr = input.toCharArray();
        this.validExp = false;
    }

    public Boolean getValidStatus() {
        return validExp;
    }

    private Boolean isValidOperator(char current) {
        if (current == '+' || current == '-' || current == '*' || current == '/') return true;
        else return false;
    }

    public Boolean validate() {
        int openings = 0, closings = 0;
        for (int i = 0; i < input.length(); i++) {
            // validar operadores seguidos
            if ((i != 0) && isValidOperator(inputArr[i]) && isValidOperator(inputArr[i-1])) {
                System.out.println("Operadores seguidos detectados!");
                return false;
            };
            // validar pontos seguidos
            if ((i != 0) && (inputArr[i] == '.') && (inputArr[i-1] == '.')) {
                System.out.println("Pontos decimais seguidos detectados!");
                return false;
            };
            // validar abertura e fechamento de parenteses
            if (inputArr[i] == '(') openings++;
            else if (inputArr[i] == ')') closings++;
        }
        if (openings != closings) {
            System.out.println("Parênteses desbalanceados!");
            return false;
        };
        return true;
    }

}