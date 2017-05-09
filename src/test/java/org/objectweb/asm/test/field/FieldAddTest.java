package org.objectweb.asm.test.field;

import org.junit.Test;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.test.util.ClassVisitorCallback;
import org.objectweb.asm.test.util.ClassVisitorUtils;

import static org.objectweb.asm.Opcodes.*;

/**
 * 新增字段
 */
public class FieldAddTest {

    @Test
    public void test() throws Exception {
        ClassVisitorUtils.modify("org.objectweb.asm.test.Sample", "-p", new ClassVisitorCallback() {

            @Override
            public ClassVisitor callback(String className, ClassWriter cw) {
                return new ClassVisitor(ASM5, cw) {

                    @Override
                    public void visitEnd() {
                        FieldVisitor fv = cv.visitField(ACC_PRIVATE, "count", "I", null, null);
                        if (fv != null) {
                            fv.visitEnd();
                        }
                        super.visitEnd();
                    }

                };
            }

        });
    }

}
