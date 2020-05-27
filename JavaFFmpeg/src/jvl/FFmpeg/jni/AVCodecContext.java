
package jvl.FFmpeg.jni;

public class AVCodecContext extends AbstractJNIObject
{

    protected AVCodecContext(long AVCodecContextPointer)
    {
        super(AVCodecContextPointer);
    }
    
    /*
    public long getPointer()
    {
        return this.
    }
    */
    
    public void freeContext()
    {
        this.freeContext(this.getPointer());
    }
    
    private native void freeContext(long AVCodecContextPointer);
    
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
    
    /**
     * The Numerator of the framerate or 0 if it is unknown
     * 
     * @return Numerator 
     */
    public int getFrameRateNumerator()
    {
        return this.getFramerateNumerator(getPointer());
    }
    
    private native int getFramerateNumerator(long AVCodecContextPointer);
    
    /**
     * The denominator of the framerate
     * 
     * @return Denominator 
     */
    public int getFrameRateDenominator()
    {
        return this.getFramerateDenominator(getPointer());
    }
   
    private native int getFramerateDenominator(long AVCodecContextPointer);
    
    
    /**
     * Takes the num / den and returns it.  If either num or den are 0 then
     * if is assumed the framerate is unknown
     * 
     * @return The framerate if known, or 0 
     */
    public double getFramerate()
    {
        int num = this.getFrameRateNumerator();
        int den = this.getFrameRateDenominator();
        
        if(num == 0 || den == 0)
        {
            return 0;
        }
        else
        {
            return (num * 1.0) / (den * 1.0);
        }
    }
    
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
    
    //TODO:  THis is where I left off
    /*
    public AVRational getSampleAspectRatio()
    {
         
    }
    */
    
    private native int getSampleAspectRatioDen(long AVCodecContextPointer);
    
    private native int getSampleAspectRatioNum(long AVCodecContextPointer);
    
}
