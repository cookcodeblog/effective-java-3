package cn.xdevops.ch5.generictype;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Item29Test {

    @Test
    @DisplayName("should print all elements in stack")
    void shouldPrintAllElementsInStack() {
        String[] names = {"william", "john", "tom", "lily", "lucy"};

        // The raw type Stack
        // FILO, First In Last Out
        Stack stack = new Stack();
        for (String name : names) {
            stack.push(name);
        }
        while(!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

        System.out.println("--------");

        // The generic type Stack
        // String type Stack
        GenericStack<String> genericStack = new GenericStack<>();
        for (String name : names) {
            genericStack.push(name);
        }
        while(!genericStack.isEmpty()) {
            System.out.println(genericStack.pop());
        }

        System.out.println("--------");

        // Integer type Stack
        Integer[] numbers = {1, 2, 3, 5, 8 , 13};
        GenericStack<Integer> intStack = new GenericStack<>();
        Arrays.stream(numbers).forEach(intStack::push);
        while(!intStack.isEmpty()) {
            System.out.println(intStack.pop());
        }

    }


    class Stack {
        private Object[] elements;
        private int size = 0;
        private static final int DEFAULT_INITIAL_CAPACITY = 16;

        public Stack() {
            elements = new Object[DEFAULT_INITIAL_CAPACITY];
        }

        public void push(Object e) {
            ensureCapacity();
            elements[size++] = e;
        }

        public Object pop() {
            if (size == 0) {
                throw new EmptyStackException();
            }
            Object result = elements[--size];
            elements[size] = null;
            return result;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private void ensureCapacity() {
            if (elements.length == size) {
                elements = Arrays.copyOf(elements, 2 * size + 1);
            }
        }
    }

    public class GenericStack<E> {
        private E[] elements;
        private int size = 0;
        private static final int DEFAULT_INITIAL_CAPACITY = 16;

        // Elements数组中只包含push()方法添加的E类型的实例
        // 类型安全，但是运行时类型为Object[]而不是E[]，所以有非受检警告，该警告不会导致类型不安全
        @SuppressWarnings("unchecked")
        public GenericStack() {
            // 不能创建不可具体化（non-reifiable)类型的数组
//            elements = new E[DEFAULT_INITIAL_CAPACITY];
            elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
        }

        public void push(E e) {
            ensureCapacity();
            elements[size++] = e;
        }

        public E pop() {
            if (size == 0) {
                throw new EmptyStackException();
            }
            E result = elements[--size];
            elements[size] = null;
            return result;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private void ensureCapacity() {
            if (elements.length == size) {
                elements = Arrays.copyOf(elements, 2 * size + 1);
            }
        }
    }
}
