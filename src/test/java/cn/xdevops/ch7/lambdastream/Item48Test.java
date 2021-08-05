package cn.xdevops.ch7.lambdastream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.stream.LongStream;

class Item48Test {

    @Test
    @DisplayName("testPi")
    void testPi() {
//        long pi = pi(BigInteger.valueOf(10).pow(8).longValue());
        long pi = piParallel(BigInteger.valueOf(10).pow(8).longValue());
        System.out.println(pi);
    }

    private long pi(long n) {
        return LongStream.rangeClosed(2, n)
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }

    private long piParallel(long n) {
        return LongStream.rangeClosed(2, n)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }
}
