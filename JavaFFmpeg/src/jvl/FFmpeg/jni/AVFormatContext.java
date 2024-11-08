/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jvl.FFmpeg.jni;

//TODO: Split out AVFormat and AVFormatContext.  Everything that needs the pointer shoulr probably use the context

import java.io.File;
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

        try
        {
            /* Perform some basic checks */
            File file = new File(fileNamePath);
            
            if(!file.exists())
            {
                System.out.println("File: " + file.getAbsolutePath());
                System.out.println("Warning: It does not appear that the file exists");
            }
            
            if(!file.canRead())
            {
                System.out.println("File: " + file.getAbsolutePath());
                System.out.println("Warning: It does not appear that there is read access to the file");
            }
            
        }
        catch(Exception ex)
        {
            System.out.println("Unexpected exeception checking access to file: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        
        
        AVFormatContext avformat = new AVFormatContext(pointer, fileNamePath);
        
        int ret = avformat.openInput(avformat.getPointer(), fileNamePath);
        
        if(ret < 0)
        {
            throw new RuntimeException("Error opening file.  ffmpeg error : " + ret + " - " + AVGlobal.getAVError(ret));
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
    * @param fileNamePath Full path to the file
    * @return avformant context if there is no error opening file
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
    * note: this function isn't guaranteed to open all the codecs, so
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
    
    public static native AVRational guessFramerate();
    
    /**
     * The duration in microseconds
     * 
     * Note: Divide by 1000 to get milliseconds
     * @return Duration in microseconds
     */
    public long getDuration()
    {
        validateFileOpen();   
        this.findStreamInfo();
        return this.getDuration(this.getPointer());
    }
    
    private native long getDuration(long AVFormatContextPointer);
    
    /**
     * Descriptive name for the format, meant to be more human-readable
     * than name. You should use the NULL_IF_CONFIG_SMALL() macro
     * to define it.
     * 
     * @return Format name in long form
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
     * @return Format name
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
     * @return Mime type
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
        this.findStreamInfo();
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
    * @param  packet A packet to read the frame into
    * 
    * @return 0 if OK, less than 0 on error or end of file
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
