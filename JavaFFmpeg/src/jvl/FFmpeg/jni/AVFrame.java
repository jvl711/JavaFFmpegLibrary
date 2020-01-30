
package jvl.FFmpeg.jni;


public class AVFrame
{
    private final long AVFramePointer;

    static
    {
        Global.loadLibraries();
    }
    
    protected AVFrame(long AVFramePointer)
    {
        this.AVFramePointer = AVFramePointer;
    }
    
    public long getPointer()
    {
        return this.AVFramePointer;
    }
    
    public static AVFrame buildAVFrame()
    {
        long pointer = allocate();
        
        return new AVFrame(pointer);
    }
    
    private static native long  allocate();
    
    public long getPTS()
    {
        return this.getPTS(this.AVFramePointer);
    }
    
    private native long getPTS(long AVFramePointer);
    
    public int getPacketSize()
    {
        return this.getPacketSize(AVFramePointer);
    }
    
    private native int getPacketSize(long AVFramePointer);
    
    public int getKeyFrame()
    {
        return this.getKeyFrame(AVFramePointer);
    }
    
    private native int getKeyFrame(long AVFramePointer);
    
    public int getCodedPictureFrame()
    {
        return this.getCodedPictureNumber(AVFramePointer);
    }
    
    private native int getCodedPictureNumber(long AVFramePointer);
    
    public int getPictureType()
    {
        return this.getPictureType(AVFramePointer);
    }
    
    private native int getPictureType(long AVFramePointer);
    
    public char getPictureTypeChar()
    {
        return this.getPictureTypeChar(AVFramePointer);
    }
    
    private native char getPictureTypeChar(long AVFramePointer);
    
    public int getLineSize()
    {
        return this.getLineSize(this.AVFramePointer);
    }
    
    private native int getLineSize(long AVFramePointer);
    
    public int getWidth()
    {
        return this.getWidth(AVFramePointer);
    }
    
    private native int getWidth(long AVFramePointer);
    
    public int getHeight()
    {
        return this.getHeight(AVFramePointer);
    }
    
    private native int getHeight(long AVFramePointer); 
    
    public void test()
    {
        this.test(AVFramePointer);
    }
    
    public byte getData(int j, int i)
    {
        return getData(AVFramePointer, j, i);
    }
    
    private native byte getData(long AVFramePointer, int j, int i); 
    
    public void free()
    {
        this.free(AVFramePointer);
    }
    
    private native void free(long AVFramePointer);
            
    public native void test(long AVFramePointer);
    
    /**
     * The Numerator of the framerate or 0 if it is unknown
     * 
     * @return Numerator 
     */
    public int getFrameRateNumerator()
    {
        return this.getFramerateNumerator(AVFramePointer);
    }
    
    private native int getFramerateNumerator(long AVFramePointer);
    
    /**
     * The denominator of the framerate
     * 
     * @return Denominator 
     */
    public int getFrameRateDenominator()
    {
        return this.getFramerateDenominator(AVFramePointer);
    }
   
    private native int getFramerateDenominator(long AVFramePointer);
    
    
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
