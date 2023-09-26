package in.obsoft.bizassist.base.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryUtils {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private AppUtils appUtils;

    @Value("${cloudinary.brand-image-folder}")
    private String brandImageFolder;

    public Map uploadImage(Object image) throws IOException {
        Map params = ObjectUtils.asMap(
                "public_id", brandImageFolder + "/" + appUtils.generateId(),
                "overwrite", true,
                "resource_type", "image"
        );
        return cloudinary.uploader().upload(image, params);
    }

    public Map replaceImage(Object image, String publicId) throws IOException {
        Map params = ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
        return cloudinary.uploader().upload(image, params);
    }

    public Map deleteImage(String publicId) throws IOException {
        Map params = ObjectUtils.asMap();
        return cloudinary.uploader().destroy(publicId, params);
    }
}
