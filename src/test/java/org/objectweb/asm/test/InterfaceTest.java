package org.objectweb.asm.test;

import org.junit.Test;
import org.objectweb.asm.ClassWriter;

import java.io.FileOutputStream;

import static org.objectweb.asm.Opcodes.*;

public class InterfaceTest {

    @Test
    public void test() throws Exception {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_8, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, "org/objectweb/asm/test/User", null, "java/lang/Object", null);
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "name", "Ljava/lang/String;", null, "txazo").visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "age", "I", null, 25).visitEnd();
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "getName", "()Ljava/lang/String;", null, null).visitEnd();
        cw.visitEnd();
        byte[] bytes = cw.toByteArray();

        FileOutputStream fis = null;
        try {
            fis = new FileOutputStream(InterfaceTest.class.getResource("/").getPath() + "org/objectweb/asm/test/User.class");
            fis.write(bytes);
        } finally {
            fis.close();
        }

        com.sun.tools.javap.Main.main(new String[]{"org.objectweb.asm.test.User"});
    }

}
