package com.zw.test;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPClient
{

    private final String USER_AGENT = "Java";

    public static void main(String[] args) throws Exception
    {

        HTTPClient http = new HTTPClient();

        String method = args[0];
        String url = args[1];

        if ("HEAD".equals(method))
        {
            http.headMethod(url);
        }
        else if ("GET".equals(method))
        {
            http.getMethod(url);
        }
        else if ("PUT".equals(method))
        {
            http.putMethod(url);
        }
        else if ("POST".equals(method))
        {
            http.postMethod(url);
        }
        else if ("DELETE".equals(method))
        {
            http.deleteMethod(url);
        }
        else if ("TRACE".equals(method))
        {
            http.traceMethod(url);
        } else {
            System.out.println("Invalid argument");
        }


    }

    // HTTP HEAD request
    private void headMethod(String url) throws Exception
    {

        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        // Set default to method
        conn.setRequestMethod("HEAD");

        //add request header
        conn.setRequestProperty("User-Agent", USER_AGENT);

        int i = 0;
        while(conn.getHeaderField(i) != null)
        {
            if(conn.getHeaderFieldKey(i) == null)
            {
                System.out.println(conn.getHeaderField(i));
            } else {
                System.out.println(conn.getHeaderFieldKey(i)+" : "+conn.getHeaderField(i));
            }
            if(conn.getHeaderField(i+1) == null) System.out.println(" ");

            i++;
        }

    }

    // HTTP GET request
    private void getMethod(String url) throws Exception
    {

        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        // Set default method
        conn.setRequestMethod("GET");

        //add request header
        conn.setRequestProperty("User-Agent", USER_AGENT);

        int i = 0;
        while(conn.getHeaderField(i) != null)
        {
            if(conn.getHeaderFieldKey(i) == null)
            {
                System.out.println(conn.getHeaderField(i));
            } else {
                System.out.println(conn.getHeaderFieldKey(i)+" : "+conn.getHeaderField(i));
            }
            if(conn.getHeaderField(i+1) == null) System.out.println(" ");

            i++;
        }
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();

            //print result
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(" ");
        }

    }

    // HTTP POST request
    private void postMethod(String url) throws Exception
    {

        URL obj = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();

        // Set default method
        conn.setRequestMethod("POST");

        //add request header
        conn.setRequestProperty("User-Agent", USER_AGENT);

        // Send post request
        conn.setDoOutput(true);
        DataOutputStream dout = new DataOutputStream(conn.getOutputStream());
        dout.writeBytes("some parameter");
        dout.flush();
        dout.close();

        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();

            //print result
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(" ");
        }

    }

    // HTTP PUT request
    private void putMethod(String url) throws Exception
    {

        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setDoOutput(true);

        // Set default method
        conn.setRequestMethod("PUT");

        //add request header
        conn.setRequestProperty("User-Agent", USER_AGENT);

        try {

            // Write data
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write("TESTE CONTENT");

            // Print Header
            int i = 0;
            while(conn.getHeaderField(i) != null)
            {
                if(conn.getHeaderFieldKey(i) == null)
                {
                    System.out.println(conn.getHeaderField(i));
                } else {
                    System.out.println(conn.getHeaderFieldKey(i)+" : "+conn.getHeaderField(i));
                }
                if(conn.getHeaderField(i+1) == null) System.out.println(" ");

                i++;
            }

            // Print body
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();

            //print result
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }

    // HTTP DELETE request
    private void deleteMethod(String url) throws Exception
    {

        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setDoOutput(true);

        // Set default method
        conn.setRequestMethod("DELETE");

        //add request header
        conn.setRequestProperty("User-Agent", USER_AGENT);

        try {
            // Print Header
            int i = 0;
            while(conn.getHeaderField(i) != null)
            {
                if(conn.getHeaderFieldKey(i) == null)
                {
                    System.out.println(conn.getHeaderField(i));
                } else {
                    System.out.println(conn.getHeaderFieldKey(i)+" : "+conn.getHeaderField(i));
                }
                if(conn.getHeaderField(i+1) == null) System.out.println(" ");

                i++;
            }

            // Print body
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();

            //print result
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }

    // HTTP TRACE request
    private void traceMethod(String url) throws Exception
    {

        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setDoOutput(true);

        // Set default method
        conn.setRequestMethod("TRACE");

        //add request header
        conn.setRequestProperty("User-Agent", USER_AGENT);

        try {
            // Print Header
            int i = 0;
            while(conn.getHeaderField(i) != null)
            {
                if(conn.getHeaderFieldKey(i) == null)
                {
                    System.out.println(conn.getHeaderField(i));
                } else {
                    System.out.println(conn.getHeaderFieldKey(i)+" : "+conn.getHeaderField(i));
                }
                if(conn.getHeaderField(i+1) == null) System.out.println(" ");

                i++;
            }

            // Print body
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();

            //print result
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }
}