package com.transcodium.masternode.core.tns_master_node

import com.transcodium.masternode.core.Logger
import com.transcodium.masternode.core.async
import com.transcodium.masternode.core.get
import com.typesafe.config.Config
import net.tomp2p.connection.Bindings
import net.tomp2p.connection.DiscoverNetworks
import net.tomp2p.p2p.Peer
import net.tomp2p.p2p.PeerBuilder
import net.tomp2p.peers.Number160
import java.net.InetAddress
import java.util.*


class MasterNodeCore {

    companion object {

        val logger = Logger.Companion.getLogger(this::class.java)

        //master node group
        val TNS_MASTER_NODE_GROUP = Number160.createHash("TNS_MASTER_NODE")

        /**
         * startMasterNode
         */
        fun startMasterNode(mainConfig: Config) {

            val masterNodeConfig = mainConfig.getConfig("masterNode") ?: null

            println("masterNodeConfig - ${masterNodeConfig}")

            if(masterNodeConfig == null){
                logger.fatal("MasterNode configuration block was not detected, execution terminated")
                return
            }

            val nodePort = masterNodeConfig.get("port",12000)!!

            val nodeHost = masterNodeConfig.get("host","")

            println("nodeHost - $nodeHost")

            val ipBinding = if(nodeHost.isNullOrEmpty()){
                Bindings().listenAny()
            } else {
                Bindings().addAddress(InetAddress.getByName(nodeHost))
            }

            val rnd = Random(43L)

            logger.info("MasterNode Starting")

            val nodePeer = PeerBuilder(Number160(rnd))
                                .ports(nodePort)
                                .bindings(ipBinding)
                                .start()

            logger.info("MasterNode Started on: ${DiscoverNetworks.discoverInterfaces(ipBinding)}")
            logger.info("address visible to outside is " + nodePeer.peerAddress())


            //lets get peers

        } //end fun

    } //end companion object
}