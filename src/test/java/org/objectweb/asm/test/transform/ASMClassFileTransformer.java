package org.objectweb.asm.test.transform;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import static org.objectweb.asm.Opcodes.*;

public class ASMClassFileTransformer {

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {

            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                if (className.equals("org/objectweb/asm/test/Sample")) {
                    ClassReader cr = new ClassReader(classfileBuffer);
                    ClassWriter cw = new ClassWriter(cr, 0);
                    cr.accept(new ClassVisitor(ASM5, cw) {

                        @Override
                        public void visitEnd() {
                            FieldVisitor fv = cv.visitField(ACC_PRIVATE, "age", "I", null, null);
                            if (fv != null) {
                                fv.visitEnd();
                            } else {
                                super.visitEnd();
                            }
                        }

                    }, 0);
                    return cw.toByteArray();
                }
                return classfileBuffer;
            }

        });
    }

}
