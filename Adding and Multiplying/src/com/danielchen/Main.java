package com.danielchen;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        String in = "1 + 2 + 4 + (10 + 16) * 2";
        Stack<String> numbers = new Stack<>();
        for(int i = 0; i < in.length(); ++i) {
            char character = in.charAt(i);
            if(Character.isWhitespace(character)) {
                continue;
            }
            numbers.push(Character.toString(character));
            if(Character.isDigit(character) && ) {

            }
        }
    }
}
