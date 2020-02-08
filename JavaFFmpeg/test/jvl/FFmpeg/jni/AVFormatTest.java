
package jvl.FFmpeg.jni;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import jvl.FFmpeg.jni.AVFormatContext;
import org.junit.Assert;


public class AVFormatTest
{
    AVFormatContext avformaterr;
    AVFormatContext avformat1;
    
    
    
    public AVFormatTest()
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
        avformat1 = AVFormatContext.buildAVFormatContext();
        avformaterr = AVFormatContext.buildAVFormatContext();
        
        avformat1.openInput("src/examples/H265_60_seconds.mkv");       
    }
    
    @After
    public void tearDown()
    {
        avformat1.closeInput();
        avformaterr.closeInput();
        
    }

     
    
    
    @Test
    public void TestFormatLongName()
    {
        String ret = avformat1.getFormatLongName();
        
        Assert.assertEquals("Matroska / WebM", avformat1.getFormatLongName());
    }
    
    @Test
    public void TestFormatName()
    {
        String ret = avformat1.getFormatName();
        
        Assert.assertEquals("matroska,webm", avformat1.getFormatName());
    }
    
    @Test
    public void TestMimeType()
    {
        Assert.assertEquals("audio/webm,audio/x-matroska,video/webm,video/x-matroska", avformat1.getMimeType());
    }
    
    @Test
    public void testGetNumberOfStreams()
    {
        avformat1.findStreamInfo();
        
        int streams = avformat1.getNumberOfStreams();
        
        System.out.println(streams);
    }
    
    @Test 
    public void getAVCodecParameters()
    {
        //avformat.getAVCodecParameters(0);
        
        System.out.println(avformat1.getAVCodecParameters(0).getPointer());
    }
    
    @Test 
    public void getAVCodecParametersCodecType()
    {
        //avformat.getAVCodecParameters(0);
        
        //avformat.findStreamInfo();
        
        System.out.println(avformat1.getAVCodecParameters(0).getCodecType());
    }
    
    @Test
    public void getBitrate()
    {
        //avformat.findStreamInfo();
        
        System.out.println(avformat1.getBitrate());
    }
    
    @Test
    public void getDuration()
    {
        System.out.println(avformat1.getDuration());
    }
    
    @Test
    public void debug()
    {
        //avformat.findStreamInfo();
        //avformat.debug();
    }
}
