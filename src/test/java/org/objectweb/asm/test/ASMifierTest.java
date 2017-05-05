package org.objectweb.asm.test;

import org.junit.Test;
import org.objectweb.asm.util.ASMifier;

public class ASMifierTest {

    private static final String debug = "-debug";

    @Test
    public void testSourceCode() throws Exception {
        String className = "org.objectweb.asm.test.Sample";
        ASMifier.main(new String[]{debug, className});
    }

    @Test
    public void testByteCode() throws Exception {
        String classFile = ASMifierTest.class.getResource("/").getPath() + "org/objectweb/asm/test/Sample.class";
        ASMifier.main(new String[]{debug, classFile});
    }

}
