package com.example

import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.net.URI
import javax.inject.Inject

@MicronautTest
class DemoTest {

    @Inject
    lateinit var application: EmbeddedApplication<*>

    @Inject
    lateinit var embeddedServer: EmbeddedServer

    @Test
    fun testItWorks() {
        Assertions.assertTrue(application.isRunning)
    }

    @Test
    fun testWebsocket() {
        val testClient = WebsocketTestClient(URI("ws://${embeddedServer.host}:${embeddedServer.port}/ws"))
        testClient.connectBlocking()
        testClient.sendPing()
    }
}
