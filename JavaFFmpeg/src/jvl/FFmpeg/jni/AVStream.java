/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jvl.FFmpeg.jni;

import java.util.HashMap;

/**
 *
 * @author jvl711
 */
public class AVStream 
{
    private final long AVStreamPointer;

    static
    {
        Global.loadLibraries();
    }
    
    protected AVStream(long AVStreamPointer)
    {
        this.AVStreamPointer = AVStreamPointer;
    }
    
    public long getPointer()
    {
        return this.AVStreamPointer;
    }
    
    
    public boolean isForced()
    {
        return this.isForced(AVStreamPointer);
    }
    
    private native boolean isForced(long AVStreamPointer);
    
    public boolean isDefault()
    {
        return this.isForced(AVStreamPointer);
    }
    
    private native boolean isDefault(long AVStreamPointer);
    
    /**
     * The Numerator of the framerate or 0 if it is unknown
     * 
     * @return Numerator 
     */
    public int getFramerateNumerator()
    {
        return this.getFramerateNumerator(AVStreamPointer);
    }
    
    private native int getFramerateNumerator(long AVStreamPointer);
    
    /**
     * The denominator of the framerate
     * 
     * @return Denominator 
     */
    public int getFramerateDenominator()
    {
        return this.getFramerateDenominator(AVStreamPointer);
    }
   
    private native int getFramerateDenominator(long AVStreamPointer);
    
    /**
     * Takes the num / den and returns it.  If either num or den are 0 then
     * if is assumed the framerate is unknown
     * 
     * @return The framerate if known, or 0 
     */
    public double getFramerate()
    {
        int num = this.getFramerateNumerator();
        int den = this.getFramerateDenominator();
        
        if(num == 0 || den == 0)
        {
            return 0;
        }
        else
        {
            return (num * 1.0) / (den * 1.0);
        }
    }
    
    public String debug()
    {
        return this.debug(AVStreamPointer);
    }
    
    private native String debug(long AVStreamPointer);
    
    /*
    private native String getMetadata(long AVStreamPointer);
    
    public HashMap<String, String> getMetadata()
    {
        HashMap<String, String> map = new HashMap<String, String>();
        
        String[] temp = this.getMetadata(this.AVStreamPointer).split("\n");
        
        for(int i = 0; i < temp.length; i++)
        {
            String[] entry = temp[i].split("\r");
            
            if(entry.length > 0)
            {
                String key = entry[0];
                String value = "";
                
                if(entry.length > 1)
                {
                    value = entry[1];
                }
                
                map.put(key, value);
            }
            
        }
        
        return map;
    }
    */
    
    public String getLanguage()
    {
        String temp = this.getLanguage(AVStreamPointer);
        
        if(temp == null)
        {
            temp = "";
        }
        
        return temp;
    }
    
    private native String getLanguage(long AVStreamPointer);
}
