
package jvl.FFmpeg.jni;


public class AVCodec extends AbstractJNIObject
{
    //private final AVCodecParameters avparams;

    protected AVCodec(long AVCodecPointer)
    {
        super(AVCodecPointer);        
        //this.avparams = avparams;
    }
    
    public static AVCodec getAVCodecDecoder(AVCodecParameters avparam)
    {
        long pointer = findDecoder(avparam.getCodecID());
        
        if(pointer == 0)
        {
            throw new RuntimeException("Codec not found");
        }
        
        AVCodec avcodec = new AVCodec(pointer);
        
        return avcodec;
    }
    
    private static native long findDecoder(int codec_id);
    
    public String getLongName()
    {
        return this.getLongName(this.getPointer());
    }
    
    public static AVCodec findEncoderByName(String name, AVCodecParameters avparam)
    {
        long pointer = findEncoderByName(name);
        
        if(pointer == 0)
        {
            throw new RuntimeException("Codec not found");
        }
        
        AVCodec avcodec = new AVCodec(pointer);
        
        return avcodec;
    }
    
    private static native long findEncoderByName(String name);
    
    public static AVCodec getAVCodecEncoder(AVCodecParameters avparam)
    {
        long pointer = findEncoder(avparam.getCodecID());
        
        if(pointer == 0)
        {
            throw new RuntimeException("Codec not found");
        }
        
        AVCodec avcodec = new AVCodec(pointer);
        
        return avcodec;
    }
    
    public static AVCodec getAVCodecEncoder(int codecID)
    {
        long pointer = findEncoder(codecID);
        
        if(pointer == 0)
        {
            throw new RuntimeException("Codec not found");
        }
        
        AVCodec avcodec = new AVCodec(pointer);
        
        return avcodec;
    }
    
    private static native long findEncoder(int codec_id);
    
    private native String getLongName(long AVCodecPointer);
    
    public String getName()
    {
        return this.getName(this.getPointer());
    }
    
    private native String getName(long AVCodecPointer);
    
    /**
     * Allocates the context
     * 
     * Make sure to free the resource when done with it.
     * 
     * @return A new instance
     */
    public AVCodecContext allocateContext()
    {
        long pointer = allocateContext(this.getPointer());
        
        AVCodecContext context = new AVCodecContext(pointer);
        
        //this.copyParamsToContext(context, this.avparams);
        //this.open(context);
        
        return context;
    }
    
    private native long allocateContext(long AVCodecPointer);
    
    public int getPixelFormatsCount()
    {
        return this.getPixelFormatsCount(this.getPointer());
    }
    
    private native int getPixelFormatsCount(long AVCodecPointer);
    
    public AVPixelFormat getPixelFormat(int index)
    {
        return this.getPixelFormat(this.getPointer(), index);
    }
    
    private native AVPixelFormat getPixelFormat(long AVCodecPointer, int index);
    
    public int getSampleFormatsCount()
    {
        return this.getSampleFormatsCount(this.getPointer());
    }
    
    private native int getSampleFormatsCount(long AVCodecPointer);
    
    public AVSampleFormat getSampleFormat(int index)
    {
        return this.getSampleFormat(this.getPointer(), index);
    }
    
    private native AVSampleFormat getSampleFormat(long AVCodecPointer, int index);
}
