package com.backstream.sonoskids;

import android.os.AsyncTask;

import com.vmichalak.sonoscontroller.SonosDevice;
import com.vmichalak.sonoscontroller.SonosDiscovery;
import com.vmichalak.sonoscontroller.exception.SonosControllerException;

import java.io.IOException;
import java.util.List;

public class FindSpeakerTask extends AsyncTask<Void, Void, SonosDevice> {
    private SonosCallback callback;

    public FindSpeakerTask(SonosCallback callback) {
        super();
        this.callback = callback;
    }

    protected SonosDevice doInBackground(Void... params) {
        try {
            SonosDevice speaker = null;
            List<SonosDevice> sonosDevices = SonosDiscovery.discover();
            for (SonosDevice sonosDevice : sonosDevices) {
                try {
                    if ("Living Room".equals(sonosDevice.getZoneName())) {
                        callback.setDevice(sonosDevice);
                        if (sonosDevice.getCurrentTrackInfo() != null) {
                            callback.setAlbumArt(
                                    Constants.PROTOCOL_PREFIX +
                                            sonosDevice.getSpeakerInfo().getIpAddress() +
                                            Constants.PORT_SUFFIX +
                                            sonosDevice.getCurrentTrackInfo().getMetadata().getAlbumArtURI()

                            );
                        }
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
}
