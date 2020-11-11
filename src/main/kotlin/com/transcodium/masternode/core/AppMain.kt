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

import com.transcodium.masternode.core.tns_master_node.MasterNodeCore

class AppMain {

    companion object {

        //entry
        @JvmStatic
        fun main(args: Array<String>) {

            val baseDir = basePath().absolutePath

            //add path to class paths
            System.setProperty("bootclasspath/a", baseDir)

            System.setProperty("java.net.preferIPv4Stack", "true")

            //load system config
            val mainConfig = Config.loadConfig()!!

            val masterNodeConfig = mainConfig.getConfig("masterNode")

            val isMasterNodeEnabled = masterNodeConfig.getBoolean("enabled")

            if(isMasterNodeEnabled){
                MasterNodeCore.startMasterNode(mainConfig)
            }

        } //end main

    } //end companion obj

} //end class