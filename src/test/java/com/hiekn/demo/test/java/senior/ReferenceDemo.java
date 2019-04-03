package com.hiekn.demo.test.java.senior;

import com.google.common.collect.Lists;
import com.hiekn.demo.test.TestBase;
import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.WeakHashMap;

/**
 * 强引用
 * 软引用
 * 弱引用
 * 平台引用
 *
 * @author DingHao
 * @date 2019/1/24 11:05
 */
public class ReferenceDemo extends TestBase {

    @Test
    public void javaReference(){

        House house = new House();

        //强引用
        House buyer1 = house;  //最常用

        //软引用:在即将OOM之前，垃圾回收器会把这些软引用指向的对象加入回收范围，以便获得更多的内存空间，让程序能够继续健康的运行。
        SoftReference<House> buyer2 = new SoftReference<>(house);

        //弱引用：如果弱应用指向的对象只存在弱应用这一条线路，则在下一次YGC时会被回收。
        WeakReference<House> buyer3 = new WeakReference<>(house);

        //平台引用：必须配合引用队列使用
        PhantomReference<House> buyer4 = new PhantomReference<>(house,null); //几乎很难用得到

        house = null;

    }

    @Test
    public void weakHashMap(){
        House seller1 = new House("1 号卖家房源");
        SellerInfo sellerInfo1 = new SellerInfo();

        House seller2 = new House("2 号卖家房源");
        SellerInfo sellerInfo2 = new SellerInfo();

        WeakHashMap<House,SellerInfo> weakHashMap = new WeakHashMap<>();
//        Map<House,SellerInfo> weakHashMap = new HashMap<>();
        weakHashMap.put(seller1,sellerInfo1);
        weakHashMap.put(seller2,sellerInfo2);

        System.out.println("WeakHashMap before null , size = " +  weakHashMap.size());

        seller1 = null;

        System.gc();
        System.runFinalization();

        System.out.println("WeakHashMap after null , size = " +  weakHashMap.size());

        System.out.println(weakHashMap);

    }

    @Test
    public void strong() {
        List<House> houses = Lists.newArrayList();
        int i = 0;

        while (true){
            houses.add(new House());
            System.out.println("i = " + (++i));
        }

    }

    @Test
    public void soft() {

        List<SoftReference> houses = Lists.newArrayList();
        int i = 0;
        while (true){
            houses.add(new SoftReference<>(new House()));

            System.out.println("i = " + (++i));

        }

    }

    static class House{
        private static final Integer DOOR_NUMBER = 2000;
        public Door[] doors = new Door[DOOR_NUMBER];
        private String name;
        House(){}
        House(String name){
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        class Door{

        }
    }

    class SellerInfo{

    }

}
