package com.srajgopalan.camel.process.jdbc;

import org.apache.camel.Exchange;

public class SimpleJDBCInsertProcessor implements org.apache.camel.Processor {

    public void process(Exchange exchange) throws Exception {

        String payload  = exchange.getIn().getBody(String.class);

        System.out.println("Payload:"+payload);
        String insertSQL = "INSERT INTO messages values ('1','" + payload + "')";
        System.out.println("Insert statement:"+insertSQL);

        exchange.getIn().setBody(insertSQL);
    }
}
