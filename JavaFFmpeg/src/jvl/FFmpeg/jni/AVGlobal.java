/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jvl.FFmpeg.jni;

/**
 *
 * @author jvl711
 */
public class AVGlobal 
{
    static
    {
        Global.loadLibraries();
    }

    public static String getAVError(int errorNumber)
    {
        return getAVErrorString(errorNumber);
    }
    
    private static native String getAVErrorString(int errorNumber);
    
}
