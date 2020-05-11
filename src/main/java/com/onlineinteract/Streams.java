package com.onlineinteract;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The Java Streams API contains a set of non-terminal operations:
 * filter, map, flatMap, distinct, limit, peek
 * <p>
 * The Java Streams API contains a set of terminal operations:
 * anyMatch/all Match/noneMatch, collect, count, findAny/findAll, forEach, min/max, reduce, toArray
 * <p>
 * It is possible to concatenate multiple streams into one stream.
 * <p>
 * It is possible to do parallel stream processing.
 */
public class Streams {
    public static void main(String[] args) throws IOException, URISyntaxException {
        new Streams().samples();
    }

    public void samples() throws IOException, URISyntaxException {
        // 1. Integer Stream.
        IntStream
                .range(1, 10)
                .forEach(System.out::println);
        System.out.println();

        // 2. Integer Stream with skip.
        IntStream
                .range(1, 10)
                .skip(5)
                .forEach(x -> System.out.println(x));
        System.out.println();

        // 3. Integer Stream with sum.
        System.out.println(
                IntStream
                        .range(1, 5)
                        .sum()
        );
        System.out.println();

        // 4. Stream.of, sorted and findFirst.
        Stream.of("Gary", "Derek", "Alicia")
                .filter(x -> x.startsWith("Ga"))
                .sorted()
                .findFirst()
                .ifPresent(System.out::println);
        System.out.println();

        // 5. Stream from array, sort, filer and print.
        String[] names = {"Gary", "Derek", "Alicia", "Nadine", "George"};
        Arrays.stream(names)
                .filter(x -> x.startsWith("G"))
                .sorted()
                .forEach(System.out::println);
        System.out.println();

        // 6. Average of squares of an int array.
        Arrays.stream(new int[]{2, 4, 6, 8, 10})
                .map(x -> x * x)
                .average()
                .ifPresent(System.out::println);
        System.out.println();

        // 7. Stream from List, filer and print.
        Stream.of("Gary", "Derek", "Alicia")
                .filter(x -> x.length() > 4)
                .sorted()
                .forEach(System.out::println);
        System.out.println();

        // 8. Stream rows from text file, sort, filer and print.
        URL url = getClass().getResource("/bands.txt");
        Path path = (Paths.get(url.toURI()));
        Stream<String> bands = Files.lines(path);
        bands
                .sorted()
                .filter(x -> x.length() < 14)
                .forEach(System.out::println);
        bands.close();
        System.out.println();

        // 9. Stream rows from text file and save to list.
        List<String> bands2 = Files.lines(path)
                .filter(x -> x.contains("ovi"))
                .collect(Collectors.toList());
        bands2.forEach(x -> System.out.println(x));
        System.out.println();

        // 10. Stream rows from CSV file and count.
        Stream<String> rows1 = Files.lines(Paths.get(getClass().getResource("/data.txt").toURI()));
        int rowCount = (int) rows1
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .count();
        System.out.println(rowCount + " rows.");
        System.out.println();

        // 11. Stream rows from csv file, parse data from rows.
        Stream<String> rows2 = Files.lines(Paths.get(getClass().getResource("/data.txt").toURI()));
        rows2
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .forEach(x -> System.out.println(x[0] + " " + x[1] + " " + x[2]));
        System.out.println();

        // 12. Stream rows from CSV file, store fields in HashMap.
        Stream<String> rows3 = Files.lines(Paths.get(getClass().getResource("/data.txt").toURI()));
        Map<String, Integer> map = new HashMap<>();
        map = rows3
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .collect(Collectors.toMap(
                        x -> x[0],
                        x -> Integer.parseInt(x[1])));
        rows3.close();
        for (String key : map.keySet()) {
            System.out.println("key: " + key + "  value: " + map.get(key));
        }
        System.out.println();

        // 13. Reduction - sum.
        double total = Stream.of(7.3, 1.5, 4.8)
                .reduce(0.0, (Double a, Double b) -> a + b);
        System.out.println("Total = " + total);
        System.out.println();

        // 14. Reduction - summary statistics.
        IntSummaryStatistics summary = IntStream.of(7, 2, 19, 88, 73, 4, 10)
                .summaryStatistics();
        System.out.println(summary);

        class Employee {
            String salary;

            public Employee(String salary) {
                this.salary = salary;
            }

            public String getSalary() {
                return salary;
            }

            public void setSalary(String salary) {
                this.salary = salary;
            }
        }
        System.out.println();

        // 15. Reduction -> Employee Salary.
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("5000"));
        list.add(new Employee("6000"));
        list.add(new Employee("4000"));

        int sum = list.stream()
                .map(employee -> employee.salary)
                .map(Integer::parseInt)
                .reduce(0, (tempSum, value) -> tempSum + value);
        System.out.println("salary sum = " + sum);
    }
}
