## `FileReader` class

Tests simple file reading of single-column data. _Inspired by the format of HackerRank problems._

<a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/">Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License</a>.

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
