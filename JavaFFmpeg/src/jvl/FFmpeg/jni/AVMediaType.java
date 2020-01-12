
package jvl.FFmpeg.jni;

public enum AVMediaType
{
    UNKNOWN(-1),
    VIDEO(0),
    AUDIO(1),
    DATA(2),
    SUBTITLE(3),
    ATTACHMENT(4),
    NB(5);
    
    private final int value;
   
    private AVMediaType(int value)
    {
        this.value = value;
    }
    
    public static AVMediaType parse(int value)
    {
        if(AVMediaType.UNKNOWN.value == value)
        {
            return AVMediaType.UNKNOWN;
        }
        else if(AVMediaType.VIDEO.value == value)
        {
            return AVMediaType.VIDEO;
        }
        else if(AVMediaType.AUDIO.value == value)
        {
            return AVMediaType.AUDIO;
        }
        else if(AVMediaType.DATA.value == value)
        {
            return AVMediaType.DATA;
        }
        else if(AVMediaType.SUBTITLE.value == value)
        {
            return AVMediaType.SUBTITLE;
        }
        else if(AVMediaType.ATTACHMENT.value == value)
        {
            return AVMediaType.ATTACHMENT;
        }
        else if(AVMediaType.NB.value == value)
        {
            return AVMediaType.NB;
        }
        else
        {
            return AVMediaType.UNKNOWN;
        }
        
    }
}
