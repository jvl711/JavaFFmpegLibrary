package jvl.FFmpeg.jni;


public class Version
{
    private final static String BUILDTIME = "09/07/2022 20:13:06";
    private final static String BUILDNUMBER = "788";
    private final static String VERSION = "0.6";
    
    public static String getVersion()
    {
        return VERSION;
    }
    
    public static String getBuildNumber()
    {
        return BUILDNUMBER;
    }

    public static String getBuildTime()
    {
        return BUILDTIME;
    }
}
