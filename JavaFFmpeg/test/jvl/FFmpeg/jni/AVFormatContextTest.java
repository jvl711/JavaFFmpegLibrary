
package jvl.FFmpeg.jni;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Assert;

public class AVFormatContextTest
{
    AVFormatContext avformat;
        
    public AVFormatContextTest()
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
        assertTrue(avformat.getPointer() > 0);        
        avformat.openInput("src/examples/SampleVideo_1280x720_1mb.mkv");
    }
    
    @After
    public void tearDown()
    {
        avformat.closeInput();    
    }

     
    @Test
    public void buildOutputContext()
    {
        AVFormatContext context = AVFormatContext.buildAVFormatOutputContext("test.mkv");
        assertTrue(context.getPointer() > 0);
    }
    
    @Test 
    public void allocateNewStream()
    {
        AVFormatContext context = AVFormatContext.buildAVFormatOutputContext("test.mkv");
        AVStream newStream = context.allocateNewStream();
        assertTrue(newStream.getPointer() > 0);
    }
    
    @Test
    public void getFormatLongName()
    {
        Assert.assertEquals("Matroska / WebM", avformat.getFormatLongName());
    }
    
    @Test
    public void getFormatName()
    {   
        Assert.assertEquals("matroska,webm", avformat.getFormatName());
    }
    
    @Test
    public void getMimeType()
    {
        Assert.assertEquals("audio/webm,audio/x-matroska,video/webm,video/x-matroska", avformat.getMimeType());
    }
    
    @Test
    public void getNumberOfStreams()
    {       
        Assert.assertEquals(avformat.getNumberOfStreams(), 2);
    }
    
    @Test 
    public void getAVCodecParameters()
    {
        assertTrue(avformat.getAVCodecParameters(0).getPointer() > 0);
    }
    
    @Test
    public void getBitrate()
    {
        assertTrue(avformat.getBitrate() == 2338695);
    }
    
    @Test
    public void getDuration()
    {
        assertTrue(avformat.getDuration() == 3600000);
    }
}
