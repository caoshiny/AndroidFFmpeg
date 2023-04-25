package com.shiny.ffmpegdemo.utils;

public class CMDUtils {
    public static String[] splitCmd(String cmd) {
        String regulation = "[ \\t]+";
        final String[] split = cmd.split(regulation);
        return  split;
    }
}
