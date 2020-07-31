/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jvl.FFmpeg.jni;

/**
 *
 * @author jvl711
 */
public class AVRational
{
    private int den;
    private int num;
    
    /**
    * Rational number (pair of numerator and denominator).  Used for hold aspect ratios
    * @param num Numerator
    * @param den denominator
    */
    public AVRational(int num, int den)
    {
        this.den = den;
        this.num = num;
                
    }
    
    public int getDenominator()
    {
        return this.den;
    }
    
    public int getNumerator()
    {
        return this.num;
    }
    
    public double getValue()
    {
        if(num == 0 || den == 0)
        {
            return 0;
        }
        else
        {
            return (num * 1.0) / (den * 1.0);
        }
    }
    
}
