/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import lombok.Getter;

public class GenerationBuilderCallableRequestContext {
    
    @Getter
    private final int row;
    
    @Getter
    private final int column;
    
    @Getter
    private final int totalRows;
    
    @Getter
    private final int totalColumns;
    
    @Getter
    private final Grid grid;
    
    @Getter
    private final TransitionRuleProcessor processor;
    
    @Getter
    private final Generation generation;
    
    public GenerationBuilderCallableRequestContext(Grid grid, TransitionRuleProcessor processor, int row, int column, int totalRows, int totalColumns, Generation generation) {
        this.row = row;
        this.column = column;
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.grid = grid;
        this.processor = processor;
        this.generation = generation;
    }
}
