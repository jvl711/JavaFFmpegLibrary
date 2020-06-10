
package jvl.FFmpeg.jni;

import java.util.HashMap;
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
        avformat = AVFormatContext.buildAVFormatInputContext("src/examples/SampleVideo_1280x720_1mb.mkv");
        assertTrue(avformat.getPointer() > 0);        
        //avformat.openInput("src/examples/SampleVideo_1280x720_1mb.mkv");
    }
    
    @After
    public void tearDown()
    {
        avformat.close();    
    }

     
    @Test
    public void buildOutputContext()
    {
        AVFormatContext context = AVFormatContext.buildAVFormatOutputContext("test.mkv");
        assertTrue(context.getPointer() > 0);
    }
    
    @Test
    public void buildInputContext()
    {
        AVFormatContext context = AVFormatContext.buildAVFormatInputContext("test.mkv");
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
    
    @Test
    public void getMetadataCount()
    {
        AVFormatContext avformat2 = AVFormatContext.buildAVFormatInputContext("C:\\Users\\jvl711.CORE\\Documents\\TestDataMusic\\02 Awolnation - Some Sort of Creature2.flac");
        //avformat2.openInput("C:\\Users\\jvl711.CORE\\Documents\\TestDataMusic\\02 Awolnation - Some Sort of Creature2.flac");
        System.out.println(avformat2.getMetadataCount());
    }
    
    @Test
    public void getMetadata()
    {
        AVFormatContext avformat2 = AVFormatContext.buildAVFormatInputContext("C:\\Users\\jvl711.CORE\\Documents\\TestDataMusic\\02 Awolnation - Some Sort of Creature2.flac");
        //avformat2.openInput("C:\\Users\\jvl711.CORE\\Documents\\TestDataMusic\\02 Awolnation - Some Sort of Creature2.flac");
        
        HashMap<String, String> metadata = avformat2.getMetadata();
        
        for(String key : metadata.keySet())
        {
            System.out.println("Key: " + key + " Value: " + metadata.get(key));
        }
    }
}
