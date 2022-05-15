package com.calculator;

public class Main
{
    static int[] arrOfNumbers = new int[2]; //it will be saving two value for calculation
    static int result;

    public static void main(String[] args)
    {
        System.out.println(calc("1 + 2 + 3"));
    }

    public static String calc(String input)
    {
        checkString(input);

        int unicode = 48;
        while (unicode != 57) //if values of the string has Arabic numbers --> call method for calculation Arabic
                              //numbers
        {
            if (input.indexOf(unicode) >= 0)
            {
                ArabicCalculationLogic(input);
                return String.valueOf(result);
            }

            unicode++;
        }
        RomanCalculationLogic(input);
        return String.valueOf(result);
    }

    public static void ArabicCalculationLogic(String input)
    {
        /**
         * Splitting a string into numbers
         * and checking these numbers for
         * x > 0 && x < 10
         * Is processing main logic of calculator
         */

        String[] str = input.split(" ");
        for (int i = 0, j = 0; i < 3; i+=2, j++)
        {
            arrOfNumbers[j] = Integer.parseInt(str[i]);
        }
        checkNumbers(arrOfNumbers); //выбрасывается исключение в случае провала проверки

        switch (str[1]) //str[1] keeps a mathematical operation
        {
            case "+":
                result = arrOfNumbers[0] + arrOfNumbers[1];
                break;

            case "-":
                result = arrOfNumbers[0] - arrOfNumbers[1];
                break;

            case "*":
                result = arrOfNumbers[0] * arrOfNumbers[1];
                break;

            case "/":
                result = arrOfNumbers[0] / arrOfNumbers[1];
                break;
        }
    }

    public static void RomanCalculationLogic(String input)
    {
        String[] str = input.split(" ");
        int value1 = 0, value2 = 0;
        char[] symbol = str[0].toCharArray();
        //int[] value = new int[symbol.length];
        for (int i = 0; i < symbol.length; i++)
        {
            switch (symbol[i])
            {
                case 'I':
                    value1 += 1;
                    break;

                case 'V':
                    value1 += 5;
                    break;

                case 'X':
                    value1 += 10;
            }
        }

        symbol = str[2].toCharArray();
        for (int i = 0; i < symbol.length; i++)
        {
            switch (symbol[i])
            {
                case 'I':
                    value2 += 1;
                    break;

                case 'V':
                    value2 += 5;
                    break;

                case 'X':
                    value2 += 10;
            }
        }

        switch (str[1]) //str[1] keeps a mathematical operation
        {
            case "+":
                result = value1 + value2;
                break;

            case "-":
                result = value1 - value2;
                break;

            case "*":
                result = value1 * value2;
                break;

            case "/":
                result = value1 / value2;
                break;
        }
    }

    private static boolean checkString(String input)
    {
        /**
         * Checking the input string
         * for emptiness, for the presence of 2 numbers,
         * for the presence of float value in the string
         */
        if (input.isEmpty()) //if line is empty
        {
            System.out.println("The string is empty");
            return false;
        }

        { //if the line has a float number or an empty value(-es)
            String[] splited = input.split(" ");

            if (splited[0] == "" || splited.length < 3 || splited.length > 3) //if "7 + " or " + 7"
            {
                System.out.println("The mathematical expression has an empty value(-es) or expression has 3 numbers");
                return false;
            }

            for (String el : splited)
            {
                //a dot '.' and comme ',' are 46 and 44 unicode numbers
                if (el.indexOf(44) >= 0 || el.indexOf(46) >= 0)
                {
                    System.out.println("The mathematical expression has a float number");
                    return false;
                }
            }
        }

        //проверка на 7 + VII и VII + 7
        //проверка на 7V3 + X2 (число в перемешку с разными системами счисления)


        return true;
    }

    private static boolean checkNumbers(int[] arr)
    {
        /**
         * Checking two number for x>0 and x x<10
         */
        if (arr[0] < 0 || arr[0] > 10 || arr[1] < 0 || arr[1] > 10)
        {
            System.out.println("One of the two values is > 10 or < 0");
            return false;
        }

        return true;
    }
}
