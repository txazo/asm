package org.objectweb.asm.test.method;

import org.junit.Test;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.test.util.ClassVisitorCallback;
import org.objectweb.asm.test.util.ClassVisitorUtils;

import static org.objectweb.asm.Opcodes.*;

/**
 * 删除方法
 */
public class MethodRemoveTest {

    @Test
    public void test() throws Exception {
        ClassVisitorUtils.modify("org.objectweb.asm.test.Sample", "-p", new ClassVisitorCallback() {

            @Override
            public ClassVisitor callback(String className, ClassWriter cw) {
                return new ClassVisitor(ASM5, cw) {

                    @Override
                    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                        if ("getName".equals(name)) {
                            return null;
                        }
                        return super.visitMethod(access, name, desc, signature, exceptions);
                    }

                };
            }

        });
    }

}
