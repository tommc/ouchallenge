package org.lulliloo.ouchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * main app class
 *
 * @author <a href="mailto:tom@lulliloo.org">Tom McCann</a>
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ItemPricesApplication extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {
	public static void main(String[] args) {
		SpringApplication.run(ItemPricesApplication.class, args);
	}
}
