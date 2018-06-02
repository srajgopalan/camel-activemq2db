package com.srajgopalan.camel.test;

import com.srajgopalan.camel.route.jms2jdbc.Jms2JdbcRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class Jms2JdbcRouteTest extends CamelTestSupport {


    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new Jms2JdbcRoute();
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {

        //this is where we will create the datasource
        String url = "jdbc:postgresql://localhost:5432/localdb";
        DataSource dataSource = setupDataSource(url);

        SimpleRegistry registry = new SimpleRegistry();
        registry.put("myDataSource",dataSource);

        return new DefaultCamelContext(registry);
    }

    private static DataSource setupDataSource(String url){
        BasicDataSource ds = new BasicDataSource();
        ds.setUsername("srajgopalan");
        ds.setPassword("srajgopalan");
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl(url);

        return ds;
    }

    @Test
    public void checkRoute(){

        List response = consumer.receiveBody("direct:jdbcOutput", ArrayList.class);

        assertNotEquals(response.size(),0);
    }
}
