package id.alinea.dms;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

import id.alinea.dms.repository.SystemParameterRepository;

@ConfigurationProperties("storage")
public class StorageProperties {

	@Autowired
	private SystemParameterRepository systemParameterRepository;

	/**
	 * Folder location for storing files
	 */
	private String location = "upload-dir";

	public String getLocation() {
		final String uploadDir = systemParameterRepository.getValueByCode("DMS_UPLOAD_DIR");
		if(StringUtils.isNotBlank(uploadDir)){
			this.location = uploadDir;
		}
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}