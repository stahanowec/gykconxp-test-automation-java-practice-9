package com.epam.test.automation.java.practice9;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Matrix {

    public static final String NULL_MATRIX = "Matrix is null";
    public static final String INCOMP_MATRIX_SIZE = "Incompatible matrix sizes";
    private final double[][] actualmatrix;
    private final int rowcount;
    private final int columncount;

    public Matrix(int row, int column) {
        this.rowcount = row;
        this.columncount = column;
        checkRowAndColumn(rowcount, columncount);
        this.actualmatrix = new double[rowcount][columncount];
        for (double[] doubles : actualmatrix) {
            Arrays.fill(doubles, 0);
        }
    }

    public Matrix(double[][] twoDimensionalArray) throws MatrixException {
        checkDoubleMatrix(twoDimensionalArray);
        this.actualmatrix = twoDimensionalArray;
        rowcount = actualmatrix.length;
        columncount = actualmatrix[0].length;
    }

    public final int rows() {
        return rowcount;
    }

    public final int columns() {
        return columncount;
    }

    public double[][] twoDimensionalArrayOutOfMatrix() {
        return actualmatrix;
    }

    public double getValue(int row, int column) throws MatrixException {
        checkCorrectIndex(row, column, actualmatrix);
        return actualmatrix[row][column];
    }

    public void setValue(int row, int column, double newValue) throws MatrixException {
        checkCorrectIndex(row, column, actualmatrix);
        actualmatrix[row][column] = newValue;
    }

    public Matrix addition(Matrix matrix) throws MatrixException {
        checkTwoMatrix(matrix, rows(), columns());
        Matrix summatrix = new Matrix(rows(), columns());
        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < columns(); j++) {
                summatrix.setValue(i, j, actualmatrix[i][j] + matrix.getValue(i, j));
            }
        }
        return summatrix;
    }

    public Matrix subtraction(final Matrix matrix) throws MatrixException {
        checkTwoMatrix(matrix, rows(), columns());
        Matrix submatrix = new Matrix(rows(), columns());
        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < columns(); j++) {
                submatrix.setValue(i, j, actualmatrix[i][j] - matrix.getValue(i, j));
            }
        }
        return submatrix;
    }

    public Matrix multiplication(final Matrix matrix) throws MatrixException {
        if (columns() != matrix.rows()) {
            throw new MatrixException(INCOMP_MATRIX_SIZE);
        }
        var multimatrix = new Matrix(rows(), matrix.columns());
        for (int i = 0; i < rows(); i++) {
            for (int u = 0; u < matrix.columns(); u++) {
                for (int j = 0; j < columns(); j++) {
                    multimatrix.setValue(i, u, multimatrix.getValue(i, u) + actualmatrix[i][j] * matrix.getValue(j, u));
                }
            }
        }
        return multimatrix;
    }



    public static void checkRowAndColumn(int rowcount, int columncount) {
        if (rowcount < 1 || columncount <1 ) throw new UnsupportedOperationException("The number of rows or columns is less than 1");
    }

    public static void checkDoubleMatrix(double[][] twoDimensionalArray) throws MatrixException {
        if (twoDimensionalArray == null) throw new UnsupportedOperationException(NULL_MATRIX);
        if (twoDimensionalArray.length == 0) throw new MatrixException("Array passed with zero number of rows");
        if (twoDimensionalArray[0] == null) throw new UnsupportedOperationException(NULL_MATRIX);
        if (twoDimensionalArray[0].length == 0) throw new MatrixException("Array passed with zero number of columns");
    }

    public static void checkTwoMatrix(Matrix matrix, int rowcount, int columncount) throws MatrixException {
        if (matrix.rows() != rowcount || matrix.columns() != columncount) throw new MatrixException(INCOMP_MATRIX_SIZE);
    }

    public static void checkCorrectIndex(int row, int column, double[][] actualmatrix) throws MatrixException {
        if (row >= actualmatrix.length || column >= actualmatrix[0].length || row < 0 || column < 0)
            throw new MatrixException(INCOMP_MATRIX_SIZE);
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.rows(); i++) {
            for (int j = 0; j < this.columns(); j++) {
                try {
                    if (j != this.columns() - 1) {
                        builder.append(decimalFormat.format(getValue(i, j)) + " ");
                    } else {
                        builder.append(decimalFormat.format(getValue(i, j)));
                    }
                } catch (MatrixException e) {
                    e.getMessage();
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}

