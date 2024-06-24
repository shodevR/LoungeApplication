package com.app.config;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConnectionPoolHealthLogger implements CommandLineRunner {

    @Autowired
    private HikariDataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        HikariPoolMXBean poolMXBean = dataSource.getHikariPoolMXBean();
        System.out.println("Total Connections: " + poolMXBean.getTotalConnections());
        System.out.println("Active Connections: " + poolMXBean.getActiveConnections());
        System.out.println("Idle Connections: " + poolMXBean.getIdleConnections());
        System.out.println("Threads Awaiting Connection: " + poolMXBean.getThreadsAwaitingConnection());
    }
}
