package org.objectweb.asm.test.util;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.test.classloader.ASMClassLoader;

public abstract class ClassVisitorUtils {

    public static Class<?> modify(String className, String javapArgs, ClassVisitorCallback callback) throws Exception {
        ClassWriter cw = new ClassWriter(0);
        ClassReader cr = new ClassReader(className);
        cr.accept(callback.callback(className, cw), 0);
        JavapUtils.javap(className, cw.toByteArray(), javapArgs);
        return new ASMClassLoader().defineClass(className, cw.toByteArray());
    }

}
