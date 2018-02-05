package com.hiekn.demo.test.java;


/**
 * https://www.zhihu.com/question/20400700
 *
 * 协变和逆变指的是宽类型和窄类型在某种情况下的替换或交换的特性。
 * 简单的说，协变就是用一个窄类型替代宽类型，而逆变则用宽类型覆盖窄类型。
 */
public class GenericTest {
    public static void main(String[] args) throws Exception {
        //1.上界通配符<? extends T>
//        Plate<Fruit> p = new Plate<Apple>(new Apple());//装苹果的盘子”无法转换成“装水果的盘子”
        Plate<? extends Fruit> p = new Plate<>(new Apple());//Plate<？ extends Fruit>是Plate<Fruit>以及Plate<Apple>的基类
        //2.下界通配符<? super T>
        //一个能放水果以及一切是水果基类的盘子。Plate<？ super Fruit>是Plate<Fruit>的基类，但不是Plate<Apple>的基类

        //******副作用********
        //不能存入任何元素
        //标上一个占位符：CAP#1，来表示捕获一个Fruit或Fruit的子类，具体是什么类不知道，所以就都不允许。
//        p.set(new Fruit());    //Error
//        p.set(new Apple());    //Error


        //读取出来的东西只能存放在Fruit或它的基类里。
        Fruit newFruit1 = p.get();
        Object newFruit2 = p.get();
//        Apple newFruit3=p.get();    //Error

        //**************************************************************************************//

        //下界<? super T>不影响往里存，但往外取只能放在Object对象里
        Plate<? super Fruit> p2 = new Plate<>(new Fruit());
        p2.set(new Fruit());
        p2.set(new Apple());

        //读取出来的东西只能存放在Object类里。
//        Fruit newFruit3 = p2.get();//Error
//        Fruit newFruit11 = p2.get();//Error
        Object newFruit22= p2.get();    //Error

        /*
        PECS（Producer Extends Consumer Super）原则
        1.频繁往外读取内容的，适合用上界Extends。
        2.经常往里插入的，适合用下界Super。
         */
        System.out.println(genericMethod(GenericTest.class));
    }

    /**
     *
     * 泛型有三种使用方式，分别为：泛型类、泛型接口、泛型方法
     *  * 说明：
     *     1）public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
     *     2）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
     *     3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
     *     4）与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
     *
     *   注意：1.  泛型通配符?,?是一种类型实参
     *     2. 静态方法无法访问类上定义的泛型
     */
    public static <T> T genericMethod(Class<T> tClass)throws InstantiationException ,IllegalAccessException{
        T instance = tClass.newInstance();
        return instance;
    }
}


class Plate<T> {
    private T item;

    public Plate(T t) {
        item = t;
    }

    public void set(T t) {
        item = t;
    }

    public T get() {
        return item;
    }
}

//Lev 1
class Food{}

class Fruit extends Food{}

//Lev 2
class Meat extends Food{}

//Lev 3
class Apple extends Fruit {}
class Banana extends Fruit{}
class Pork extends Meat{}
class Beef extends Meat{}

//Lev 4
class RedApple extends Apple{}
class GreenApple extends Apple{}
