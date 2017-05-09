package org.objectweb.asm.test.transform;

import java.lang.reflect.Field;

public class ASMClassFileTransformerTest {

    // VM Args: -javaagent:/Users/txazo/TxazoProject/asm/target/transform-agent.jar
    public static void main(String[] args) throws Exception {
        Class<?> sampleClass = Class.forName("org.objectweb.asm.test.Sample");
        Field[] fields = sampleClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getType().getName() + " " + field.getName());
        }
    }

}
