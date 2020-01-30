
package jvl.FFmpeg.jni;

public class AVCodecContext
{
    private final long AVCodecContextPointer;
    
    static
    {
        Global.loadLibraries();
    }
    
    protected AVCodecContext(long AVCodecContextPointer)
    {
        this.AVCodecContextPointer = AVCodecContextPointer;
    }
    
    public long getPointer()
    {
        return this.AVCodecContextPointer;
    }
    
    public void freeContext()
    {
        this.freeContext(this.AVCodecContextPointer);
    }
    
    private native void freeContext(long AVCodecContextPointer);
    
    public int sendPacket(AVPacket packet)
    {
        return this.sendPacket(AVCodecContextPointer, packet.getPointer());
    }
    
    private native int sendPacket(long AVCodecContextPointer, long AVPacketPointer);
    
    public int receiveFrame(AVFrame frame)
    {
        return this.receiveFrame(this.AVCodecContextPointer, frame.getPointer());
    }
    
    private native int receiveFrame(long AVCodecContextPointer, long AVFramePointer);
    
    /**
     * The Numerator of the framerate or 0 if it is unknown
     * 
     * @return Numerator 
     */
    public int getFrameRateNumerator()
    {
        return this.getFramerateNumerator(AVCodecContextPointer);
    }
    
    private native int getFramerateNumerator(long AVCodecContextPointer);
    
    /**
     * The denominator of the framerate
     * 
     * @return Denominator 
     */
    public int getFrameRateDenominator()
    {
        return this.getFramerateDenominator(AVCodecContextPointer);
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
}
