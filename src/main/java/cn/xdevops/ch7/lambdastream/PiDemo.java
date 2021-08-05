package cn.xdevops.ch7.lambdastream;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class PiDemo {

    private static long pi(long n) {
        return LongStream.rangeClosed(2, n)
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }

    private static long piParallel(long n) {
        return LongStream.rangeClosed(2, n)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        long pi = piParallel(BigInteger.valueOf(10).pow(8).longValue());
        long end = System.nanoTime();
        System.out.println(pi);
        System.out.println(String.format("Elapsed time: %d ms", (end - start) / 1000000));
    }
}
