package academy.kata;

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
        String[] operations = new String[]{" + ", " - ", " / ", " * "};
        StringBuilder sb = new StringBuilder();
        for (String operation : operations) {
            for (int i = 1; i < 11; i++) {
                for (int j = 1; j < 11; j++) {
                    RomanNum first = new RomanNum(i);
                    RomanNum second = new RomanNum(j);
                    sb.append(first.getRomanNumStr());
                    sb.append(operation);
                    sb.append(second.getRomanNumStr());
                    String numStr = first.getRomanNumInt() + operation + second.getRomanNumInt();
                    try {
                        System.out.println(sb + " = " + calc(sb.toString()) + " | " + numStr + " = " + calc(numStr));
                    } catch (NegativeRomanResultException e) {
                        System.out.println(sb + " = " + e.getMessage() + " | " + numStr + " = " + calc(numStr));
                    }
                    sb.delete(0, sb.length());
                }
            }
        }
    }
}
