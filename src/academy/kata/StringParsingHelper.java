package academy.kata;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

class StringParsingHelper {
    private static final Pattern patternArab = Pattern.compile("^[0-9]+\\s*[+-/*/]\\s*[0-9]+$");
    private static final Pattern patternRoman = Pattern.compile("^[IVX]+\\s*[+-/*/]\\s*[IVX]+$");

    public static String[] parsingString(String str) throws
            OutOfRangeException,
            NotMathOperation,
            InvalidRomanNumFormatException,
            NegativeRomanResultException
    {
        if (isOperationWithArabicNum(str)) {
            return getMathExpressionAsStringArray(str, false);
        } else if (isOperationWithRomanNum(str)) {
            return getMathExpressionAsStringArray(str, true);
        } else {
            throw new NotMathOperation(
                    "Нарушен формат ожидаемой строки. Ожидается \"операнд оператор операнд\"/. Оба операда либо арабские, либо римские."
            );
        }
    }

    private static String[] getMathExpressionAsStringArray(String str, boolean isRoman) throws
            OutOfRangeException,
            InvalidRomanNumFormatException,
            NegativeRomanResultException
    {
        String[] result = new String[4];
        String[] arr_str = str.strip().split("\\s*[+-/*/]\\s*", 2);
        result[1] = getOperation(arr_str[0], arr_str[1], str);
        result[3] = String.valueOf(isRoman);
        if (isRoman) {
            RomanNum first = new RomanNum(arr_str[0]);
            RomanNum second = new RomanNum(arr_str[1]);
            if ((
                    first.getRomanNumInt() <= second.getRomanNumInt() && result[1].equals("-")) ||
                    first.getRomanNumInt() < second.getRomanNumInt() && result[1].equals("/")
            ) {
                throw new NegativeRomanResultException(
                        "Результат операции отрицательный или ноль. В римской системе нет отрицательных чисел и нуля."
                );
            }
            arr_str[0] = String.valueOf(first.getRomanNumInt());
            arr_str[1] = String.valueOf(second.getRomanNumInt());
        }
        if (isRangeOneToTen(arr_str[0]) && isRangeOneToTen(arr_str[1])) {
            result[0] = arr_str[0];
            result[2] = arr_str[1];
        }
        return result;
    }

    private static String getOperation(String firstNum, String secondNum, String str) {
        str = str.strip().replaceFirst(String.valueOf(firstNum), "");
        str = str.strip().replaceFirst(String.valueOf(secondNum), "");
        return str.trim();
    }

    private static boolean isOperationWithArabicNum(String operation) {
        Matcher matcher = patternArab.matcher(operation.strip());
        return matcher.find();
    }

    private static boolean isOperationWithRomanNum(String operation) {
        Matcher matcher = patternRoman.matcher(operation.strip());
        return matcher.find();
    }

    private static boolean isRangeOneToTen(String strNum) throws OutOfRangeException {
        int num = Integer.parseInt(strNum);
        boolean condition = num >= 1 && num <= 10;
        if (condition) {
            return true;
        } else {
            throw new OutOfRangeException("Допускаются числа от 1 до 10");
        }
    }
}
