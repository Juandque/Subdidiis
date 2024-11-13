package co.edu.uniquindio.Subdidiis.Servicios.Implementaciones;

import co.edu.uniquindio.Subdidiis.Servicios.Interfaces.ImagenesServicio;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class ImagenesServicioImpl implements ImagenesServicio {
    private final Cloudinary cloudinary;
    public ImagenesServicioImpl(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dwrm9lws4");
        config.put("api_key", "961184435155958");
        config.put("api_secret", "EYXQEHag7myEui4BWIn6o9rzhzE");
        cloudinary = new Cloudinary(config);
    }
    @Override
    public Map subirImagen(MultipartFile imagen) throws Exception {
        File file = convertir(imagen);
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", "Unilocal"));    }

    @Override
    public Map eliminarImagen(String urlImagen) throws Exception {
        String publicId = extraerPublicIdDeUrl(urlImagen);
        return cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
    private File convertir(MultipartFile imagen) throws IOException, IOException {
        File file = File.createTempFile(imagen.getOriginalFilename(), null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());
        fos.close();
        return file;
    }

    private String extraerPublicIdDeUrl(String urlImagen) {
        int indexUpload = urlImagen.indexOf("/upload/") + "/upload/".length();
        return urlImagen.substring(indexUpload);
    }
}
