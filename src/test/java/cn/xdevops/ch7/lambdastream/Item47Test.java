package cn.xdevops.ch7.lambdastream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Comparator.comparing;
import static org.junit.jupiter.api.Assertions.fail;

public class Item47Test {

    @Test
    @DisplayName("test convert stream to iterable")
    void testConvertStreamToIterable() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 5, 8, 13);
        Iterable<Integer> iter = iterableOf(stream);
        for(Integer i : iter) {
            // stream有forEach方法，这里只是做个演示
            System.out.println(i);
        }
    }

    private <E> Iterable<E> iterableOf(Stream<E> stream) {
        return stream::iterator;
    }

    @Test
    @DisplayName("test convert iterable to stream")
    void testConvertIterableToStream() {
        // list.stream() 可以直接转成stream，这里只是做个演示
        // 针对实现了Iterable接口的自定义集合
        List<String> list = Arrays.asList("effective", "java", "stream", "iterable");
        Stream<String> stream = streamOf(list);
        stream.sorted(comparing(String::length))
                .forEach(System.out::println);
    }

    private <E> Stream<E> streamOf(Iterable<E> iter) {
        return StreamSupport.stream(iter.spliterator(), false);
    }
}
