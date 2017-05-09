package org.objectweb.asm.test.util;

import java.io.FileOutputStream;
import java.io.PrintWriter;

public abstract class JavapUtils {

    public static void javap(String className, byte[] bytes, String javapArgs) throws Exception {
        // 类字节码写入到文件
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(JavapUtils.class.getResource("/").getPath() + className.replaceAll("\\.", "/") + ".class");
            fos.write(bytes);
        } finally {
            fos.close();
        }

        // javap反编译
        com.sun.tools.javap.Main.run(new String[]{javapArgs, className}, new PrintWriter(System.out));
    }

}
