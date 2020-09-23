package jvl.FFmpeg.jni;


public class Version
{
    private final static String BUILDTIME = "09/13/2020 09:20:43";
    private final static String BUILDNUMBER = "605";
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
