/**
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
public class VeryBasicTokenizer {
    private char[] input;
    private int index;

    public VeryBasicTokenizer(String str) {
        input = str.toCharArray();
        index = 0;
    }

    // Avança para o próximo caractere e retorna seu valor.
    // O retorno '\0' quando chegou no final da string.
    private char getNextChar() {
        if (index >= input.length) { return '\0'; }
        return input[index++];
    }

    // Separa a string em tokens e retorna uma lista de strings,
    // sendo que cada string é um token reconhecido pelo método.
    public List<String> tokenize() {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char currChar = getNextChar();

        boolean isTokenizing = true;
        while (isTokenizing) {
            // Ignora espaços em branco.
            while (Character.isWhitespace(currChar)) {
                currChar = getNextChar();
            }

            if (Character.isDigit(currChar)) { // Reconhece um número (inteiro ou float)
                sb.setLength(0);
                Boolean hasDecimalPoint = false;
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
                System.out.println("Chegou ao final da string.");
                isTokenizing = false;

            } else {
                System.out.println("Token não reconhecido: " + currChar);
                isTokenizing = false;
            }
        }

        //System.out.println("Encerrando...\n");
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
