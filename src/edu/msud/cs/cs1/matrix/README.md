## `Filter` class

Tests the proficiency of working with multi-dimensional arrays and the ability to read and trace code. _Inspired by pooling layers in convolutional neural nets._

<a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/">Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License</a>.

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
