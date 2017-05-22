/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.concurrent.Callable;

public class GenerationLoggerCallableFactory {

    private final PrintWriter writer;
    private final Grid grid;

    public GenerationLoggerCallableFactory(Grid grid, Writer writer) {
        this.writer = new PrintWriter(writer);
        this.grid = grid;
    }
    
    public void create(List<Callable<Void>> callables, Generation generation) {
        callables.add(this.create(generation));
    }
    
    public GenerationLoggerCallable create(Generation generation) {
        return new GenerationLoggerCallable(grid, generation, writer);
    }
    
    public void shutdown() {
        LogHelper.logInfo("Closing generation log writer");
        writer.close();
    }
}

