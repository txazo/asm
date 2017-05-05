package org.objectweb.asm.test;

import org.junit.Test;
import org.objectweb.asm.util.Textifier;

public class TextifierTest {

    private static final String debug = "-debug";

    @Test
    public void testSourceCode() throws Exception {
        String className = "org.objectweb.asm.test.Sample";
        Textifier.main(new String[]{debug, className});
    }

    @Test
    public void testByteCode() throws Exception {
        String classFile = ASMifierTest.class.getResource("/").getPath() + "org/objectweb/asm/test/Sample.class";
        Textifier.main(new String[]{debug, classFile});
    }

}
