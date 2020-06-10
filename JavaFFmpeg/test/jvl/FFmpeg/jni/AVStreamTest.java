
package jvl.FFmpeg.jni;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jvl711
 */
public class AVStreamTest 
{
    
    AVFormatContext avformat;
    AVFormatContext avformat2;
    AVCodecParameters avparams;
    AVCodec avcodec;
    AVStream avstreamVideo;
    AVStream avstreamAudio;
    AVStream avstreamAttachedPicture;
    
    public AVStreamTest() 
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
        avformat = AVFormatContext.buildAVFormatInputContext("src/examples/Sample_BeeMoved_96kHz24bit.flac");
        avformat2 = AVFormatContext.buildAVFormatInputContext("src/examples/SampleVideo_1280x720_2mb.mp4");
        avstreamVideo = avformat2.getAVStream(0);
        avstreamAudio = avformat2.getAVStream(1);
        avstreamAttachedPicture = avformat.getAVStream(1);
    }
    
    @After
    public void tearDown() 
    {
        avformat.close();
        avformat2.close();
    }

    @Test 
    public void TestIsDefault()
    {
        Assert.assertTrue(avstreamVideo.isDefault());
        Assert.assertTrue(!avstreamAttachedPicture.isDefault());
    }
    
    @Test 
    public void TestIsForced()
    {
        //TODO: Add an example file that has a forced subtitle
        Assert.assertTrue(!avstreamVideo.isForced());
        Assert.assertTrue(!avstreamAttachedPicture.isForced());
        
    }
    
    @Test
    public void testIsAttachedPicture()
    {
        Assert.assertTrue(avstreamAttachedPicture.isAttachedPicture());
        Assert.assertFalse(avstreamAudio.isAttachedPicture());
        
    }
    
    @Test
    public void testGetAttachedPicture()
    {
        AVPacket picture = avstreamAttachedPicture.getAttachedPicturePacket();
        
        Assert.assertEquals(picture.getPosition(), 251);
        Assert.assertEquals(picture.getSize(), 58336);
        Assert.assertEquals(picture.getData().length, 58336);
    }

    @Test
    public void testFramerate()
    {
        Assert.assertEquals(avstreamVideo.getFramerateDenominator(), 1);
        Assert.assertEquals(avstreamVideo.getFramerateNumerator(), 25);
        Assert.assertEquals(avstreamVideo.getFramerate(), 25.0,  0.0);
    }

    @Test
    public void testGetLanguage()
    {
        Assert.assertEquals(avstreamVideo.getLanguage(), "und");
    }
    
    @Test
    public void getMetadata()
    {
        HashMap<String, String> metadata = avstreamAttachedPicture.getMetadata();
        
        Assert.assertEquals(metadata.size(), 2);
        Assert.assertEquals(metadata.get("title"), "image/jpeg");
    }

}
