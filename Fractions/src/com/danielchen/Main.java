package com.danielchen;

import java.io.*;
import java.util.*;

class Node {
    String name;
    Map<Node, Double> children;
    public Node(String name) {
        this.name = name;
        children = new HashMap<>();
    }

    void addChild(Node child, double weight) {
        children.put(child, weight);
    }

    double getFraction(Node denominator, Set<Node> visited) {
        visited.add(this);
        for(Map.Entry<Node, Double> entry : children.entrySet()) {
            if(entry.getKey() == denominator) {
                return entry.getValue();
            }
            if(visited.contains(entry.getKey())) {
                continue;
            }
            double result = entry.getKey().getFraction(denominator, visited);
            if(Double.isNaN(result)) {
                continue;
            }
            return entry.getValue() * result;
        }
        return Double.NaN;
    }
}

public class Main {

    public static void main(String[] args) throws IOException{
	    Scanner sc = new Scanner(new File(args[0]));
        int asc = sc.nextInt();
        Map<String, Node> vars = new HashMap<>();
        for(int i = 0; i < asc; ++i) {
            String name1 = sc.next();
            String name2 = sc.next();
            Node var1 = vars.containsKey(name1) ? vars.get(name1) : new Node(name1);
            Node var2 = vars.containsKey(name2) ? vars.get(name2) : new Node(name2);
            vars.putIfAbsent(name1, var1);
            vars.putIfAbsent(name2, var2);
            double weight = sc.nextDouble();
            var1.addChild(var2, weight);
            var2.addChild(var1, 1/weight);
        }
        Scanner sc2 = new Scanner(new File(args[1]));
        int numOut = sc2.nextInt();
        for(int i = 0; i < numOut; ++i) {
            String input1 = sc2.next();
            String input2 = sc2.next();
            Node var1 = vars.get(input1);
            Node var2 = vars.get(input2);
            double f = var1.getFraction(var2, new HashSet<>());
            System.out.println(f);
        }
    }



}
