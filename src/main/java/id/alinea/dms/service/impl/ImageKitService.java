package id.alinea.dms.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import id.alinea.dms.config.Constant;
import id.alinea.dms.service.IImageKitService;
import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.results.Result;
import io.imagekit.sdk.utils.Utils;

@Service
public class ImageKitService extends BaseService implements IImageKitService {

    @Override
    public Result upload(MultipartFile file, String filename, String mimeType, List<String> tags) throws IOException {

        if(file != null && StringUtils.isNotBlank(filename) && StringUtils.isNotBlank(mimeType)){

            final String uploadDir = repositoryLocator.getSystemParameterRepository().getValueByCode(Constant.IMAGEKIT_UPLOAD_DIR);
            String base64 = Utils.bytesToBase64(file.getBytes()); 
            FileCreateRequest fileCreateRequest = new FileCreateRequest(base64, filename);
            fileCreateRequest.setTags(tags); // optional
            fileCreateRequest.setFolder(StringUtils.isNotBlank(uploadDir) ? uploadDir : "alinea/uploads");  // optional
            fileCreateRequest.setPrivateFile(false);  // optional
            fileCreateRequest.setUseUniqueFileName(true);  // optional

            List<String> responseFields=new ArrayList<>();
            responseFields.add("tags");
            fileCreateRequest.setResponseFields(responseFields); // optional
            return ImageKit.getInstance().upload(fileCreateRequest);
        } else {
            return null;
        }
    }
    
}
