package org.objectweb.asm.test.clazz;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.FileOutputStream;

import static org.objectweb.asm.Opcodes.*;

/**
 * 克隆类
 */
public class ClassCopyTest {

    @Test
    public void test() throws Exception {
        String className = "org.objectweb.asm.test.Sample";
        final String copyClassName = "org.objectweb.asm.test.$Sample";

        ClassWriter cw = new ClassWriter(0);
        ClassReader cr = new ClassReader(className);
        cr.accept(new ClassVisitor(ASM5, cw) {

            @Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                super.visit(version, access, copyClassName.replaceAll("\\.", "/"), signature, superName, interfaces);
            }

        }, 0);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(ClassCopyTest.class.getResource("/").getPath() + copyClassName.replaceAll("\\.", "/") + ".class");
            fos.write(cw.toByteArray());
        } finally {
            fos.close();
        }

        com.sun.tools.javap.Main.main(new String[]{"-p", copyClassName});
    }

}
