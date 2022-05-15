package com.calculator;

public enum Roman
{
    I(1), V(5), X(10);

    int value;
    Roman(int val)
    {
        value = val;
    }

    int getValue() { return value; }
}
