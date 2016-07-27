package org.spring.idgen;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * Created by springcat on 16/6/31.
 */
public class IdUtils {

    private static SecureRandom random = new SecureRandom();

    public static String objectId(){
        return ObjectId.get().toString();
    }

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 改造JDK自带的UUID的投String, 通过Random数字生成, 中间无-分割,性能更好
     */
    public static String uuid2() {

        UUID uuid = UUID.randomUUID();
        long mostSigBits = uuid.getMostSignificantBits();
        long leastSigBits = uuid.getMostSignificantBits();

        StringBuilder sb = new StringBuilder();
        sb.append(digits(mostSigBits >> 32, 8))
                .append(digits(mostSigBits >> 16, 4))
                .append(digits(mostSigBits, 4))
                .append(digits(leastSigBits >> 48, 4))
                .append(digits(leastSigBits, 12));

        return  sb.toString();
    }


    /** Returns val represented by the specified number of hex digits. */
    private static String digits(long val, int digits) {
        long hi = 1L << (digits * 4);
        return Long.toHexString(hi | (val & (hi - 1))).substring(1);
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

}
