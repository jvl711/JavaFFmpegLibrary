/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jvl.FFmpeg.jni;

/**
 *
 * @author jvl711
 */
public enum AVFieldOrder 
{
    AV_FIELD_UNKNOWN(0),
    AV_FIELD_PROGRESSIVE(1),
    AV_FIELD_TT(2),  //< Top coded_first, top displayed first
    AV_FIELD_BB(3),  //< Bottom coded first, bottom displayed first
    AV_FIELD_TB(4),  //< Top coded first, bottom displayed first
    AV_FIELD_BT(5);  //< Bottom coded first, top displayed first
    
    private final int value;
   
    private AVFieldOrder(int value)
    {
        this.value = value;
    }
    
    public boolean isProgrssive()
    {
        if(this == this.AV_FIELD_PROGRESSIVE)
        {
            return true;
        }
        
        return false;
    }
    
    public boolean isInterlaced()
    {
        if(this == this.AV_FIELD_BB || this == this.AV_FIELD_BT || this == this.AV_FIELD_TB || this == this.AV_FIELD_TT)
        {
            return true;
        }
        
        return false;
    }
    
    public static AVFieldOrder parse(int value)
    {
        if(AVFieldOrder.AV_FIELD_BB.value == value)
        {
            return AVFieldOrder.AV_FIELD_BB;
        }
        else if(AVFieldOrder.AV_FIELD_BT.value == value)
        {
            return AVFieldOrder.AV_FIELD_BT;
        }
        else if(AVFieldOrder.AV_FIELD_PROGRESSIVE.value == value)
        {
            return AVFieldOrder.AV_FIELD_PROGRESSIVE;
        }
        else if(AVFieldOrder.AV_FIELD_TB.value == value)
        {
            return AVFieldOrder.AV_FIELD_TB;
        }
        else if(AVFieldOrder.AV_FIELD_TT.value == value)
        {
            return AVFieldOrder.AV_FIELD_TT;
        }
        else if(AVFieldOrder.AV_FIELD_UNKNOWN.value == value)
        {
            return AVFieldOrder.AV_FIELD_UNKNOWN;
        }        
        else
        {
            return AVFieldOrder.AV_FIELD_UNKNOWN;
        }
        
    }
    
}
