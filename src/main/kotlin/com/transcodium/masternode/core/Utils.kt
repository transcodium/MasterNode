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

import kotlinx.coroutines.*
import java.io.File
import kotlin.reflect.KClass
import com.typesafe.config.Config as HConfig


val logger by lazy { Logger.getLogger("Utils.kt") }

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
    } //end if

    return File(appDir)
}//end


/**
 * coroutine launch
 */
fun launch(block : suspend CoroutineScope.() -> Unit): Job {

    val scope = CoroutineScope(Dispatchers.Default)

    return scope.launch {
        try {
            block.invoke(this)
        } catch (e: Exception){
            logger.fatal(e.message,e)
            throw e
        }
    }
}

/**
 * coroutine async
 */
fun <T>async(block : suspend CoroutineScope.() -> T) : Deferred<T> {

    val scope = CoroutineScope(Dispatchers.IO)

    return  scope.async{
        try {
            block.invoke(this)
        } catch (e: Exception){
            logger.fatal(e.message,e)
            throw e
        }
    }
}

/**
 * HConfig Extension to allow setting of defaults
 **/
fun <T>HConfig.get(key: String, default: T?): T? {

    if(!hasPath(key)){
          return default
    }

    return getAnyRef(key) as T?
}//end fun