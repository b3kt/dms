package id.alinea.dms;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import id.alinea.dms.vo.ImageKitConfig;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = ImageKitConfig.class)
@TestPropertySource("classpath:application.properties")
public class ConfigurationTests {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ImageKitConfig imageKitConfig;

    @Test
	public void checkImageKitEnabled() {
		
        logger.info("----> {}", imageKitConfig.getEnabled());
        assertNotNull(imageKitConfig.getEnabled(), "not null");
        assertTrue(imageKitConfig.getEnabled(), "enabled");
	}
    
}
