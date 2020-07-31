
package jvl.FFmpeg.jni;

public class AVPixelFormat
{
    private String name;
    private int id;
    
    public AVPixelFormat(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public int getId()
    {
        return this.id;
    }
    
}
