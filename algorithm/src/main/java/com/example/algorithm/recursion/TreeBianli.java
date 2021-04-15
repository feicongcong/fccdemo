package com.example.algorithm.recursion;

public class TreeBianli {

    public static void f(Node node){
        if(node!=null){
            System.out.println(node.value);
            f(node.left);
            f(node.right);
        }
    }
}

class Node{
    int value;
    Node left;
    Node right;

}