
package jvl.FFmpeg.jni;

public class AVCodecContext extends AbstractJNIObject
{

    protected AVCodecContext(long AVCodecContextPointer)
    {
        super(AVCodecContextPointer);
    }
   
    /**
    * Free the codec context and everything associated with it and write NULL to
    * the provided pointer.
    */
    public void freeContext()
    {
        this.freeContext(this.getPointer());
    }
    
    private native void freeContext(long AVCodecContextPointer);
    
    /**
    * Fill the codec context based on the values from the supplied codec
    * parameters. Any allocated fields in codec that have a corresponding field in
    * parameter are freed and replaced with duplicates of the corresponding field in par.
    * Fields in codec that do not have a counterpart in par are not touched.
    * 
    * @param avcodecParameters The AVCodecParameter to copy properties from
    */
    public void copyParamsToContext(AVCodecParameters avcodecParameters)
    {
        int ret = copyParamsToContext(this.getPointer(), avcodecParameters.getPointer());
        
        if(ret < 0)
        {
            throw new RuntimeException("Error copying parameters to context");
        }
    }
    
    private native int copyParamsToContext(long AVCodePointer, long AVCodecParamPointer);
    
    /**
    * Initialize the AVCodecContext to use the given AVCodec. Prior to using this
    * function the context has to be allocated with avcodec_alloc_context3().
    * 
    * Notes:
    * This function is not thread safe!
    * Always call this function before using decoding routines (such as avcodec_receive_frame()).
    */
    public void open()
    {
        int ret = open(this.getPointer());
        
        if (ret < 0)
        {
            throw new RuntimeException("Error open codec");
        }
    }
    
    private native int open(long AVCodecContextPointer);
    
    public int sendPacket(AVPacket packet)
    {
        return this.sendPacket(getPointer(), packet.getPointer());
    }
    
    private native int sendPacket(long AVCodecContextPointer, long AVPacketPointer);
    
    public int receiveFrame(AVFrame frame)
    {
        return this.receiveFrame(this.getPointer(), frame.getPointer());
    }
    
    private native int receiveFrame(long AVCodecContextPointer, long AVFramePointer);
    
    public void setFramerate(AVRational framerate)
    {
        this.setFramerate(this.getPointer(), framerate.getNumerator(), framerate.getDenominator());
    }
    
    private native void setFramerate(long AVCodecContextPointer, int num, int den);
    
    public AVRational getFramerate()
    {
        return this.getFramerate(this.getPointer());
    }
    
    private native AVRational getFramerate(long AVCodecContextPointer);
    
    public void setHeight(int value)
    {
        this.setHeight(this.getPointer(), value);
    }
    
    private native void setHeight(long AVCodecContextPointer, int value);
    
    public void setwidth(int value)
    {
        this.setWidth(this.getPointer(), value);
    }
    
    private native void setWidth(long AVCodecContextPointer, int value);
    
    public int getHeight()
    {
        return this.getHeight(this.getPointer());
    }
    
    private native int getHeight(long AVCodecContextPointer);
    
    public int getWidth()
    {
        return this.getWidth(this.getPointer());
    }
    
    private native int getWidth(long AVCodecContextPointer);
    

    /**
     * That is the width of a pixel divided by the height of the pixel.
     * Numerator and denominator must be relatively prime and smaller than 256 for some video standards.
     * - encoding: Set by user.
     * - decoding: Set by libavcodec.
     * @return: The sample aspect ratio. If num or den are 0 than the aspect is unknow)
     */
    public AVRational getSampleAspectRatio()
    {
        return this.getSampleAspectRatio(this.getPointer());
    }
    
    private native AVRational getSampleAspectRatio(long AVCodecContextPointer);
    
    public void setSampleAspectRatio(AVRational rational)
    {
        this.setSampleAspectRatio(this.getPointer(), rational.getNumerator(), rational.getDenominator());
    }
            
    private native void setSampleAspectRatio(long AVCodecContextPointer, int num, int den);
    
    //private native int getSampleAspectRatioDen(long AVCodecContextPointer);
    
    //private native int getSampleAspectRatioNum(long AVCodecContextPointer);
    
    public AVPixelFormat getPixelFormat()
    {
        return this.getPixelFormat(this.getPointer());
    }
    
    private native AVPixelFormat getPixelFormat(long AVCodecContextPointer);
    
    public void setPixelFormat(AVPixelFormat pixelFormat)
    {
        setPixelFormat(this.getPointer(), pixelFormat.getId(), pixelFormat.getName());
    }
    
    private native void setPixelFormat(long AVCodecContextPointer, int pixelFormatID, String pixelFormatName);
    
    
    
}
