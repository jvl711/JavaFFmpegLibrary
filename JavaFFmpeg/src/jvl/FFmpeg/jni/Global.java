/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jvl.FFmpeg.jni;



public class Global
{
    private static boolean isLirariesLoaded = false;
    
    public static void loadLibraries()
    {
        if(!isLibrariesLoaded())
        {        
            /* Load Ffmpeg lirbaries */
            System.loadLibrary("avutil-56");
            System.loadLibrary("swresample-3");
            System.loadLibrary("swscale-5");
            System.loadLibrary("avcodec-58");
            System.loadLibrary("avformat-58");
            System.loadLibrary("postproc-55");
            System.loadLibrary("avfilter-7");
            System.loadLibrary("avdevice-58");

            /* Load JNI library */
            System.loadLibrary("libFFmpegJNIWrapper");           
            
            isLirariesLoaded = true;
        }
    }
    
    public static boolean isLibrariesLoaded()
    {
        return isLirariesLoaded;
    }
    
}
