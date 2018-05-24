package com.lechinepay.framework.security;

public interface SignatureService {

    byte[] sign(byte[] data) throws SecurityException;

    boolean verify(byte[] data, byte[] sign);

    String sign(String data) throws SecurityException;

    boolean verify(String data, String sign);

    String sign(String data, String encoding) throws SecurityException;

    boolean verify(String data, String sign, String encoding);

}
