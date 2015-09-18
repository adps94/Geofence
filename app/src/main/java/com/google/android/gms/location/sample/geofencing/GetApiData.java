package com.google.android.gms.location.sample.geofencing;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;



public class GetApiData extends AsyncTask<URL, Integer, String>  {
    private MainActivity activity;
    private String URL="http://ec2-54-148-0-61.us-west-2.compute.amazonaws.com:1337/geofence/findgeofences";
    public GetApiData(MainActivity activity){
        this.activity=activity;
    }
    protected String doInBackground(URL... urls) {
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(URL);
            String requestString="{\"tripID\":"+activity.tripId+"}";
            StringEntity requestJSON = new StringEntity(requestString);
            requestJSON.setContentType("application/json");
            postRequest.setEntity(requestJSON);

            HttpResponse response = httpClient.execute(postRequest);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String line;
            String responseString="";
            while ((line = br.readLine()) != null)
                responseString+=line;


            httpClient.getConnectionManager().shutdown();
            return responseString;
        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return null;
    }
    protected void onPostExecute(String result) {
        try {

            JSONObject data = new JSONObject(result);
            JSONArray points =data.getJSONArray("points");
            for (int i = 0; i < points.length(); i++)
            {
                String place=points.getJSONObject(i).getString("place");
                double lat=Double.parseDouble(points.getJSONObject(i).getString("lat"));
                double lng=Double.parseDouble(points.getJSONObject(i).getString("lng"));
                Log.d("place","("+place+","+lat+","+lng+")");
                Constants.TRIP_POINTS.put(place,new LatLng(lat,lng));
            }
            activity.populateGeofenceList();
            try {
                LocationServices.GeofencingApi.addGeofences(
                        activity.mGoogleApiClient,
                        // The GeofenceRequest object.
                        activity.getGeofencingRequest(),
                        // A pending intent that that is reused when calling removeGeofences(). This
                        // pending intent is used to generate an intent when a matched geofence
                        // transition is observed.
                        activity.getGeofencePendingIntent()
                ).setResultCallback(activity); // Result processed in onResult().
            } catch (SecurityException securityException) {
                // Catch exception generated if the app does not use ACCESS_FINE_LOCATION permission.
                //logSecurityException(securityException);
            }

        }
        catch(Exception e){
                Log.d("exception:", e.toString());
        }
    }
}
