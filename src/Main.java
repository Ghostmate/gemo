// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.Scanner;

import static java.lang.Integer.getInteger;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.toString;

public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Type expression in arabic or roman numbers with symbolic operator, for exit type \"quit\"\n");
        Scanner sc = new Scanner(System.in);
        while(true){
            if(sc.hasNextLine()){
                String input = sc.nextLine();
                if(input.toLowerCase().contains("quit")){
                    break;
                }
                System.out.printf("Answer: " + calc(input) + "\n");
                System.out.printf("Type expression:");
                //System.exit(-1);
                //calc(input);
            }
            try{
                Thread.sleep(50);
            } catch(Throwable e){
                System.out.println(e.toString());
            }
        }
        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
            // Press Shift+F9 to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Ctrl+F8.
            System.out.println("Good day to you!\n");

    }

    static int roman(String input) {
        try {
            int result = 0;
            input = input.strip();
            for (int i = 0; i < input.length(); ++i) {
                boolean b = (i + 1) < input.length();
                switch (input.charAt(i)) {
                    case 'i':
                    case 'I':
                        if (b) {
                            switch (input.charAt(i + 1)) {
                                case 'i':
                                case 'I':
                                    ++result;
                                    break;
                                default:
                                    if (i > 0 && (input.charAt(i - 1) == 'i' || input.charAt(i - 1) == 'I')) {
                                        throw new Exception();
                                    }
                                    --result;
                            }
                        } else {
                            ++result;
                        }
                        break;
                    case 'v':
                    case 'V':
                        if (b) {
                            switch (input.charAt(i + 1)) {
                                case 'i':
                                case 'I':
                                case 'v':
                                case 'V':
                                    result += 5;
                                    break;
                                default:
                                    if (i > 0 && (input.charAt(i - 1) == 'v' || input.charAt(i - 1) == 'V')) {
                                        throw new Exception();
                                    }
                                    result -= 5;
                            }
                        } else {
                            result += 5;
                        }
                        break;
                    default:
                        result += 10;
                }
            }
            return result;
        } catch (Throwable e) {
            System.out.printf(e.toString());
            System.exit(-1);

        }
        return 0;
    }

    static String romanString(int input) {
        String result = "";
        try{
            if(input < 1){
                throw new Exception("Non natural Roman");
            }
        final char[] cArabic = {1000, 500, 100, 50, 10, 5, 1, 0};
        final String[] sRoman = {"M", "D", "C", "L", "X", "V", "I", ""};
        final char[] iRomanRepl = {2, 2, 4, 4, 6, 6, 7};
        int iPos = 0;
        while (input > 0) {
            if (input >= cArabic[iPos]) {
                result = result.concat(sRoman[iPos]);
                input -= cArabic[iPos];
            } else if (input >= (cArabic[iPos] - cArabic[iRomanRepl[iPos]])) {
                result = result.concat(sRoman[iRomanRepl[iPos]].concat(sRoman[iPos]));
                input -= (cArabic[iPos] - cArabic[iRomanRepl[iPos]]);
            } else iPos++;
        }
        return result;
        }catch(Throwable e){
            System.out.printf(e.toString());
            System.exit(-1);
            return "";
        }
    }

    public static String calc(String input) {
        String out = "";
        try {
            boolean bRomanSrt = false;
            String switchStr = "";
            int[] val = new int[2];
            int result = 0;
            if (input.matches("\\s*\\d+\\s*[\\-\\+*/]\\s*\\d+\\s*")) {
                String[] s = input.strip().split("\\d+");
                switchStr = s[1].strip();
                String[] s2 = input.strip().split("[\\-\\+*/]");
                val[0] = parseInt(s2[0].strip());
                val[1] = parseInt(s2[1].strip());
            } else if (input.matches("\\s*[iIvVxX]+\\s*[\\-\\+*/]\\s*[iIvVxX]+\\s*")) {
                String[] s = input.strip().split("[iIvVxX]+");
                switchStr = s[1].strip();
                String[] s2 = input.strip().split("[\\-\\+*/]");
                val[0] = roman(s2[0]);
                val[1] = roman(s2[1]);
                bRomanSrt = true;
            } else {
                throw new Exception("Input not supported");
            }
            if(val[0] > 10 || val[0] == 0 || val[1] >10 || val[1] > 10){
                throw new Exception("Unsupported number");
            }
            switch (switchStr) {
                case "+":
                    result = val[0] + val[1];
                    break;
                case "-":
                    result = val[0] - val[1];
                    break;
                case "/":
                    result = val[0] / val[1];
                    break;
                case "*":
                    result = val[0] * val[1];
                    break;
            }
            if(bRomanSrt && result < 1) throw new Exception("Unsupported answer");
            out = (bRomanSrt)?romanString(result):Integer.toString(result);
        } catch (Throwable e) {
            out = e.toString();
            System.out.printf(e.toString());
            System.exit(-1);
        }
        return out;
    }
}