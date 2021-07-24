package id.alinea.dms;

import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.results.Result;
import io.imagekit.sdk.utils.Utils;
import java.io.File;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.util.Assert;

@TestComponent
public class ImageKitTests {
    
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
	public void checkImageKitInstance() throws Exception {
		
        ImageKit ik = ImageKit.getInstance();
        logger.info("instance --> {} ", ik);

        Assert.isTrue((ik instanceof ImageKit), "check instance");

	}

    @Test
	public void testUpload() throws Exception {

        try{
			ImageKit imageKit=ImageKit.getInstance();
			Configuration config=Utils.getSystemConfig(DmsApplication.class);
			imageKit.setConfig(config);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
        String filePath = "/tmp/sample.png";
        String base64 = Utils.fileToBase64(new File(filePath));
        FileCreateRequest fileCreateRequest = new FileCreateRequest(base64, "file_name.png");
        String customCoordinates = "10,10,20,20";
        fileCreateRequest.setCustomCoordinates(customCoordinates);  // optional
        List<String> tags = new ArrayList();
        tags.add("Sample-tag");
        tags.add("T-shirt");
        fileCreateRequest.setTags(tags); // optional
        fileCreateRequest.setFileName("override_file_name.jpg");  // optional
        fileCreateRequest.setFolder("alinea/uploads");  // optional
        fileCreateRequest.setPrivateFile(false);  // optional
        fileCreateRequest.setUseUniqueFileName(true);  // optional
        List<String> responseFields=new ArrayList<>();
        responseFields.add("tags");
        responseFields.add("customCoordinates");
        fileCreateRequest.setResponseFields(responseFields); // optional
        Result result = ImageKit.getInstance().upload(fileCreateRequest);
        System.out.println("======FINAL RESULT=======");
        System.out.println(result);
        System.out.println("Raw Response:");
        System.out.println(result.getRaw());
        System.out.println("Map Response:");
        System.out.println(result.getMap());        

        logger.info("raw: {}", result.getRaw());

        Assert.isTrue((result != null), "check instance");

	}
    
}
