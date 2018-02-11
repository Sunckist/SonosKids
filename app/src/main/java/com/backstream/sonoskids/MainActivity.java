package com.backstream.sonoskids;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.vmichalak.sonoscontroller.SonosDevice;

public class MainActivity extends AppCompatActivity implements SonosCallback {

    private SonosDevice sonosDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("SonosKids started");

        new FindSpeakerTask().execute(this);
    }

    public void togglePlay(View view) {
        if (sonosDevice != null) {
            new TogglePlayTask().execute(sonosDevice);
        } else {
            System.out.println("Can't toggle play - sonosDevice is null!");
        }
    }

    @Override
    public void setDevice(SonosDevice sonosDevice) {
        this.sonosDevice = sonosDevice;
    }
}
