
package jvl.FFmpeg.jni;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AVCodecTest
{
    AVFormatContext avformat;
    AVCodecParameters avparamVideo;
    AVCodecParameters avparamAudio;
    AVCodec avcodecVideo;
    
    public AVCodecTest()
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
    }
    
    @After
    public void tearDown()
    {
        avformat.closeInput();
    }

    @Test
    public void testGetLongName()
    {
        System.out.println(avcodecVideo.getLongName());
    }
    
    @Test
    public void testGetName()
    {
        System.out.println(avcodecVideo.getName());
    }
    
    @Test 
    public void testGetEncoder()
    {
        AVCodec encoder = AVCodec.getAVCodecEncoder(avparamVideo);
        
        Assert.assertTrue(encoder.getPointer() > 0);
    }
    
    @Test 
    public void testGetEncoderByName()
    {
        AVCodec encoder = AVCodec.findEncoderByName("libx265", avparamVideo);
        
        Assert.assertTrue(encoder.getPointer() > 0);
    }
    
    @Test
    public void testAllocateContext()
    {
        AVCodecContext avcodecContext = avcodecVideo.allocateContext();
        System.out.println(avcodecContext.getPointer());
        avcodecContext.freeContext();
    }
    
    @Test
    public void testReadFrame()
    {
        
        AVCodecContext avcodecContext = avcodecVideo.allocateContext();
        
        System.out.println("AVCodecContext: " + avcodecContext.getPointer());
        
        //avcodecVideo.copyParamsToContext(avcodecContext, avparamVideo);
        //avcodecVideo.open(avcodecContext);
        
        AVPacket packet = AVPacket.buildAVPacket();
        
        int ret = this.avformat.readFrame(packet);
        
        
        System.out.println("PTS: " + packet.getPTS());
        System.out.println("DTS: " + packet.getDTS());
        System.out.println("Position: " + packet.getPosition());
        
        packet.unreference();
       
    }
    
    @Test
    public void testDecodeFrame()
    {
        
        AVCodecContext avcodecContext = avcodecVideo.allocateContext();
        
        System.out.println("AVCodecContext: " + avcodecContext.getPointer());
        
        //avcodecVideo.copyParamsToContext(avcodecContext, avparamVideo);
        //avcodecVideo.open(avcodecContext);
        
        AVPacket packet = AVPacket.buildAVPacket();
        AVFrame frame = AVFrame.buildAVFrame();
        
        int ret = this.avformat.readFrame(packet);
        
        /*
        System.out.println("PTS: " + packet.getPTS());
        System.out.println("DTS: " + packet.getDTS());
        System.out.println("Position: " + packet.getPosition());
        */
        ret = avcodecContext.sendPacket(packet);
        System.out.println("Send Packet Ret: " + ret);
        
        ret = avcodecContext.receiveFrame(frame);
        System.out.println("Receive Frame Ret: " + ret);
        
        System.out.println("Picture Type: " + frame.getPictureTypeChar());
        System.out.println("PTS: " + frame.getPTS());
        System.out.println("Key Frame: " + frame.getKeyFrame());
        System.out.println("Packet Size: " + frame.getPacketSize());
        System.out.println("Line Size: " + frame.getLineSize());
        System.out.println("Width: " + frame.getWidth());
        System.out.println("Height: " + frame.getHeight());
        
        int amount = frame.getWidth() * frame.getHeight();
        
        long start;
        long end;
        
        start = System.currentTimeMillis();
        
        for(int i = 0; i < amount; i++)
        {
            byte test = frame.getData(0, i);
        }
        
        end = System.currentTimeMillis();

        System.out.println("Length of time: " + (end - start));
        
        start = System.currentTimeMillis();
        
        frame.test();
        
        end = System.currentTimeMillis();
        
        System.out.println("Length of time: " + (end - start));
        
        packet.unreference();
       
    }
    
    @Test
    public void testBuildFrame()
    {
        System.out.println(AVFrame.buildAVFrame().getPointer());
    }
    
    @Test
    public void testBuildPacket()
    {
        System.out.println(AVFrame.buildAVFrame().getPointer());
    }
    
    @Test
    public void testFramerate()
    {
        
        AVCodecContext avcodecContext = avcodecVideo.allocateContext();
        
        //avcodecVideo.copyParamsToContext(avcodecContext, avparamVideo);
        
        //avcodecVideo.open(avcodecContext);
        
        System.out.println("Framerate den: " + avcodecContext.getFrameRateDenominator());
        System.out.println("Framerate num: " + avcodecContext.getFrameRateNumerator());
        System.out.println("Framerate " + avcodecContext.getFramerate());
       
    }
    
    @Test
    public void test()
    {
        long temp = gcd(1920, 1080);
        
        System.out.println(temp);
        
        System.out.println((1920 / temp) + ":" + (1080 / temp));
                
    }
    
    private static long lcm(long a, long b)
    {
        return a * (b / gcd(a, b));
    }
    
    private static long gcd(long a, long b)
    {
        while (b > 0)
        {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }
}

    

