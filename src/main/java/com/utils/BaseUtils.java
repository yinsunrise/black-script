package com.utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * 进制转换工具类
 * */
public class BaseUtils {

    /**
     * 字符转二进制
     * */
    public static String charToBinary(char c) {
        return stringToBinary(String.valueOf(c));
    }

    /**
     * 字符转指定编码二进制
     * */
    public static String charToBinary(char c,String charsetName){
        try {
            return stringToBinary(String.valueOf(c),charsetName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 字符串转二进制
     * */
    public static String stringToBinary(String str) {
        byte[] bytes = str.getBytes();
        return byteArrayToBinary(bytes);
    }

    /**
     * 字符串转指定编码二进制
     * */
    public static String stringToBinary(String str,String charsetName){
        try {
            byte[] bytes = str.getBytes(charsetName);
            return byteArrayToBinary(bytes);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 字节数组转二进制
     * */
    public static String byteArrayToBinary(byte[] bArr) {
        StringBuilder binary = new StringBuilder();
        for (byte b : bArr) {
            for (int bit = 7; bit >= 0; bit--) {
                binary.append((b >> bit) & 1);
            }
        }
        return binary.toString();
    }


    /**
     * 二进制字符串转byte数组
     * */
    public static byte[] binaryStrToByteArray(String binaryStr){
        int length = binaryStr.length();
        byte[] resultByte = new byte[length % 8 == 0 ? length/8:length/8+1];
        for (int j=resultByte.length - 1,i = j; i >= 0; i--) {
            int start = i==0?0:(length - 8) - 8 * (j - i);
            int end = length - 8 * (j - i);
            String substring = binaryStr.substring(start,end);
            substring = substring.length() == 8?substring:String.format("%08d",Long.valueOf(substring));
            resultByte[i] = (byte)Integer.parseInt(substring,2);
        }
        System.out.println(Arrays.toString(resultByte));
        return resultByte;
    }



    /**
     * 二进制字符串转unicode编码字符串
     * */
    public static String binaryStrToStr(String binaryStr){
        return new String(binaryStrToByteArray(binaryStr));

    }

    /**
     * 二进制字符串转指定编码字符串
     * */
    public static String binaryStrToStr(String binaryStr,String charsetName){
        try {
            return new String(binaryStrToByteArray(binaryStr),charsetName);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 十六进制字符串转unicode编码字符串
     * */
    public static String hexStrToStr(String hexString){
        return binaryStrToStr(hexStringToBinary(hexString));
    }

    /**
     * 十六进制字符串转指定编码字符串
     * */
    public static String hexStrToStr(String hexString,String charsetName){
        return binaryStrToStr(hexStringToBinary(hexString),charsetName);
    }



    /**
     * 字节数组转十六进制
     * 一个字节拆分成2组4位来计算十六进制
     * 例如：10进制127 转 16进制
     *  127 -> 0111 1111
     *  0111 -> 7
     *  1111 -> F
     *  0111 1111 -> 7F
     * */
    public static String byteArrayToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            //右偏移四位，取前四位计算
            sb.append("0123456789ABCDEF".charAt((b >> 4) & 15));
            //计算后四位，ar.m = 15 -> 0000 1111  =>前四位0000在位运算时，自动忽略掉变量b的前四位
            // 123 -> 0111 1011
            // 15  -> 0000 1111
            // 123 & 15 -> 0000 1011 -> 11
            // & => 与运算，两边同位都为1时才则得1
            sb.append("0123456789ABCDEF".charAt(b & 15));
        }
        return sb.toString();
    }

    /**
     * 字符串转16进制
     * */
    public static String stringToHexString(String str){
        return byteArrayToHexString(str.getBytes());
    }

    /**
     * 字符串转指定字符编码16进制
     * */
    public static String stringToHexString(String str,String charsetName){
        try {
            return byteArrayToHexString(str.getBytes(charsetName));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 十六进制字符串转二进制
     * */
    public static String hexStringToBinary(String hexString){
        StringBuilder binary = new StringBuilder();
        int hexLength = hexString.length();
        for (int i = 0; i < hexLength; i++) {
            char c = hexString.charAt(i);
            String binaryString = Integer.toBinaryString(Integer.parseInt(String.valueOf(c), 16));
            binary.append(String.format("%04d",Integer.valueOf(binaryString)));
        }
        return binary.toString();
    }
}
