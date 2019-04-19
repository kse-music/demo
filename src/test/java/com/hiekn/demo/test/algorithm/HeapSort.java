package com.hiekn.demo.test.algorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * 大顶堆 和 小顶堆
 *
 * @author: DingHao
 * @date: 2019/4/19 22:43
 */
public class HeapSort {

    @Test
    public void heap() {
        int[] arr = new int[]{56, 30, 71, 18, 29, 93, 44, 75, 20, 65, 68, 34};
        System.out.println(Arrays.toString(topMax(arr, 5)));//[65, 68, 71, 75, 93]
        System.out.println(Arrays.toString(topMin(arr, 5)));//[34, 30, 20, 18, 29]
    }

    public static int[] topMax(int[] input,int topN){
        return top(input,topN,true);
    }

    public static int[] topMin(int[] input,int topN){
        return top(input,topN,false);
    }

    private static int[] top(int[] input,int topN,boolean topMax){
        int[] result = Arrays.copyOf(input, topN);

        for (int i = 0; i < topN; i++) {
            int currentIndex = i;
            while (currentIndex != 0 && topMax ? result[parentIndex(currentIndex)] > result[currentIndex] : result[parentIndex(currentIndex)] < result[currentIndex]){
                swap(result,currentIndex,currentIndex = parentIndex(currentIndex));
            }
        }

        for (int i = topN; i < input.length; i++) {
            if(topMax){//小顶堆
                if(input[i] > result[0]){
                    result[0] = input[i];
                    int from = 0;
                    while ((leftIndex(from) < topN && result[from] > result[leftIndex(from)])
                            || (rightIndex(from) < topN && result[from] > result[rightIndex(from)])){
                        //和小的交换，大的下沉
                        if(rightIndex(from) < topN && result[leftIndex(from)] > result[rightIndex(from)]){
                            swap(result,from,from = rightIndex(from));
                        }else{
                            swap(result,from,from = leftIndex(from));
                        }
                    }
                }
            }else{//大顶堆
                if(input[i] < result[0]){
                    result[0] = input[i];
                    int from = 0;
                    while ((leftIndex(from) < topN && result[from] < result[leftIndex(from)])
                            || (rightIndex(from) < topN && result[from] < result[rightIndex(from)])){
                        //和大的交换，小的下沉
                        if(rightIndex(from) < topN && result[leftIndex(from)] < result[rightIndex(from)]){
                            swap(result,from,from = rightIndex(from));
                        }else{
                            swap(result,from,from = leftIndex(from));
                        }
                    }
                }
            }

        }

        return result;
    }

    private static void swap(int[] inputN,int i,int j){
        int temp = inputN[i];
        inputN[i] = inputN[j];
        inputN[j] = temp;
    }

    private static int parentIndex(int i) {
        return (i - 1) / 2;
    }

    private static int leftIndex(int i) {
        return 2 * i + 1;
    }

    private static int rightIndex(int i) {
        return 2 * i + 2;
    }

}
