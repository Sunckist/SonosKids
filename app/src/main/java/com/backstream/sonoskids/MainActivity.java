package com.backstream.sonoskids;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.vmichalak.sonoscontroller.SonosDevice;

public class MainActivity extends AppCompatActivity implements SonosCallback {

    private SonosDevice sonosDevice;
    private String currentAlbumArtUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("SonosKids started");

        new FindSpeakerTask(this).execute();
    }

    public void togglePlay(View view) {
        if (sonosDevice != null) {
            new TogglePlayTask(this).execute(sonosDevice);
        } else {
            System.out.println("Can't toggle play - sonosDevice is null!");
        }
    }

    @Override
    public void setDevice(SonosDevice sonosDevice) {
        this.sonosDevice = sonosDevice;
    }

    @Override
    public void setAlbumArt(String albumArtUri) {
        System.out.println("Setting album art uri: " +albumArtUri);
        this.currentAlbumArtUri = albumArtUri;

        new SetAlbumArtTask((ImageView) findViewById(R.id.imageView))
                .execute(currentAlbumArtUri);


        //ImageView albumArtView = findViewById(R.id.imageView);
        //albumArtView.setImageURI(new Uri.Builder().path(currentAlbumArtUri).build());
    }


}
