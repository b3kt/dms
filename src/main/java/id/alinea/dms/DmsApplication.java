package id.alinea.dms;

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
import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import io.imagekit.sdk.utils.Utils;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@EnableAutoConfiguration
@EnableJpaRepositories("id.alinea.dms.repository")
@EntityScan("id.alinea.dms.entity")
@ComponentScan("id.alinea.dms")
public class DmsApplication {

	public static void main(String[] args) {
		initImageKit();
		SpringApplication.run(DmsApplication.class, args);
	}

	private static void initImageKit(){
		try{
			ImageKit imageKit=ImageKit.getInstance();
			Configuration config=Utils.getSystemConfig(DmsApplication.class);
			imageKit.setConfig(config);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Bean
	CommandLineRunner init(FileSystemStorageService storageService) {
		return (args) -> {
			// storageService.deleteAll();
			storageService.init();
		};
	}

}
