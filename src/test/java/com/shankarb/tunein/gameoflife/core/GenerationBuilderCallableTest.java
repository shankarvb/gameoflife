/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import org.junit.Assert;
import org.junit.Test;

public class GenerationBuilderCallableTest {
    
    @Test
    public void testBasics() throws Exception {
        TransitionRuleProcessor processor = new TransitionRuleProcessor(TransitionRulesLoader.load());
        byte[] cellStatus = new byte[]{
                0, 0, 0, 0, 0,
                0, 0, 0, 1, 0,
                0, 1, 1, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 0, 0};
        Grid grid = new Grid(5,5,cellStatus);
        GenerationBuilderCallableRequestContext request = new GenerationBuilderCallableRequestContext(grid, processor, 0, 0, 5, 5, new Generation(0));
        GenerationBuilderCallable callable = new GenerationBuilderCallable(request);
        callable.call();
        byte[] expectedGen1Status = new byte[] {
                0, 0, 0, 0, 0,
                0, 0, 16, 1, 0,
                0, 17, 17, 16, 0,
                0, 16, 17, 0, 0,
                0, 0, 0, 0, 0
        };
        Assert.assertArrayEquals(expectedGen1Status, grid.getCellsStatus());
        
        request = new GenerationBuilderCallableRequestContext(grid, processor, 0, 0, 5, 5, new Generation(1));
        callable = new GenerationBuilderCallable(request);
        callable.call(); 
        byte[] expectedGen2Status = new byte[] {
                0, 0, 0, 0, 0,
                0, 1, 17, 1, 0,
                0, 16, 16, 17, 0,
                0, 17, 16, 1, 0,
                0, 0, 0, 0, 0
        };
        Assert.assertArrayEquals(expectedGen2Status, grid.getCellsStatus()); 
    }
}
