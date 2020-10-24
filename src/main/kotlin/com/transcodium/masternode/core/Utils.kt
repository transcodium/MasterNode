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

import java.io.File
import kotlin.reflect.KClass

/**
 *basePath - App's Root Directory
 *@param callerClass
 * @return File Object
 **/
fun basePath(callerClass: KClass<*>? = null): File {

    val clazz = callerClass ?: AppMain::class

    var appDir: String = System.getProperty("app.dir","")

    if(appDir.isEmpty()) {

        //get current Jar path
        appDir = clazz.java.protectionDomain.codeSource.location.path

        if(appDir.endsWith(".jar")){
            appDir = File(appDir).parent
        }

        System.setProperty("app.dir",appDir)
    }

    return File(appDir)
}//end