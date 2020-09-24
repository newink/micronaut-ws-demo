package com.example

import io.micronaut.http.annotation.Body
import io.micronaut.websocket.WebSocketSession
import io.micronaut.websocket.annotation.OnClose
import io.micronaut.websocket.annotation.OnMessage
import io.micronaut.websocket.annotation.OnOpen
import io.micronaut.websocket.annotation.ServerWebSocket
import org.reactivestreams.Publisher
import org.slf4j.LoggerFactory

@ServerWebSocket("/ws")
class WebsocketEndpoint {

    private val log = LoggerFactory.getLogger(WebsocketEndpoint::class.java)

    @OnOpen
    fun onOpen(session: WebSocketSession) {
        log.info("WS onOpen: $session")
    }

    @OnClose
    fun onClose(session: WebSocketSession) {
        log.info("WS onClose: $session")
    }

    @OnMessage
    fun onMessage(session: WebSocketSession, @Body message: EchoMessage): Publisher<EchoMessage> {
        log.info("WS onMessage $message")
        return session.send(message)
    }
}

data class EchoMessage(val message: String)
