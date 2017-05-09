package org.objectweb.asm.test.method;

import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.test.util.ClassVisitorCallback;
import org.objectweb.asm.test.util.ClassVisitorUtils;

import java.lang.reflect.Method;

import static org.objectweb.asm.Opcodes.*;

/**
 * 生成方法
 */
public class MethodGenerateTest {

    @Test
    public void test() throws Exception {
        Class<?> sampleClass = ClassVisitorUtils.modify("org.objectweb.asm.test.Sample", "-c", new ClassVisitorCallback() {

            @Override
            public ClassVisitor callback(String className, ClassWriter cw) {
                return new ClassVisitor(ASM5, cw) {

                    @Override
                    public void visitEnd() {
                        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC + ACC_STATIC, "add", "(II)I", null, null);
                        if (mv != null) {
                            mv.visitCode();
                            mv.visitVarInsn(ILOAD, 0);
                            mv.visitVarInsn(ILOAD, 1);
                            mv.visitInsn(IADD);
                            mv.visitInsn(IRETURN);
                            mv.visitMaxs(2, 2);
                            mv.visitEnd();
                        }
                        super.visitEnd();
                    }

                };
            }

        });

        Method addMethod = sampleClass.getDeclaredMethod("add", new Class[]{int.class, int.class});
        int result = (Integer) addMethod.invoke(null, 1, 2);
        Assert.assertSame(3, result);
    }

}
