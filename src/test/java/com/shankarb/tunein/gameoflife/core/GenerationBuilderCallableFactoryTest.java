/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.junit.Assert;

import org.junit.Test;

import com.shankarb.tunein.gameoflife.core.Grid.CellStatus;

public class GenerationBuilderCallableFactoryTest {

    private static final TransitionRule RULE_1 = new TransitionRule(CellStatus.DEAD, CellStatus.LIVE, new int[]{3});
    private static final TransitionRule[] RULES = new TransitionRule[]{RULE_1};
    private static final TransitionRuleProcessor PROCESSOR = new TransitionRuleProcessor(RULES);
    
    @Test
    public void testUnevenDistribution() {
        byte[] cellStatus = new byte[]{
                1, 1, 0, 
                0, 1, 0,
                1, 1, 1,
                0, 0, 1,
                0, 1, 0,
                1, 1, 1,
                0, 0, 1,
                0, 0, 1};
        Grid grid = new Grid(8,3,cellStatus);
        GenerationBuilderCallableFactory factory = new GenerationBuilderCallableFactory(grid, 3, PROCESSOR);
        List<Callable<Void>> callables = new ArrayList<>();
        factory.create(callables, new Generation(0));
        Assert.assertEquals(3, callables.size());
        GenerationBuilderCallable callable1 = (GenerationBuilderCallable) callables.get(0);
        Assert.assertEquals(3, callable1.getRequest().getTotalColumns());
        Assert.assertEquals(2, callable1.getRequest().getTotalRows());
        Assert.assertEquals(0, callable1.getRequest().getRow());
        Assert.assertEquals(0, callable1.getRequest().getColumn());
        
        GenerationBuilderCallable callable2 = (GenerationBuilderCallable) callables.get(1);
        Assert.assertEquals(3, callable2.getRequest().getTotalColumns());
        Assert.assertEquals(2, callable2.getRequest().getTotalRows());
        Assert.assertEquals(2, callable2.getRequest().getRow());
        Assert.assertEquals(0, callable2.getRequest().getColumn());
        
        GenerationBuilderCallable callable3 = (GenerationBuilderCallable) callables.get(2);
        Assert.assertEquals(3, callable3.getRequest().getTotalColumns());
        Assert.assertEquals(4, callable3.getRequest().getTotalRows());
        Assert.assertEquals(4, callable3.getRequest().getRow());
        Assert.assertEquals(0, callable3.getRequest().getColumn());
    }
    
    @Test
    public void testEvenDistribution() {
        byte[] cellStatus = new byte[]{
                1, 1, 0, 
                0, 1, 0,
                1, 1, 1};
        Grid grid = new Grid(3,3,cellStatus);
        GenerationBuilderCallableFactory factory = new GenerationBuilderCallableFactory(grid, 3, PROCESSOR);
        List<Callable<Void>> callables = new ArrayList<>();
        factory.create(callables, new Generation(0));
        
        Assert.assertEquals(3, callables.size());
        GenerationBuilderCallable callable1 = (GenerationBuilderCallable) callables.get(0);
        Assert.assertEquals(3, callable1.getRequest().getTotalColumns());
        Assert.assertEquals(1, callable1.getRequest().getTotalRows());
        Assert.assertEquals(0, callable1.getRequest().getRow());
        Assert.assertEquals(0, callable1.getRequest().getColumn());
        
        GenerationBuilderCallable callable2 = (GenerationBuilderCallable) callables.get(1);
        Assert.assertEquals(3, callable2.getRequest().getTotalColumns());
        Assert.assertEquals(1, callable2.getRequest().getTotalRows());
        Assert.assertEquals(1, callable2.getRequest().getRow());
        Assert.assertEquals(0, callable2.getRequest().getColumn());
        
        GenerationBuilderCallable callable3 = (GenerationBuilderCallable) callables.get(2);
        Assert.assertEquals(3, callable3.getRequest().getTotalColumns());
        Assert.assertEquals(1, callable3.getRequest().getTotalRows());
        Assert.assertEquals(2, callable3.getRequest().getRow());
        Assert.assertEquals(0, callable3.getRequest().getColumn());
    }
}
