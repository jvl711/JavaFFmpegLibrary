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
        
        //avformat.openInput("src/examples/SampleVideo_1280x720_1mb.mkv");
        avformat.openInput("src/examples/H265_60_seconds.mkv");
        
        avparamVideo = avformat.getAVCodecParameters(0);
        avparamAudio = avformat.getAVCodecParameters(1);
    }
    
    
    @After
    public void tearDown()
    {
        avformat.freeContext();
        
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
}
