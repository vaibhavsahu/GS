package com.vaibhav.example;

public class Main {

    public static void main(String[] args) {
        int [][] matrix = {{0,0,0,0,5}, {0,1,1,1,0}, {2,0,0,0,0}};
        Point source = new Point(2,0);
        Point destination = new Point(0,4);

        int totalRocks = Point.calculateOptimalPath(matrix, source, destination);

    }
}
