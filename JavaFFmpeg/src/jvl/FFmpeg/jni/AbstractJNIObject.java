
package jvl.FFmpeg.jni;


public class AbstractJNIObject
{
    private long pointer;
    private boolean isValidPointer;
    
    static
    {
        Global.loadLibraries();
    }
    
    protected AbstractJNIObject(long pointer)
    {
        this.setPointer(pointer);
    }
    
    protected void setPointer(long pointer)
    {
        if(pointer > 0)
        {
            this.pointer = pointer;
            isValidPointer = true;
        }
    }
    
    public long getPointer()
    {
        if(!isValidPointer)
        {
            throw new RuntimeException("Attempting to access an unassigned or invalid pointer");
        }
        else
        {
            return this.pointer;
        }
    }
    
    protected boolean isValidPointer()
    {
        return this.isValidPointer;
    }
    
}
