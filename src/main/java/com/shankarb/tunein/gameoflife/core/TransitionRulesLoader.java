/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TransitionRulesLoader {
    
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final ClassLoader CLASS_LOADER = ConfigLoader.class.getClassLoader();
    
    public static TransitionRule[] load() throws IOException, GolException {
        LogHelper.logInfo(String.format("Loading transition rules"));
        TransitionRule[] rules = MAPPER.readValue(CLASS_LOADER.getResourceAsStream("transitionrules.json"), TransitionRule[].class);
        validate(rules);
        return rules;
    }
    
    private static void validate(TransitionRule[] rules) throws GolException {
        for(TransitionRule rule : rules) {
            if(rule.getCurrentStatus() == null) {
                throw new GolException("Transition Rule File Error: currentStatus cannot be null");
            }
            
            if(rule.getNewStatus() == null) {
                throw new GolException("Transition Rule File Error: newStatus cannot be null");
            }
            
            if(rule.getLiveNeighborCounts() == null) {
                throw new GolException("Transition Rule File Error: liveNeighborCounts cannot be null");
            }
           
            for(int i : rule.getLiveNeighborCounts()) {
                if(i > 8 || i < 0) {
                    throw new GolException("Transition Rule File Error: liveNeighborCounts must be between 0 and 8");
                }
            }
        }
    }
}
