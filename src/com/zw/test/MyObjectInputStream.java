package com.zw.test;

import java.io.*;

public class MyObjectInputStream extends ObjectInputStream {
    public MyObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    protected MyObjectInputStream() throws IOException, SecurityException {
    }

    @Override
    protected Object readObjectOverride() throws IOException, ClassNotFoundException {
        return super.readObjectOverride();
    }
}

class MyObjectOutPutStream extends ObjectOutputStream {

    public MyObjectOutPutStream(OutputStream out) throws IOException {
        super(out);
    }

    protected MyObjectOutPutStream() throws IOException, SecurityException {
    }

    @Override
    protected void writeObjectOverride(Object obj) throws IOException {
        super.writeObjectOverride(obj);
    }
}
