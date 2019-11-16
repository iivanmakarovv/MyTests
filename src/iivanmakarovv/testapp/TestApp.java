package iivanmakarovv.testapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TestApp {
    private static double getMultiplyOrSum(String line) {
        String[] elements = line.split(" ");
        ArrayList<Double> list = new ArrayList<>();

        for (int i = 1; i < elements.length; ++i) {
            list.add(Double.parseDouble(elements[i]));
        }

        switch (elements[0]) {
            case "mul":
                return getMultiply(list);
            case "sum":
                return getSum(list);
            case "mulsum":
                return getMultiplyAndSum(list);
            default:
                throw new IllegalArgumentException("Некорректно указаны аргументы программы");
        }
    }

    private static double getMultiply(ArrayList<Double> list) {
        double mul = 1;

        for (Double i : list) {
            mul *= i;
        }

        return mul;
    }

    private static double getSum(ArrayList<Double> list) {
        double sum = 0;

        for (Double i : list) {
            sum += i;
        }

        return sum;
    }

    private static double getMultiplyAndSum(ArrayList<Double> list) {
        double mulsum = 1;
        int i = 0;

        for (Double e : list) {
            if (i < 2) {
                mulsum *= e;
            } else {
                mulsum += e;
            }
            ++i;
        }

        return mulsum;
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