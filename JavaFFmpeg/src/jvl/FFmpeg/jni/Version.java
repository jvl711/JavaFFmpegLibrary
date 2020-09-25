package jvl.FFmpeg.jni;


public class Version
{
    private final static String BUILDTIME = "09/25/2020 12:37:44";
    private final static String BUILDNUMBER = "678";
    private final static String VERSION = "0.5";
    
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
