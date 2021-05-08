package id.alinea.dms.service;

import org.springframework.web.multipart.MultipartFile;
import id.alinea.dms.entity.SystemFile;

public interface ISystemFileService {
    
    IRepositoryLocator getRepositoryLocator();

    void storeFile(MultipartFile file, String entityId, String entityName, String entityField); 

    void deleteFileByUuid(String uuid);

    SystemFile findSystemFileByUuid(String uuid);
}
