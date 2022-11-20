package com.claims.config;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;


public class ServerConfig {
	
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2Server1() throws SQLException {
	    return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "8080");
	}

}
