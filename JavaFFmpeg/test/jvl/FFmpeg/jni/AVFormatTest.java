
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
    AVFormatContext avformat;
    
    
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
        avformat = AVFormatContext.buildAVFormatContext();
        
        avformat.openInput("src/examples/H265_60_seconds.mkv");       
    }
    
    @After
    public void tearDown()
    {
        avformat.freeContext();
        
    }

    
    @Test
    public void testFormatLongName()
    {
        String ret = avformat.getFormatLongName();
        
        System.out.println(ret);        
    }
    
    @Test
    public void testFormatName()
    {
        String ret = avformat.getFormatName();
        
        System.out.println(ret);
    }
    
    @Test
    public void testMimeType()
    {
        String ret = avformat.getMimeType();
        
        System.out.println(ret);
    }
    
    @Test
    public void testGetNumberOfStreams()
    {
        avformat.findStreamInfo();
        
        int streams = avformat.getNumberOfStreams();
        
        System.out.println(streams);
    }
    
    @Test 
    public void getAVCodecParameters()
    {
        //avformat.getAVCodecParameters(0);
        
        System.out.println(avformat.getAVCodecParameters(0).getPointer());
    }
    
    @Test 
    public void getAVCodecParametersCodecType()
    {
        //avformat.getAVCodecParameters(0);
        
        //avformat.findStreamInfo();
        
        System.out.println(avformat.getAVCodecParameters(0).getCodecType());
    }
    
    @Test
    public void getBitrate()
    {
        //avformat.findStreamInfo();
        
        System.out.println(avformat.getBitrate());
    }
    
    @Test
    public void getDuration()
    {
        System.out.println(avformat.getDuration());
    }
}
