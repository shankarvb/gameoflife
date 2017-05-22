/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GameOfLifeApp {
    
    private final AppRequestContext request;
    
    public GameOfLifeApp(AppRequestContext request) {
        this.request = request;
    }
    
    public void start() throws Exception {
        run(request.getMaxGenerations(), request.getBuildConcurrency());
    }
    
    public void shutdown() {
        request.getGenerationLoggerFactory().shutdown();
        LogHelper.logInfo("Shutting down executors");
        request.getExecutors().shutdown();
    }
    
    private void run(int maxGenerations, int buildConcurrency) throws Exception {
        LogHelper.logInfo(String.format("Building %d generations with concurrency level %d", maxGenerations, buildConcurrency));
        for(int i = 0; i < maxGenerations; i++) {
            computeNextGeneration(new Generation(i), buildConcurrency);
        }
        // print final generation
        GenerationLoggerCallable loggerCallable = request.getGenerationLoggerFactory().create(new Generation(maxGenerations));
        loggerCallable.call();
    }
    
    private void computeNextGeneration(Generation currentGeneration, int buildConcurrency) throws InterruptedException, GolException {
        List<Callable<Void>> callables = new ArrayList<>(buildConcurrency + 1);
        request.getGenerationBuilderFactory().create(callables, currentGeneration);
        // add the logger callable. this prints the current generation as we build the next generation.
        request.getGenerationLoggerFactory().create(callables, currentGeneration);
        List<Future<Void>> futures = request.getExecutors().invokeAll(callables);
        validate(futures);
    } 
    
    private void validate(List<Future<Void>> futures) throws GolException {
        for(Future<Void> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new GolException("Callable Failed", e); 
            }
        }
    }
}
