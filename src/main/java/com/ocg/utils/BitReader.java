package com.ocg.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BitReader {
    public static int ReadUInt32(byte[] buff, MutateInt head) {
        int value = head.getValue();
        head.step(4);
        if (value < 0) return -1;
        if (value > buff.length - 4) return -1;
        int ans = 0;
        for (int i = 0; i < 4; i++) {
            ans |= buff[value + i];
            ans = ans << 8;
        }
        return ans;
    }

    public static int ReadInt32(byte[] buff, MutateInt head) {
        int value = head.getValue();
        head.step(4);
        ByteBuffer buf = ByteBuffer.wrap(buff);
        buf.order(ByteOrder.LITTLE_ENDIAN); //转为小端模式
        return buf.getInt(value);
    }

    public static String getBit(byte[] buff, int head, int length) {
        if (buff.length == 0) return null;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buff.length; i++) {
            System.out.println(buff[head + i]);
            byte cur = buff[head + i];
            for (int j = 7; j > -1; j--) {
                if (length == 0) return sb.toString();
                sb.append((cur >> j) & 0x1);
                length--;
            }
        }
        return sb.toString();
    }

    public static int ReadUInt8(byte[] buff, MutateInt index) {
        int value = index.getValue();
        index.step();
        return (int) (buff[value] & 0xff);
    }

    public static int ReadInt8(byte[] buff, MutateInt index) {
        int value = index.getValue();
        index.step();
        return (int) (buff[value]);
    }
}
