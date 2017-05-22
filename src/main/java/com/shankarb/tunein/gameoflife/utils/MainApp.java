/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.utils;

import com.shankarb.tunein.gameoflife.core.Config;
import com.shankarb.tunein.gameoflife.core.ConfigLoader;
import com.shankarb.tunein.gameoflife.core.GameOfLifeApp;
import com.shankarb.tunein.gameoflife.core.GameOfLifeAppFactory;

public class MainApp {

    public static void main(String[] args) throws Exception {
        Config config;
        if(args.length > 0) {
            config = ConfigLoader.load(args[0]);
        } else {
            config = ConfigLoader.load(); 
        }
        GameOfLifeApp golApp = GameOfLifeAppFactory.create(config);
        try {
            golApp.start();
        } finally {
            golApp.shutdown();
        }
    }
}
