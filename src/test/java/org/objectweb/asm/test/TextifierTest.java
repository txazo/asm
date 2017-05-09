package org.objectweb.asm.test;

import org.junit.Test;
import org.objectweb.asm.util.Textifier;

/**
 * asm反编译
 */
public class TextifierTest {

    private static final String DEBUG = "-debug";
    private static final String CLASS_NAME = "org.objectweb.asm.test.Sample";

    @Test
    public void test1() throws Exception {
        Textifier.main(new String[]{DEBUG, CLASS_NAME});
    }

    @Test
    public void test2() throws Exception {
        String classFile = ASMifierTest.class.getResource("/").getPath() + CLASS_NAME.replaceAll("\\.", "/") + ".class";
        Textifier.main(new String[]{DEBUG, classFile});
    }

}
