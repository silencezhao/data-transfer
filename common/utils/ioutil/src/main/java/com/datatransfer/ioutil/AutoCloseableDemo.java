package com.datatransfer.ioutil;

import java.io.Closeable;
import java.io.IOException;

public class AutoCloseableDemo implements Closeable {


    @Override
    public void close() throws IOException {
        System.out.println("_____close____");
    }
}
