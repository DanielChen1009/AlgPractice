package com.danielchen;

public class Main {
    public static void main(String[] args) {
        int reds = 10000;
        int blacks = 10000;
        double[][] exVals = new double[blacks + 1][reds + 1];
        for(int i = 0; i <= reds; ++i) {
            exVals[0][i] = i;
        }
        for(int i = 0; i <= blacks; ++i) {
            exVals[i][0] = 0;
        }
        for(int i = 1; i <= blacks; ++i) {
            for(int j = 1; j <= reds; ++j) {
                exVals[i][j] = Math.max(0, ((double) j / (j + i)) * (1 + exVals[i][j - 1]) + ((double) i / (j + i)) * (-1 + exVals[i - 1][j]));
            }
        }
        System.out.println(exVals[blacks][reds]);
    }
}
