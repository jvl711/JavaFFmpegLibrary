package jvl.FFmpeg.jni;


public class Version
{
    private final static String BUILDTIME = "08/02/2020 10:35:32";
    private final static String BUILDNUMBER = "499";
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
