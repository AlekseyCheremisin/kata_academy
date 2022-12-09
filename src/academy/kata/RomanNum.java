package academy.kata;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanNum {
    private final LinkedHashMap<String, Integer> romanNumMap = new LinkedHashMap<String, Integer>();
    {
        romanNumMap.put("C", 100);
        romanNumMap.put("XC", 90);
        romanNumMap.put("L", 50);
        romanNumMap.put("XL", 40);
        romanNumMap.put("X", 10);
        romanNumMap.put("IX", 9);
        romanNumMap.put("V", 5);
        romanNumMap.put("IV", 4);
        romanNumMap.put("I", 1);
    }

    private String romanNumStr = "I";
    private int romanNumInt = 1;

    public RomanNum(String romanNumStr) throws InvalidRomanNumFormatException {
        Pattern patternValidRomanNum = Pattern.compile("^X{0,3}(IX|IV|V?I{0,3})$");
        Matcher matcher = patternValidRomanNum.matcher(romanNumStr.strip());
        if (matcher.find()) {
            this.romanNumStr = romanNumStr.strip();
            this.romanNumInt = this.romanToInteger();
            return;
        }
        throw new InvalidRomanNumFormatException("Неверный формат римского числа");
    }

    public RomanNum(int romanNumInt) throws InvalidRomanNumFormatException {
        if (romanNumInt >= 1 && romanNumInt <= 100) {
            this.romanNumInt = romanNumInt;
            this.romanNumStr = this.integerToRoman();
            return;
        }
        throw new InvalidRomanNumFormatException("Числа должны быть от 1 до 100.");
    }

    public String getRomanNumStr() {
        return this.romanNumStr;
    }

    public int getRomanNumInt() {
        return this.romanNumInt;
    }

    private int romanToInteger() {
        int result =  0;
        for (int index = 0; index < this.romanNumStr.length(); index++) {
            String tmpCur = String.valueOf(this.romanNumStr.charAt(index));
            int num = this.romanNumMap.get(tmpCur);
            if (index > 0) {
                String tmpPrev = String.valueOf(this.romanNumStr.charAt(index - 1));
                if (num > this.romanNumMap.get(tmpPrev)) {
                    result += num - (2 * this.romanNumMap.get(tmpPrev));
                } else {
                    result += num;
                }
            } else {
                result += num;
            }
        }
        return result;
    }

    private String integerToRoman() {
        StringBuilder result = new StringBuilder();
        int num = this.romanNumInt;
        int count = 0;
        for (Map.Entry<String, Integer> entry : romanNumMap.entrySet()) {
            count = num / entry.getValue();
            for (int i = 0; i < count; i++) {
                result.append(entry.getKey());
            }
            num = num % entry.getValue();
        }
        return result.toString();
    }
}
