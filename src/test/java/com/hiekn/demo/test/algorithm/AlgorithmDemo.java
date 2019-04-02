package com.hiekn.demo.test.algorithm;

import com.google.common.collect.Lists;
import com.hiekn.demo.test.TestBase;
import org.junit.After;
import org.junit.Test;

import java.util.*;

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
    public void numDupDigitsAtMostN(){
        int input = 5987490;
        long s = System.currentTimeMillis();
        System.out.println(numDupDigitsAtMostN(input));
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
            System.out.println(res);
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

    @Test
    public void consistentHash(){
        ConsistentHash h = new ConsistentHash();
        h.addNode(new ConsistentHash.Node(1));
        h.addNode(new ConsistentHash.Node(2));
//      h.addNode(new ConsistentHash.Node(3));
//      h.addNode(new ConsistentHash.Node(4));
//      h.addNode(new ConsistentHash.Node(5));
//      h.addNode(new ConsistentHash.Node(6));
//      h.addNode(new ConsistentHash.Node(7));
//      h.addNode(new ConsistentHash.Node(8));
//      h.addNode(new ConsistentHash.Node(9));

        for (int i = 0; i < 50; i++) {
            h.getNode("" + i);
        }
    }

    @Test
    public void lru(){
        LRULinkedHashMap<String,String> lru = new LRULinkedHashMap<>(4);
        lru.put("1","one");
        lru.put("2","two");
        lru.put("3","three");
        lru.put("4","four");

        lru.get("1");
        lru.put("5","five");

        lru.forEach((k,v) -> System.out.println(k+" = "+v));
    }

    @Test
    public void lfu(){
        LFUMap<String,String> lfu = new LFUMap<>(4);
        lfu.put("1","one");
        lfu.put("2","two");
        lfu.put("3","three");
        lfu.put("4","four");

        lfu.get("1");
        lfu.put("5","five");
        lfu.get("3");
        lfu.put("6","six");

        System.out.println(lfu);

    }

    @Test
    public void dynamicPlan(){

        int trees = 5;
        int[] peaches = {10,4,5,12,8};
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
        int max = 1 ;
        for (int i = 0; i < dp.length; i++) {
            int current = i;
            int prev = i - 1;
            int next = i + 1;
            if(dp[current] > 1){
                if(pos[prev] == 0){
                    pos[prev] = 1;
                }
                pos[current] = 1;
                if(current != dp.length -1 && dp[current] == dp[next]){
                    pos[current] = -1;
                }
            }
            if(max < dp[current]){
                max = dp[current];
            }
        }
        System.out.println(max);
    }


    @Test
    public void sti() {
        System.out.println(myAtoi(" -912834s72332"));
    }

    public int myAtoi(String str) {
        int n = str.length();
        int i = 0;
        while(i < n && str.charAt(i) == ' ') {//开头是空白字符
            i++;
        }
        if(i == n || !((str.charAt(i) == '+') || (str.charAt(i) == '-') ||(str.charAt(i) >= '0' && str.charAt(i) <= '9'))) {//开头非+-和0-9数字
            return 0;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if(str.charAt(i) == '-') {//下一个字符是-
            stringBuilder.append('-');
            i++;
        }else if(str.charAt(i) == '+') {//下一个字符是+
            i++;
        }
        if(i == n || !(str.charAt(i) >= '0' && str.charAt(i) <= '9')) {//下一个字符不是数字则直接返回0
            return 0;
        }
        while(i < n && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            stringBuilder.append(str.charAt(i));
            i++;
        }
        try {
            return Integer.valueOf(stringBuilder.toString());
        }catch (Exception e) {
            if(stringBuilder.substring(0, 1).equals("-")) {
                return Integer.MIN_VALUE;
            }else {
                return Integer.MAX_VALUE;
            }
        }
    }


    @Test
    public void computeAdjacentWords(){

        List<String> words = Lists.newArrayList("fine","foot","nine","boot","shoot","wine","shout","fort");

        Map<String,List<String>> adjWords = new TreeMap<>();

        Map<Integer,List<String>> wordsByLength = new TreeMap<>();

        for (String word : words) {
            update(wordsByLength,word.length(),word);
        }

        for (Map.Entry<Integer, List<String>> entry : wordsByLength.entrySet()) {
            List<String> groupWords = entry.getValue();
            int groupNum = entry.getKey();

            for (int i = 0; i < groupNum; i++) {

                Map<String,List<String>> repToWord = new TreeMap<>();

                for (String str : groupWords) {
                    String rep = str.substring(0,i) + str.substring(i+1);
                    update(repToWord,rep,str);
                }

                for (List<String> wordClique : repToWord.values()) {
                    if(wordClique.size() >= 2){
                        for (String s1 : wordClique) {
                            for (String s2 : wordClique) {
                                if(s1 != s2){
                                    update(adjWords,s1,s2);
                                }
                            }
                        }
                    }
                }

            }

        }

        System.out.println(adjWords);

    }

    private <T> void update(Map<T,List<String>> m, T key, String value){
        List<String> list = m.get(key);
        if(Objects.isNull(list)){
            list = Lists.newArrayList();
            m.put(key,list);
        }
        list.add(value);
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
