package com.lechinepay.framework.util.io;

import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Closeables {

    private static final Logger LOGGER = LoggerFactory.getLogger(Closeables.class);

    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
