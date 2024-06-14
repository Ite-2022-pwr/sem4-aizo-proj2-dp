package pl.pwr.ite.aizo2.service.utils;

import pl.pwr.ite.aizo2.model.IncidenceMatrix;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class HeapSort {

    public static <T> void sort(T[] array, BiFunction<T, T, Integer> compareFunction) {
        buildHeap(array, compareFunction);
        for(int i = array.length - 1; i > 0; i--) {
            T tmp = array[0];
            array[0] = array[i];
            array[i] = tmp;
            heapify(array, i, 0, compareFunction);
        }
    }

    private static <T> void buildHeap(T[] array, BiFunction<T, T, Integer> compareFunction) {
        int n = array.length;
        for(int i = n / 2; i >= 0; i--) {
            heapify(array, n, i, compareFunction);
        }
    }

    private static <T> void heapify(T[] array, int size, int root, BiFunction<T, T, Integer> compareFunction) {
        int left = 2 * root + 1;
        int right = 2 * root + 2;
        int largest = root;

        if(left < size && compareFunction.apply(array[left], array[largest]) > 0) {
            largest = left;
        }
        if(right < size && compareFunction.apply(array[right], array[largest]) > 0) {
            largest = right;
        }
        if(largest != root) {
            T temp = array[root];
            array[root] = array[largest];
            array[largest] = temp;
            heapify(array, size, largest, compareFunction);
        }
    }
}
