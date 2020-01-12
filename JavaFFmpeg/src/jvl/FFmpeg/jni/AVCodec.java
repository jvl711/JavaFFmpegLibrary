
package jvl.FFmpeg.jni;


public class AVCodec
{
    private final long AVCodecPointer;
    
    static
    {
        Global.loadLibraries();
    }
    
    protected AVCodec(long AVCodecPointer)
    {
        this.AVCodecPointer = AVCodecPointer;
    }
    
    public static AVCodec getAVCodec(AVCodecParameters avparam)
    {
        long pointer = findDecoder(avparam.getCodecID());
        
        if(pointer == 0)
        {
            throw new RuntimeException("Codec not found");
        }
        
        AVCodec avcodec = new AVCodec(pointer);
        
        return avcodec;
    }
    
    public long getPointer()
    {
        return this.AVCodecPointer;
    }
    
    private static native long findDecoder(int codec_id);
    
    public String getLongName()
    {
        return this.getLongName(this.AVCodecPointer);
    }
    
    private native String getLongName(long AVCodecPointer);
    
    public String getName()
    {
        return this.getName(this.AVCodecPointer);
    }
    
    private native String getName(long AVCodecPointer);
    
    public AVCodecContext allocateContext()
    {
        long pointer = allocateContext(this.AVCodecPointer);
        
        return new AVCodecContext(pointer);
    }
    
    private native long allocateContext(long AVCodecPointer);
    
    public void copyParamsToContext(AVCodecContext avcodecContext, AVCodecParameters avcodecParameters)
    {
        int ret = copyParamsToContext(avcodecContext.getPointer(), avcodecParameters.getPointer());
        
        if(ret < 0)
        {
            throw new RuntimeException("Error copying parameters to context");
        }
    }
    
    private native int copyParamsToContext(long AVCodePointer, long AVCodecParamPointer);
    
    public void open(AVCodecContext avcodeccontext)
    {
        int ret = open(avcodeccontext.getPointer(), this.AVCodecPointer);
        
        if (ret < 0)
        {
            throw new RuntimeException("Error open codec");
        }
    }
    
    private native int open(long AVCodecContextPointer, long AVCodecPointer);
    
}
