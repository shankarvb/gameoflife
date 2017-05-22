/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import java.util.List;
import java.util.concurrent.Callable;

public class GenerationBuilderCallableFactory {
    
    private final Grid grid;
    private final int buildConcurrency;
    private final TransitionRuleProcessor ruleProcessor;
    
    public GenerationBuilderCallableFactory(Grid grid, int buildConcurrency, TransitionRuleProcessor ruleProcessor) {
        this.grid = grid;
        this.buildConcurrency = buildConcurrency;
        this.ruleProcessor = ruleProcessor;
    }
    
    public void create(List<Callable<Void>> callables, Generation generation) {
        int totalRows = grid.getTotalRows();
        int rowsPerThread = totalRows / buildConcurrency;
        for(int i = 0; i < buildConcurrency - 1; i++) {
            GenerationBuilderCallableRequestContext callableRequest = getCallableRequestContext(i * rowsPerThread, rowsPerThread, generation); 
            callables.add(new GenerationBuilderCallable(callableRequest));
        }
        // last callable may get more rows than the average.
        int startRow = (buildConcurrency - 1) * rowsPerThread;
        GenerationBuilderCallableRequestContext callableRequest = getCallableRequestContext(startRow, totalRows - startRow, generation);
        callables.add(new GenerationBuilderCallable(callableRequest));
    }
    
    private GenerationBuilderCallableRequestContext getCallableRequestContext(int startRow, int rowsPerThread, Generation generation) {
        int columnsPerThread = grid.getTotalColumns();
        GenerationBuilderCallableRequestContext callableRequest = new GenerationBuilderCallableRequestContext(
                grid, 
                ruleProcessor,
                startRow,
                0,
                rowsPerThread,
                columnsPerThread,
                generation
                );
        return callableRequest;
    }
}
