package com.srajgopalan.camel.test;

import com.srajgopalan.camel.route.jdbc.JDBCInsertRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JDBCInsertRouteTest extends CamelTestSupport {

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new JDBCInsertRoute();
    }

    @Override
    public CamelContext createCamelContext() throws Exception {
        //this is where we will create the datasource
        String url = "jdbc:postgresql://localhost:5432/localdb";
        DataSource dataSource = setupDataSource(url);

        SimpleRegistry registry = new SimpleRegistry();
        registry.put("myDataSource",dataSource);

        CamelContext camelContext = new DefaultCamelContext(registry);

        return camelContext;
    }



    private static DataSource setupDataSource(String url){
        BasicDataSource ds = new BasicDataSource();
        ds.setUsername("srajgopalan");
        ds.setPassword("srajgopalan");
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl(url);

        System.out.println( "returning datasource");

        return ds;
    }

    @Test
    public void checkJDBCInsert() {
        String input = "Hello World!";

        List response = template.requestBody("direct:jdbcInput",input,ArrayList.class);

        for(int i=0;i<response.size();i++){
            System.out.println(response.get(i));
        }

        assertNotEquals(0,response.size());

//        assertTrue(true);

    }
}
