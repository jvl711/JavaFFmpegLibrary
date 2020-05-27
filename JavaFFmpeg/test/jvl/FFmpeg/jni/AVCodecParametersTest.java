/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jvl.FFmpeg.jni;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class AVCodecParametersTest
{
    AVFormatContext avformat;
    AVCodecParameters avparamVideo;
    AVCodecParameters avparamAudio;
        
    public AVCodecParametersTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
        avformat = AVFormatContext.buildAVFormatContext();
        avformat.openInput("src/examples/SampleVideo_1280x720_1mb.mkv");
        assertTrue(avformat.getPointer() > 0);
        avparamVideo = avformat.getAVCodecParameters(0);
        assertTrue(avparamVideo.getPointer() > 0);
        avparamAudio = avformat.getAVCodecParameters(1);
        assertTrue(avparamAudio.getPointer() > 0);
    }
    
    
    @After
    public void tearDown()
    {
        avformat.closeInput();
    }
    
    @Test 
    public void getAVCodecParametersCodecType()
    {
        assertTrue(avparamVideo.getCodecType() == AVMediaType.VIDEO);
        assertTrue(avparamAudio.getCodecType() == AVMediaType.AUDIO);
    }
    
    @Test
    public void testGetWidth()
    {
        System.out.println(this.avparamVideo.getWidth());
    }
    
    @Test
    public void testGetHeight()
    {
        System.out.println(this.avparamVideo.getHeight());
    }
    
    @Test
    public void testGetProfileName()
    {
        System.out.println(this.avparamVideo.getProfileName());
        System.out.println(this.avparamAudio.getProfileName());
    }
    
    @Test
    public void testGetSampleRate()
    {
        System.out.println(this.avparamAudio.getSampleRate());
    }
    
    @Test
    public void testBitrate()
    {
        System.out.println(this.avparamAudio.getBitrate());
    }
    
    @Test
    public void testGetChannels()
    {
        System.out.println(this.avparamAudio.getChannels());
    }
    
    @Test
    public void testGetLevel()
    {
        System.out.println(this.avparamAudio.getLevel());
    }
    
    @Test 
    public void testGetCodecID()
    {
        System.out.println(avparamVideo.getCodecID());
    }
    
    @Test 
    public void testGetFieldOrder()
    {
        System.out.println(avparamVideo.getFieldOrder());
    }
    
    @Test
    public void TestgetAspectRatio()
    {
        System.out.println("Aspect Ratio " + avparamVideo.getAspectRatio());
        System.out.println("Aspect Ratio String " + avparamVideo.getAspectRatioString());
        System.out.println("Samp aspect den " + avparamVideo.getSampleAspectRatioDenominator());
        System.out.println("Samp aspect num " + avparamVideo.getSampleAspectRatioNumerator());
    }
}
