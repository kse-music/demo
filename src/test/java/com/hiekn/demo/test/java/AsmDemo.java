package com.hiekn.demo.test.java;

import org.junit.Test;
import org.objectweb.asm.*;

import java.io.*;

/**
 * @author: DingHao
 * @date: 2019/4/3 17:34
 */
public class AsmDemo {

    @Test
    public void classReader() throws IOException {
        ClassReader classReader = new ClassReader(getClass().getResourceAsStream("HelloWorld.class"));
        String className = classReader.getClassName();
        System.out.printf(className);
    }


    /**
     * asm 操作字节码
     */
    @Test
    public void classWriter() {
        ClassWriter classWriter = new ClassWriter(0);
        String className = "com/hiekn/demo/test/java/HelloWorld";
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, className, null, "java/lang/Object", null);

        MethodVisitor initVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        initVisitor.visitCode();
        initVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        initVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "V()");
        initVisitor.visitInsn(Opcodes.RETURN);
        initVisitor.visitMaxs(1, 1);
        initVisitor.visitEnd();

        MethodVisitor helloVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "sayHello", "()V;", null, null);
        helloVisitor.visitCode();
        helloVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        helloVisitor.visitLdcInsn("hello world!");
        helloVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        helloVisitor.visitInsn(Opcodes.RETURN);
        helloVisitor.visitMaxs(1, 1);
        helloVisitor.visitEnd();

        classWriter.visitEnd();

        byte[] code = classWriter.toByteArray();

        File file = new File("F:\\IDEAProject\\demo\\target\\test-classes\\com\\hiekn\\demo\\test\\java\\HelloWorld.class");
        try (FileOutputStream output = new FileOutputStream(file)) {
            output.write(code);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
