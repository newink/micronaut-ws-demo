package com.example

import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import org.junit.platform.commons.logging.LoggerFactory
import java.net.URI
import java.nio.ByteBuffer

class WebsocketTestClient(uri: URI, headers: Map<String, String>? = emptyMap()) : WebSocketClient(uri, headers) {

    private val log = LoggerFactory.getLogger(WebsocketTestClient::class.java)

    override fun onOpen(handshakedata: ServerHandshake) {
        log.info { "WS HANDSHAKE $handshakedata" }
    }

    override fun onMessage(message: String) {
        log.info { "WS MESSAGE $message" }

    }

    override fun onClose(code: Int, reason: String, remote: Boolean) {
        log.info { "WS CLOSE code: $code, reason: $reason, remote: $remote" }
    }

    override fun onError(ex: Exception) {
        log.info { "WS MESSAGE $ex" }
    }

    override fun onMessage(bytes: ByteBuffer) {
        log.info { "WS BYTE ARRAY MESSAGE $bytes" }
    }
}
