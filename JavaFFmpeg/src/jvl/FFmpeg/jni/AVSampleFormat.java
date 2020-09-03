
package jvl.FFmpeg.jni;


public class AVSampleFormat
{
    private String name;
    private int id;
    
    public AVSampleFormat(int id, String name)
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
