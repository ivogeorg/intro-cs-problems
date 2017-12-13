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
        File f = new File(filename);
        Scanner input = new Scanner(f);
        int numElem = input.nextInt();

        arr = new double[numElem];

        for (int i=0; i<numElem; i++) {
            arr[i] = input.nextDouble();
        }
    }

    public double sum() {
        double sum = 0.0;
        if (arr != null)
            for (double d: arr) sum += d;
        return sum;
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
