package academy.kata;

import java.util.Scanner;


public class Main {
    public static String calc(String input) throws
            OutOfRangeException,
            NotMathOperation,
            OperationNotFoundException,
            InvalidRomanNumFormatException,
            NegativeRomanResultException
    {
        String[] arr = StringParsingHelper.parsingString(input);
        Calculate calculate = CalculateFactory.getCalculate(arr[1]);
        int first = Integer.parseInt(arr[0]);
        int second = Integer.parseInt(arr[2]);
        boolean isRoman = Boolean.parseBoolean(arr[3]);
        int result = calculate.calculate(first, second);
        if (isRoman) {
            return (new RomanNum(result).getRomanNumStr());
        }
        return String.valueOf(result);
    }

    public static void main(String[] args) throws OutOfRangeException, NotMathOperation, OperationNotFoundException, InvalidRomanNumFormatException, NegativeRomanResultException {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(calc(str));
    }
}
