/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import java.io.PrintWriter;
import java.util.concurrent.Callable;

import com.shankarb.tunein.gameoflife.core.Grid.CellStatus;

public class GenerationLoggerCallable implements Callable<Void> {

    private final PrintWriter writer;
    private final Grid grid;
    private final Generation generation;
    private static final String FOOTER = "===========================================";
    private static final String HEADER_PREFIX = "PRINTING GENERATION ";
    
    public GenerationLoggerCallable(Grid grid, Generation generation, PrintWriter writer) {
        this.grid = grid;
        this.generation = generation;
        this.writer = writer;
    }
    
    @Override
    public Void call() throws Exception {
        LogHelper.logInfo(String.format("Logging generation %d", generation.getGenerationNumber()));
        writer.println(HEADER_PREFIX + generation.getGenerationNumber());
        for(int i=0; i<grid.getTotalRows(); i++) {
            for(int j=0; j<grid.getTotalColumns(); j++) {
                CellStatus status = grid.getCellStatus(i, j, generation.isEvenGen());
                writer.print(status.getValue());
                writer.print(' ');
            }
            writer.println();
        }
        writer.println(FOOTER);
        writer.flush();
        return null;
    }
}
