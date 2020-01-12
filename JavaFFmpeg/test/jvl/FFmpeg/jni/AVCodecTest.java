
package jvl.FFmpeg.jni;

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
        
        avparamVideo = avformat.getAVCodecParameters(0);
        avparamAudio = avformat.getAVCodecParameters(1);
        
        avcodecVideo = AVCodec.getAVCodec(avparamVideo);
    }
    
    @After
    public void tearDown()
    {
        avformat.freeContext();
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
    public void testAllocateContext()
    {
        AVCodecContext avcodecContext = avcodecVideo.allocateContext();
        System.out.println(avcodecContext.getPointer());
        avcodecContext.freeContext();
    }
    
    @Test
    public void testOpen()
    {
        AVCodecContext avcodecContext = avcodecVideo.allocateContext();
        avcodecVideo.open(avcodecContext);
        avcodecContext.freeContext();
    }
    
    @Test
    public void testReadFrame()
    {
        
        AVCodecContext avcodecContext = avcodecVideo.allocateContext();
        
        System.out.println("AVCodecContext: " + avcodecContext.getPointer());
        
        avcodecVideo.copyParamsToContext(avcodecContext, avparamVideo);
        
        avcodecVideo.open(avcodecContext);
        
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
        
        avcodecVideo.copyParamsToContext(avcodecContext, avparamVideo);
        
        avcodecVideo.open(avcodecContext);
        
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
    public void testJNITime()
    {
        
        AVCodecContext avcodecContext = avcodecVideo.allocateContext();
        
        avcodecVideo.copyParamsToContext(avcodecContext, avparamVideo);
        avcodecVideo.open(avcodecContext);
        
        AVPacket packet = AVPacket.buildAVPacket();
        AVFrame frame = AVFrame.buildAVFrame();
        
        //int ret = this.avformat.readFrame(packet);

        
        int amount = frame.getWidth() * frame.getHeight();
        
        long start;
        long end;
        
        start = System.currentTimeMillis();
        
        for(int j = 0; j < 80; j++)
        {
            this.avformat.readFrame(packet);
            avcodecContext.sendPacket(packet);
            avcodecContext.receiveFrame(frame);
            
            if(packet.getStreamIndex() == avparamVideo.getStreamIndex())
            {
            
                System.out.println(frame.getPTS());

                for(int i = 0; i < amount; i++)
                {
                    byte test = frame.getData(0, i);
                }

                packet.unreference();
            }
        }
        
        packet.free();
        frame.free();
        
        end = System.currentTimeMillis();

        System.out.println("Length of time: " + (end - start));
       
    }
    
    @Test
    public void testNativeTime()
    {
        
        AVCodecContext avcodecContext = avcodecVideo.allocateContext();
        
        avcodecVideo.copyParamsToContext(avcodecContext, avparamVideo);
        avcodecVideo.open(avcodecContext);
        
        AVPacket packet = AVPacket.buildAVPacket();
        AVFrame frame = AVFrame.buildAVFrame();
        
        int amount = frame.getWidth() * frame.getHeight();
        
        long start;
        long end;
        
        start = System.currentTimeMillis();
        
        for(int j = 0; j < 250; j++)
        {
            this.avformat.readFrame(packet);
            avcodecContext.sendPacket(packet);
            avcodecContext.receiveFrame(frame);
            
            if(packet.getStreamIndex() == avparamVideo.getStreamIndex())
            {
            
                System.out.println(frame.getPTS());

                for(int i = 0; i < amount; i++)
                {
                    frame.test();
                }

                packet.unreference();
            }
        }
        
        end = System.currentTimeMillis();

        System.out.println("Length of time: " + (end - start));

       
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
    public void test()
    {
        
        //byte value = AVFrame.test();
        
        //char cvalue = (char)value;
        
        //System.out.println("Value: " + cvalue);
    }
}
