package com.simulate;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import static com.utils.BaseUtils.*;

public class SimulateMain {

    public static void main(String[] args) throws Exception{
        //登录封包
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(1);// 固定为0或其他，因为加入整体长度计算后会被覆盖,第一号位为临时占位
        dataOutputStream.writeShort(0);
        dataOutputStream.writeUTF("aa1151640928");
        dataOutputStream.writeUTF("lc412309");
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        System.out.println("原生未计算：" + Arrays.toString(byteArray));
        System.out.println("未计算转16："+byteArrayToHexString(byteArray));
        byte[] a = a(byteArrayOutputStream);
        System.out.println("长度计算后：" + Arrays.toString(a));
        String j = byteArrayToHexString(a);
        System.out.println("计算后转16："+j);
    }




    /**
     * 在数据流中加入前缀，例如001C的二进制形式
     * */
    public static byte[] a(ByteArrayOutputStream byteArrayOutputStream) {
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byte[] b = b((short) byteArray.length);
        System.arraycopy(b, 0, byteArray, 0, b.length);
        return byteArray;
    }


    /**
     * 计算前缀长度，如何计算未知  十进制转字节流
     *
     * length : 128
     * 128的计算机存储形式是补码，需要将128的原码转换为补码(正数原码及为补码)
     * 128 -> 00000000 10000000 -> 00000000 10000000
     * 补码
     *
     * */
    public static byte[] b(short s) {
        byte[] bArr = new byte[2];
        for (int i = 0; i < 2; i++) {
            bArr[i] = (byte) ((s >>> (((bArr.length - 1) - i) * 8)) & 255);
        }
        return bArr;
    }

    /**
     * 待认证枚举，用处未知，可能用于进制转换
     * */
    public final class ar {
        public static final byte a = 0;
        public static final byte b = 1;
        public static final byte c = 2;
        public static final byte d = 3;
        public static final byte e = 4;
        public static final byte f = 6;
        public static final byte g = 8;
        public static final byte h = 10;
        public static final byte i = 11;
        public static final byte j = 12;
        public static final byte k = 13;
        public static final byte l = 14;
        public static final byte m = 15;
        public static final byte n = 16;
    }

}
