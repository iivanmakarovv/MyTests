package iivanmakarovv.testapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TestApp {
    private static double getMultiplicationOrAddition(String line) {
        String[] elements = line.split(" ");
        int firstNumberIndex = 1;

        switch (elements[0]) {
            case "mul":
                return getMultiplication(elements, firstNumberIndex, elements.length - 1);
            case "add":
                return getAddition(elements, firstNumberIndex, elements.length - 1);
            case "muladd":
                return getMultiplication(elements, firstNumberIndex, 2)
                        + getAddition(elements, 3, elements.length - 1);
            default:
                throw new IllegalArgumentException("Некорректно указано название операции");
        }
    }

    private static double getMultiplication(String[] elements, int firstNumberIndex, int lastNumberIndex) {
        double mul = 1;

        for (int i = firstNumberIndex; i <= lastNumberIndex; ++i) {
            try {
                mul *= Double.parseDouble(elements[i]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Некорректно указаны числа");
            }
        }

        return mul;
    }

    private static double getAddition(String[] elements, int firstNumberIndex, int lastNumberIndex) {
        double add = 0;

        for (int i = firstNumberIndex; i <= lastNumberIndex; ++i) {
            try {
                add += Double.parseDouble(elements[i]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Некорректно указаны числа");
            }
        }

        return add;
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args[0].equals("-") && args[1].equals("-")) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите название операции и числа");

            String line = scanner.nextLine();
            System.out.println(getMultiplicationOrAddition(line));
        } else if (args[0].equals("input.txt") && args[1].equals("output.txt")) {
            File input = new File(args[0]);

            try (PrintWriter writer = new PrintWriter(args[1]);
                 Scanner scanner = new Scanner(new FileInputStream(input))) {

                String line = scanner.nextLine();
                writer.println(getMultiplicationOrAddition(line));
            }
        } else {
            throw new IllegalArgumentException("Некорректно указаны аргументы программы");
        }
    }
}