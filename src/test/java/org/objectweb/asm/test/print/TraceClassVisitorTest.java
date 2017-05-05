package org.objectweb.asm.test.print;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;

public class TraceClassVisitorTest {

    @Test
    public void test() throws Exception {
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new TraceClassVisitor(cw, new PrintWriter(System.out));
        ClassReader cr = new ClassReader("org.objectweb.asm.test.Sample");
        cr.accept(cv, 0);
    }

}
