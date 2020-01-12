
package jvl.FFmpeg.jni;

public class AVCodecParameters
{
    public static final int FF_PROFILE_UNKNOWN = -99; 
    public static final int FF_LEVEL_UNKNOWN = -99;
    
    private final long AVCodeParamPointer;
    private int streamIndex;
    
    static
    {
        Global.loadLibraries();
    }
    
    protected AVCodecParameters(long AVCodeParamPointer, int streamIndex)
    {
        this.AVCodeParamPointer = AVCodeParamPointer;
        this.streamIndex = streamIndex;
    }
    
    
    public long getPointer()
    {
        return this.AVCodeParamPointer;
    }
    
    
    public AVMediaType getCodecType()
    {
        return AVMediaType.parse(this.getCodecType(this.AVCodeParamPointer));
    }
    
    private native int getCodecType(long AVCodeParamPointer);
    
    public int getWidth()
    {
        return getWidth(this.AVCodeParamPointer);
    }
    
    public int getHeight()
    {
        return getHeight(this.AVCodeParamPointer);
    }
    
    private native int getWidth(long AVCodeParamPointer);
    
    private native int getHeight(long AVCodeParamPointer);
    
    public String getProfileName()
    {
        return getProfileName(this.AVCodeParamPointer);
    }
    
    private native String getProfileName(long AVCodeParamPointer);
    
    public int getProfile()
    {
        return getProfile(this.AVCodeParamPointer);
    }
    
    private native int getProfile(long AVCodeParamPointer);
   
    public int getChannels()
    {
        if(this.getCodecType() == AVMediaType.AUDIO)
        {
            return getChannels(this.AVCodeParamPointer);
        }
        else
        {
            throw new RuntimeException("Error: This getChannels is only available for Audio type tracks");
        }
    }
    
    private native int getChannels(long AVCodeParamPointer);
    
    public int getSampleRate()
    {
        if(this.getCodecType() == AVMediaType.AUDIO)
        {
            return getSampleRate(this.AVCodeParamPointer);
        }
        else
        {
            throw new RuntimeException("Error: This getSampleRate is only available for Audio type tracks");
        }
    }
    
    private native int getSampleRate(long AVCodeParamPointer);
    
    public int getLevel()
    {
        return this.getLevel(this.AVCodeParamPointer);
    }
    
    private native int getLevel(long AVCodeParamPointer);
    
    public long getBitrate()
    {
        return this.getBitrate(this.AVCodeParamPointer);
    }
    
    private native long getBitrate(long AVCodeParamPointer);
    
    public int getCodecID()
    {
        return this.getCodecID(this.AVCodeParamPointer);
    }
    
    private native int getCodecID(long AVCodecParamPointer);
            
    public int getStreamIndex()
    {
        return streamIndex;
    }
    
}
