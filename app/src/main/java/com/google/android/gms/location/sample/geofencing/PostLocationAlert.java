package com.google.android.gms.location.sample.geofencing;


import android.os.AsyncTask;
import android.util.Log;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class PostLocationAlert extends AsyncTask<String, Void, Void>{
    private final String EXCHANGE_NAME = "location";
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel1;
    @Override
    protected Void doInBackground(String... Message) {
        try {

            String tempstr = "";
            for (String aMessage : Message) tempstr += aMessage;
            // JSONObject message=new JSONObject(tempstr);
            if (channel1 == null) {

                factory = new ConnectionFactory();
                factory.setHost("ec2-54-148-0-61.us-west-2.compute.amazonaws.com");
                // my internet connection is a bit restrictive so I have use an
                // external server
                // which has RabbitMQ installed on it. So I use "setUsername"
                // and "setPassword"
                factory.setUsername("guest");
                factory.setPassword("guest");
                //factory.setVirtualHost("/");
                factory.setPort(5672);
                System.out.println("" + factory.getHost() + factory.getPort() + factory.getRequestedHeartbeat() + factory.getUsername());
                connection = factory.newConnection();
                channel1 = connection.createChannel();
//                channel1.exchangeDeclare(EXCHANGE_NAME, "fanout");
            }

            channel1.basicPublish(EXCHANGE_NAME, "", null,
                    tempstr.toString().getBytes());
            Log.e("send message:", tempstr);


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();




        }
        // TODO Auto-generated method stub
        return null;
    }
}
