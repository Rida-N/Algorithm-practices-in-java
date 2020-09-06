package chapter1.part1_basic;

import edu.princeton.cs.algs4.StdOut;

import java.util.Random;

public class ArrayTranspose {
    private int[][] array;
    private int row;
    private int column;
    Random random = new Random();

    public ArrayTranspose(int m, int n) {
        row = m;
        column = n;
        int[][] tempArray = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tempArray[i][j] = random.nextInt(101);
                StdOut.print(tempArray[i][j] + " ");
            }
            StdOut.println();
        }
        array = tempArray;
    }

    public void printTranspose() {
        StdOut.println("---------- After Transpose ----------");
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                StdOut.print(array[j][i] + " ");
            }
            StdOut.println();
        }
    }

    public static void main(String[] args) {
        
        StdOut.println();
        ArrayTranspose at = new ArrayTranspose(4, 6);
        at.printTranspose();
    }
}

