/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jvl.FFmpeg.jni;

//TODO: Split out AVFormat and AVFormatContext.  Everything that needs the pointer shoulr probably use the context

import java.util.HashMap;


public class AVFormatContext extends AbstractJNIObject
{
    //private final long AVFormatContextPointer;
    
    private boolean isFindStreamInfoCalled;
    private boolean isFileOpen;
    private String fileNamePath;
    
    
    private AVFormatContext(long AVFormatContextPointer, String fileNamePath)
    {
        super(AVFormatContextPointer);
        this.isFindStreamInfoCalled = false;
        this.isFileOpen = false;
        this.fileNamePath = fileNamePath;
    }
    
    public static AVFormatContext buildAVFormatInputContext(String fileNamePath)
    {
        long pointer = allocateContext();

        AVFormatContext avformat = new AVFormatContext(pointer, fileNamePath);
        
        int ret = avformat.openInput(avformat.getPointer(), fileNamePath);
        
        if(ret < 0)
        {
            throw new RuntimeException("Error opening file.  ffmpeg error code: " + ret);
        }
        else
        {
            avformat.isFileOpen = true;
        }
        
        return avformat;
    }
    
    /**
    * Allocate an AVFormatContext for an output format.
    * avformat_free_context() can be used to free the context and
    * everything allocated by the framework within it.
    *
    * @param *ctx is set to the created format context, or to NULL in
    * case of failure
    * @param oformat format to use for allocating the context, if NULL
    * format_name and filename are used instead
    * @param format_name the name of output format to use for allocating the
    * context, if NULL filename is used instead
    * @param filename the name of the filename to use for allocating the
    * context, may be NULL
    * @return >= 0 in case of success, a negative AVERROR code in case of
    * failure
    */
    public static AVFormatContext buildAVFormatOutputContext(String fileNamePath)
    {
        long pointer = allocateOutputContext(fileNamePath);
        
        AVFormatContext avformat = new AVFormatContext(pointer, fileNamePath);
        
        avformat.isFileOpen = true;
        
        return avformat; 
    }
    
    private void validateFileOpen()
    {
        if(!this.isFileOpen)
        {
            throw new RuntimeException("There is no open file to execute request against.");
        }
    }
    
    private static native long allocateContext();
    
    private static native long allocateOutputContext(String filePath);
    
    /**
    * Add a new stream to a media file.
    *
    * When demuxing, it is called by the demuxer in read_header(). If the
    * flag AVFMTCTX_NOHEADER is set in s.ctx_flags, then it may also
    * be called in read_packet().
    *
    * When muxing, should be called by the user before avformat_write_header().
    *
    * User is required to call avcodec_close() and avformat_free_context() to
    * clean up the allocation by avformat_new_stream().
    *
    * @return newly created stream or NULL on error.
    */
    public AVStream allocateNewStream()
    {
        long pointer = this.allocateNewStream(this.getPointer());
        
        return new AVStream(pointer, this);
    }
    
    public String getFileNamePath()
    {
        return this.fileNamePath;
    }
    
    private static native long allocateNewStream(long AVFormatPointer);
    
    
    private native int openInput(long AVFormatPointer, String filePath);
    
    /**
    * Close an opened input AVFormatContext. Free it and all its contents
    * and set *s to NULL.
    */
    public void close()
    {
        //TODO: Change all pointer calls to getPointer, and throw error if deallocated
        
        //TODO: If the context is not open, but the pointer is valid, then need to avformat_free_context()
        
        if(this.isFileOpen)
        {
            this.closeInput(this.getPointer());
        }
    }
        
    private native void closeInput(long AVFormatPointer);
    
    /**
    * Read packets of a media file to get stream information. This
    * is useful for file formats with no headers such as MPEG. This
    * function also computes the real framerate in case of MPEG-2 repeat
    * frame mode.
    * The logical file position is not changed by this function;
    * examined packets may be buffered for later processing.
    *
    * @note this function isn't guaranteed to open all the codecs, so
    *       options being non-empty at return is a perfectly normal behavior.
    */
    public void findStreamInfo()
    {
        validateFileOpen();
        
        if(!this.isFindStreamInfoCalled)
        {
            int ret = this.findStreamInfo(this.getPointer());
            
            if(ret >= 0)
            {
                isFindStreamInfoCalled = true;
            }
            else
            {
                throw new RuntimeException("FFmpeg error finding stream info: " + ret);
            }
        }
    }
    
    /**
    * Read packets of a media file to get stream information. This
    * is useful for file formats with no headers such as MPEG. This
    * function also computes the real framerate in case of MPEG-2 repeat
    * frame mode.
    * The logical file position is not changed by this function;
    * examined packets may be buffered for later processing.
    *
    * @param AVFormatPointer pointer to avformat
    * @return >=0 if OK, AVERROR_xxx on error
    *
    * @note this function isn't guaranteed to open all the codecs, so
    *       options being non-empty at return is a perfectly normal behavior.
    */
    private native int findStreamInfo(long AVFormatPointer);
    
    /**
     * The duration in microseconds
     * 
     * Note: Divide by 1000 to get milliseconds
     * @return 
     */
    public long getDuration()
    {
        validateFileOpen();
        
        return this.getDuration(this.getPointer());
    }
    
    private native long getDuration(long AVFormatContextPointer);
    
    /**
     * Descriptive name for the format, meant to be more human-readable
     * than name. You should use the NULL_IF_CONFIG_SMALL() macro
     * to define it.
     */
    public String getFormatLongName()
    {
        validateFileOpen();
        
        return this.getFormatLongName(this.getPointer());
    }
    
    private native String getFormatLongName(long AVFormatContextPointer);
    
    /**
     * A comma separated list of short names for the format. New names
     * may be appended with a minor bump.
     */
    public String getFormatName()
    {
        validateFileOpen();
        
        return this.getFormatName(this.getPointer());
    }
    
    private native String getFormatName(long AVFormatContextPointer);
    
    /**
     * Comma-separated list of mime types.
     * It is used check for matching mime types while probing.
     */
    public String getMimeType()
    {
        validateFileOpen();
        
        return this.getMimeType(this.getPointer());
    }
    
    private native String getMimeType(long AVFormatContextPointer);
    
    public int getNumberOfStreams()
    {
        validateFileOpen();
        
        return this.getNumberOfStreams(this.getPointer());
    }
    
    private native int getNumberOfStreams(long AVFormatContextPointer);
    
    public AVCodecParameters getAVCodecParameters(int streamIndex)
    {
        validateFileOpen();
        
        this.findStreamInfo();
        return new AVCodecParameters(this.getAVCodecParameters(this.getPointer(), streamIndex), streamIndex);
    }
    
    private native long getAVCodecParameters(long AVFormatContextPointer, int streamIndex);
    
    public AVStream getAVStream(int streamIndex)
    {
        validateFileOpen();
        
        this.findStreamInfo();
        return new AVStream(this.getAVStream(this.getPointer(), streamIndex), this);
    }
    
    private native long getAVStream(long AVFormatContextPointer, int streamIndex);
    
    public long getBitrate()
    {
        validateFileOpen();
        
        this.findStreamInfo();
        return this.getBitrate(getPointer());
    }
    
    private native long getBitrate(long AVFormatContextPointer);
    
    /**
    * Return the next frame of a stream.
    * This function returns what is stored in the file, and does not validate
    * that what is there are valid frames for the decoder. It will split what is
    * stored in the file into frames and return one for each call. It will not
    * omit invalid data between valid frames so as to give the decoder the maximum
    * information possible for decoding.
    *
    * If pkt->buf is NULL, then the packet is valid until the next
    * av_read_frame() or until avformat_close_input(). Otherwise the packet
    * is valid indefinitely. In both cases the packet must be freed with
    * av_packet_unref when it is no longer needed. For video, the packet contains
    * exactly one frame. For audio, it contains an integer number of frames if each
    * frame has a known fixed size (e.g. PCM or ADPCM data). If the audio frames
    * have a variable size (e.g. MPEG audio), then it contains one frame.
    *
    * pkt->pts, pkt->dts and pkt->duration are always set to correct
    * values in AVStream.time_base units (and guessed if the format cannot
    * provide them). pkt->pts can be AV_NOPTS_VALUE if the video format
    * has B-frames, so it is better to rely on pkt->dts if you do not
    * decompress the payload.
    *
    * @param  packet A packet to read the frame into
    * 
    * @return 0 if OK, < 0 on error or end of file
    */
    public int readFrame(AVPacket packet)
    {
        validateFileOpen();
        
        return readFrame(this.getPointer(), packet.getPointer());    
    }
    
    private native int readFrame(long AVFormatContextPointer, long AVFramePointer);
    
     public HashMap<String, String> getMetadata()
    {
        HashMap<String, String> metadata = new HashMap<>();
        int count = this.getMetadataCount();
        
        for(int i = 0; i < count; i++)
        {
            System.out.println("Provcessing: " + i);
            System.out.println("Provcessing: " + this.getMetadataKey(i));
            metadata.put(this.getMetadataKey(i), this.getMetadataValue(i));
        }
        
        return metadata;
    }
    
    public int getMetadataCount()
    {
        return this.getMetadataCount(this.getPointer());
    }
    
    private native int getMetadataCount(long AVFormatContextPointer);
    
    private String getMetadataKey(int index)
    {
       if(index >= this.getMetadataCount())
       {
           throw new IndexOutOfBoundsException();
       }
       
       return this.getMetadataKey(this.getPointer(), index);
    }
    
    private native String getMetadataKey(long AVFormatContextPointer, int index);
    
    private String getMetadataValue(int index)
    {
       if(index >= this.getMetadataCount())
       {
           throw new IndexOutOfBoundsException();
       }
        
       return this.getMetadataValue(this.getPointer(), index);
    }
    
    private native String getMetadataValue(long AVFormatContextPointer, int index);
    
    private String getMetadataValue(String key)
    {
        if(key == null)
        {
            return null;
        }
        
       return this.getMetadataValueByKey(this.getPointer(), key);
    }
    
    private native String getMetadataValueByKey(long AVFormatContextPointer, String key);
            
}
