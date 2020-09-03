package jvl.FFmpeg.jni;


public class Version
{
    private final static String BUILDTIME = "09/03/2020 09:11:29";
    private final static String BUILDNUMBER = "599";
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
