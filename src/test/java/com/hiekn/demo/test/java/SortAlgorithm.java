package com.hiekn.demo.test.java;

import java.util.Arrays;

public class SortAlgorithm {
	public static void main(String[] args) {
		int[] arr = {77,99,44,55,22,88,11,0,66,33};
//		bubbleSort(arr);
//		selectSort(arr);
//		insertSort(arr);
      quickSort(arr,0,arr.length-1);
		System.out.println(Arrays.toString(arr));
	}

    /**
     * 找到最小数就交换位置,稳定
     * @param arr
     */
	public static void bubbleSort(int[] arr){
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if(arr[i] > arr[j]){
                    swap(arr,i,j);
                }
			}
		}
	}

    /**
     * 找到最小数的小标，最后交换一次位置，不稳定
     * @param arr
     */
	public static void selectSort(int[] arr){
		for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
				if(arr[min] > arr[j]){
					min = j;
				}
			}
            swap(arr,i,min);
		}
	}

	private static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 稳定
     * @param arr
     */
	public static void insertSort(int[] arr){
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
     * @param arr
     * @param low
     * @param high
     */
    public static void quickSort(int[] arr, int low, int high) {// 传入low=0，high=array.length-1;
        int pivot, p_pos, i, t;// pivot->位索引;p_pos->轴值。
        if (low < high) {
            p_pos = low;
            pivot = arr[p_pos];
            for (i = low + 1; i <= high; i++){
                if (arr[i] < pivot) {
                    p_pos++;
                    swap(arr,i,p_pos);
                }
            }
            swap(arr,low,p_pos);
            // 分而治之
            quickSort(arr, low, p_pos - 1);// 排序左半部分
            quickSort(arr, p_pos + 1, high);// 排序右半部分
        }
    }

}
