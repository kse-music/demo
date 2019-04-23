package com.hiekn.demo.test.algorithm;

import com.google.common.collect.Lists;
import com.hiekn.demo.test.TestBase;
import org.junit.Test;

import java.util.*;

/**
 * study algorithm
 *
 * @author: DingHao
 * @date: 2018/12/15 22:14
 */
public class AlgorithmDemo extends TestBase {

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



}
