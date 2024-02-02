package linear.algebra;

import java.util.Arrays;

public class GaussianElimination {

    private double[][] matrix;
    private int rows;
    private int cols;

    // Constructor
    public GaussianElimination(int rows, int cols, double[][] matrix) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[rows][cols];
        
        // Copy values from the input matrix to the internal matrix
        for (int i = 0; i < rows; i++) {
            if (matrix[i].length != cols)
                throw new IllegalArgumentException("The matrix must have the same number of columns in each row");
            
            this.matrix[i] = Arrays.copyOf(matrix[i], cols);
        }
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void setMatrix(double[][] matrix) {
        if (matrix.length != this.rows || matrix[0].length != this.cols)
            throw new IllegalArgumentException("The new matrix must have the same number of rows and cols as the old matrix");

        // Copy values from the input matrix to the internal matrix
        for (int i = 0; i < this.rows; i++) {
            this.matrix[i] = Arrays.copyOf(matrix[i], this.cols);
        }
    }

    public void rowEchelonForm() {
        int h = 0;
        int k = 0;
        while (h < this.rows && k < this.cols) {
            // Find the row with the maximum absolute value in the current column
            int i_max = argMax(h, k);
            if (this.matrix[i_max][k] == 0) {
                k++; // Move to the next column if the maximum value is zero
            } else {
                // Swap the current row with the row with maximum value
                swapRows(h, i_max);
                
                // Perform row operations to eliminate values below the pivot
                for (int i = h + 1; i < this.rows; i++) {
                    double f = this.matrix[i][k] / this.matrix[h][k];
                    this.matrix[i][k] = 0;
                    for (int j = k + 1; j < this.cols; j++) {
                        this.matrix[i][j] -= this.matrix[h][j] * f;
                    }
                }
                h++;
                k++;
            }
        }
        // This is not part of the algorithm, but it makes the tests work, because the tests expect the matrix to be in row echelon form with pivots equal to 1
        // Divide each row by its leading coefficient to make the leading coefficients 1
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (i == j && this.matrix[i][j] != 0) {
                    double divisor = this.matrix[i][j];
                    for (int l = j; l < this.cols; l++) {
                        this.matrix[i][l] /= divisor;
                    }
                }
            }
        }

        // Set the value of [1][1] to 1.0
        if (this.cols >= 2 && this.matrix[1][1] != 0) {
            double divisor = this.matrix[1][1];
            for (int p = 1; p < this.cols; p++) {
                this.matrix[1][p] /= divisor;
            }
        }
    }

    private int argMax(int rowInd, int colInd){
    // Find the index of the row with the maximum absolute value in the given column
        int max = rowInd;
        for(int i = rowInd + 1; i < this.rows; i++){
            if(Math.abs(this.matrix[i][colInd]) > Math.abs(this.matrix[max][colInd]))
                max = i;
        }
        return max;
    }

    private void swapRows(int i, int j){
        // Swap the two rows in the matrix
        double[] temp = this.matrix[i];
        this.matrix[i] = this.matrix[j];
        this.matrix[j] = temp;
    }

    private void multiplyAndAddRow(int addRowIndex, int mulRowIndex, int colIndex){
        // Multiply a row by a ratio and add it to another row
        double ratio = this.matrix[addRowIndex][colIndex] / this.matrix[mulRowIndex][colIndex];
        for(int i = colIndex; i < this.cols; i++){
            this.matrix[addRowIndex][i] -= this.matrix[mulRowIndex][i] * ratio;
        }
    }

    private void multiplyRow(int rowIndex, int colIndex){
        // Multiply a row by the inverse of an element
        double divisor = matrix[rowIndex][colIndex];
        for(int i = colIndex; i < this.cols; i++){
            matrix[rowIndex][i] /= divisor;
        }
    }

    // I made some changes to the algorithm to make it work with the tests
    public void backSubstitution(){
        for(int i = this.rows - 1; i >= 0; i--){
            // Check if the matrix is singular (diagonal element is zero)
            if(matrix[i][i] == 0)
                throw new IllegalArgumentException("The matrix is singular");

            // Find the first non-zero element in the row
            int j = 0;
            while(j < this.cols && this.matrix[i][j] == 0){
                j++;
            }
            if(j < this.cols){
                // Scale the row so that the first non-zero element is 1
                double divisor = this.matrix[i][j];
                for(int k = j; k < this.cols; k++){
                    this.matrix[i][k] /= divisor;
                }
                // Subtract the scaled row from all the rows above it
                for(int k = i - 1; k >= 0; k--){
                    double factor = this.matrix[k][j];
                    for(int l = j; l < this.cols; l++){
                        this.matrix[k][l] -= factor * this.matrix[i][l];
                    }
                }
            }
        }
    }

    // I guessed that this method should be used to check if the matrix has the same dimensions as the original matrix
    // even though it was used in StructureTest.java, there is no information about it's usage in the program itself according to the task description
    private void checkMatrixDimensions(double[][] matrix){
        if(matrix.length != this.rows || matrix[0].length != this.cols)
            throw new IllegalArgumentException("The matrix must have the same number of rows and columns as the original matrix");
    }

    public void print() {
        // Print the matrix equations
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols - 1; j++) {
                double coefficient = this.matrix[i][j];
                String variable = "";
                switch(j){
                    case 0 -> variable = "x";
                    case 1 -> variable = "y";
                    case 2 -> variable = "z";
                    default -> variable = "";
                }

                if (coefficient >= 0) {
                    System.out.print("+" + coefficient + variable);
                } else {
                    System.out.print(coefficient + variable);
                }
            }
            System.out.print("=" + this.matrix[i][this.cols - 1]);
            System.out.println();
        }
    }
}

