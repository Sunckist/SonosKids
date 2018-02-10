package com.backstream.sonoskids;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vmichalak.sonoscontroller.SonosDevice;
import com.vmichalak.sonoscontroller.SonosDiscovery;
import com.vmichalak.sonoscontroller.exception.SonosControllerException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Apa");
//TODO not the correct way...
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try {
            List<SonosDevice> sonosDevices = SonosDiscovery.discover();
            for (SonosDevice sonosDevice : sonosDevices) {
                try {
                    System.out.println(sonosDevice.getSpeakerInfo());
                } catch (SonosControllerException e) {
                    e.printStackTrace();
                }
            }
            //return sonosDevices;

        } catch (IOException e) {
            e.printStackTrace();
        }
        //new ListDevicesTask().doInBackground("apa");
    }
}
