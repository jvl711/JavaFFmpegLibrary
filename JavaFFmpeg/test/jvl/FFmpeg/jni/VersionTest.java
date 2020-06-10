package jvl.FFmpeg.jni;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class VersionTest
{
    
    public VersionTest()
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
    public void testVersion()
    {
        System.out.println(Version.getVersion());
    }
    
    @Test
    public void testBuildNumber()
    {
        System.out.println(Version.getBuildNumber());
    }
    
    @Test
    public void testBuildTime()
    {
        System.out.println(Version.getBuildTime());
    }
}
