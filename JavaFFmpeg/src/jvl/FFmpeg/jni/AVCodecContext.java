
package jvl.FFmpeg.jni;

public class AVCodecContext extends AbstractJNIObject
{

    protected AVCodecContext(long AVCodecContextPointer)
    {
        super(AVCodecContextPointer);
    }
   
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
    
    //TODO:  THis is where I left off
    /*
    public AVRational getSampleAspectRatio()
    {
         
    }
    */
    
    private native int getSampleAspectRatioDen(long AVCodecContextPointer);
    
    private native int getSampleAspectRatioNum(long AVCodecContextPointer);
    
}
