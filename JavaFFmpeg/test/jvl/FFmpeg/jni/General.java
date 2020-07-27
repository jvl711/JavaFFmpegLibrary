/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jvl.FFmpeg.jni;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
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
public class General
{
    
    public General()
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
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void test3()
    {
        AVRational rational = AVFormatContext.guessFramerate();
        
        System.out.println(rational.getDenominator());
        System.out.println(rational.getNumerator());
    }
    
    @Test
    public void transcodeDevelopmentTesting() 
    {
        //"C:\\Users\\jvl711.CORE\\Documents\\TestData\\june.ts"
        //AVFormatContext avcontext = AVFormatContext.buildAVFormatInputContext("src/examples/SampleVideo_1280x720_1mb.mkv");
        AVFormatContext avcontext = AVFormatContext.buildAVFormatInputContext("C:\\Users\\jvl711.CORE\\Documents\\TestData\\june.ts");
        
        //avcontext.openInput();
        avcontext.findStreamInfo();
        
        AVStreamMap [] streamMaps = new AVStreamMap[avcontext.getNumberOfStreams()];
        
        for(int i = 0; i < avcontext.getNumberOfStreams(); i++)
        {
            AVStream avstream = avcontext.getAVStream(i);
            AVCodecParameters avparams = avcontext.getAVCodecParameters(i);
            AVCodec avcodec = AVCodec.getAVCodecDecoder(avparams);
            
            /*
            I wonder if we need to do this?  I would need to add this code
            if (codec_ctx->codec_type == AVMEDIA_TYPE_VIDEO)
            {
                codec_ctx->framerate = av_guess_frame_rate(ifmt_ctx, stream, NULL);
            }
            */
            
            //Copy of parameters happens automatically in my code.
            //Opening of the codec also occurs?  The example code does not open subtitles.  May need to change this as well
            AVCodecContext avcodeccontext = avcodec.allocateContext();
            
            if(avparams.getCodecType() == AVMediaType.AUDIO || avparams.getCodecType() == AVMediaType.VIDEO)
            {
                if(avparams.getCodecType() == AVMediaType.VIDEO)
                {
                    avcodeccontext.setFramerate(avstream.guessFramerate());
                    System.out.println(avcodeccontext.getFramerate().getValue());
                }
            }
            
            //stream_ctx[i].dec_ctx = codec_ctx;
            streamMaps[i] = new AVStreamMap();
            streamMaps[i].setDecoderContext(avcodeccontext);
        }
        
        //av_dump_format(ifmt_ctx, 0, filename, 0);
        
        
        
        //Temp        
        avcontext.close();
    }
    
    @Test
    public void test2()
    {
        AVFormatContext avformat = AVFormatContext.buildAVFormatInputContext("c:/users/jvl711.core/documents/testdatamusic/02 Awolnation - Some Sort of Creature2.flac");
        //AVFormatContext avformat = AVFormatContext.buildAVFormatInputContext("src/examples/Sample_BeeMoved_96kHz24bit.flac");
        
        System.out.println(avformat.getNumberOfStreams());
        
        
    }
            
    
    @Test
    public void testFormatParsing()
    {
        //ContainerFormat format = new ContainerFormat();
        AVFormatContext avformat = null;
        //ArrayList<BitstreamFormat> streams = new ArrayList<BitstreamFormat>();
        
        try
        {
            //"C:\\Users\\jvl711.CORE\\Documents\\TestData\\june.ts"
            System.out.println("MediaFormatParserPlugin processing: " + "src/examples/SampleVideo_1280x720_1mb.mkv");
            
            avformat = AVFormatContext.buildAVFormatInputContext("src/examples/SampleVideo_1280x720_1mb.mkv");
            
            //avformat.openInput("src/examples/SampleVideo_1280x720_1mb.mkv");
        
            //format.setFormatName(FormatParser.substituteName(avformat.getFormatName()));
            //format.setDuration(avformat.getDuration() / 1000);
            //format.setBitrate((int)avformat.getBitrate());
            
            for(int i = 0; i < avformat.getNumberOfStreams(); i++)
            {

                 AVCodecParameters avparm = avformat.getAVCodecParameters(i);
                 AVCodec avcodec = AVCodec.getAVCodecDecoder(avparm);
                 AVStream avstream = avformat.getAVStream(i);

                 if(avparm.getCodecType() == AVMediaType.VIDEO)
                 {
                     System.out.println("(" + i + ") - Processing MediaType: VIDEO" );
                     int arDen = 0;
                     int arNum = 0;

                    //VideoFormat video = new VideoFormat();
                    //video.setFormatName(FormatParser.substituteName(avcodec.getName()));
                    System.out.println("\tFormat Name: " + avcodec.getName());
                    assertTrue(avcodec.getName().length() > 0);
                    
                    if(avparm.getAspectRatioString().length() > 0)
                    {
                        try
                        {
                            arNum = Integer.parseInt(avparm.getAspectRatioString().split(":")[0]);
                            arDen = Integer.parseInt(avparm.getAspectRatioString().split(":")[1]);
                        }
                        catch(Exception ex){}
                    }

                    //video.setArNum(arNum);
                    System.out.println("\tAspect Ratio Num: " + arNum);
                    assertTrue(arNum > 0);
                    //video.setArDen(arDen);
                    System.out.println("\tAspect Ratio Den: " + arDen);
                    assertTrue(arDen > 0);
                    //video.setAspectRatio((float)avparm.getAspectRatio());
                    System.out.println("\tAspect Ratio: " + (float)avparm.getAspectRatio());
                    assertTrue(avparm.getAspectRatio() > 0);
                    //video.setFps((float)avstream.getFramerate());
                    System.out.println("\tFramerate: " + avstream.getFramerate().getValue());
                    assertTrue(avstream.getFramerate().getDenominator() > 0);
                    //video.setWidth(avparm.getWidth());
                    System.out.println("\tWdith: " + avparm.getWidth());
                    assertTrue(avparm.getWidth() > 0);
                    //video.setHeight(avparm.getHeight());
                    System.out.println("\tHeight: " + avparm.getHeight());
                    assertTrue(avparm.getHeight() > 0);                    
                    //video.setInterlaced(avparm.getFieldOrder().isInterlaced());
                    System.out.println("\tInteflaced: " + avparm.getFieldOrder().isInterlaced());
                    //TODO: Add colorspace
                }
                else if(avparm.getCodecType() == AVMediaType.AUDIO)
                {
                     
                    /*
                     * If the first stream is audio lets not process for now.
                     * Need to properly handle metadata and audio files that
                     * include video tracks.
                     */
                    if(i == 0)
                    {
                        return;
                    }
                     
                    //audio.setFormatName(FormatParser.substituteName(avcodec.getName()));
                    //audio.setAudioTransport(); TODO: See if I can find this 
                    //audio.setChannels(avparm.getChannels());
                    //audio.setSamplingRate(avparm.getSampleRate());
                    //audio.setBitrate((int)avparm.getBitrate());
                    //audio.setLanguage(avstream.getLanguage());
                    //audio.setOrderIndex(i);
                }
                else if(avparm.getCodecType() == AVMediaType.SUBTITLE)
                {
                    //subpicture.setFormatName(FormatParser.substituteName(avcodec.getName()));
                    //subpicture.setLanguage(avstream.getLanguage());

                    //subpicture.setOrderIndex(i);
                    //subpicture.setForced(avstream.isForced());
                    //streams.add(subpicture);
                }
                else if(avparm.getCodecType() == AVMediaType.DATA) { }
                else if(avparm.getCodecType() == AVMediaType.ATTACHMENT) { }
                else if(avparm.getCodecType() == AVMediaType.NB) { }
                else { /* Unknown */ }
            }
        }
        finally
        {
            if(avformat != null)
            {
                avformat.close();
            }
        }
    }
    
    @Test
    public void test()
    {
        boolean debug = true;
        if(debug) System.out.println(Boolean.parseBoolean("rgln"));
    }
}
