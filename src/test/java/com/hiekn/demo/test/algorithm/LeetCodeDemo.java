package com.hiekn.demo.test.algorithm;

import com.hiekn.demo.test.TestBase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * zt
 *
 * @author: DingHao
 * @date: 2019/4/3 14:21
 */
public class LeetCodeDemo extends TestBase {

    public int addDigits(int num) {
        return 1 + (num - 1) % 9;
    }

    @Test
    public void addDigits(){
        System.out.println(addDigits(121));
    }

    @Test
    public void addBinary(){
        System.out.println(addBinary("1111","111"));
    }

    private void fillStr(StringBuilder sb,int len){
        for (int i = 0; i < len; i++) {
            sb.insert(0,"0");
        }
    }

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder(a);
        StringBuilder sb2 = new StringBuilder(b);
        int maxLen = Math.max(a.length(),b.length());
        fillStr(a.length() > b.length()?sb2:sb1,Math.abs(a.length() - b.length()));
        boolean j = false;
        for (int i =  maxLen - 1; i >= 0; i--) {
            char s1 = sb1.charAt(i);
            char s2 = sb2.charAt(i);
            if(j){
                if(s1 != s2){
                    sb.append("0");
                }else {
                    sb.append("1");
                    if(s1 == '0'){
                        j = false;
                    }
                }
            }else{
                if(s1 != s2){
                    sb.append("1");
                }else {
                    sb.append("0");
                    if(s1 == '1'){
                        j = true;
                    }
                }
            }
        }
        if(j){
            sb.append("1");
        }
        return sb.reverse().toString();
    }

    @Test
    public void numDupDigitsAtMostN(){
        long s = System.currentTimeMillis();
        System.out.println(numDupDigitsAtMostN(5987490));
        System.out.println(System.currentTimeMillis()-s);
    }

    public int numDupDigitsAtMostN(int N) {
        // Transform N + 1 to arrayList
        List<Integer> L = new ArrayList<>();
        for (int x = N + 1; x > 0; x /= 10){
            L.add(0, x % 10);
        }

        // Count the number with digits < N
        int res = 0, n = L.size();
        for (int i = 1; i < n; ++i){
            res += 9 * A(9, i - 1);
        }

        // Count the number with same prefix
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            for (int j = i > 0 ? 0 : 1; j < L.get(i); ++j){
                if (!seen.contains(j)){
                    res += A(9 - i, n - i - 1);
                }
            }
            if (seen.contains(L.get(i))){
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
