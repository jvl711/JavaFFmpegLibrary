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
public class AVStreamMap
{
    AVCodecContext decoderContext = null;
    AVCodecContext encoderContext = null;
    
    public AVStreamMap() { }
    
    public void setDecoderContext(AVCodecContext context)
    {
        this.decoderContext = context;
    }
    
    public void setEncoderContext(AVCodecContext context)
    {
        this.encoderContext = context;
    }
    
    public AVCodecContext getEncoderContext()
    {
        return this.encoderContext;
    }
    
    public AVCodecContext getDecoderContext()
    {
        return this.decoderContext;
    }
}
