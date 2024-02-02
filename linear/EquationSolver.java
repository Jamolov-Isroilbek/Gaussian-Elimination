package linear;

import linear.algebra.GaussianElimination;

public class EquationSolver {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java linear.EquationSolver <equation>");
            return;
        }
        String equation = args[0];
        String[] rows = equation.split(" ");
        int numRows = rows.length;
        int numColumns = rows[0].split(",").length;
        double[][] matrix = new double[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            String[] rowValues = rows[i].split(",");
            if (rowValues.length != numColumns) {
                System.out.println("Invalid equation");
                return;
            }
            //matrix[i] = stringToDoubles(rowValues);
            double[] doubles = stringToDoubles(rowValues);
            for(int j = 0; j < doubles.length; j++){
                matrix[i][j] = doubles[j];
            }
        }

        try {
            GaussianElimination solver = new GaussianElimination(numRows, numColumns, matrix);
            System.out.println("Input matrix:");
            solver.print();
            solver.rowEchelonForm();
            System.out.println("\nMatrix in row echelon form:");
            solver.print();
            solver.backSubstitution();
            System.out.println("\nMatrix after back substitution:");
            solver.print();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static double[] stringToDoubles(String[] arr){
        double[] result = new double[arr.length];
        for(int i = 0; i < arr.length; i++){
            result[i] = Double.parseDouble(arr[i]);
        }
        return result;
    }
}
