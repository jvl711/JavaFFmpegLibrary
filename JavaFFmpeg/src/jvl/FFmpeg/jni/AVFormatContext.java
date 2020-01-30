/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jvl.FFmpeg.jni;

//TODO: Split out AVFormat and AVFormatContext.  Everything that needs the pointer shoulr probably use the context

public class AVFormatContext
{
    private final long AVFormatContextPointer;
    
    private boolean isFindStreamInfoCalled;
    private boolean isFileOpen;
    
    static
    {
        Global.loadLibraries();
    }
    
    private AVFormatContext(long AVFormatContextPointer)
    {
        this.isFindStreamInfoCalled = false;
        this.isFileOpen = false;
        this.AVFormatContextPointer = AVFormatContextPointer;
    }
    
    public static AVFormatContext buildAVFormatContext()
    {
        long pointer = allocateContext();
        
        return new AVFormatContext(pointer);
    }
    
    private static native long allocateContext();
    
    
    /*
    public void freeContext()
    {
        this.freeContext(this.AVFormatContextPointer);
    }
    
    private native void freeContext(long AVFormatPointer);
    */
    
    public void openInput(String filePath)
    {
        int ret = openInput(this.AVFormatContextPointer, filePath);

        if(ret < 0)
        {
            throw new RuntimeException("Error opening file.  ffmpeg error code: " + ret);
        }
        else
        {
            isFileOpen = true;
        }
        
        //:TODO get the error mesaage from ffmpeg library
    }
    
    
    private native int openInput(long AVFormatPointer, String filePath);
    
    /**
    * Close an opened input AVFormatContext. Free it and all its contents
    * and set *s to NULL.
    */
    public void closeInput()
    {
        //TODO: Change all pointer calls to getPointer, and throw error if deallocated
        
        if(this.isFileOpen)
        {
            this.closeInput(this.AVFormatContextPointer);
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
        if(!this.isFindStreamInfoCalled)
        {
            System.out.println("Find stream info called");
            int ret = this.findStreamInfo(this.AVFormatContextPointer);
            
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
        return this.getDuration(this.AVFormatContextPointer);
    }
    
    private native long getDuration(long AVFormatContextPointer);
    
    /**
     * Descriptive name for the format, meant to be more human-readable
     * than name. You should use the NULL_IF_CONFIG_SMALL() macro
     * to define it.
     */
    public String getFormatLongName()
    {
        return this.getFormatLongName(this.AVFormatContextPointer);
    }
    
    private native String getFormatLongName(long AVFormatContextPointer);
    
    /**
     * A comma separated list of short names for the format. New names
     * may be appended with a minor bump.
     */
    public String getFormatName()
    {
        return this.getFormatName(this.AVFormatContextPointer);
    }
    
    private native String getFormatName(long AVFormatContextPointer);
    
    /**
     * Comma-separated list of mime types.
     * It is used check for matching mime types while probing.
     */
    public String getMimeType()
    {
        return this.getMimeType(this.AVFormatContextPointer);
    }
    
    private native String getMimeType(long AVFormatContextPointer);
    
    public int getNumberOfStreams()
    {
        return this.getNumberOfStreams(this.AVFormatContextPointer);
    }
    
    private native int getNumberOfStreams(long AVFormatContextPointer);
    
    public AVCodecParameters getAVCodecParameters(int streamIndex)
    {
        this.findStreamInfo();
        return new AVCodecParameters(this.getAVCodecParameters(this.AVFormatContextPointer, streamIndex), streamIndex);
    }
    
    private native long getAVCodecParameters(long AVFormatContextPointer, int streamIndex);
    
    public AVStream getAVStream(int streamIndex)
    {
        this.findStreamInfo();
        return new AVStream(this.getAVStream(this.AVFormatContextPointer, streamIndex), streamIndex);
    }
    
    private native long getAVStream(long AVFormatContextPointer, int streamIndex);
    
    public long getBitrate()
    {
        this.findStreamInfo();
        return this.getBitrate(AVFormatContextPointer);
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
        return readFrame(this.AVFormatContextPointer, packet.getPointer());    
    }
    
    private native int readFrame(long AVFormatContextPointer, long AVFramePointer);
    
    
    public void debug()
    {
        this.debug(this.AVFormatContextPointer);
    }
    
    private native void debug(long pointer);
            
}
