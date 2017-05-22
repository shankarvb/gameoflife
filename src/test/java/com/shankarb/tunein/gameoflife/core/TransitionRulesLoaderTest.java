/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class TransitionRulesLoaderTest {
    
    @Test
    public void testBasics() throws IOException, GolException {
        TransitionRule[] rules = TransitionRulesLoader.load();
        Assert.assertTrue(rules.length > 0);
        for(TransitionRule rule : rules) {
            Assert.assertNotNull(rule.getCurrentStatus());
            Assert.assertNotNull(rule.getNewStatus());
            Assert.assertNotNull(rule.getLiveNeighborCounts());
            Assert.assertTrue(rule.getLiveNeighborCounts().length > 0);
        }
    }
}
