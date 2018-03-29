package com.zw.test;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class Test12 {
    public static void main(String[] args) {
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new Demo_X509Certificate()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            // 创建URL对象
            URL myURL = new URL("https://ebanks.gdb.com.cn/sperbank/perbankLogin.jsp");
            // 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
            HttpsURLConnection httpsConn = (HttpsURLConnection) myURL.openConnection();
            httpsConn.setSSLSocketFactory(ssf);
            // 取得该连接的输入流，以读取响应内容
            InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream());
            // 读取服务器的响应内容并显示
            int respInt = insr.read();
            while (respInt != -1) {
                System.out.print((char) respInt);
                respInt = insr.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class Demo_X509Certificate implements X509TrustManager {
    public X509TrustManager sunJSSEX509TrustManager;

    public Demo_X509Certificate() throws Exception {
        // create a "default" JSSE X509TrustManager.
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(new FileInputStream("trustedCerts"),
                "passphrase".toCharArray());
        TrustManagerFactory tmf =
                TrustManagerFactory.getInstance("SunX509", "SunJSSE");
        tmf.init(ks);
        TrustManager tms[] = tmf.getTrustManagers();
        /*
         * Iterate over the returned trustmanagers,
         * look for an instance of X509TrustManager.  If found,
         * use that as our "default" trust manager.
         */
        for (int i = 0; i < tms.length; i++) {
            if (tms[i] instanceof X509TrustManager) {
                sunJSSEX509TrustManager = (X509TrustManager) tms[i];
                return;
            }
        }
        /*
         * Find some other way to initialize, or else we have to fail the
         * constructor.
         */
        throw new Exception("Couldn't initialize");
    }

    @Override
    /*
     * Delegate to the default trust manager.
     */
    public void checkClientTrusted(X509Certificate[] x509Certificates, String authType) throws CertificateException {
        try {
            sunJSSEX509TrustManager.checkClientTrusted(x509Certificates, authType);
        } catch (CertificateException e) {
            // do any special handling here, or rethrow exception.
        }
    }

    @Override
    /*
     * Delegate to the default trust manager.
     */
    public void checkServerTrusted(X509Certificate[] x509Certificates, String authType) throws CertificateException {
        try {
            // 采用sun JSSE X509校验机制
            sunJSSEX509TrustManager.checkServerTrusted(x509Certificates, authType);

            // 自定义校验
            // doCheckServerTrusted(x509Certificates, authType);
        } catch (CertificateException ex) {
            /*
             * Possibly pop up a dialog box asking whether to trust the
             * cert chain.
             */
        }
    }

    private void doCheckServerTrusted(X509Certificate[] x509Certificates, String authType) {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return sunJSSEX509TrustManager.getAcceptedIssuers();
    }
}
