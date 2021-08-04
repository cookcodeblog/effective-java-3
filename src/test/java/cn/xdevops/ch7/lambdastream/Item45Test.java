package cn.xdevops.ch7.lambdastream;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

class Item45Test {

    @Test
    @DisplayName("should find anagram in dictionary")
    void shouldFindAnagramInDictionary() throws FileNotFoundException {
        String fileName = "wordlist-10000.txt";
        int minGroupSize = 4;
        AnagramInDictionary dictionary = new AnagramInDictionary(fileName, minGroupSize);
        List<Set<String>> angarams = dictionary.findAnagrams();
        System.out.println(angarams);

        List<List<String>> angarams2 = dictionary.findAnagrams2();
        System.out.println(angarams2);

    }



    @RequiredArgsConstructor
    class AnagramInDictionary {

        private final String fileName;
        private final int minGroupSize;

        public List<Set<String>> findAnagrams() throws FileNotFoundException {
            // https://www.mit.edu/~ecprice/wordlist.10000
            // http://www.english-for-students.com/Complete-List-of-Anagrams.html
            InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);

            Map<String, Set<String>> groups = new HashMap<>();
            try (Scanner scan = new Scanner(in)) {
                while(scan.hasNext()) {
                    String word = scan.next();
                    groups.computeIfAbsent(alphabetize(word), (unused) -> new TreeSet<>())
                            .add(word);
                }
            }

            List<Set<String>> angarams = new ArrayList<>();

            for (Set<String> group : groups.values()) {
                if (group.size() >= minGroupSize) {
                    angarams.add(group);
                }
            }

//            angarams.stream().filter(group -> group.size() >= minGroupSize).collect(toList());

            return angarams;
        }

        public List<List<String>> findAnagrams2() throws FileNotFoundException {
            // https://www.mit.edu/~ecprice/wordlist.10000
            // http://www.english-for-students.com/Complete-List-of-Anagrams.html
            InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);

            // convert InpuStream to Stream<String>
            // https://stackoverflow.com/questions/30336257/convert-inputstream-into-streamstring-given-a-charset
            try (Stream<String> words = new BufferedReader(new InputStreamReader(in)).lines()) {
                return words.collect(groupingBy(word -> alphabetize(word)))
                        .values().stream()
                        .filter(group -> group.size() >= minGroupSize)
                        .collect(Collectors.toList());
            }
        }



        private String alphabetize(String word) {
            char[] arr = word.toCharArray();
            Arrays.sort(arr);
            return new String(arr);
        }
    }

    @Test
    @DisplayName("test mersenne primes")
    void testMersennePrimes() {
        new MersennePrimes().printMersennePrimes(20);
    }

    class MersennePrimes {
        private Stream<BigInteger> primes() {
            // 无限Stream pipeline
            return Stream.iterate(BigInteger.valueOf(2), BigInteger::nextProbablePrime);
        }

        public void printMersennePrimes(int n) {
            primes().map(p -> BigInteger.valueOf(2).pow(p.intValueExact()).subtract(BigInteger.ONE)) // 2 ** p - 1
                    .filter(mersenne -> mersenne.isProbablePrime(50))
                    .limit(n)
                    .forEach(mp -> System.out.println(mp.bitLength() + ":" + mp)); // log2
        }
    }
}
