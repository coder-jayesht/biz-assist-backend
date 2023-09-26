package in.obsoft.bizassist.base.utils;

//import com.google.common.io.Files;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AppUtils {

    public String generateId() {
        return UUID.randomUUID().toString();
    }

//    public File toFile(MultipartFile multipartFile, String fileName) throws IOException {
//        File tempFile = new File(Files.createTempDir(), fileName);
//        multipartFile.transferTo(tempFile);
//        return tempFile;
//    }

}
