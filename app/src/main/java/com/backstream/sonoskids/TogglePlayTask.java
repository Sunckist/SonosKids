package com.backstream.sonoskids;

import android.os.AsyncTask;

import com.vmichalak.sonoscontroller.SonosDevice;
import com.vmichalak.sonoscontroller.exception.SonosControllerException;
import com.vmichalak.sonoscontroller.model.PlayState;
import com.vmichalak.sonoscontroller.model.TrackInfo;

import java.io.IOException;

public class TogglePlayTask extends AsyncTask<SonosDevice,Void,TrackInfo> {
    private final SonosCallback callback;

    public TogglePlayTask(SonosCallback callback) {
        super();
        this.callback = callback;
    }


    @Override
    protected TrackInfo doInBackground(SonosDevice... sonosDevices) {
        TrackInfo trackInfo = null;
        if (sonosDevices != null) {
            SonosDevice sonosDevice = sonosDevices[0];
            try {
                trackInfo = sonosDevice.getCurrentTrackInfo();
                if (sonosDevice.getPlayState().equals(PlayState.PLAYING)) {
                    sonosDevice.pause();
                } else {
                    sonosDevice.play();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SonosControllerException e) {
                e.printStackTrace();
            }
        }
        return trackInfo;
    }
}
