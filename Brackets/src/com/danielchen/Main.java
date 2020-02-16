package com.danielchen;

import java.util.*;

public class Main {
    public static void main(String[] args) {
	    String in = "([)]";
	    Stack<Character> brackets = new Stack<>();
	    for(int i = 0; i < in.length(); ++i) {
	        char bracket = in.charAt(i);
	        if(bracket == '{' || bracket == '(' || bracket == '[') {
	            brackets.push(bracket);
            } else {
	            if(brackets.size() != 0) {
                    char lastBrac = brackets.pop();
                    if (lastBrac == '{' && bracket != '}') {
                        System.out.println(false);
                        return;
                    }
                    if (lastBrac == '[' && bracket != ']') {
                        System.out.println(false);
                        return;
                    }
                    if (lastBrac == '(' && bracket != ')') {
                        System.out.println(false);
                        return;
                    }
                } else {
	                System.out.println(false);
	                return;
                }
            }
        }
	    if(brackets.size() == 0) {
	        System.out.println(true);
        }
    }
}
