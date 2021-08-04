package cn.xdevops.ch9.general;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Item61Test {

    private static Integer unblieveable;
    // 对装箱类型要初始化
    private static Integer defaultZero = 0;

    @Test
    @DisplayName("testBrokenComparator")
    void testBrokenComparator() {
        List<Integer> numbers = Arrays.asList(2, 5, 1, 3, 13, 8);

        Comparator<Integer> brokenComparator = (a, b) -> (a < b) ? -1 : (a == b) ? 0 : 1;
        numbers.sort(brokenComparator);
        assertThat(numbers).containsExactly(1, 2, 3, 5, 8, 13);

        // 对装箱基本类型运用==操作符几乎总是错误的。（因为这时不是比较值，而是比较对象的同一性）
        assertThat(brokenComparator.compare(42, 42)).isEqualTo(0);
        assertThat(brokenComparator.compare(new Integer(42), new Integer(42))).isNotEqualTo(0);
        // valueOf() 方法可以让这两个对象相同 ？但是只要比内存地址，总是还不安全
        assertThat(brokenComparator.compare(Integer.valueOf(42), Integer.valueOf(42))).isEqualTo(0);

        Comparator<Integer> naturalOrder = (a, b) -> {
            int v1 = a, v2 = b; // auto boxing
            return (v1 < v2) ? -1 : (v1 == v2) ? 0 : 1;
        };

        // 对装箱基本类型运用==操作符几乎总是错误的。（因为这时不是比较值，而是比较对象的同一性）
        assertThat(naturalOrder.compare(42, 42)).isEqualTo(0);
        assertThat(naturalOrder.compare(new Integer(42), new Integer(42))).isEqualTo(0);
        // valueOf() 方法可以让这两个对象相同 ？但是只要比内存地址，总是还不安全
        assertThat(naturalOrder.compare(Integer.valueOf(42), Integer.valueOf(42))).isEqualTo(0);

    }

    @Test
    @DisplayName("testIntegerDefaultNull")
    void testIntegerDefaultNull() {
        assertThat(unblieveable).isNull();
        assertThat(defaultZero).isNotNull().isEqualTo(0);
    }

}
