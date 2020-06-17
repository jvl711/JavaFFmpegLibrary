package jvl.FFmpeg.jni;


public class Version
{
    private final static String BUILDTIME = "06/17/2020 10:41:29";
    private final static String BUILDNUMBER = "90";
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
