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

        // Elements??????????????????push()???????????????E???????????????
        // ???????????????????????????????????????Object[]?????????E[]??????????????????????????????????????????????????????????????????
        @SuppressWarnings("unchecked")
        public GenericStack() {
            // ??????????????????????????????non-reifiable)???????????????
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
