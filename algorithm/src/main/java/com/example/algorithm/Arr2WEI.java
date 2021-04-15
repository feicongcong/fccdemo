package com.example.algorithm;

public class Arr2WEI {
    public static void main(String[] args) {
        int x = 10;
        int y = 10;

        int data[][] = new int[x + 1][y + 1];
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                data[i][j] = (i-1)*10+j;
            }
        }
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                System.out.print(data[i][j]);
                System.out.print(" ");

            }
            System.out.print("\n");
        }

    }
}
