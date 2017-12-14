# Problems for written final

## `FileReader` class

Tests simple file reading of single-column data. _Inspired by the format of HackerRank problems._

### Notes
1. The format of the file to read is as follows:
    * The first number is an integer indicating the count of doubles
    * The indicated number of doubles each on a separate line
    ```
    5
    2.13
    6.12
    5.22
    7.09
    10.31
    ```
2. The correct sum is `30.87`.
3. The difficulty can be varied by varying how much of the implementation is withheld. For example:
    * API only
    ```java
    package edu.msud.cs.cs1.fileread;
    
   // TODO: import the necessary classes
    
    public class FileReader {
    
        private double[] arr;
    
        public FileReader() {
            // TODO: Initialize the array
        }
    
        public void read(String filename) throws IOException {
            // TODO: Read in the doubles from the file named 'filename' and populate the array
        }
    
        public double sum() {
            // TODO: Return the sum of the numbers in the array, 0.0 of array is not allocated
        }
    
        public static void main(String[] args) {
            // TODO: Print the sum of the doubles in file "doubles.txt"
        }
    }
    ```
    * Imports, `FileReader` constructor, and `main` provided, but there are no instructions for `read` and `sum`
    ```java
    package edu.msud.cs.cs1.fileread;
    
    import java.io.File;
    import java.io.IOException;
    import java.util.Scanner;
    
    public class FileReader {
    
        private double[] arr;
    
        public FileReader() {
            arr = null;
        }
    
        public void read(String filename) throws IOException {
            // TODO
        }
    
        public double sum() {
            // TODO
        }
    
        public static void main(String[] args) {
            FileReader reader = new FileReader();
            try {
                reader.read("doubles.txt");
            } catch (IOException ioe) {
                System.err.println("ERROR: No such file");
            }
            System.out.printf("Sum of doubles: %4.2f", reader.sum());
        }
    }
    ```
### Bonus
1. For realizing that the array cannot be initialized in the constructor, assigning `null` in the constructor, and leaving initialization for `read`.
2. For properly handling an uninitialized array in `sum`.
3. For properly formatting the sum with 2 digits after the decimal point.


## `Filter` class

Tests the proficiency of working with multi-dimensional arrays and the ability to read and trace code. _Inspired by pooling layers in convolutional neural nets._

### Notes
1. This problem applies _valid_ max-pooling and average-pooling transformations to images in 2D matrix form. This means that the output dimensions are the input dimensions minus the filter size plus 1.
2. The filters are square. The filter size cannot be bigger than any of the input dimensions.
3. The output is:
   ```
    6.00 7.00
    9.00 10.00
    9.00 10.00
    
    6.00 7.00 8.00
    4.33 5.33 6.33
   ```
4. This problem affords a variety of questions:
    * Provide the output of the application of a filter to an image, possibly withholding the name of the filter to prevent _"short-circuiting"_ the solution. For example:
    ```java
    package edu.msud.cs.cs1.matrix;
    
    public class Filter {
    
        public static double[][] filter(double[][] input, int size) {
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
            double[][] filtered1 = filter(image1, 2);
        }
    }
    ```
    * Given the output of a the application of a filter to an image, provide the implementation of `print`
    * Vary the input and the size of the filter to achieve interesting and/or edge cases
    * Given the implementation of `max`, and possibly partial implementation of `avg`, and possibly the desired output of `main`, provide the full implementation of `avg`. For example:
    ```java
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
                    // TODO: Fill in this line
                    for (int k = 0; k < size; k++) {
                        for (int l = 0; l < size; l++) {
                            // TODO: Fill in this line
                        }
                    }
                    // TODO: Fill in this line
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
    ```
    
### Bonus
1. For correctly completing the `output` initialization when that is withheld.
2. For correctly completing the loop termaination conditions for the filter application when those are withheld.


## `Game` class and `GamePiece` hierarchy

Tests the knowledge of basic object-oriented terminology including types of variables and methods, identifier categories, abstract classes, inheritance, and polimorphism. It also provides a rich context in which various method implementations can be asked for. _Inspired by The World of Warcraft._

### Notes

### Bonus

