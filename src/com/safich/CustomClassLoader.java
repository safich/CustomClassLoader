package com.safich;

import java.io.*;

public class CustomClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File f = new File(name + ".class");
        if(!f.isFile()) {
            throw new ClassNotFoundException();
        }
        Class c = null;
        try(InputStream ins = new BufferedInputStream(new FileInputStream(f))) {
            byte[]b = new byte[(int)f.length()];
            ins.read(b);
            c = defineClass("CustomClass", b, 0, b.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return c;
    }
}
