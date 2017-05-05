package org.objectweb.asm.test;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import static org.objectweb.asm.Opcodes.*;

public class TransformTest {

    @Test
    public void test1() throws Exception {
        ClassWriter cw = new ClassWriter(0);
        ClassReader cr = new ClassReader("org.objectweb.asm.test.Sample");
        cr.accept(cw, 0);
        byte[] bytes = cw.toByteArray();
    }

    @Test
    public void test2() throws Exception {
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new ChangeVersionAdapter(ASM5, cw);
        ClassReader cr = new ClassReader("org.objectweb.asm.test.Sample");
        cr.accept(cv, 0);
        byte[] bytes = cw.toByteArray();
    }

    @Test
    public void test3() throws Exception {
        ClassReader cr = new ClassReader("org.objectweb.asm.test.Sample");
        ClassWriter cw = new ClassWriter(cr, 0);
        ClassVisitor cv = new ChangeVersionAdapter(ASM5, cw);
        cr.accept(cv, 0);
        byte[] bytes = cw.toByteArray();
    }

    private static class ChangeVersionAdapter extends ClassVisitor {

        public ChangeVersionAdapter(int api, ClassVisitor cv) {
            super(api, cv);
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            super.visit(V1_6, access, name, signature, superName, interfaces);
        }

    }

}
