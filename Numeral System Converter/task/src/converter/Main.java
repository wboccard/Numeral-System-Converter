package converter;

import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        //takes in inputs
        try {
            Scanner sc = new Scanner(System.in);
            int originalBase = sc.nextInt();
            String numberToConvert = sc.next();
            int nextBase = sc.nextInt();
            sc.close();
            if (originalBase <= 0 || nextBase <= 0 || originalBase > 36 || nextBase > 36){
                throw new Exception("Invalid Radix");
            }
            //Splits inputNumber into an array of integer and decimal portions
            String[] arrOfNumbers = numberToConvert.split("\\.", 2);
            if (nextBase == 1 || arrOfNumbers.length == 1) {
                String integerPart = inputIntegerConverter(originalBase, arrOfNumbers[0], nextBase);
                System.out.print(integerPart);
            } else {
                String integerPart = inputIntegerConverter(originalBase, arrOfNumbers[0], nextBase);
                StringBuilder decimalPart = decimalBaseConverter(originalBase, arrOfNumbers[1], nextBase);
                System.out.print(integerPart + decimalPart);
            }
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    static StringBuilder decimalBaseConverter(int firstBase, String inputNumber, int newBase) {
        StringBuilder digits = new StringBuilder(".");
        double base10InputValue = 0;
        for (int index = 0; index < inputNumber.length(); index++) {
            double digitValue = Character.digit(inputNumber.charAt(index), firstBase);
            digitValue = digitValue / (Math.pow(firstBase, (index + 1)));
            base10InputValue += digitValue;
        }
        String base10InputValueString = Double.toString(base10InputValue);
        int index = 0;
        while (index < 5) {
            double nextNumber = Double.parseDouble(base10InputValueString) * newBase;
            String nextNumberAsString = Double.toString(nextNumber);
            String integerPortion = nextNumberAsString.substring(0, nextNumberAsString.indexOf("."));
            integerPortion = Integer.toUnsignedString(Integer.parseInt(integerPortion), newBase);
            digits.append(integerPortion);
            String decimalPortion = nextNumberAsString.substring(nextNumberAsString.indexOf("."));
            base10InputValueString = decimalPortion;
            index++;
        }
        return digits;
    }


    static String inputIntegerConverter(int sourceBase, String inputNumber, int newBase) {
        Integer decimalNumber = 0;
        String convertedNumber = "";
        if (sourceBase == 1) {
            decimalNumber = inputNumber.length();
        } else {
            decimalNumber = Integer.parseInt(inputNumber, sourceBase);
        }
        if (newBase == 1) {
            while (decimalNumber > 0) {
                convertedNumber += "1";
                decimalNumber--;
            }
        } else {
            convertedNumber = Integer.toString(decimalNumber, newBase);
        }
        return convertedNumber;
    }
}
//    static void binaryConverter() {
//        int number = 300;
//        String binary = "";
//        int binaryComputer = number;
//        while (binaryComputer > 15) {
//            binary = (binaryComputer % 2) + binary;
//            binaryComputer /= 2;
//        }
//        System.out.println(number + " = 0b" + binary);
//    }
//
//    static void octalLastDigit() {
//        Scanner sc = new Scanner(System.in);
//        int inputNumber = sc.nextInt();
//        int outputNumber = inputNumber % 8;
//        System.out.print(outputNumber);
//        sc.close();
//    }
//
//    static void multiRadixConverter() {
//        Scanner sc = new Scanner(System.in);
//        long inputNumber = sc.nextLong();
//        int inputRadix = sc.nextInt();
//        String outputNumber = Long.toString(inputNumber, inputRadix);
//        if (inputRadix == 2) {
//            outputNumber = "0b" + outputNumber;
//        } else if (inputRadix == 8) {
//            outputNumber = "0" + outputNumber;
//        } else {
//            outputNumber = "0x" + outputNumber;
//        }
//        System.out.println(outputNumber);
//        sc.close();
//    }
