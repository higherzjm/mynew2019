package org.crazyit.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer //启动配置服务
public class ConfigApp {
	public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigApp.class).run(args);
	}
}
