/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import java.util.concurrent.Callable;
import lombok.Getter;

public class GenerationBuilderCallable implements Callable<Void> {
    
    @Getter
    private final GenerationBuilderCallableRequestContext request;
    
    public GenerationBuilderCallable(GenerationBuilderCallableRequestContext request) {
        this.request = request;
    }
    
    @Override
    public Void call() throws Exception {
        Grid grid = request.getGrid();
        Generation generation = request.getGeneration();
        int startRow = request.getRow();
        int startColumn = request.getColumn();
        int endRow = startRow + request.getTotalRows() - 1;
        int endColumn =  startColumn + request.getTotalColumns() - 1;
        
        LogHelper.logInfo(String.format("Building generation %d, startRow=%d, startColumn=%d, endRow=%d, endColumn=%d", 
                generation.getGenerationNumber(), 
                startRow,
                startColumn,
                endRow,
                endColumn));
        
        TransitionRuleProcessor processor = request.getProcessor();
        for(int i = startRow; i <= endRow; i++) {
            for(int j = startColumn; j <= endColumn; j++) {
                processor.setNextGenStatus(grid, i, j, generation.isEvenGen());
            }
        }
        return null;
    }
}
