/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Config {
    
    @Getter
    @JsonProperty("maxGenerations")
    private final int maxGenerations;
    
    @Getter
    @JsonProperty("buildConcurrency")
    private final int buildConcurrency;
    
    @Getter
    @JsonProperty("seedFile")
    private final String seedFile;
    
    @Getter
    @JsonProperty("outFile")
    private final String outFile;
    
    @JsonCreator
    public Config(
            @JsonProperty("maxGenerations") int maxGenerations, 
            @JsonProperty("buildConcurrency") int concurrency,
            @JsonProperty("seedFile") String seedFile,
            @JsonProperty("outFile") String outFile) {
        this.maxGenerations = maxGenerations;
        this.buildConcurrency = concurrency;
        this.seedFile = seedFile;
        this.outFile = outFile;
    }
}
