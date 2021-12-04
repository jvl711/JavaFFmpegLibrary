package jvl.FFmpeg.jni;


public class Version
{
    private final static String BUILDTIME = "12/04/2021 08:55:10";
    private final static String BUILDNUMBER = "707";
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
