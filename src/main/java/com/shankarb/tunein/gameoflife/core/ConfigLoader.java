/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigLoader {
    
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final ClassLoader CLASS_LOADER = ConfigLoader.class.getClassLoader();
    
    public static Config load() throws IOException, GolException {
        return load(CLASS_LOADER.getResourceAsStream("config.json"));
    }
    
    public static Config load(String name) throws IOException, GolException {
        return load(new FileInputStream(name));
    }
    
    private static Config load(InputStream input) throws IOException, GolException {
        LogHelper.logInfo("Loading config");
        Config config = MAPPER.readValue(input, Config.class);
        validate(config);
        return config;
    }
    
    private static void validate(Config config) throws GolException {
        if(config.getBuildConcurrency() <= 0) {
            throw new GolException("Config Error: build concurrency must be a positive int");
        }
        
        if(config.getMaxGenerations() <= 0) {
            throw new GolException("Config Error: max generations must be a positive int");
        }
        
        if(StringUtils.isBlank(config.getOutFile())) {
            throw new GolException("Config Error: out file cannot be null");
        }
        
        if(StringUtils.isBlank(config.getSeedFile())) {
            throw new GolException("Config Error: seed file cannot be null");
        }
    }
}
