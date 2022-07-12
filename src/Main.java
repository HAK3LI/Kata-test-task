import static java.lang.Integer.parseInt;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter expression:");
        String str = in.nextLine();
        System.out.println(calc(str));
    }

    public static String calc(String input) throws Exception {
        String stroutput = null;
        int output = 0;
        boolean rom = false;
        int[] data = new int[2];
        input = input.replaceAll("\\s+", "");
        String[] info = input.split("\\+|\\-|\\*|\\/");
        if (info.length > 2) {
            throw new Exception("The format of the mathematical operation does not match the task");
        }
        if (info.length == 1) {
            throw new Exception("A string is not a mathematical operation");
        }
        input = input.replace(info[0], "");
        char oper = input.charAt(0);
        try {
            data[0] = parseInt(info[0]);
            data[1] = parseInt(info[1]);
        } catch (NumberFormatException e) {
            rom = true;
            data[0] = getInt(info[0]);
            data[1] = getInt(info[1]);
        }
        if (data[0] < 1 || data[0] > 10 || data[1] < 1 || data[1] > 10) {
            throw new Exception("Numbers out of range 1-10");
        }
        switch (oper) {
            case '+':
                output = data[0] + data[1];
                break;
            case '-':
                output = data[0] - data[1];
                break;
            case '/':
                output = data[0] / data[1];
                break;
            case '*':
                output = data[0] * data[1];
                break;
            default:
                output = -1;
                break;
        }
        if (rom) {
            if (output > 0) {
                stroutput = getRom(output);
            } else {
                throw new Exception("Roman numbers cannot be negative or equal to zero");
            }

        } else {
            stroutput = String.valueOf(output);
        }
        return stroutput;
    }

    public static int getInt(String str) throws Exception {
        int num = transform(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
            int curr = transform(str.charAt(i));
            int pre = transform(str.charAt(i - 1));
            if (curr <= pre) {
                num += curr;
            } else {
                num = num - (pre * 2) + curr;
            }
        }
        return num;

    }

    public static int transform(char rom) throws Exception {
        switch (rom) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            default:
                throw new Exception("Number system definition error");
        }
    }

    public static String getRom(int num) {
        String r = "";
        int[] decimal = {1, 4, 5, 9, 10, 40, 50, 90, 100};
        String[] roman = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
        for (int i = 8; i >= 0; i--) {
            while (num >= decimal[i]) {
                r += roman[i];
                num -= decimal[i];
            }
        }
        return r;
    }

}