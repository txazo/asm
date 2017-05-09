package org.objectweb.asm.test.print;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;

public class TraceClassVisitorTest {

    @Test
    public void test() throws Exception {
        ClassVisitor cv = new TraceClassVisitor(new PrintWriter(System.out));
        ClassReader cr = new ClassReader("org.objectweb.asm.test.Sample");
        cr.accept(cv, 0);
    }

}
