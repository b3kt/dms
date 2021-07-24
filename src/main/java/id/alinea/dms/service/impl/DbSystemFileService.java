package id.alinea.dms.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import id.alinea.dms.entity.SystemFile;
import id.alinea.dms.service.IRepositoryLocator;
import id.alinea.dms.service.IDbSystemFileService;
import id.alinea.dms.utils.MimeTypes;
import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.models.results.Result;

@Service
public class DbSystemFileService extends BaseService implements IDbSystemFileService{

    @Override
    public SystemFile findSystemFileByUuid(String uuid) {
        return repositoryLocator.getSystemFileRepository().findByFileUuid(uuid);
    }

    @Override
    public IRepositoryLocator getRepositoryLocator() {
        return repositoryLocator;
    }


    @Override
    public void storeFile(MultipartFile file, String entityId, String entityName, String entityField, String createdBy) {
        if(file != null && StringUtils.isNotEmpty(entityId) && StringUtils.isNotEmpty(entityName) && StringUtils.isNotEmpty(entityField)){
            
            final List<String> tags = Arrays.asList(entityId, entityName, entityField, createdBy);
            try{
                final Result result = serviceLocator.getImageKitService().upload(file, file.getOriginalFilename(), file.getContentType(), tags);

                if(result != null){
                    SystemFile sysFile = new SystemFile();
                    sysFile.setFileName(result.getName());
                    sysFile.setEntityId(entityId);
                    sysFile.setEntityType(entityName);
                    sysFile.setEntityField(entityField);
                    sysFile.setCreatedBy(StringUtils.isNotBlank(createdBy) ? createdBy : "System");
                    sysFile.setCreatedDate(new Date());
                    sysFile.setFileType(file.getContentType());
                    sysFile.setFileUuid(result.getFileId());

                    sysFile.setFilePath(result.getFilePath());
                    sysFile.setTags(gson.toJson(result.getTags()));
                    sysFile.setUrl(result.getUrl());

                    final JsonObject jsonObject = gson.fromJson(result.getRaw(), JsonObject.class);
                    if(jsonObject.has("thumbnailUrl")){
                        final String thumbnailUrl = jsonObject.get("thumbnailUrl").getAsString();
                        sysFile.setThumbnailUrl(thumbnailUrl);
                    }
                    sysFile.setImagekitRawResponse(result.getRaw());

                    sysFile = repositoryLocator.getSystemFileRepository().save(sysFile);
                    logger.info("File meta {} stored to DB with id {}", file.getOriginalFilename(), sysFile.getId());
                    logger.info("File {} stored to disk with path ", result.getRaw());
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteFileByUuid(String uuid) {
        if(StringUtils.isNotBlank(uuid)){
            SystemFile sysFile = repositoryLocator.getSystemFileRepository().findByFileUuid(uuid);
            if(sysFile != null){
                final String filename = sysFile.getFileUuid().concat(".").concat(MimeTypes.getDefaultExt(sysFile.getFileType()));
                
                Result result = ImageKit.getInstance().deleteFile(filename);
                logger.info("File {} stored to disk with path ", result.getRaw());
                
                //serviceLocator.getStorageService().delete(filename);
                repositoryLocator.getSystemFileRepository().delete(sysFile);
            }
        }
        
    }
    
}
