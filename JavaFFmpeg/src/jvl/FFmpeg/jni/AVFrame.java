
package jvl.FFmpeg.jni;


public class AVFrame extends AbstractJNIObject
{    
    
    protected AVFrame(long AVFramePointer)
    {
        super(AVFramePointer);
    }
       
    public static AVFrame buildAVFrame()
    {
        long pointer = allocate();
        
        return new AVFrame(pointer);
    }
    
    private static native long  allocate();
    
    public long getPTS()
    {
        return this.getPTS(this.getPointer());
    }
    
    private native long getPTS(long AVFramePointer);
    
    public int getPacketSize()
    {
        return this.getPacketSize(this.getPointer());
    }
    
    private native int getPacketSize(long AVFramePointer);
    
    public int getKeyFrame()
    {
        return this.getKeyFrame(getPointer());
    }
    
    private native int getKeyFrame(long AVFramePointer);
    
    public int getCodedPictureFrame()
    {
        return this.getCodedPictureNumber(getPointer());
    }
    
    private native int getCodedPictureNumber(long AVFramePointer);
    
    public int getPictureType()
    {
        return this.getPictureType(getPointer());
    }
    
    private native int getPictureType(long AVFramePointer);
    
    public char getPictureTypeChar()
    {
        return this.getPictureTypeChar(getPointer());
    }
    
    private native char getPictureTypeChar(long AVFramePointer);
    
    public int getLineSize()
    {
        return this.getLineSize(this.getPointer());
    }
    
    private native int getLineSize(long AVFramePointer);
    
    public int getWidth()
    {
        return this.getWidth(getPointer());
    }
    
    private native int getWidth(long AVFramePointer);
    
    public int getHeight()
    {
        return this.getHeight(getPointer());
    }
    
    private native int getHeight(long AVFramePointer); 
    
    public void test()
    {
        this.test(getPointer());
    }
    
    public byte getData(int j, int i)
    {
        return getData(getPointer(), j, i);
    }
    
    private native byte getData(long AVFramePointer, int j, int i); 
    
    public void free()
    {
        this.free(getPointer());
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
        return this.getFramerateNumerator(getPointer());
    }
    
    private native int getFramerateNumerator(long AVFramePointer);
    
    /**
     * The denominator of the framerate
     * 
     * @return Denominator 
     */
    public int getFrameRateDenominator()
    {
        return this.getFramerateDenominator(getPointer());
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
