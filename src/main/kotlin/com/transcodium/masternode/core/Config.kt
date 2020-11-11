/**
# Copyright 2020 - Transcodium.
#  All rights reserved. This program and the accompanying materials
#  are made available under the terms of the  Apache License v2.0 which accompanies this distribution.
#
#  The Apache License v2.0 is available at
#  http://www.opensource.org/licenses/apache2.0.php
#
#  You are required to redistribute this code under the same licenses.
#  https://transcodium.com
 **/

package com.transcodium.masternode.core

import com.typesafe.config.ConfigFactory
import com.typesafe.config.Config as HConfig

class Config {
    companion object {

        private val logger = Logger.getLogger(this::class)
        private var config: HConfig? = null;

        /**
         * loadConfig
         * This loads the config file in the config directory
         */
         fun loadConfig(): HConfig? {

            if(config != null){
                return config!!
            }

            try {

                val configFile = basePath(this::class)
                                    .resolve("config/main.conf")

                return ConfigFactory.parseFile(configFile)
                                    .getConfig("tnsNode")

            } catch(e: Exception){
                logger.fatal("failed to load configuration file: ${e.message}",e)
                return null
            }

        } //end fun


    } //end companion object
}