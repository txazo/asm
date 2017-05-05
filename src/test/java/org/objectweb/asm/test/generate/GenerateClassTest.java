package org.objectweb.asm.test.generate;

import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.test.classloader.ASMClassLoader;

import java.io.FileOutputStream;

import static org.objectweb.asm.Opcodes.*;

/**
 * asm生成类
 */
public class GenerateClassTest {

    @Test
    public void test() throws Exception {
        String className = "org.objectweb.asm.test.User";
        String internalName = className.replaceAll("\\.", "/");

        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_8, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, internalName, null,
                "java/lang/Object", new String[]{});
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "name", "Ljava/lang/String;", null, "txazo").visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "age", "I", null, 25).visitEnd();
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "getName", "()Ljava/lang/String;", null, null).visitEnd();
        cw.visitEnd();
        byte[] bytes = cw.toByteArray();

        Class<?> userClass = new ASMClassLoader().defineClass(className, bytes);
        Assert.assertEquals(2, userClass.getDeclaredFields().length);

        // 生成的类字节码写入到文件
        FileOutputStream fis = null;
        try {
            fis = new FileOutputStream(this.getClass().getResource("/").getPath() + internalName + ".class");
            fis.write(bytes);
        } finally {
            fis.close();
        }

        // javap反编译
        com.sun.tools.javap.Main.main(new String[]{className});
    }

}
