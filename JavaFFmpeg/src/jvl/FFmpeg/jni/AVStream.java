/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jvl.FFmpeg.jni;

import java.util.Dictionary;
import java.util.HashMap;

/**
 *
 * @author jvl711
 */
public class AVStream extends AbstractJNIObject
{
    

    private AVFormatContext avFormat;
    
    protected AVStream(long AVStreamPointer, AVFormatContext avFormat)
    {
        super(AVStreamPointer);
        this.avFormat = avFormat;
    }
    
    /**
    * Track should be used during playback by default.
    * Useful for subtitle track that should be displayed
    * even when user did not explicitly ask for subtitles.
    * @return true id this is a forced stream
    */
    public boolean isForced()
    {
        return this.isForced(this.getPointer());
    }
    
    private native boolean isForced(long AVStreamPointer);
    
    /**
    * The stream is stored in the file as an attached picture/"cover art" (e.g.
    * APIC frame in ID3v2). The first (usually only) packet associated with it
    * will be returned among the first few packets read from the file unless
    * seeking takes place. It can also be accessed at any time in
    * AVStream.getAttachedPicturePacket
    * 
    * @return  True if the stream is an attached picture, false otherwise
    */
    public boolean isAttachedPicture()
    {
        return this.isAttachedPicture(this.getPointer());
    }
    
    private native boolean isAttachedPicture(long AVStreamPointer);
    
    /**
     * Determines if this is the default playback stream 
     * @return True if this is the default stream otherwise false
     */
    public boolean isDefault()
    {
        return this.isDefault(this.getPointer());
    }
    
    private native boolean isDefault(long AVStreamPointer);
    
    public AVRational guessFramerate()
    {
        return this.guessFramerate(this.avFormat.getPointer(), this.getPointer());
    }
    
    private native AVRational guessFramerate(long AVFormatContextPointer, long AVStreamPointer);
    
    /**
     * Gets the framerate in AVRational object. If the Denominator is 0 than the framerate is unknown 
     * 
     * @return Framerate in AVRational 
     */
    public AVRational getFramerate()
    {
        return this.getFramerate(this.getPointer());
    }
    
     
    private native AVRational getFramerate(long AVStreamPointer);
    
    /**
     * This will return the language from the metadata associated with the
     * stream if one exists.  This is a shortcut to the metadata 
     * @return The language or an empty string if one is not present
     */
    public String getLanguage()
    {
        String temp = this.getMetadataValue("language");
        
        if(temp == null)
        {
            temp = "";
        }
        
        return temp;
    }
    
    /**
     * Gets all of the metadata attached/associated with this stream
     * @return HashMap of key value pairs of the metadata.
     */
    public HashMap<String, String> getMetadata()
    {
        HashMap<String, String> metadata = new HashMap<String, String>();
        int count = this.getMetadataCount();
        
        for(int i = 0; i < count; i++)
        {
            metadata.put(this.getMetadataKey(i), this.getMetadataValue(i));
        }
        
        return metadata;
    }
    
    /**
     * Count of metadata key/value pairs attached/associated with the stream
     * @return The count
     */
    public int getMetadataCount()
    {
        return this.getMetadataCount(this.getPointer());
    }
    
    private native int getMetadataCount(long AVStreamPointer);
    
    private String getMetadataKey(int index)
    {
       if(index >= this.getMetadataCount())
       {
           throw new IndexOutOfBoundsException();
       }
       
       return this.getMetadataKey(this.getPointer(), index);
    }
    
    private native String getMetadataKey(long AVStreamPointer, int index);
    
    private String getMetadataValue(int index)
    {
       if(index >= this.getMetadataCount())
       {
           throw new IndexOutOfBoundsException();
       }
        
       return this.getMetadataValue(this.getPointer(), index);
    }
    
    private native String getMetadataValue(long AVStreamPointer, int index);
    
    private String getMetadataValue(String key)
    {
        if(key == null)
        {
            return null;
        }
        
       return this.getMetadataValueByKey(this.getPointer(), key);
    }
    
    private native String getMetadataValueByKey(long AVStreamPointer, String key);
    
    /**
     * If the stream represents an attached picture stream, than this method will
     * return the attached picture.  If the packet does not indicate the position
     * the data in the file, than it will attempt to find the position and set it
     * in the packet.
     * @return AVPacket of the attached picture if it exists
     */
    public AVPacket getAttachedPicturePacket()
    {
        if(this.isAttachedPicture())
        {
            long pointer = this.getAttachedPicturePacket(this.getPointer());
            AVPacket picture = new AVPacket(pointer);
            
            //Check to see if the position is -1 and see if we can identify the packet position inside the stream
            if(picture.getPosition() == -1 && !this.avFormat.getFileNamePath().isEmpty())
            {
                int ret = picture.findPositionInFile(this.avFormat.getFileNamePath(), 60000);
                
                if(ret != -1)
                {
                    picture.setPosition(ret);
                }
            }

            return picture;
        }
        else
        {
            return null;
        }
    }
    
    private native long getAttachedPicturePacket(long AVStreamPointer);
    
    public int getDisposition()
    {
        return this.getDisposition(this.getPointer());
    }
    
    private native int getDisposition(long AVStreamPointer);
    
    /**
     * Format-specific stream ID.
     * decoding: set by libavformat
     * encoding: set by the user, replaced by libavformat if left unset
     * 
     * @return The id of the stream
     */
    public int getID()
    {
        return this.getID(this.getPointer());
    }
    
    /**
     * Format-specific stream ID.
     * decoding: set by libavformat
     * encoding: set by the user, replaced by libavformat if left unset
     * 
     * Note: This is the same value as getID, but converted to a Hex String 
     * 
     * @return The id of the stream
     */
    public String getIDHex()
    {
        
        return Integer.toHexString(this.getID(this.getPointer()));
    }
    
    private native int getID(long AVStreamPointer);
    
}
