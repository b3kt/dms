package id.alinea.dms;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.filters.RemoteAddrFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import id.alinea.dms.service.impl.FileSystemStorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@EnableAutoConfiguration
@EnableJpaRepositories("id.alinea.dms.repository")
@EntityScan("id.alinea.dms.entity")
@ComponentScan("id.alinea.dms")
public class DmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmsApplication.class, args);
	}

	@Bean
	CommandLineRunner init(FileSystemStorageService storageService) {
		return (args) -> {
			// storageService.deleteAll();
			storageService.init();
		};
	}

}
