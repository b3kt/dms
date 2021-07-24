package id.alinea.dms.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import io.imagekit.sdk.models.results.Result;

public interface IImageKitService {

    Result upload(MultipartFile file, String filename, String mimeType, List<String> tags) throws IOException;
    
}
