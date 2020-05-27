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

/**
 *
 * @author jvl711
 */
public class AVCodecContextTest
{
    AVFormatContext avformat;
    AVCodecParameters avparamVideo;
    AVCodecParameters avparamAudio;
    AVCodec avcodecVideo;
    AVCodecContext avcodecVideoContext;
        
    public AVCodecContextTest()
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
        //avformat.openInput("src/examples/H265_60_seconds.mkv");
        
        avformat.getNumberOfStreams();
        
        avparamVideo = avformat.getAVCodecParameters(0);
        avparamAudio = avformat.getAVCodecParameters(1);
        
        avcodecVideo = AVCodec.getAVCodecDecoder(avparamVideo);
        
        avcodecVideoContext = avcodecVideo.allocateContext();
        
    }
    
    @After
    public void tearDown()
    {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testGetWidth()
    {
        System.out.println(avcodecVideoContext.getWidth());
    }
    
    @Test
    public void testGetHeight()
    {
        System.out.println(avcodecVideoContext.getHeight());
    }
}
