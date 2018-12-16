package com.hiekn.demo.test.algorithm;

import com.hiekn.demo.test.TestBase;
import org.junit.After;
import org.junit.Test;

import java.util.Arrays;

/**
 * study algorithm
 *
 * @author: DingHao
 * @date: 2018/12/15 22:14
 */
public class AlgorithmDemo extends TestBase {

    private static final int[] arr = {-2,11,-4,13,-5,-2};

    @After
    public void out() {
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void pow(){
        System.out.println(pow(2,10));
    }

    /**
     * O(logN)
     * @param x
     * @param n
     * @return
     */
    private long pow(long x,int n){
        if(n == 0){
            return 1;
        }
        if(n == 1){
            return x;
        }
        if(n % 2 == 0){
            return pow(x * x,n / 2);
        }else {
            return pow(x * x,n / 2) * x;
        }
    }

    /**
     * 公因数也叫公约数
     * 计算最大公因数的欧几里得算法
     */
    @Test
    public void gcd(){
        long m = 1989;
        long n = 1590;
        while (n != 0){
            long rem = m % n;
            m = n;
            n = rem;
        }
        System.out.println(m);
    }

    @Test
    public void binarySearch(){
        int[] sortArr = {1,3,15,37,49,71,88,99};
        int low = 0,high = sortArr.length - 1;
        int x = 99;
        int mid = -1;
        while (low <= high){
            mid = (low + high) / 2;
            if(x < sortArr[mid]){
                high = mid - 1;
            }else if(x > sortArr[mid]){
                low = mid + 1;
            }else{
                break;
            }
        }
        System.out.println(mid);
    }

    @Test
    public void maxSubSum4(){
        int maxSum = 0;
        int firstIndex = -1;
        int lastIndex = 0;
        int thisSum = 0;
        for (int i = 0; i < arr.length; i++) {
            thisSum += arr[i];
            if(thisSum > maxSum){
                maxSum = thisSum;
                if(firstIndex == -1 && arr[i] > 0){
                    firstIndex = i;
                }
                lastIndex = i;
            }else if(thisSum < 0){
                thisSum = 0;
            }
        }
        System.out.println(Arrays.toString(Arrays.copyOfRange(arr,firstIndex,lastIndex+1)));
        System.out.println(maxSum);
    }

    @Test
    public void maxSubSum3(){

    }

    @Test
    public void maxSubSum2(){
        int maxSum = 0;
        int firstIndex = 0;
        int lastIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            int thisSum = 0;
            for (int j = i; j < arr.length; j++) {
                thisSum += arr[j];
                if(thisSum > maxSum){
                    maxSum = thisSum;
                    firstIndex= i;
                    lastIndex= j;
                }
            }
        }
        System.out.println(Arrays.toString(Arrays.copyOfRange(arr,firstIndex,lastIndex+1)));
        System.out.println(maxSum);
    }


    @Test
    public void maxSubSum1(){
        int maxSum = 0;
        int firstIndex = 0;
        int lastIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int thisSum = 0;
                for (int k = i; k <= j; k++) {
                    thisSum += arr[k];
                    if(thisSum > maxSum){
                        maxSum = thisSum;
                        firstIndex= i;
                        lastIndex= j;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(Arrays.copyOfRange(arr,firstIndex,lastIndex+1)));
        System.out.println(maxSum);
    }



    /**
     * 找到最小数就交换位置,  稳定
     */
    @Test
    public void bubbleSort() {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    /**
     * 找到最小数的小标，最后交换一次位置,   不稳定
     */
    @Test
    public void selectSort() {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }

    }


    /**
     * 稳定
     */
    @Test
    public void insertSort() {
        int i, j, insertNote;// 要插入的数据
        for (i = 1; i < arr.length; i++) {// 从数组的第二个元素开始循环将数组中的元素插入
            insertNote = arr[i];// 设置数组中的第2个元素为第一次循环要插入的数据
            j = i - 1;
            while (j >= 0 && insertNote < arr[j]) {
                arr[j + 1] = arr[j];// 如果要插入的元素小于第j个元素,就将第j个元素向后移动
                j--;
            }
            arr[j + 1] = insertNote;// 直到要插入的元素不小于第j个元素,将insertNote插入到数组中
        }
    }

    /**
     * 不稳定
     */
    @Test
    public void quickSort() {
        quick(arr, 0, arr.length - 1);
    }

    private void quick(int[] arr, int low, int high) {
        int pivot, p_pos, i, t;// pivot->位索引;p_pos->轴值。
        if (low < high) {
            p_pos = low;
            pivot = arr[p_pos];
            for (i = low + 1; i <= high; i++) {
                if (arr[i] < pivot) {
                    p_pos++;
                    swap(arr, i, p_pos);
                }
            }
            swap(arr, low, p_pos);
            // 分而治之
            quick(arr, low, p_pos - 1);// 排序左半部分
            quick(arr, p_pos + 1, high);// 排序右半部分
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
