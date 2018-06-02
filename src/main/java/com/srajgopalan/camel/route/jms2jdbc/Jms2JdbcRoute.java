package com.srajgopalan.camel.route.jms2jdbc;

import com.srajgopalan.camel.exception.SimpleExceptionhandlerProcessor;
import com.srajgopalan.camel.process.jdbc.SimpleJDBCInsertProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.postgresql.util.PSQLException;

public class Jms2JdbcRoute extends RouteBuilder {
    public void configure() throws Exception {

        onException(PSQLException.class).handled(true).redeliveryDelay(2000).maximumRedeliveries(3)
                .log("Exception Encountered while inserting messages to DB")
                .process(new SimpleExceptionhandlerProcessor() );

        from("activemq:queue:srQueue")
                .log("Received message from ActiveMQ with body: ${body} and headers: ${headers}")
                .process(new SimpleJDBCInsertProcessor())
                .to("jdbc:myDataSource")
                .to("sql:select * from messages?dataSource=myDataSource")
                .to("direct:jdbcOutput");
    }
}
