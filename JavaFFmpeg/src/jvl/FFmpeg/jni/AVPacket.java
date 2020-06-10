
package jvl.FFmpeg.jni;

import java.io.FileInputStream;

public class AVPacket extends AbstractJNIObject
{
    
    protected AVPacket(long AVPacketPointer)
    {
        super(AVPacketPointer);
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
        unreference(this.getPointer());
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
        return getPTS(this.getPointer());
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
        return getDTS(this.getPointer());
    }
    
    private native long getDTS(long AVPacketPointer);
    
    /**
     * Byte position in the stream or -1 if not known
     */
    public long getPosition()
    {
        return getPosition(this.getPointer());
    }
    
    private native long getPosition(long AVPacketPointer);
    
    /**
     * Used in occasions where FFmpeg does not set the position, and
     * this library, or the user is able to find and set the position
    */
    public void setPosition(long value)
    {
        this.setPosition(this.getPointer(), value);
    }
    
    private native void setPosition(long AVPacketPointer, long value);
    
    public int getStreamIndex()
    {
        return this.getStreamIndex(getPointer());
    }
    
    private native int getStreamIndex(long AVPacketPointer);
    
    
    
    public void free()
    {
        this.free(getPointer());
    }
    
    private native void free(long AVPacketPointer);
    
    public int getSize()
    {
        return this.getSize(this.getPointer());
    }
    
    private native int getSize(long AVPacketPointer);
    
    public byte[] getData()
    {
        return this.getData(this.getPointer());
    }
    
    private native byte[] getData(long AVPacketPointer);
    
    /**
     * Find the first occurrence of the packet in the given file.
     * @param fileNamePath file to search
     * @param searchLimitBytes How far into the file to search for the data
     * @return Returns the start position of the data, or -1 if there was an error, or the data was not found.
     */
    public int findPositionInFile(String fileNamePath, int searchLimitBytes)
    {
        FileInputStream input = null;
        int ret = -1;
        
        try
        {
            byte[] picture = this.getData();
            input = new FileInputStream(fileNamePath);

            byte value[] = new byte[1];
            int inputPos = 0;
            int picPos = 0;
            int matchPos = 0;

            
            while(input.read(value) != -1 && inputPos < (searchLimitBytes + this.getSize()))
            {
                if(picPos == picture.length - 1)
                {
                    //Complete match found
                    ret = matchPos;
                    break;
                }

                if(value[0] == picture[picPos])
                {
                    //Current pos still matching
                    picPos++;
                    inputPos++;
                }
                else
                {
                    //Did not match.  Reset variables
                    inputPos++;
                    matchPos = inputPos;
                    picPos = 0;
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        finally
        {
            if(input != null)
            {
                try
                {
                    input.close();
                }
                catch(Exception ex2){}
            }
        }
        
        return ret;
    }
    
}
