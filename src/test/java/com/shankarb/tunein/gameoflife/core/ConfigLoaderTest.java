/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import java.io.IOException;

import org.junit.Assert;

import org.junit.Test;

public class ConfigLoaderTest {

    @Test
    public void testBasics() throws IOException, GolException {
        Config config = ConfigLoader.load();
        Assert.assertNotNull(config.getOutFile());
        Assert.assertNotNull(config.getSeedFile());
        Assert.assertTrue(config.getBuildConcurrency() > 0);
        Assert.assertTrue(config.getMaxGenerations() > 0);
    }
}