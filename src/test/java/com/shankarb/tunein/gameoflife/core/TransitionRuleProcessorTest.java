/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import org.junit.Assert;
import org.junit.Test;

import com.shankarb.tunein.gameoflife.core.Grid.CellStatus;

public class TransitionRuleProcessorTest {
    
    private static final TransitionRule RULE_1 = new TransitionRule(CellStatus.DEAD, CellStatus.LIVE, new int[]{3});
    private static final TransitionRule RULE_2 = new TransitionRule(CellStatus.LIVE, CellStatus.DEAD, new int[]{0, 1, 4, 5, 6, 7, 8});
    private static final TransitionRule[] RULES = new TransitionRule[]{RULE_1, RULE_2};
    
    @Test
    public void testDeadToLiveStateTransitionEvenGen() {
        TransitionRuleProcessor processor = new TransitionRuleProcessor(RULES);
        byte[] cellStatus = new byte[]{0,1,1,1};
        Grid grid = new Grid(2,2,cellStatus);
        processor.setNextGenStatus(grid, 0, 0, true);
        Assert.assertEquals(16, cellStatus[0]);
        Assert.assertEquals(1, cellStatus[1]);
        Assert.assertEquals(1, cellStatus[2]);
        Assert.assertEquals(1, cellStatus[3]);
    }
    
    @Test
    public void testDeadToLiveStateTransitionOddGen() {
        TransitionRuleProcessor processor = new TransitionRuleProcessor(RULES);
        byte[] cellStatus = new byte[]{0,17,17,17};
        Grid grid = new Grid(2,2,cellStatus);
        processor.setNextGenStatus(grid, 0, 0, false);
        Assert.assertEquals(1, cellStatus[0]);
        Assert.assertEquals(17, cellStatus[1]);
        Assert.assertEquals(17, cellStatus[2]);
        Assert.assertEquals(17, cellStatus[3]);
    }
    
    @Test
    public void testDeadToDeadStateTransitionOddGen() {
        TransitionRuleProcessor processor = new TransitionRuleProcessor(RULES);
        byte[] cellStatus = new byte[]{0,0,17,17};
        Grid grid = new Grid(2,2,cellStatus);
        processor.setNextGenStatus(grid, 0, 0, false);
        Assert.assertEquals(0, cellStatus[0]);
        Assert.assertEquals(0, cellStatus[1]);
        Assert.assertEquals(17, cellStatus[2]);
        Assert.assertEquals(17, cellStatus[3]);
    }
    
    @Test
    public void testDeadToDeadStateTransitionEvenGen() {
        TransitionRuleProcessor processor = new TransitionRuleProcessor(RULES);
        byte[] cellStatus = new byte[]{0,0,17,17};
        Grid grid = new Grid(2,2,cellStatus);
        processor.setNextGenStatus(grid, 0, 0, true);
        Assert.assertEquals(0, cellStatus[0]);
        Assert.assertEquals(0, cellStatus[1]);
        Assert.assertEquals(17, cellStatus[2]);
        Assert.assertEquals(17, cellStatus[3]);
    }
    
    @Test
    public void testLiveToDeadStateTransitionEvenGen() {
        TransitionRuleProcessor processor = new TransitionRuleProcessor(RULES);
        byte[] cellStatus = new byte[]{16,16,16,17};
        Grid grid = new Grid(2,2,cellStatus);
        processor.setNextGenStatus(grid, 1, 1, true);
        Assert.assertEquals(16, cellStatus[0]);
        Assert.assertEquals(16, cellStatus[1]);
        Assert.assertEquals(16, cellStatus[2]);
        Assert.assertEquals(1, cellStatus[3]);
    }
    
    @Test
    public void testLiveToDeadStateTransitionOddGen() {
        TransitionRuleProcessor processor = new TransitionRuleProcessor(RULES);
        byte[] cellStatus = new byte[]{1,1,1,17};
        Grid grid = new Grid(2,2,cellStatus);
        processor.setNextGenStatus(grid, 1, 1, false);
        Assert.assertEquals(1, cellStatus[0]);
        Assert.assertEquals(1, cellStatus[1]);
        Assert.assertEquals(1, cellStatus[2]);
        Assert.assertEquals(16, cellStatus[3]);
    }
    
    @Test
    public void testLiveToLiveStateTransitionEvenGen() {
        TransitionRuleProcessor processor = new TransitionRuleProcessor(RULES);
        byte[] cellStatus = new byte[]{16,1,1,17};
        Grid grid = new Grid(2,2,cellStatus);
        processor.setNextGenStatus(grid, 1, 0, true);
        Assert.assertEquals(16, cellStatus[0]);
        Assert.assertEquals(1, cellStatus[1]);
        Assert.assertEquals(17, cellStatus[2]);
        Assert.assertEquals(17, cellStatus[3]);
    }
    
    @Test
    public void testLiveToLiveStateTransitionOddGen() {
        TransitionRuleProcessor processor = new TransitionRuleProcessor(RULES);
        byte[] cellStatus = new byte[]{0,16,16,17};
        Grid grid = new Grid(2,2,cellStatus);
        processor.setNextGenStatus(grid, 1, 0, false);
        Assert.assertEquals(0, cellStatus[0]);
        Assert.assertEquals(16, cellStatus[1]);
        Assert.assertEquals(17, cellStatus[2]);
        Assert.assertEquals(17, cellStatus[3]);
    }
}
