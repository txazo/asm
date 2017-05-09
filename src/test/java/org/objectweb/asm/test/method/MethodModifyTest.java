package org.objectweb.asm.test.method;

import org.junit.Test;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.test.util.ClassVisitorCallback;
import org.objectweb.asm.test.util.ClassVisitorUtils;

import java.lang.reflect.Method;

import static org.objectweb.asm.Opcodes.*;

/**
 * 修改方法
 */
public class MethodModifyTest {

    @Test
    public void test() throws Exception {
        Class<?> sampleClass = ClassVisitorUtils.modify("org.objectweb.asm.test.Sample", "-c", new ClassVisitorCallback() {

            @Override
            public ClassVisitor callback(String className, ClassWriter cw) {
                return new ClassVisitor(ASM5, cw) {

                    @Override
                    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
                        if (mv != null) {
                            return new MethodVisitor(ASM5, mv) {

                                @Override
                                public void visitCode() {
                                    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                                    mv.visitLdcInsn("Method " + name + " begin");
                                    mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                                }

                                @Override
                                public void visitInsn(int opcode) {
                                    if ((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) {
                                        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                                        mv.visitLdcInsn("Method " + name + " end");
                                        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                                    }
                                    super.visitInsn(opcode);
                                }

                                @Override
                                public void visitMaxs(int maxStack, int maxLocals) {
                                    mv.visitMaxs(maxStack + 2, maxLocals);
                                }

                            };
                        }
                        return mv;
                    }
                };
            }

        });

        Method setNameMethod = sampleClass.getDeclaredMethod("setName", new Class[]{String.class});
        setNameMethod.invoke(sampleClass.newInstance(), "root");
    }

}
