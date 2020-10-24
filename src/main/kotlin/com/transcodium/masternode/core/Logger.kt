package com.transcodium.masternode.core

import org.apache.logging.log4j.Level
import org.apache.logging.log4j.core.Filter
import org.apache.logging.log4j.core.Logger
import org.apache.logging.log4j.core.LoggerContext
import org.apache.logging.log4j.core.appender.ConsoleAppender
import org.apache.logging.log4j.core.config.Configuration
import org.apache.logging.log4j.core.config.ConfigurationFactory
import org.apache.logging.log4j.core.config.ConfigurationSource
import org.apache.logging.log4j.core.config.Configurator
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration
import org.bouncycastle.asn1.x500.style.RFC4519Style
import java.net.URI
import kotlin.reflect.KClass


class Logger: ConfigurationFactory() {

    companion object {

        private val loggerContext by lazy {
            val builder = newConfigurationBuilder()
            Configurator.initialize(createConfiguration("", builder))
        }

        @JvmStatic
        fun createConfiguration(
            name: String,
            builder: ConfigurationBuilder<BuiltConfiguration>
        ): Configuration {

            builder.setConfigurationName(RFC4519Style.name.toString())

            builder.setStatusLevel(Level.ERROR)

            builder.add(
                builder.newFilter("ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.NEUTRAL)
                    .addAttribute("level", Level.DEBUG)
            )

            val appenderBuilder = builder.newAppender("Stdout", "CONSOLE")
                                        .addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT)

            appenderBuilder.add(
                builder.newLayout("PatternLayout").addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable")
            )

            appenderBuilder.add(
                builder.newFilter("MarkerFilter", Filter.Result.DENY, Filter.Result.NEUTRAL).addAttribute(
                    "marker",
                    "FLOW"
                )
            )

            builder.add(appenderBuilder)

            builder.add(
                builder.newLogger("org.apache.logging.log4j", Level.DEBUG).add(builder.newAppenderRef("Stdout"))
                    .addAttribute("additivity", false)
            )

            builder.add(builder.newRootLogger(Level.ERROR).add(builder.newAppenderRef("Stdout")))

            return builder.build()
        } //end config builder


        /**
         * getLogger
         */
        fun getLogger(name: String?): Logger {
            return  loggerContext.getLogger(name)
        }

        /**
         * getLogger
         */
        fun getLogger(clazz: Class<*>): Logger {
            return  getLogger(clazz::class.java.name)
        } //end fun

        /**
         * getLogger
         */
        fun getLogger(clazz: KClass<*>): Logger {
            return  getLogger(clazz::class.java.name)
        } //end fun

    } //enc companion object

    override fun getConfiguration(loggerContext: LoggerContext?, source: ConfigurationSource): Configuration? {
        return getConfiguration(loggerContext, source.toString(), null)
    }

    override fun getConfiguration(loggerContext: LoggerContext?, name: String?, configLocation: URI?): Configuration? {
        val builder = newConfigurationBuilder()
        return createConfiguration(name!!, builder)
    }

    override fun getSupportedTypes(): Array<String>? {
        return arrayOf("*")
    }

} //end class