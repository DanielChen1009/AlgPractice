package com.danielchen;

import java.io.File;
import java.util.Scanner;

public class Main {
    static int[][] enlargedGrid;

    public static void main(String[] args) throws Exception{
        File in = new File(args[0]);
        Scanner scan = new Scanner(in);
        int rows = scan.nextInt();
        int columns = scan.nextInt();
        int[][] seaGrid = new int[rows][columns];
        for(int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                seaGrid[i][j] = scan.nextInt();
            }
        }
        enlargedGrid = new int[rows + 2][columns + 2];
        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < columns; ++j) {
                enlargedGrid[i + 1][j + 1] = seaGrid[i][j];
            }
        }
        int islands = 0;
        for(int i = 1; i < rows + 1; ++i) {
            for(int j = 1; j < columns + 1; ++j) {
                if(enlargedGrid[i][j] == 1) {
                    checkSurroundings(i, j);
                    islands++;
                }
            }
        }
        System.out.println(islands);
    }

    private static void checkSurroundings(int rowNum, int columnNum) {
        enlargedGrid[rowNum][columnNum] = 0;
        if(enlargedGrid[rowNum + 1][columnNum] == 1) {
            enlargedGrid[rowNum + 1][columnNum] = 0;
            checkSurroundings(rowNum + 1, columnNum);
        }
        if(enlargedGrid[rowNum - 1][columnNum] == 1) {
            enlargedGrid[rowNum -1][columnNum] = 0;
            checkSurroundings(rowNum - 1, columnNum);
        }
        if(enlargedGrid[rowNum][columnNum + 1] == 1) {
            enlargedGrid[rowNum][columnNum + 1] = 0;
            checkSurroundings(rowNum, columnNum + 1);
        }
        if(enlargedGrid[rowNum][columnNum - 1] == 1) {
            enlargedGrid[rowNum][columnNum - 1] = 0;
            checkSurroundings(rowNum, columnNum - 1);
        }
    }
}
