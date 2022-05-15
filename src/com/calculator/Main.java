package com.calculator;

public class Main
{
    public static void main(String[] args)
    {
        calc("13 - 1,2");

    }

    public static String calc(String input)
    {
        checkString(input);


        return "";
    }

    private static boolean checkString(String input)
    {
        if (input.isEmpty()) //if line is empty
        {
            System.out.println("The string is empty");
            return false;
        }

        { //if the line has a float number or an empty value(-es)
            String[] str = input.split(" ");

            if (str[0] == "" || str.length < 3) //if "7 + " or " + 7"
            {
                System.out.println("The mathematical expression has an empty value(-es)");
                return false;
            }

            for (String el : str)
            {
                //a dot '.' and comme ',' are 46 and 44 unicode numbers
                if (el.indexOf(44) <= 0 || el.indexOf(46) <= 0)
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
}
