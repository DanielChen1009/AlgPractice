package com.danielchen;

import java.util.*;
import java.io.*;

class Matrix {
    double[][] matrix;
    int size;
    public Matrix(int size) {
        matrix = new double[size][size];
        this.size = size;
    }

    Matrix getMinor(int row, int col) {
        Matrix minor = new Matrix(size - 1);
        int mRow = 0, mCol = 0;
        for(int i = 0; i < size; ++i) {
            if(i == row) continue;
            for(int j = 0; j < size; ++j) {
                if(j == col) continue;
                minor.matrix[mRow][mCol++] = matrix[i][j];
            }
            mRow++;
            mCol = 0;
        }
        return minor;
    }

    double getDet() {
        double det = 0;
        if(size == 1) {
            return matrix[0][0];
        }
        for(int i = 0; i < size; ++i) {
            Matrix minor = this.getMinor(0, i);
            double result = minor.getDet();
            det += matrix[0][i] * result * Math.pow(-1, i);
        }
        return det;
    }
    Matrix transpose() {
        Matrix newMatrix = new Matrix(size);
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                newMatrix.matrix[j][i] = matrix[i][j];
            }
        }
        return newMatrix;
    }
}
public class Main {

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new File(args[0]));
        int size = sc.nextInt();
        BufferedWriter out = new BufferedWriter(new FileWriter(new File(args[1])));
        Matrix matrix = new Matrix(size);
        Map<Integer, Integer> numZerosInRow = new HashMap<>();
        for(int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                double entry = sc.nextDouble();
                matrix.matrix[i][j] = entry;
            }
        }
        Matrix inverse = new Matrix(size);
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                inverse.matrix[j][i] = matrix.getMinor(i, j).getDet() * Math.pow(-1, j + i) * 1/matrix.getDet();
            }
        }
        out.write("Inverse: \n");
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                out.write(inverse.matrix[i][j] + " ");
            }
            out.write("\n");
        }
        out.write("\nDeterminant: ");
        out.write(String.valueOf(matrix.getDet()));
        out.close();
    }
}
