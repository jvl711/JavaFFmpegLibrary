/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jvl.FFmpeg.jni;

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
public class AVStreamTest 
{
    
    AVFormatContext avformat;
    AVCodecParameters avparams;
    AVCodec avcodec;
    AVStream avstream;
    AVStream avstreamSub;
    
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
        avformat = AVFormatContext.buildAVFormatContext();
        //avformat.openInput("src/examples/SampleVideo_1280x720_1mb.mkv");
        //avformat.openInput("src/examples/H265_60_seconds.mkv");
        avformat.openInput("//egypt/tv/The Man in the High Castle/Season 04/The Man in the High Castle - S04E01 - Hexagram 64.mkv");
        //AVCodecParameters avparams = avformat.getAVCodecParameters(0);
        avstream = avformat.getAVStream(0);
        //avstream = avformat.getAVStream(1);
        avparams = avformat.getAVCodecParameters(2);
        avcodec = AVCodec.getAVCodecDecoder(avparams);
        avstreamSub = avformat.getAVStream(3);
        
        
        //AVCodec avcodecVideo = AVCodec.getAVCodec(avparams);
        
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
    public void testFramerate()
    {
        System.out.println("Framerate den: " + avstream.getFramerateDenominator());
        System.out.println("Framerate num: " + avstream.getFramerateNumerator());
        System.out.println("Framerate num: " + avstream.getFramerate());
                
    }

    @Test
    public void testGetLanguage()
    {
        System.out.println("Language: " + avstream.getLanguage());
    }
    
    @Test
    public void testSub()
    {
        System.out.println("Language: " + avstreamSub.debug());
    }
    
    @Test
    public void debug()
    {
        System.out.println("Create Context");
        AVFormatContext avformat = AVFormatContext.buildAVFormatContext();

        System.out.println("Open file");
        avformat.openInput("//egypt/tv/The Man in the High Castle/Season 04/The Man in the High Castle - S04E01 - Hexagram 64.mkv");

        System.out.println("Number of streams: " + avformat.getNumberOfStreams());

        for(int i = 0; i < avformat.getNumberOfStreams(); i++)
        {
            //System.out.println("Checking stream: " + i);

            AVCodecParameters avparm = avformat.getAVCodecParameters(i);
            //AVCodec avcodec = AVCodec.getAVCodec(avparm);
            AVStream avstream = avformat.getAVStream(i);
            //AVCodecContext avcodecContext = avcodec.allocateContext();

            System.out.println(i + " - " + avparm.getCodecType());

            if(avparm.getCodecType() == AVMediaType.VIDEO)
            {
                System.out.println("\t\tFFMPEG Default: " + avstream.isDefault());
                //System.out.println("\tSage Duration: " + this.GetAiring().GetDuration());
                System.out.println("\t\tFFMPEG Duration: " + avformat.getDuration());
                System.out.println("\t\tFFMPEG Lang: " + avstream.getLanguage());

                
                //System.out.println("\tSage Resolution: " + this.GetVideoResolution());
                //System.out.println("\tSage Height: " + this.GetVideoHeight());
                //System.out.println("\tSage Width: " + this.GetVideoWidth());
                //System.out.println("\tSage Progressive:" + this.GetVideoProgressive());
                System.out.println("\t\tFFMPEG Height: " + avparm.getHeight());
                System.out.println("\t\tFFMPEG Width: " + avparm.getWidth());
                System.out.println("\t\tFFMPEG Field Order: " + avparm.getFieldOrder());

                //System.out.println("\tSage FPS: " + this.GetVideoFPS());
                System.out.println("\t\tFFMPEG FPS: " + avstream.getFramerate());
                System.out.println("\t\tFFMPEG FPS Num: " + avstream.getFramerateNumerator());
                System.out.println("\t\tFFMPEG FPS Den: " + avstream.getFramerateDenominator());

                //System.out.println("\tSage Codec: " + this.GetVideoCodec());
                //System.out.println("\tSage Codec.ID: " + this.GetMetadata("Format.Video.ID"));
                System.out.println("\t\tFFMPEG Codec: " + avcodec.getName());
                System.out.println("\t\tFFMPEG Codec Long: " + avcodec.getLongName());
                System.out.println("\t\tFFMPEG Codec ID: " + avparm.getCodecID());


                //System.out.println("\tSage Bitrate: " + this.GetMetadata("Format.Video.Bitrate"));
                System.out.println("\t\tFFMPEG Video Params Bitrate: " + avparm.getBitrate());
                System.out.println("\t\tFFMPEG AVFormat Bitrate: " + avformat.getBitrate());


                //System.out.println("\tSage Aspect Ratio: " + this.GetVideoAspect());
                System.out.println("\t\tFFMPEG Aspect Num: " + avparm.getSampleAspectRatioNumerator());
                System.out.println("\t\tFFMPEG Aspect Den: " + avparm.getSampleAspectRatioDenominator());
                System.out.println("\t\tFFMPEG Aspect: " + avparm.getAspectRatio());
                System.out.println("\t\tFFMPEG Aspect: " + avparm.getAspectRatioString());
                
            }
            else if(avparm.getCodecType() == AVMediaType.AUDIO)
            {
                System.out.println("\t\tFFMPEG Default: " + avstream.isDefault());
                System.out.println("\t\tFFMPEG Lang: " + avstream.debug());
                System.out.println("\t\tFFMPEG Channels: " + avparm.getChannels());
                System.out.println("\t\tFFMPEG Bitrate: " + avparm.getBitrate());
                System.out.println("\t\tFFMPEG Codec: " + avcodec.getName());
                System.out.println("\t\tFFMPEG Codec Long: " + avcodec.getLongName());
                System.out.println("\t\tFFMPEG Codec ID: " + avparm.getCodecID());
            }
            else if(avparm.getCodecType() == AVMediaType.SUBTITLE)
            {
                System.out.println("\t\tFFMPEG Default: " + avstream.isDefault());
                System.out.println("\t\tFFMPEG Languag: " + avstream.getLanguage());
                System.out.println("\t\tFFMPEG Codec: " + avcodec.getName());
                System.out.println("\t\tFFMPEG Codec Long: " + avcodec.getLongName());
                System.out.println("\t\tFFMPEG Codec ID: " + avparm.getCodecID());
                System.out.println("\t\tFFMPEG Forced: " + avstream.isForced());
            }
            else if(avparm.getCodecType() == AVMediaType.DATA)
            {

            }
            else if(avparm.getCodecType() == AVMediaType.ATTACHMENT)
            {

            }
            else if(avparm.getCodecType() == AVMediaType.NB)
            {

            }
            else //Unknown
            {

            }
        }
        
        
        System.out.println("Close AVFormat");    
        avformat.closeInput();
        
        //System.out.println("Free Context");    
        //avformat.freeContext();
    }
}
