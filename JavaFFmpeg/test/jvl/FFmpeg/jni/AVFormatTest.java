
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
        
        //avformat.openInput("src/examples/H265_60_seconds.mkv");       
        //avformat.openInput("C:\\Users\\jvl711.CORE\\Documents\\TestData\\The Man in the High Castle - S01E01 - The New World1.mkv");       
        avformat.openInput("\\\\egypt\\tv\\Anthony Bourdain No Reservations\\Season 04\\Anthony.Bourdain.No.Reservations.S04E20.At.the.Table.with.Anthony.Bourdain.mkv");
        //System.out.println("Finished open");
    }
    
    @After
    public void tearDown()
    {
        avformat.closeInput();
        
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
    
    @Test
    public void debug()
    {
        //avformat.findStreamInfo();
        //avformat.debug();
    }
}
