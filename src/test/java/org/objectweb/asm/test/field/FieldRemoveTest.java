package org.objectweb.asm.test.field;

import org.junit.Test;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.test.util.ClassVisitorCallback;
import org.objectweb.asm.test.util.ClassVisitorUtils;

import static org.objectweb.asm.Opcodes.ASM5;

/**
 * 删除字段
 */
public class FieldRemoveTest {

    @Test
    public void test() throws Exception {
        ClassVisitorUtils.modify("org.objectweb.asm.test.Sample", "-p", new ClassVisitorCallback() {

            @Override
            public ClassVisitor callback(String className, ClassWriter cw) {
                return new ClassVisitor(ASM5, cw) {

                    @Override
                    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
                        if ("CODE".equals(name)) {
                            return null;
                        }
                        return super.visitField(access, name, desc, signature, value);
                    }

                };
            }

        });
    }

}
