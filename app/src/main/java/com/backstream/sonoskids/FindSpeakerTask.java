package com.backstream.sonoskids;

import android.os.AsyncTask;

import com.vmichalak.sonoscontroller.SonosDevice;
import com.vmichalak.sonoscontroller.SonosDiscovery;
import com.vmichalak.sonoscontroller.exception.SonosControllerException;

import java.io.IOException;
import java.util.List;

public class FindSpeakerTask extends AsyncTask<SonosCallback, Void, SonosDevice> {
    private SonosCallback callback;

    protected SonosDevice doInBackground(SonosCallback... callbacks) {
        if (callbacks != null) {
            callback = callbacks[0];
        }
        try {
            SonosDevice speaker = null;
            List<SonosDevice> sonosDevices = SonosDiscovery.discover();
            for (SonosDevice sonosDevice : sonosDevices) {
                try {
                    if ("Living Room".equals(sonosDevice.getZoneName())) {
                        speaker = sonosDevice;
                    }
                    System.out.println(sonosDevice.getSpeakerInfo());
                } catch (SonosControllerException e) {
                    e.printStackTrace();
                }
            }
            return speaker;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(SonosDevice sonosDevice) {
       callback.setDevice(sonosDevice);
    }
}
