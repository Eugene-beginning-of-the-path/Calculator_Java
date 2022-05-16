package com.calculator;

//the exception for stop this program if the input string not meet the criteria
public class InputStrException extends Exception
{
    InputStrException(String description)
    {
        System.out.println(description);
    }
}
