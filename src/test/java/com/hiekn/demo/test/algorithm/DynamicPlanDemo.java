package com.hiekn.demo.test.algorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * 动态规划算法的核心就是记住已经解决过的子问题的解
 * <p>
 * 求解的方式有两种：1.自顶向下的备忘录法
 * 2.自底向上。
 *
 *
 *
 * 每个阶段只有一个状态->递推；
 * 每个阶段的最优状态都是由上一个阶段的最优状态得到的->贪心；
 * 每个阶段的最优状态是由之前所有阶段的状态的组合得到的->搜索；
 * 每个阶段的最优状态可以从之前某个阶段的某个或某些状态直接得到而不管之前这个状态是如何得到的->动态规划。
 *
 * @author: DingHao
 * @date: 2019/4/23 23:31
 */
public class DynamicPlanDemo {

    @Test
    public void fibonacci() {
        System.out.println(fibonacci(6));
        System.out.println(fib(6));
    }

    public int fibonacci(int n) {
        if (n <= 0)
            return n;
        int[] memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
        return fib(n, memo);
    }

    public int fib(int n, int[] memo) {
        if (memo[n] != -1) {
            return memo[n];
        }
        //如果已经求出了fib（n）的值直接返回，否则将求出的值保存在Memo备忘录中。
        if (n <= 2) {
            memo[n] = 1;
        } else {
            memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
        }

        return memo[n];
    }

    public static int fib(int n) {
        if (n <= 0) {
            return n;
        }
        int[] memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }

    @Test
    public void dynamicPlan() {
        int trees = 5;
        int[] peaches = {10, 4, 5, 12, 8};
        int[] dp = new int[trees];
        for (int i = 0; i < trees; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                /**
                 * 表示，第j个位置上的树可以拿，并且拿了桃子的话，总大小能够超过第i个位置
                 */
                if (peaches[j] <= peaches[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        int[] pos = new int[trees];
        int max = 1;
        for (int i = 0; i < dp.length; i++) {
            int current = i;
            int prev = i - 1;
            int next = i + 1;
            if (dp[current] > 1) {
                if (pos[prev] == 0) {
                    pos[prev] = 1;
                }
                pos[current] = 1;
                if (current != dp.length - 1 && dp[current] == dp[next]) {
                    pos[current] = -1;
                }
            }
            if (max < dp[current]) {
                max = dp[current];
            }
        }
        System.out.println(Arrays.toString(pos));
        System.out.println(max);
    }

}
