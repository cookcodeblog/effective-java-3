package cn.xdevops.ch7.lambdastream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThat;

class Item46Test {

    @Test
    @DisplayName("test stream operator functions")
    void testStreamOperatorFunctions() {
        assertThat(IntStream.of(2, 4, 6, 8, 10, 12).count()).isEqualTo(6); // element count
        assertThat(IntStream.of(2, 4, 6, 8, 10, 12).average().getAsDouble()).isEqualTo(7); // average
        assertThat(IntStream.of(2, 4, 6, 8, 10, 12).sum()).isEqualTo(42); // sum
        assertThat(IntStream.of(2, 4, 6, 8, 10, 12).min().getAsInt()).isEqualTo(2); // min
        assertThat(IntStream.of(2, 4, 6, 8, 10, 12).max().getAsInt()).isEqualTo(12); // max
    }

    @Test
    @DisplayName("test bad stream")
    void testBadStream() {
        String fileName = "code.txt";

        // calculate word frequency in a file
        Map<String, Long> freq = new HashMap<>();
        try (Stream<String> words = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName))).lines()) {
            words.forEach(word -> {
                freq.merge(word.toLowerCase(), 1L, Long::sum);
            });
        }
        System.out.println(freq);

        List<String> topWords = freq.keySet().stream()
                .sorted(comparing(freq::get).reversed())
                .limit(5)
                .collect(toList());

        System.out.println(topWords);
    }

    @Test
    @DisplayName("test collect stream")
    void testCollectStream() {

        String fileName = "code.txt";

        // calculate word frequency in a file
        Map<String, Long> freq;
        try (Stream<String> words =
                     new BufferedReader(
                             new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName))).lines()) {
            // collect to a map<key, value>
            // key: toLowerCase(word), value: frequency
            freq = words.collect(groupingBy(String::toLowerCase, counting()));
        }

        System.out.println(freq);
    }

    @Test
    @DisplayName("test joining stream")
    void testJoiningStream() {
       String joined = Stream.of("came", "saw", "conquered").collect(joining()) ;
       assertThat(Stream.of("came", "saw", "conquered").collect(joining(", "))).isEqualTo("came, saw, conquered");
       assertThat(Stream.of("came", "saw", "conquered").collect(joining(", ", "[", "]"))).isEqualTo("[came, saw, conquered]");

       // similar as String.join()
       String[] arr = {"came", "saw", "conquered"};
       assertThat(String.join(", ", arr)).isEqualTo("came, saw, conquered");
    }
}
