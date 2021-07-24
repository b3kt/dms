package id.alinea.dms.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "imagekit")
public class ImageKitConfig {
    
    @Getter @Setter
    private Boolean enabled;
}
