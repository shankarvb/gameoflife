/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameOfLifeAppFactory {
    
    public static GameOfLifeApp create(Config config) throws IOException, GolException {
        Grid grid = createGrid(config);
        TransitionRuleProcessor ruleProcessor = createRuleProcessor();
        GenerationLoggerCallableFactory loggerFactory = createGenerationLoggerFactory(config, grid);
        GenerationBuilderCallableFactory builderFactory = createGenerationBuilderFactory(config, ruleProcessor, grid);
        ExecutorService executors = createExecutorService(config);
        AppRequestContext request = new AppRequestContext(executors, loggerFactory, builderFactory, config.getMaxGenerations(), config.getBuildConcurrency());
        GameOfLifeApp app = new GameOfLifeApp(request);
        return app;
    }
    
    private static Grid createGrid(Config config) throws GolException {
        try {
            Readable reader = new BufferedReader(new FileReader(config.getSeedFile()));
            Grid grid = GridLoader.load(reader);
            return grid;
        } catch (FileNotFoundException e) {
            throw new GolException("Error loading Grid data", e);
        }
    }
    
    private static TransitionRuleProcessor createRuleProcessor() throws GolException {
        try {
            TransitionRule[] rules = TransitionRulesLoader.load();
            TransitionRuleProcessor processor = new TransitionRuleProcessor(rules);
            return processor;
        } catch (IOException e) {
            throw new GolException("Error parsing transition rules file", e);
        }
    }
    
    private static GenerationLoggerCallableFactory createGenerationLoggerFactory(Config config, Grid grid) throws GolException {
        try {
            Writer writer = new BufferedWriter(new FileWriter(config.getOutFile(), false));
            GenerationLoggerCallableFactory loggerFactory = new GenerationLoggerCallableFactory(grid, writer);
            return loggerFactory;
        } catch (IOException e) {
            throw new GolException("Error accessing the out file", e);
        }
    }
    
    private static GenerationBuilderCallableFactory createGenerationBuilderFactory(Config config, TransitionRuleProcessor ruleProcessor, Grid grid) {
        int buildConcurrency = config.getBuildConcurrency();
        GenerationBuilderCallableFactory generationBuilderFactory = new GenerationBuilderCallableFactory(grid, buildConcurrency, ruleProcessor);
        return generationBuilderFactory;
    }
    
    private static ExecutorService createExecutorService(Config config) {
        int buildConcurrency = config.getBuildConcurrency();
        ExecutorService executors = Executors.newFixedThreadPool(buildConcurrency + 1);
        return executors;
    }
}
