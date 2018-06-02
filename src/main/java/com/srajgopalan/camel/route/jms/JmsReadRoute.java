package com.srajgopalan.camel.route.jms;

import org.apache.camel.builder.RouteBuilder;

public class JmsReadRoute extends RouteBuilder {
    public void configure() throws Exception {

        from("activemq:queue:srQueue")
                .log("Received message from ActiveMQ with body: ${body} and headers: ${headers}")
                .to("direct:activeMQOutput");
    }
}
