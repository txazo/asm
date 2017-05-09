package org.objectweb.asm.test.clazz;

import org.junit.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.test.util.JavapUtils;

import static org.objectweb.asm.Opcodes.*;

/**
 * 生成类
 */
public class ClassGenerateTest {

    @Test
    public void test() throws Exception {
        String className = "org.objectweb.asm.test.User";
        String internalName = className.replaceAll("\\.", "/");

        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_8, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, internalName, null,
                "java/lang/Object", new String[]{"java/lang/Comparable"});
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "name", "Ljava/lang/String;", null, "txazo").visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "age", "I", null, 25).visitEnd();
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "getName", "()Ljava/lang/String;", null, new String[]{"java/lang/Exception"}).visitEnd();
        cw.visitEnd();

        JavapUtils.javap(className, cw.toByteArray(), "-p");
    }

}
