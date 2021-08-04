package cn.xdevops.ch7.lambdastream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Item43Test {

    @Test
    @DisplayName("shouldReturnSumOfList")
    void shouldReturnSumOfList() {
        // https://www.baeldung.com/java-stream-sum
        List<Integer> numbers = Arrays.asList(1, 2, 3, 5, 8, 13);

        assertThat(numbers.stream().reduce(0, (a, b) -> a + b)).isEqualTo(32);
        assertThat(numbers.stream().reduce(0, Integer::sum)).isEqualTo(32);
    }
}
