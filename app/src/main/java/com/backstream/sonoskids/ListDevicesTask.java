package com.backstream.sonoskids;

import android.os.AsyncTask;

import com.vmichalak.sonoscontroller.SonosDevice;
import com.vmichalak.sonoscontroller.SonosDiscovery;
import com.vmichalak.sonoscontroller.exception.SonosControllerException;

import java.io.IOException;
import java.util.List;

public class ListDevicesTask extends AsyncTask<String, Void, List<SonosDevice>> {

    protected List<SonosDevice> doInBackground(String... urls) {
        try {
            List<SonosDevice> sonosDevices = SonosDiscovery.discover();
            for (SonosDevice sonosDevice : sonosDevices) {
                try {
                    System.out.println(sonosDevice.getSpeakerInfo());
                } catch (SonosControllerException e) {
                    e.printStackTrace();
                }
            }
            return sonosDevices;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
