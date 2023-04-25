package com.shiny.ffmpegdemo.utils;

public class FFmpegCmd {
    static {
        System.loadLibrary("ffmpeg");
    }

    public static native int runFFMpegCMD(String[] cmd);
}
