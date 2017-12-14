package edu.msud.cs.cs1.matrix;

public class Filter {

    public static double[][] max(double[][] input, int size) {
        double[][] output = new double[input.length-size+1][input[0].length-size+1];

        for (int i=0; i<input.length-size+1; i++) {
            for (int j = 0; j < input[0].length - size + 1; j++) {
                double max = input[i][j];
                for (int k = 0; k < size; k++) {
                    for (int l = 0; l < size; l++) {
                        double num = input[i + k][j + l];
                        if (num > max) max = num;
                    }
                }
                output[i][j] = max;
            }
        }

        return output;
    }

    public static double[][] avg(double[][] input, int size) {
        double[][] output = new double[input.length-size+1][input[0].length-size+1];

        for (int i=0; i<input.length-size+1; i++) {
            for (int j = 0; j < input[0].length - size + 1; j++) {
                double sum = 0.0;
                for (int k = 0; k < size; k++) {
                    for (int l = 0; l < size; l++) {
                        sum += input[i + k][j + l];
                    }
                }
                output[i][j] = sum/(size*size);
            }
        }

        return output;
    }

    public static void print(double[][] matrix) {
        for (int i=0; i<matrix.length; i++)
            for (int j=0; j<matrix[0].length; j++) {
                System.out.printf("%4.2f", matrix[i][j]);
                if (j == matrix[0].length - 1)
                    System.out.println();
                else
                    System.out.print(" ");
            }
    }

    public static void main(String[] args) {
        double[][] image1 = {{2, 3, 4}, {5, 6, 7}, {8, 9, 10}, {-1, 0, 1}};
        double[][] filtered1 = max(image1, 2);

        print(filtered1);

        System.out.println();

        double[][] image2 = {{2, 3, 4, 5, 6}, {5, 6, 7, 8, 9}, {8, 9, 10, 11, 12}, {-3, -2, -1, 0, 1}};
        double[][] filtered2 = avg(image2, 3);

        print(filtered2);
    }
}
