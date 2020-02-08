
package jvl.FFmpeg.jni;

public class AVCodecParameters extends AbstractJNIObject
{
    public static final int FF_PROFILE_UNKNOWN = -99; 
    public static final int FF_LEVEL_UNKNOWN = -99;
    
    private final int streamIndex;
   
    protected AVCodecParameters(long AVCodeParamPointer, int streamIndex)
    {
        super(AVCodeParamPointer);
        this.streamIndex = streamIndex;
    }
    
    
    public AVMediaType getCodecType()
    {
        return AVMediaType.parse(this.getCodecType(this.getPointer()));
    }
    
    private native int getCodecType(long AVCodeParamPointer);
    
    public int getWidth()
    {
        return getWidth(this.getPointer());
    }
    
    public int getHeight()
    {
        return getHeight(this.getPointer());
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
        return this.getSampleAspectRatioNumerator(getPointer());
    }
    
    private native int getSampleAspectRatioNumerator(long AVCodeParamPointer);
    
    /**
     * The denominator of the aspect ration
     * 
     * @return Denominator 
     */
    public int getSampleAspectRatioDenominator()
    {
        return this.getSampleAspectRatioDenominator(getPointer());
    }
   
    private native int getSampleAspectRatioDenominator(long AVCodeParamPointer);
    
    private native int getWidth(long AVCodeParamPointer);
    
    private native int getHeight(long AVCodeParamPointer);
    
    public String getProfileName()
    {
        return getProfileName(this.getPointer());
    }
    
    private native String getProfileName(long AVCodeParamPointer);
    
    public int getProfile()
    {
        return getProfile(this.getPointer());
    }
    
    private native int getProfile(long AVCodeParamPointer);
   
    public int getChannels()
    {
        if(this.getCodecType() == AVMediaType.AUDIO)
        {
            return getChannels(this.getPointer());
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
            return getSampleRate(this.getPointer());
        }
        else
        {
            throw new RuntimeException("Error: This getSampleRate is only available for Audio type tracks");
        }
    }
    
    private native int getSampleRate(long AVCodeParamPointer);
    
    public int getLevel()
    {
        return this.getLevel(this.getPointer());
    }
    
    private native int getLevel(long AVCodeParamPointer);
    
    public long getBitrate()
    {
        return this.getBitrate(this.getPointer());
    }
    
    private native long getBitrate(long AVCodeParamPointer);
    
    public int getCodecID()
    {
        return this.getCodecID(this.getPointer());
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
        int temp = this.getFieldOrder(this.getPointer());
        
        System.out.println(temp);
        
        return AVFieldOrder.parse(temp);
    }
    
    private native int getFieldOrder(long AVCodecParamPointer);
    
}
