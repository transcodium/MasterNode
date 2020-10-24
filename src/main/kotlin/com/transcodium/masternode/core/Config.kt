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

class Config {
    companion object {

        /**
         * loadConfig
         * This loads the config file in the config directory
         */
        suspend fun loadConfig(){

            val configFile = basePath(this::class)
                                .resolve("config/main.conf")
                                .absolutePath
            
            val cf = ConfigFactory.load(configFile)
        } //end fun

    }
}