package jvl.FFmpeg.jni;


public class Version
{
    private final static String BUILDTIME = "09/26/2022 19:38:04";
    private final static String BUILDNUMBER = "827";
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
