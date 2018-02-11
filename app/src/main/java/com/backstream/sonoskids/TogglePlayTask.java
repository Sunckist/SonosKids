package com.backstream.sonoskids;

import android.os.AsyncTask;

import com.vmichalak.sonoscontroller.SonosDevice;
import com.vmichalak.sonoscontroller.exception.SonosControllerException;
import com.vmichalak.sonoscontroller.model.PlayState;

import java.io.IOException;

public class TogglePlayTask extends AsyncTask<SonosDevice,Void,Void> {
    @Override
    protected Void doInBackground(SonosDevice... sonosDevices) {
        if (sonosDevices != null) {
            SonosDevice sonosDevice = sonosDevices[0];
            try {
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
        return null;
    }
}
