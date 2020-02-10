package com.onlineinteract;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
    }
}
