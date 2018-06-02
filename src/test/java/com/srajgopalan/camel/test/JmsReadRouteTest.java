package com.srajgopalan.camel.test;

import com.srajgopalan.camel.route.jms.JmsReadRoute;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class JmsReadRouteTest extends CamelTestSupport {

    @Override
    protected RouteBuilder createRouteBuilder(){
        return new JmsReadRoute();
    }

    @Test
    public void checkJMSRead() throws InterruptedException {

        String expectedPayload = "123";

        //use direct consumer template to get msg from queue
        Exchange exchange = consumer.receive("direct:activeMQOutput");
        String payload = (String) exchange.getIn().getBody();

        assertEquals(expectedPayload,payload);

    }
}
