package org.objectweb.asm.test.util;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public interface ClassVisitorCallback {

    public ClassVisitor callback(String className, ClassWriter cw);

}
