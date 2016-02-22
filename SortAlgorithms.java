/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortalgorithms;

import java.util.Random;
import java.lang.*;
import java.util.Arrays;

/**
 * import java.lang.*;
 *
 *
 * @author Kathia
 */
public class SortAlgorithms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int[] array = new int[100];
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(Integer.MAX_VALUE);
        }

        long time1 = System.nanoTime();
        RadixSort(array);
  
        System.out.println("Tiempo de Ejecucion: " + (System.nanoTime() - time1) * 0.000000001 + " segundos");

    }
    public static void RadixSort(int [] array){
        int i;
  	int[] b = new int[array.length];
  	int max = array[0];
 	for (i = 0; i < array.length; i++) {
 		if (array[i] > max)
			max = array[i];
 	}
        for(int exp = 1; max/exp > 1; exp *=10){
            int [] bucket = new int[array.length];
            int [] count = new int[10];
            for(i = 0 ; i < array.length ; i++){
                count[(array[i]/exp)%10]++;
            }
            for(i=1; i <10;i++){
                count[i] += count[i-1];
            }
            for(i=array.length - 1; i >= 0 ; i--){
                bucket[count[(array[i]/exp)%10]-1] = array[i];
                count[(array[i]/exp)%10]--;
            }
            for(i = 0 ; i < array.length; i++){
                array[i] = bucket[i];
            }
        }
	
    }

    public static void HeapSort(int[] array) {
        int lastLeaf = 0;
        for (int i = 1; i < array.length; i++) {
            lastLeaf = i;
            int index = i;
            while (index != 0) {
                int parentIndex;
                if (index % 2 == 0) {
                    parentIndex = (index - 2) / 2;
                } else {
                    parentIndex = (index - 1) / 2;
                }
                if (array[parentIndex] < array[index]) {
                    int temp = array[index];
                    array[index] = array[parentIndex];
                    array[parentIndex] = temp;
                    index = parentIndex;

                } else {
                    index = 0;
                }
            }
        }

        while (lastLeaf != 0) {
            int temp = array[lastLeaf];
            array[lastLeaf] = array[0];
            array[0] = temp;
            boolean bool = true;
            int index = 0;
            lastLeaf--;
            while (bool) {
                int leftSon, rightSon;
                if (2 * index + 2 <= lastLeaf) {
                    leftSon = array[2 * index + 1];
                    rightSon = array[2 * index + 2];
                    if (leftSon >= rightSon) {
                        if (leftSon > array[index]) {
                            array[2 * index + 1] = array[index];
                            array[index] = leftSon;
                            index = 2 * index + 1;
                        } else {
                            bool = false;
                        }
                    } else if (rightSon > array[index]) {
                        array[2 * index + 2] = array[index];
                        array[index] = rightSon;
                        index = 2 * index + 2;
                    } else {
                        bool = false;
                    }
                } else if (2 * index + 1 <= lastLeaf) {
                    leftSon = array[2 * index + 1];
                    if (leftSon > array[index]) {
                        array[2 * index + 1] = array[index];
                        array[index] = leftSon;
                        index = 2 * index + 1;
                    } else {
                        bool = false;
                    }
                } else {
                    bool = false;
                }
            }
        }

    }

    public static void BubbleSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {

                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static void InsertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i;
            while (j > 0 && array[j - 1] > temp) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
    }

    public static void SelectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = array[min];
                array[min] = array[i];
                array[i] = min;
            }
        }
    }

    public static void MergeSort(int[] array, int n) {
        if (n <= 1) {
            return;
        }
        int m = n / 2;
        int[] array2 = Arrays.copyOfRange(array, 0, m);
        int[] array3 = Arrays.copyOfRange(array, m, n);
        MergeSort(array2, m);
        MergeSort(array3, m);
        int b = 0, c = 0;
        for (int i = 0; i < n; i++) {
            if (b < m && c < n - m) {

                if (array2[b] < array3[c]) {
                    array[i] = array2[b];
                    b++;
                } else {
                    array[i] = array3[c];
                    c++;
                }
            } else if (b < m) {
                array[i] = array2[b];
                b++;
            } else {
                array[i] = array3[c];
                c++;
            }
        }
    }

    public static void QuickSort(int[] array, int left, int right) {
        int index = left, j = right;
     
        int pivot = array[(left + right)/ 2] ;
        while (index <= j) {
            while (array[index] < pivot) {
                index++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (index <= j) {
                int temp = array[index];
                array[index] = array[j];
                array[j] = temp;
                index++;
                j--;
            }
        }
        if (left < index - 1) {
            QuickSort(array, left, index - 1);
        }
        if (index < right) {
            QuickSort(array, index, right);
        }
    }
}
