package com.zw.test.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class MulitThreadDownloader {
    private static final int thread_count = 3;

    private static final long maxCount = 1024 * 1024 * 1024 * 3;

    public interface DownLoadListener {
        void onDownloading();

        void onFaild(int errorCode, String errorMsg);

        void onFinish();
    }

    public int finishedThreadCount = 0;

    public static void download(String urlStr, DownLoadListener downLoadListener) {
        try {
            HttpURLConnection conn = openConnection(urlStr);
            int responseCode = conn.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode) {
                int totalLength = conn.getContentLength();
                if (totalLength > maxCount) {
                    startMulitThreadDownload(urlStr, downLoadListener);
                } else {
                    MessageDigest digest = MessageDigest.getInstance("MD5");
                    String fileName = new String(digest.digest(urlStr.getBytes()));
                    startDownload(fileName, conn, downLoadListener);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != downLoadListener) {
                downLoadListener.onFaild(-1, e.getMessage());
            }
        }
    }

    private static HttpURLConnection openConnection(String urlStr, Map<String, String> params) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            if (null != params && params.size() > 0) {
                for (Map.Entry<String, String> en : params.entrySet()) {
                    connection.setRequestProperty(en.getKey(), en.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 单线程下载
     *
     * @param fileName
     * @param conn
     * @param downLoadListener
     */
    private static void startDownload(String fileName, HttpURLConnection conn, DownLoadListener downLoadListener) {
        try {
            File file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);

            InputStream inputStream = conn.getInputStream();
            byte[] buffer = new byte[1024 * 10];
            int len = 0;
            while ((len = inputStream.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
                fos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();

            if (null != downLoadListener) {
                downLoadListener.onFaild();
            }
        }
    }

    /**
     * 多线程下载
     *
     * @param urlStr
     * @param downLoadListener
     */
    private static void startMulitThreadDownload(String urlStr, DownLoadListener downLoadListener) {
        for (int i = 0; i < thread_count - 1; i++) {
            long perLength = 0;
            long start = 0;
            long end = 0;
            // TODO 计算 start end 启动下载任务
            startThread(urlStr, start, end, downLoadListener);
        }
    }

    private static void startThread(String urlStr, long start, long end, DownLoadListener downLoadListener) {
        new Task(urlStr, start, end, downLoadListener).start();
    }

    private static class Task extends Thread {
        private String urlStr;
        private long start;
        private long end;

        public Task(String urlStr, long start, long end, DownLoadListener downLoadListener) {
            this.urlStr = urlStr;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                Map<String, String> params = new HashMap<>();
                params.put("Range", "bytes=" + start + "-" + end);
                HttpURLConnection conn = openConnection(urlStr, params);
                if (null != conn) {
                    int responseCode = conn.getResponseCode();
                    if (HttpURLConnection.HTTP_OK == responseCode) {
                        InputStream inputStream = conn.getInputStream();
                        byte[] buffer = new byte[1024 * 10];
                        int len = 0;
                        while ((len = inputStream.read(buffer)) > 0) {
                            // TODO
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
