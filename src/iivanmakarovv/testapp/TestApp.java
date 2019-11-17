package iivanmakarovv.testapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TestApp {
    private static double getMultiplyOrSum(String line) {
        String[] elements = line.split(" ");
        int firstElementIndex = 1;

        switch (elements[0]) {
            case "mul":
                return getMultiply(elements, firstElementIndex, elements.length - 1);
            case "sum":
                return getSum(elements, firstElementIndex, elements.length - 1);
            case "mulsum":
                return getMultiply(elements, firstElementIndex, 2)
                        + getSum(elements, 3, elements.length - 1);
            default:
                throw new IllegalArgumentException("Некорректно указаны аргументы программы");
        }
    }

    private static double getMultiply(String[] elements, int firstElementIndex, int lastElementIndex) {
        double mul = 1;

        for (int i = firstElementIndex; i <= lastElementIndex; ++i) {
            mul *= Double.parseDouble(elements[i]);
        }

        return mul;
    }

    private static double getSum(String[] elements, int firstElementIndex, int lastElementIndex) {
        double sum = 0;

        for (int i = firstElementIndex; i <= lastElementIndex; ++i) {
            sum += Double.parseDouble(elements[i]);
        }

        return sum;
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args[0].equals("-") && args[1].equals("-")) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите название операции и числа");

            String line = scanner.nextLine();
            System.out.println(getMultiplyOrSum(line));
        } else if (args[0].equals("input.txt") && args[1].equals("output.txt")) {
            File input = new File(args[0]);

            try (PrintWriter writer = new PrintWriter(args[1]);
                 Scanner scanner = new Scanner(new FileInputStream(input))) {

                String line = scanner.nextLine();
                writer.println(getMultiplyOrSum(line));
            }
        } else {
            throw new IllegalArgumentException("Некорректно указаны аргументы программы");
        }
    }
}