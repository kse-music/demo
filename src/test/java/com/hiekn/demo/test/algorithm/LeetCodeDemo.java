package com.hiekn.demo.test.algorithm;

import com.hiekn.demo.test.TestBase;
import org.junit.Test;

import java.util.*;

/**
 * zt
 *
 * @author: DingHao
 * @date: 2019/4/3 14:21
 */
public class LeetCodeDemo extends TestBase {


    @Test
    public void twoSum() {
        Map<IndexValue, IndexValue> indexValueIndexValueMap = twoSum(new int[]{1, 2, 2, 4, 5, 6, 7}, 9);
        System.out.println(indexValueIndexValueMap);
    }

    static class IndexValue{
        int index;
        int value;

        public IndexValue(int index, int value) {
            this.index = index;
            this.value = value;
        }

    }

    public Map<IndexValue, IndexValue> twoSum(int[] arr, int sum) {
        Map<IndexValue, IndexValue> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[i] + arr[j] == sum){
                    map.put(new IndexValue(i,arr[i]),new IndexValue(j,arr[j]));
                }
            }
        }
        return map;
    }

    @Test
    public void addDigits() {
        System.out.println(addDigits(121));
    }

    public int addDigits(int num) {
        return 1 + (num - 1) % 9;
    }

    @Test
    public void addBinary() {
        System.out.println(addBinary("1111", "111"));
    }

    private void fillStr(StringBuilder sb, int len) {
        for (int i = 0; i < len; i++) {
            sb.insert(0, "0");
        }
    }

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder(a);
        StringBuilder sb2 = new StringBuilder(b);
        int maxLen = Math.max(a.length(), b.length());
        fillStr(a.length() > b.length() ? sb2 : sb1, Math.abs(a.length() - b.length()));
        boolean j = false;
        for (int i = maxLen - 1; i >= 0; i--) {
            char s1 = sb1.charAt(i);
            char s2 = sb2.charAt(i);
            if (j) {
                if (s1 != s2) {
                    sb.append("0");
                } else {
                    sb.append("1");
                    if (s1 == '0') {
                        j = false;
                    }
                }
            } else {
                if (s1 != s2) {
                    sb.append("1");
                } else {
                    sb.append("0");
                    if (s1 == '1') {
                        j = true;
                    }
                }
            }
        }
        if (j) {
            sb.append("1");
        }
        return sb.reverse().toString();
    }

    @Test
    public void numDupDigitsAtMostN() {
        long s = System.currentTimeMillis();
        System.out.println(numDupDigitsAtMostN(5987490));
        System.out.println(System.currentTimeMillis() - s);
    }

    public int numDupDigitsAtMostN(int N) {
        // Transform N + 1 to arrayList
        List<Integer> L = new ArrayList<>();
        for (int x = N + 1; x > 0; x /= 10) {
            L.add(0, x % 10);
        }

        // Count the number with digits < N
        int res = 0, n = L.size();
        for (int i = 1; i < n; ++i) {
            res += 9 * A(9, i - 1);
        }

        // Count the number with same prefix
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            for (int j = i > 0 ? 0 : 1; j < L.get(i); ++j) {
                if (!seen.contains(j)) {
                    res += A(9 - i, n - i - 1);
                }
            }
            if (seen.contains(L.get(i))) {
                break;
            }
            seen.add(L.get(i));
        }
        return N - res;
    }

    public int A(int m, int n) {
        return n == 0 ? 1 : A(m, n - 1) * (m - n + 1);
    }

}
