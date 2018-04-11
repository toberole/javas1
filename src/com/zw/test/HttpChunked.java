package com.zw.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class HttpChunked {
    private static long getChunkSize(InputStream is) {
        String length = readLine(is).trim();
        if (isBlank(length)) {
            length = readLine(is).trim();
        }

        if (length.length() < 4) {
            length = 0 + length;
        }

        long res = Long.parseLong(length);
        return res;
    }

    /**
     * 读取gzip压缩的消息体
     *
     * @param is
     * @return
     * @throws IOException
     */
    private static List<Byte> readGzipBody(InputStream is) throws IOException {
        // 压缩块的大小，由于chunked编码块的前面是一个标识压缩块大小的16进制字符串，在开始读取前，需要获取这个大小
        long chunk = getChunkSize(is);
        List<Byte> bodyByteList = new ArrayList<Byte>();
        byte readByte = 0;
        int count = 0;

        while (count < chunk) {  // 读取消息体，最多读取chunk个byte
            readByte = (byte) is.read();
            bodyByteList.add(Byte.valueOf(readByte));
            count++;
        }
        if (chunk > 0) { // chunk为读取到最后，如果没有读取到最后，那么接着往下读取。
            List<Byte> tmpList = readGzipBody(is);
            bodyByteList.addAll(tmpList);
        }
        return bodyByteList;
    }

    /**
     * 获取没有压缩的消息体
     *
     * @param is
     * @param contentLe
     * @return
     */
    private static String readBody(InputStream is, int contentLe) {
        List<Byte> lineByteList = new ArrayList<Byte>();
        byte readByte;
        int total = 0;
        try {
            do {
                readByte = (byte) is.read();
                lineByteList.add(Byte.valueOf(readByte));
                total++;
            } while (total < contentLe);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] tmpByteArr = new byte[lineByteList.size()];
        for (int i = 0; i < lineByteList.size(); i++) {
            tmpByteArr[i] = ((Byte) lineByteList.get(i)).byteValue();
        }
        lineByteList.clear();

        String line = "";
        try {
            line = new String(tmpByteArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return line;
    }

    private static boolean isBlank(String length) {
        return false;
    }

    private static String readLine(InputStream is) {
        return null;
    }
}
