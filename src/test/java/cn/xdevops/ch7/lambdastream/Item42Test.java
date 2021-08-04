package cn.xdevops.ch7.lambdastream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparingInt;
import static org.assertj.core.api.Assertions.assertThat;

class Item42Test {

    @Test
    @DisplayName("shouldSortWordsByLength")
    void shouldSortWordsByLength() {
        List<String> words1 = Arrays.asList("effective", "java", "lamdba", "function");
        List<String> words2 = Arrays.asList("effective", "java", "lamdba", "function");
        List<String> words3 = Arrays.asList("effective", "java", "lamdba", "function");
        List<String> words4 = Arrays.asList("effective", "java", "lamdba", "function");

        // 匿名类
        Collections.sort(words1, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });

        // Lambda表达式
        Collections.sort(words2, (s1, s2) -> Integer.compare(s1.length(), s2.length()));

        // Lambda方法引用
        Collections.sort(words3, comparingInt(String::length));

        // List接口中有sort方法
        // static import了Comparator
        words4.sort(comparingInt(String::length));

        assertThat(words1).containsExactly("java", "lamdba", "function", "effective");
        assertThat(words2).containsExactly("java", "lamdba", "function", "effective");
        assertThat(words3).containsExactly("java", "lamdba", "function", "effective");
        assertThat(words4).containsExactly("java", "lamdba", "function", "effective");
    }
}
