
package jvl.FFmpeg.jni;

public class AVPacket
{
    private final long AVPacketPointer;

    static
    {
        Global.loadLibraries();
    }
    
    protected AVPacket(long AVPacketPointer)
    {
        this.AVPacketPointer = AVPacketPointer;
    }
    
    public long getPointer()
    {
        return this.AVPacketPointer;
    }
    
    public static AVPacket buildAVPacket()
    {
        long pointer = allocate();
        
        return new AVPacket(pointer);
    }
    
    private static native long  allocate();
    
    /**
    * Wipe the packet.
    *
    * Unreference the buffer referenced by the packet and reset the
    * remaining packet fields to their default values.
    */
    public void unreference()
    {
        unreference(this.AVPacketPointer);
    }
    
    private native void unreference(long AVPacketPointer);
    
    /**
     * Presentation timestamp in AVStream->time_base units; the time at which
     * the decompressed packet will be presented to the user.
     * Can be AV_NOPTS_VALUE if it is not stored in the file.
     * pts MUST be larger or equal to dts as presentation cannot happen before
     * decompression, unless one wants to view hex dumps. Some formats misuse
     * the terms dts and pts/cts to mean something different. Such timestamps
     * must be converted to true pts/dts before they are stored in AVPacket.
     * 
     * @return Presentation timestamp in AVStream->time_base units
     */
    public long getPTS()
    {
        return getPTS(this.AVPacketPointer);
    }
    
    private native long getPTS(long AVPacketPointer);
    
    /**
     * Decompression timestamp in AVStream->time_base units; the time at which
     * the packet is decompressed.
     * Can be AV_NOPTS_VALUE if it is not stored in the file.
     * 
     * @return Decompression timestamp in AVStream->time_base units
     */
    public long getDTS()
    {
        return getDTS(this.AVPacketPointer);
    }
    
    private native long getDTS(long AVPacketPointer);
    
    /**
     * Byte position in the stream or -1 if not known
     */
    public long getPosition()
    {
        return getPosition(this.AVPacketPointer);
    }
    
    public int getStreamIndex()
    {
        return this.getStreamIndex(AVPacketPointer);
    }
    
    private native int getStreamIndex(long AVPacketPointer);
    
    private native long getPosition(long AVPacketPointer);
    
    public void free()
    {
        this.free(AVPacketPointer);
    }
    
    private native void free(long AVPacketPointer);
}
