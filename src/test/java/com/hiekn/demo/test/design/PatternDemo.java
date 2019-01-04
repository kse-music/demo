package com.hiekn.demo.test.design;

import com.hiekn.demo.test.TestBase;
import com.hiekn.demo.test.design.adapter.MyPlayer;
import com.hiekn.demo.test.design.adapter.Player;
import com.hiekn.demo.test.design.bridge.BlackPrinter;
import com.hiekn.demo.test.design.bridge.Circle;
import com.hiekn.demo.test.design.bridge.ColorPrinter;
import com.hiekn.demo.test.design.chain.AbstractLogger;
import com.hiekn.demo.test.design.chain.ConsoleLogger;
import com.hiekn.demo.test.design.chain.ErrorLogger;
import com.hiekn.demo.test.design.chain.FileLogger;
import com.hiekn.demo.test.design.decorator.Rectangle;
import com.hiekn.demo.test.design.decorator.RedShapeDecorator;
import com.hiekn.demo.test.design.factory.Shape;
import com.hiekn.demo.test.design.factory.ShapeFactory;
import com.hiekn.demo.test.design.filter.*;
import com.hiekn.demo.test.design.observer.BinaryObserver;
import com.hiekn.demo.test.design.observer.HexObserver;
import com.hiekn.demo.test.design.observer.OctalObserver;
import com.hiekn.demo.test.design.observer.Subject;
import com.hiekn.demo.test.design.prototype.ShapeCache;
import com.hiekn.demo.test.design.proxy.Image;
import com.hiekn.demo.test.design.proxy.ProxyImage;
import com.hiekn.demo.test.design.strategy.Context;
import com.hiekn.demo.test.design.strategy.OperationAdd;
import com.hiekn.demo.test.design.strategy.OperationMultiply;
import com.hiekn.demo.test.design.strategy.OperationSubstract;
import com.hiekn.demo.test.design.template.Cricket;
import com.hiekn.demo.test.design.template.Football;
import com.hiekn.demo.test.design.template.Game;
import com.hiekn.demo.test.design.visitor.Computer;
import com.hiekn.demo.test.design.visitor.ComputerPart;
import com.hiekn.demo.test.design.visitor.ComputerPartDisplayVisitor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 设计模式（Design pattern）代表了最佳的实践，通常被有经验的面向对象的软件开发人员所采用。
 * 设计模式是软件开发人员在软件开发过程中面临的一般问题的解决方案。
 * 这些解决方案是众多软件开发人员经过相当长的一段时间的试验和错误总结出来的。
 *
 * @author DingHao
 * @date 2019/1/4 10:23
 */
public class PatternDemo extends TestBase {

    /**
     * 适配器模式,可以统一两个不兼容的接口
     *
     */
    @Test
    public void adapter(){
        Player myPlayer = new MyPlayer();

        myPlayer.play("h.mp3");
        myPlayer.play("me.avi");
    }

    /**
     * 桥接模式,使得具体类与接口实现者类无关。
     *
     */
    @Test
    public void bridge(){
        com.hiekn.demo.test.design.bridge.Shape redCircle = new Circle(100,100, 10, new ColorPrinter());
        com.hiekn.demo.test.design.bridge.Shape blackCircle = new Circle(100,100, 10, new BlackPrinter());

        redCircle.draw();
        blackCircle.draw();
    }

    @Test
    public void chain(){
        AbstractLogger loggerChain = getChainOfLoggers();

        loggerChain.logMessage(AbstractLogger.INFO,
                "This is an information.");

        loggerChain.logMessage(AbstractLogger.DEBUG,
                "This is an debug level information.");

        loggerChain.logMessage(AbstractLogger.ERROR,
                "This is an error information.");
    }

    private AbstractLogger getChainOfLoggers(){

        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    /**
     * 装饰器模式（Decorator Pattern）允许向一个现有的对象添加新的功能，同时又不改变其结构。
     * 这种类型的设计模式属于结构型模式，它是作为现有的类的一个包装。
     *
     * 这种模式创建了一个装饰类，用来包装原有的类，并在保持类方法签名完整性的前提下，提供了额外的功能。
     *
     */
    @Test
    public void decorator(){
        com.hiekn.demo.test.design.decorator.Shape circle = new com.hiekn.demo.test.design.decorator.Circle();

        com.hiekn.demo.test.design.decorator.Shape redCircle = new RedShapeDecorator(new com.hiekn.demo.test.design.decorator.Circle());

        com.hiekn.demo.test.design.decorator.Shape redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle of red border");
        redCircle.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.draw();
    }


    /**
     * 意图：定义一个创建对象的接口，让其子类自己决定实例化哪一个工厂类，工厂模式使其创建过程延迟到子类进行。
     */
    @Test
    public void factory(){
        ShapeFactory shapeFactory = new ShapeFactory();

        //获取 Circle 的对象，并调用它的 draw 方法
        Shape shape1 = shapeFactory.getShape("CIRCLE");

        //调用 Circle 的 draw 方法
        shape1.draw();

        //获取 Rectangle 的对象，并调用它的 draw 方法
        Shape shape2 = shapeFactory.getShape("RECTANGLE");

        //调用 Rectangle 的 draw 方法
        shape2.draw();

        //获取 Square 的对象，并调用它的 draw 方法
        Shape shape3 = shapeFactory.getShape("SQUARE");

        //调用 Square 的 draw 方法
        shape3.draw();
    }

    @Test
    public void filter() {
        List<Employee> persons = new ArrayList<>();

        persons.add(new Employee("Tom", "Male", "YES"));
        persons.add(new Employee("Jack", "Male", "NO"));
        persons.add(new Employee("Jane", "Female", "NO"));
        persons.add(new Employee("Diana", "Female", "YES"));
        persons.add(new Employee("Mike", "Male", "NO"));
        persons.add(new Employee("Bob", "Male", "YES"));

        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria retire = new CriteriaRetire();
        Criteria retireMale = new AndCriteria(retire, male);
        Criteria retireOrFemale = new OrCriteria(retire, female);

        System.out.println("Males: ");
        printPersons(male.meetCriteria(persons));

        System.out.println("Females: ");
        printPersons(female.meetCriteria(persons));

        System.out.println("Retire Males: ");
        printPersons(retireMale.meetCriteria(persons));

        System.out.println("Retire Or Females: ");
        printPersons(retireOrFemale.meetCriteria(persons));
    }

    private void printPersons(List<Employee> persons) {
        for (Employee person : persons) {
            System.out.println(person);
        }
    }

    @Test
    public void observer() {
        Subject subject = new Subject();

        new HexObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);

        System.out.println("Second state change: 10");
        subject.setState(10);
    }

    @Test
    public void prototype() {
        ShapeCache.loadCache();

        com.hiekn.demo.test.design.prototype.Shape clonedShape = ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape.getType());

        com.hiekn.demo.test.design.prototype.Shape clonedShape2 = ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());

        com.hiekn.demo.test.design.prototype.Shape clonedShape3 = ShapeCache.getShape("3");
        System.out.println("Shape : " + clonedShape3.getType());
    }

    /**
     * 意图：为其他对象提供一种代理以控制对这个对象的访问。
     */
    @Test
    public void proxy() {
        Image image = new ProxyImage("test_10mb.jpg");

        //图像将从磁盘加载
        image.display();
        System.out.println("");
        // 图像不需要从磁盘加载
        image.display();
    }

    @Test
    public void strategy() {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubstract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }

    @Test
    public void template() {
        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();

    }

    @Test
    public void visitor() {

        ComputerPart computer = new Computer();

        computer.accept(new ComputerPartDisplayVisitor());

    }
}
