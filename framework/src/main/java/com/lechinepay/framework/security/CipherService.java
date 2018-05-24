package com.lechinepay.framework.security;

public interface CipherService {

    byte[] encrypt(byte[] data) throws SecurityException;

    byte[] decrypt(byte[] data) throws SecurityException;

    String encrypt(String data) throws SecurityException;

    String decrypt(String data) throws SecurityException;

    String encrypt(String data, String encoding) throws SecurityException;

    String decrypt(String data, String encoding) throws SecurityException;

}
