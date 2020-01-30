
package jvl.FFmpeg.jni;


public class AVCodec
{
    private final long AVCodecPointer;
    private final AVCodecParameters avparams;
    
    static
    {
        Global.loadLibraries();
    }
    
    protected AVCodec(long AVCodecPointer, AVCodecParameters avparams)
    {
        this.AVCodecPointer = AVCodecPointer;
        this.avparams = avparams;
    }
    
    public static AVCodec getAVCodec(AVCodecParameters avparam)
    {
        long pointer = findDecoder(avparam.getCodecID());
        
        if(pointer == 0)
        {
            throw new RuntimeException("Codec not found");
        }
        
        AVCodec avcodec = new AVCodec(pointer, avparam);
        
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
    
    /**
     * Allocates the context, Copies the Codec Parameters and opens the Codec
     * 
     * Make sure to free the resource when done with it.
     * 
     * @return A new instance
     */
    public AVCodecContext allocateContext()
    {
        long pointer = allocateContext(this.AVCodecPointer);
        
        AVCodecContext context = new AVCodecContext(pointer);
        
        this.copyParamsToContext(context, this.avparams);
        this.open(context);
        
        return context;
    }
    
    private native long allocateContext(long AVCodecPointer);
    
    private void copyParamsToContext(AVCodecContext avcodecContext, AVCodecParameters avcodecParameters)
    {
        int ret = copyParamsToContext(avcodecContext.getPointer(), avcodecParameters.getPointer());
        
        if(ret < 0)
        {
            throw new RuntimeException("Error copying parameters to context");
        }
    }
    
    private native int copyParamsToContext(long AVCodePointer, long AVCodecParamPointer);
    
    private void open(AVCodecContext avcodeccontext)
    {
        int ret = open(avcodeccontext.getPointer(), this.AVCodecPointer);
        
        if (ret < 0)
        {
            throw new RuntimeException("Error open codec");
        }
    }
    
    private native int open(long AVCodecContextPointer, long AVCodecPointer);
    
}
