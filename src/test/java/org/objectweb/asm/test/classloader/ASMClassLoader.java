package org.objectweb.asm.test.classloader;

public class ASMClassLoader extends ClassLoader {

    public Class<?> defineClass(String name, byte[] b) throws ClassFormatError {
        return defineClass(name, b, 0, b.length);
    }

}
