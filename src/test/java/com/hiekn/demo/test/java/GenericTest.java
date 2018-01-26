package com.hiekn.demo.test.java;

public class GenericTest  {

	public static void main(String[] args) throws Exception{
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
