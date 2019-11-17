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
                return getMultiplication(elements, firstElementIndex, elements.length - 1);
            case "add":
                return getAddition(elements, firstElementIndex, elements.length - 1);
            case "muladd":
                return getMultiplication(elements, firstElementIndex, 2)
                        + getAddition(elements, 3, elements.length - 1);
            default:
                throw new IllegalArgumentException("Некорректно указаны аргументы программы");
        }
    }

    private static double getMultiplication(String[] elements, int firstElementIndex, int lastElementIndex) {
        double mul = 1;

        for (int i = firstElementIndex; i <= lastElementIndex; ++i) {
            mul *= Double.parseDouble(elements[i]);
        }

        return mul;
    }

    private static double getAddition(String[] elements, int firstElementIndex, int lastElementIndex) {
        double add = 0;

        for (int i = firstElementIndex; i <= lastElementIndex; ++i) {
            add += Double.parseDouble(elements[i]);
        }

        return add;
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