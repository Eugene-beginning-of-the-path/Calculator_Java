package com.calculator;

import java.util.Scanner;

public class Main
{
    static int[] arrOfNumbers = new int[2]; //it will be saving two value for calculation
    static int result;  //it will be saving result of arabic value
                        // after operation for two values
    static String resultRoman; //it will be saving result of roman value
                               // after operation for two values

    public static void main(String[] args) throws InputStrException
    {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws InputStrException
    {
        /**
         * The method for calculation two number from input string
         */

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
        return resultRoman;
    }

    public static void ArabicCalculationLogic(String input) throws InputStrException
    {
        /**
         * Splitting a string into Arabic numbers
         * and checking these numbers for
         * x > 0 && x < 10
         * Is processing main logic of calculator
         */

        String[] str = input.split(" ");
        for (int i = 0, j = 0; i < 3; i+=2, j++)
        {
            arrOfNumbers[j] = Integer.parseInt(str[i]);
        }
        checkNumbers(arrOfNumbers);

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

    public static void RomanCalculationLogic(String input) throws InputStrException
    {
        /**
         * Splitting a string into Roman numbers
         * and checking these numbers for
         * x > 0 && x < 10
         * Is processing main logic of calculator
         */

        String[] str = input.split(" ");
        int value1 = 0, value2 = 0;
        char[] symbol = str[0].toCharArray();

        //it would be necessary to create in the method these two cycle of for xd
        for (int i = 0; i < symbol.length; i++) //instead of using an enum
        {
            switch (symbol[i]) //translating our Roman symbol to integer value
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

        checkNumbers(value1, value2);

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

        if (result <= 0)
            throw new InputStrException("A negative result of roman value");

        String temp = String.valueOf(result);
        char[] resultToRoman = temp.toCharArray();
        String resultStr = "";
        if (result == 100)
        {
            resultStr += 'C';
            resultRoman = resultStr;
            return;
        }

        for (int i = 0; i < resultToRoman.length; i++)
        {
            if (i == 0)
            {
                switch (resultToRoman[i])
                {
                    case '1':
                        resultStr += 'I';
                        break;
                    case '2':
                        resultStr += "II";
                        break;
                    case '3':
                        resultStr += "III";
                        break;
                    case '4':
                        resultStr += "IV";
                        break;
                    case '5':
                        resultStr += 'V';
                        break;
                    case '6':
                        resultStr += "VI";
                        break;
                    case '7':
                        resultStr += "VII";
                        break;
                    case '8':
                        resultStr += "VIII";
                        break;
                    case '9':
                        resultStr += "IX";
                        break;
                }
            }

            if (i == 1)
            {
                switch (resultToRoman[i])
                {
                    case '1':
                        resultStr += 'X';
                        break;
                    case '2':
                        resultStr += "XX";
                        break;
                    case '3':
                        resultStr += "XXX";
                        break;
                    case '4':
                        resultStr += "XL";
                        break;
                    case '5':
                        resultStr += 'L';
                        break;
                    case '6':
                        resultStr += "LX";
                        break;
                    case '7':
                        resultStr += "LXXX";
                        break;
                    case '8':
                        resultStr += "IIC";
                        break;
                    case '9':
                        resultStr += "IC";
                        break;
                }
            }
        }
        resultRoman = resultStr;
    }

    private static void checkString(String input) throws InputStrException
    {
        /**
         * Checking the input string
         * for emptiness, for the presence of 2 numbers,
         * for the presence of float value in the string,
         * different number systems are used simultaneously
         */
        if (input.isEmpty()) //if line is empty
            throw new InputStrException("The string is empty");

        { //if the line has a float number or an empty value(-es)
            String[] splited = input.split(" ");

            if (splited[0] == "" || splited.length < 3 || splited.length > 3) //if "7 + " or " + 7"
                throw new InputStrException("The mathematical expression has an empty value(-es) or expression has" +
                        " more than 2 values");

            for (String el : splited)
            {
                //a dot '.' and comme ',' are 46 and 44 unicode numbers
                if (el.indexOf(44) >= 0 || el.indexOf(46) >= 0)
                    throw new InputStrException("The mathematical expression has a float number");
            }

            //if different number systems are used simultaneously
            int bothValues = 0;
            char[] temp = splited[0].toCharArray();
            for (int i = 48; i <= 57; i++)
            {
                if ((int)temp[0] == i)
                    bothValues++;
            }

            temp = splited[2].toCharArray();
            for (int i = 48; i <= 57; i++)
            {
                if ((int)temp[0] == i)
                    bothValues++;
            }

            if (bothValues == 1)
                throw new InputStrException("Different number systems are used simultaneously");
        }
    }

    private static void checkNumbers(int[] arr) throws InputStrException
    {
        /**
         * Checking two number for x>0 and x x<10
         */
        if (arr[0] < 0 || arr[0] > 10 || arr[1] < 0 || arr[1] > 10)
            throw new InputStrException("One of the two values is > 10 or < 0");

    }

    private static void checkNumbers(int value1, int value2) throws InputStrException
    {
        /**
         * Checking two number for x>0 and x x<10
         */
        if (value1 < 0 || value1 > 10 || value2 < 0 || value2 > 10)
            throw new InputStrException("One of the two values is > 10 or < 0");
    }
}
