package com.hiekn.demo.test.java.asm;

import com.hiekn.demo.test.TestBase;
import org.junit.Test;
import org.springframework.asm.ClassWriter;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Opcodes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * AsmDemo
 *
 * @author DingHao
 * @date 2018/12/11 15:38
 */
public class AsmDemo extends TestBase {

    @Test
    public void testWrite() {

        ClassWriter classWriter = new ClassWriter(0);
        String className = "com/hiekn/asm/HelloWorld";
        classWriter.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC, className, null,
                "java/lang/Object", null);

        MethodVisitor initVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>",
                "()V", null, null);
        initVisitor.visitCode();
        initVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        initVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>",
                "V()");
        initVisitor.visitInsn(Opcodes.RETURN);
        initVisitor.visitMaxs(1, 1);
        initVisitor.visitEnd();

        MethodVisitor helloVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "sayHello",
                "()V;", null, null);
        helloVisitor.visitCode();
        helloVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
                "Ljava/io/PrintStream;");
        helloVisitor.visitLdcInsn("hello world!");
        helloVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(Ljava/lang/String;)V");
        helloVisitor.visitInsn(Opcodes.RETURN);
        helloVisitor.visitMaxs(1, 1);
        helloVisitor.visitEnd();

        classWriter.visitEnd();

        byte[] code = classWriter.toByteArray();

        File file = new File("D:\\HelloWorld.class");
        try(FileOutputStream output = new FileOutputStream(file)){
            output.write(code);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}