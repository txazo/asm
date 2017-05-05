package org.objectweb.asm.test.print;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;

import static org.objectweb.asm.Opcodes.*;

public class ClassPrinterTest {

    @Test
    public void test() throws Exception {
        ClassVisitor cv = new ClassPrinter(ASM5);
        ClassReader cr = new ClassReader("org.objectweb.asm.test.Sample");
        cr.accept(cv, 0);
    }

}
