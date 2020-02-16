package com.danielchen;

import java.util.*;
import java.io.*;

class Node {
	int value;
	Node left;
	Node right;

	public Node(int value) {
		this.value = value;
	}

	int findChild(Node child) {
		if(left == child) {
			return left.value + value;
		}
		if(right == child) {
			return right.value + value;
		}
		int lResult = 0;
		int rResult = 0;
		if(left != null && right != null) {
			lResult = left.findChild(child);
			rResult = right.findChild(child);
		}
		if(rResult > lResult && rResult != 0) {
			return rResult + value;
		}
		if(lResult > rResult && lResult != 0) {
			return lResult + value;
		}
		return 0;
	}
}
public class Main {

    public static void main(String[] args) throws IOException{
	    Scanner sc = new Scanner(new File(args[0]));
	    int lines = sc.nextInt();
	    Node nodeMain = new Node(0);
	    List<Node> row = new ArrayList<>();
	    List<Node> row2 = new ArrayList<>();
	    for(int i = 0; i  < lines; ++i) {
			for (int j = 0; j < i + 1; ++j) {
				if (i == 0) {
					nodeMain = new Node(sc.nextInt());
					row.add(nodeMain);
					continue;
				}
				int value = sc.nextInt();
				Node node = new Node(value);
				if (j - 1 >= 0 && j < row.size()) {
					row.get(j).left = node;
					row.get(j - 1).right = node;
				}
				if (j - 1 < 0) {
					row.get(j).left = node;
				}
				if (j >= row.size()) {
					row.get(j - 1).right = node;
				}
				row2.add(node);
			}
			if (i != 0 && i != lines - 1) {
				row.clear();
				row.addAll(row2);
				row2.clear();
			}
		}
	    int result = 0;
	    for(int i = 0; i < row2.size(); ++i) {
	    	int f = nodeMain.findChild(row2.get(i));
	    	if(f > result) {
	    		result = f;
			}
		}
		System.out.println(result);
    }
}
