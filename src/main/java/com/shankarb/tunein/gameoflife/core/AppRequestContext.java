/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import java.util.concurrent.ExecutorService;

import lombok.Getter;

public class AppRequestContext {
    
    @Getter
    private final ExecutorService executors;

    @Getter
    private final GenerationLoggerCallableFactory generationLoggerFactory;
        
    @Getter
    private final GenerationBuilderCallableFactory generationBuilderFactory;
    
    @Getter 
    private final int maxGenerations;
    
    @Getter
    private final int buildConcurrency;

    public AppRequestContext(ExecutorService executors, GenerationLoggerCallableFactory generationLoggerFactory, GenerationBuilderCallableFactory generationBuilderFactory, int maxGenerations, int buildConcurrency) {
        this.executors = executors;
        this.generationLoggerFactory = generationLoggerFactory;
        this.generationBuilderFactory = generationBuilderFactory;
        this.maxGenerations = maxGenerations;
        this.buildConcurrency = buildConcurrency;
    }
}
