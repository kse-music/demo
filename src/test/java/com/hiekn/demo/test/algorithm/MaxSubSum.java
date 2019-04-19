package com.hiekn.demo.test.algorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * 最大子序列
 *
 * @author: DingHao
 * @date: 2019/4/19 23:51
 */
public class MaxSubSum {

    private static final int[] arr = {-2,11,-4,13,-5,-2};

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


}
