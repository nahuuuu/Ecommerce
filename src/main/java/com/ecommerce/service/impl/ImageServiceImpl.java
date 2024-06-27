package com.ecommerce.service.impl;

import com.ecommerce.entity.ImageEntity;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.ImageRepository;
import org.apache.catalina.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImpl {

    @Autowired
    private ImageRepository imageRepository;


    public ImageEntity imageUpload(MultipartFile image) {
        try {

            if (image.isEmpty()) {
                throw new RuntimeException("The file" + image.getOriginalFilename() + "is empty");
            }


            UUID fileName = UUID.randomUUID();

            byte[] bytes = image.getBytes();
            String fileOriginalName = image.getOriginalFilename();

            long fileSize = image.getSize();
            long maxFileSize = 3 * 1024 * 1024;

            if (fileSize > maxFileSize) {
                throw new IllegalArgumentException("The image exceeds the maximum MB allowed");
            }

            if (!fileOriginalName.endsWith(".jpg") &&
                    !fileOriginalName.endsWith(".jpeg") &&
                    !fileOriginalName.endsWith(".png")
            ) {
                throw new IllegalArgumentException("Only JPG, JPEG, PNG files are allowed!");
            }

            File folder = new File("src/main/resources/images");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String fileExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));

            String newFileName = fileName.toString() + fileExtension;

            Path path = Paths.get("src/main/resources/images/" + newFileName);

            Files.write(path, bytes);

            return ImageEntity.builder()
                    .id(fileName)
                    .name(fileOriginalName)
                    .type(image.getContentType())
                    .url(path.toString())
                    .build();

        } catch (IOException e) {
            throw new RuntimeException("Error when processing the image: " + e.getMessage());
        }
    }

    public List<String> getProductImages(ProductEntity product) {
        List<ImageEntity> images = imageRepository.findByProduct(product).orElseThrow(
                () -> new ResourceNotFoundException("Images not found")
        );

        List<Path> imagePaths = images.stream().map(image -> Paths.get(image.getUrl())).toList();
        List<String> base64Images = new ArrayList<>();
        for (Path imagePath : imagePaths) {
            try {
                BufferedImage bufferedImage = ImageIO.read(imagePath.toFile());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", baos);
                byte[] imageBytes = baos.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                base64Images.add(base64Image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
        return base64Images;
    }
}