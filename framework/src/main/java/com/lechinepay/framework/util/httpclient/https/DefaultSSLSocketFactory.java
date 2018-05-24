package com.lechinepay.framework.util.httpclient.https;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocketFactory;

public class DefaultSSLSocketFactory extends SSLSocketFactory {

    @Override
    public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
        return SSLContextFactory.createSSLContext().getSocketFactory().createSocket(s, host, port, autoClose);
    }

    @Override
    public String[] getDefaultCipherSuites() {
        return null;
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return null;
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        return SSLContextFactory.createSSLContext().getSocketFactory().createSocket(host, port);
    }

    @Override
    public Socket createSocket(InetAddress host, int port) throws IOException {
        return SSLContextFactory.createSSLContext().getSocketFactory().createSocket(host, port);
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort)
            throws IOException, UnknownHostException {
        return SSLContextFactory.createSSLContext().getSocketFactory().createSocket(host, port, localHost, localPort);
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort)
            throws IOException {
        return SSLContextFactory.createSSLContext().getSocketFactory().createSocket(address, port, localAddress,
                localPort);
    }

}
