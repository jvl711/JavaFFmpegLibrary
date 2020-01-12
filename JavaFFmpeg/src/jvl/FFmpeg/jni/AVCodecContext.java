
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
}
