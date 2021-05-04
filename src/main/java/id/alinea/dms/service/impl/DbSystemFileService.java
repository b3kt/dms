package id.alinea.dms.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import id.alinea.dms.entity.SystemFile;
import id.alinea.dms.service.IRepositoryLocator;
import id.alinea.dms.service.ISystemFileService;
import id.alinea.dms.utils.MimeTypes;

@Service
public class DbSystemFileService extends BaseService implements ISystemFileService{

    @Override
    public SystemFile findSystemFileByUuid(String uuid) {
        return repositoryLocator.getSystemFileRepository().findByFileUuid(uuid);
    }

    @Override
    public IRepositoryLocator getRepositoryLocator() {
        return repositoryLocator;
    }

    @Override
    public void storeFile(MultipartFile file, Long entityId, String entityName, String entityField) {
        if(file != null){
            final String uuid = UUID. randomUUID().toString();

            SystemFile sysFile = new SystemFile();
            sysFile.setFileName(file.getOriginalFilename());
            sysFile.setEntityId(entityId);
            sysFile.setEntityType(entityName);
            sysFile.setEntityField(entityField);
            sysFile.setCreatedBy("System");
            sysFile.setCreatedDate(new Date());
            sysFile.setFileType(file.getContentType());
            sysFile.setFileUuid(uuid);
            sysFile = repositoryLocator.getSystemFileRepository().save(sysFile);
            logger.info("File meta {} stored to DB with id {}", file.getOriginalFilename(), sysFile.getId());

            serviceLocator.getStorageService().store(file, uuid, MimeTypes.getDefaultExt(file.getContentType()) );
            logger.info("File {} stored to disk with path ", file.getOriginalFilename());


        }
    }

    @Override
    public void deleteFileByUuid(String uuid) {
        if(StringUtils.isNotBlank(uuid)){
            SystemFile sysFile = repositoryLocator.getSystemFileRepository().findByFileUuid(uuid);
            if(sysFile != null){
                final String filename = sysFile.getFileUuid().concat(".").concat(MimeTypes.getDefaultExt(sysFile.getFileType()));
                serviceLocator.getStorageService().delete(filename);
                repositoryLocator.getSystemFileRepository().delete(sysFile);
            }
        }
        
    }
    
}
