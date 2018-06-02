package com.srajgopalan.camel.launch;

import com.srajgopalan.camel.route.jms2jdbc.Jms2JdbcRoute;
import org.apache.camel.main.Main;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class AppLauncher {

    public static void main(String[] args) throws Exception {

        Main main = new Main();

        //todo - externalize this
        String url = "jdbc:postgresql://localhost:5432/localdb";

        main.bind("myDataSource",setupDataSource(url));  //map based registry

        main.addRouteBuilder(new Jms2JdbcRoute());

        System.out.println("Starting Camel JMS to DB Route.");

        main.run();
    }

    private static DataSource setupDataSource(String connectURI) {
        BasicDataSource ds = new BasicDataSource();
        ds.setUsername("srajgopalan");
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setPassword("srajgopalan");
        ds.setUrl(connectURI);
        return ds;
    }

}
