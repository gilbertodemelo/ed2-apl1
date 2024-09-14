/*
 * ESTRUTURA DE DADOS II
 * TURMA 04P11
 * APL 1
 * ALAN MENIUK GLEIZER - 10416804
 * CAIO VINICIUS CORSINI FILHO - 10342005
 * GILBERTO DE MELO JÚNIOR - 10419275
 * **/

/* REFERÊNCIAS
 * Adaptação da classe disponibilizada nas orientações
 */

import java.util.ArrayList;
import java.util.List;

// função tokenize recebe uma String e retorna uma lista de Strings com os tokens reconhecidos
// função original também validava se existia algum caractere inválido na String.
// Mantive essa funcionalidade, mas existe uma classe Validator
public class VeryBasicTokenizer {
    // attributes
    private char[] input;
    private int index;
    private Boolean expValida;

    // constructors
    public VeryBasicTokenizer(String str) {
        input = str.toCharArray();
        index = 0;
        expValida = false; // expressão é inválida até ser tokenizada
    }

    // getters
    public Boolean expValida() {
        return this.expValida;
    }


    // methods 

    // Avança para o próximo caractere e retorna seu valor.
    // O retorno '\0' quando chegou no final da string.
    private char getNextChar() {
        if (index >= input.length) { return '\0'; }
        return input[index++];
    }

    // retorna se o char é um operador válido
    private Boolean isValidOperator(char current) {
        if (current == '+' || current == '-' || current == '*' || current == '/') return true;
        else return false;
    }

    // Separa a string em tokens e retorna uma lista de strings,
    // sendo que cada string é um token reconhecido pelo método.
    public List<String> tokenize() {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char currChar = getNextChar();
        expValida = true; // ao tokenizar, expressão passa para válida (e vira false se encontrarmos algum erro)
        boolean isTokenizing = true;
        Boolean hasDecimalPoint = false;

        while (isTokenizing) {
            // System.out.println("Tokenizador! Curr: " + currChar + ", index: " + index); // PARA DEBUG

            // tokeniza numeros negativos (operador unário)
            // se atual == -    E (é o primeiro char) OU (vem depois de um parenteses) OU (vem depois de um operador)
            if (currChar == '-' && (tokens.isEmpty() || tokens.get(tokens.size() - 1).equals("(") || isValidOperator(tokens.get(tokens.size() - 1).charAt(0)))) {
                sb.setLength(0);
                hasDecimalPoint = false;
                sb.append(currChar);
                currChar = getNextChar();
                while (Character.isDigit(currChar) || (currChar == '.' && !hasDecimalPoint)) { // Permite um único ponto decimal
                    if (currChar == '.') {
                        hasDecimalPoint = true;  
                    }
                    sb.append(currChar);
                    currChar = getNextChar();
                }
                tokens.add(sb.toString());

            // Reconhece um número (inteiro ou float)
            } else if (Character.isDigit(currChar)) {
                sb.setLength(0);
                hasDecimalPoint = false;
                while (Character.isDigit(currChar) || (currChar == '.' && !hasDecimalPoint)) { // Permite um único ponto decimal
                    if (currChar == '.') {
                        hasDecimalPoint = true;  
                    }
                    sb.append(currChar);
                    currChar = getNextChar();
                }
                tokens.add(sb.toString());

            } else if (currChar == '+') { // Reconhece símbolo +
                tokens.add("+");
                currChar = getNextChar();

            } else if (currChar == '*') { // Reconhece símbolo *
                tokens.add("*");
                currChar = getNextChar();

            } else if (currChar == '-') { // Reconhece símbolo -
                tokens.add("-");
                currChar = getNextChar();

            } else if (currChar == '/') { // Reconhece símbolo /
                tokens.add("/");
                currChar = getNextChar();

            } else if (currChar == ')') { // Reconhece símbolo (
                tokens.add(")");
                currChar = getNextChar();

            } else if (currChar == '(') { // Reconhece símbolo )
                tokens.add("(");
                currChar = getNextChar();

            } else if (currChar == '\0') {
                //System.out.println("Chegou ao final da string.");
                isTokenizing = false;

            } else {
                System.out.println("ERRO: Dígito " + currChar + " não suportado!\nTente novamente.\n");
                expValida = false;
                isTokenizing = false;
            }
        }

        if (!expValida) {
            sb = null;
            tokens = null;
            return null;
        }
        expValida = true;
        return tokens;
    }

    /*
    public static void main(String[] args) {
        VeryBasicTokenizer vbt = new VeryBasicTokenizer("25+    32       * 1  + A");

        List<String> tokens = vbt.tokenize();
        for (int i = 0; i < tokens.size(); ++i) {
            System.out.println("Token[" + i + "]: " + tokens.get(i));
        }
    }
    */
}
