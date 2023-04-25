package com.shiny.ffmpegdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shiny.ffmpegdemo.databinding.ActivityMainBinding;
import com.shiny.ffmpegdemo.utils.CMDUtils;

import java.io.File;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    static {
        System.loadLibrary("ffmpeg");
    }

    private ActivityMainBinding binding;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String downloadDir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString();
        String h264Path = downloadDir + File.separatorChar + "1.h264";
        String mp4Path = downloadDir + File.separatorChar + "tmp.mp4";

        TextView tv = binding.sampleText;
        button = binding.btn;
        button.setOnClickListener(new View.OnClickListener() {
            final String cmd = "ffmpeg -i " + h264Path + " " + mp4Path;
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + Arrays.toString(CMDUtils.splitCmd(cmd)));
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        int a = runFFMpegCMD(CMDUtils.splitCmd(cmd));
                        Log.i(TAG, "run: " + "视频转换完成！");
                    }
                }.start();
            }
        });
    }

    /**
     * A native method that is implemented by the 'ffmpegdemo' native library,
     * which is packaged with this application.
     */
    public native int runFFMpegCMD(String[] cmd);
}