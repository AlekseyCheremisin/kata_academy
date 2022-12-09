package academy.kata;

class CalculateFactory {
    public static Calculate getCalculate(String operation) throws OperationNotFoundException {
        switch (operation) {
            case "+": return new Addition();
            case "-": return new Subtraction();
            case "*": return new Multiplication();
            case "/": return new Division();
            default: throw new OperationNotFoundException("Эта арефметическая операция не найдена");
        }
    }
}
