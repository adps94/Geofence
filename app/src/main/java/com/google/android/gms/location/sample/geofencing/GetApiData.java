package com.google.android.gms.location.sample.geofencing;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by aswindevps on 8/12/2015.
 */

public class GetApiData extends AsyncTask<URL, Integer, String> {
    public String data;
    protected String doInBackground(URL... urls) {
        System.out.println("Blahhh");
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet(
                    "http://dashboard.schoolsafe.co:14241/v1/getallbuildings");
            getRequest.addHeader("accept", "application/json");

            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output;
            System.out.println("Output from Server .... \n");
           /* while ((output = br.readLine()) != null) {
                System.out.println(output);
            }*/
            output=br.readLine();
            System.out.println(output);

            httpClient.getConnectionManager().shutdown();
            return output;

        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }
    protected void onPostExecute(String result) {
         System.out.println(result);
    }
}
