package jvl.FFmpeg.jni;


public class Version
{
    private final static String BUILDTIME = "07/31/2020 13:14:54";
    private final static String BUILDNUMBER = "469";
    private final static String VERSION = "0.4-alpha";
    
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
