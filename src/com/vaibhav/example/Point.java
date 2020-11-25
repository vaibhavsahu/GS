package com.vaibhav.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class Point {
    private int x;
    private int y;

    public Point() {
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static int calculateOptimalPath(int[][] matrix, Point source, Point destination) {
        boolean [][] visited = new boolean[matrix.length][matrix[0].length];

        int result = calculateOptimalPathHelper(matrix, visited, source, destination);

        return result;
    }

    private static int calculateOptimalPathHelper(int[][] matrix, boolean[][] visited, Point source, Point destination) {
        int sum = 0;
        Stack<Point> stack = new Stack<>();
        stack.push(source);

        while (!stack.isEmpty()){
            Point popped = stack.pop();
            visited[popped.getX()][popped.getY()] = true;
            if(popped.getX() == destination.getX() && popped.getY() == destination.getY()){
                sum += matrix[popped.getX()][popped.getY()];
                return sum;
            }
            sum += matrix[popped.getX()][popped.getY()];

            Point neighbour = getOptimalNeighbour(popped, matrix.length, matrix[0].length, matrix);
            if(Objects.isNull(neighbour)){
                return 0;
            } else {
                if(!visited[neighbour.getX()][neighbour.getY()]){
                    stack.push(neighbour);
                }
            }
        }
        return sum;
    }

    private static Point getOptimalNeighbour(Point popped, int numRows, int numCols, int[][] matrix) {
        List<Point> list = new ArrayList<>();
        Point north = getNorth(popped, numRows, numCols);
        Point east = getEast(popped, numRows, numCols);
        if(Objects.nonNull(north) && Objects.isNull(east)){
            return north;
        }
        if(Objects.nonNull(east) && Objects.isNull(north)){
            return east;
        }
        if(Objects.nonNull(east) && Objects.nonNull(north)
                && matrix[north.getX()][north.getY()] == matrix[east.getX()][east.getY()]){
            return north;
        }
        if(Objects.nonNull(east) && Objects.nonNull(north)){
            int N = matrix[north.getX()][north.getY()];
            int E = matrix[east.getX()][east.getY()];
            if(N > E){
                return north;
            } else {
                return east;
            }
        }
        return null;
    }


    private static Point getNorth(Point p, int numRows, int numCols){
        Point north = new Point();
        north.setX(p.getX()-1);
        north.setY(p.getY());
        if(north.getX() >= 0 && north.getX() < numRows && north.getY() >= 0 && north.getY() < numCols) {
            return north;
        }
        return null;
    }

    private static Point getEast(Point p, int numRows, int numCols){
        Point east = new Point();
        east.setX(p.getX());
        east.setY(p.getY()+1);
        if(east.getX() >= 0 && east.getX() < numRows && east.getY() >= 0 && east.getY() < numCols) {
            return east;
        }
        return null;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
