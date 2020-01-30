
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
    
    public double getAspectRatio()
    {
        return (this.getWidth() * 1.0) / (this.getHeight() * 1.0);
    }
    
    public String getAspectRatioString()
    {
        int gcd;
        int samp_num = 1;
        int samp_den = 1;
        
        if(this.getSampleAspectRatioDenominator() > 0 && this.getSampleAspectRatioNumerator() > 0)
        {
            samp_num = this.getSampleAspectRatioDenominator();
            samp_den = this.getSampleAspectRatioNumerator();
        }
        
        int width = this.getWidth() * samp_num;
        int height = this.getHeight() * samp_den;
        
        gcd = gcd(height, width);
        
        int dar_num = this.getWidth() / gcd;
        int dar_den = this.getHeight()  / gcd;
        
        return dar_num + ":" + dar_den;
    }
    
    private static int gcd(int a, int b)
    {
        while (b > 0)
        {
            int temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }
    
     /* * The Numerator of the aspect ration or 0 if it is unknown
     * 
     * @return Numerator 
     */
    public int getSampleAspectRatioNumerator()
    {
        return this.getSampleAspectRatioNumerator(AVCodeParamPointer);
    }
    
    private native int getSampleAspectRatioNumerator(long AVCodeParamPointer);
    
    /**
     * The denominator of the aspect ration
     * 
     * @return Denominator 
     */
    public int getSampleAspectRatioDenominator()
    {
        return this.getSampleAspectRatioDenominator(AVCodeParamPointer);
    }
   
    private native int getSampleAspectRatioDenominator(long AVCodeParamPointer);
    
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
    
    /**
     * Video only. The order of the fields in interlaced video.
     */
    public AVFieldOrder getFieldOrder()
    {
        int temp = this.getFieldOrder(this.AVCodeParamPointer);
        
        System.out.println(temp);
        
        return AVFieldOrder.parse(temp);
    }
    
    private native int getFieldOrder(long AVCodecParamPointer);
    
}
