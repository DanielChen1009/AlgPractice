package com.danielchen;

class Node {
    Node next;
    String name;
    public Node(String name) {
        this.name = name;
    }
}

class LinkedList {
    Node head;

    @Override
    public String toString() {
        String state = "";
        Node current = head;
        while (current != null) {
            state += current.name + "->";
            current = current.next;
        }
        return state;
    }
}

public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Node head = new Node("1");
        Node node1 = new Node("2");
        Node node2 = new Node("3");
        Node node3 = new Node("4");
        Node node4 = new Node("5");
        Node node5 = new Node("6");
        Node node6 = new Node("7");
        list.head = head;
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        Node first = head;
}
