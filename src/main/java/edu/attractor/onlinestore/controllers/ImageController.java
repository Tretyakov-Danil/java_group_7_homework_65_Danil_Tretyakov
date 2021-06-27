package edu.attractor.onlinestore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping(name = "/{image}", produces = MediaType.IMAGE_JPEG_VALUE)
    public String getImage(@PathVariable String image) throws IOException {
        //Write this the absolute root to the images
        String value = new String(Base64.encodeBase64(Files.readAllBytes(Paths.get("/Users/danil/Desktop/Программирование/classworks/java_group_7_homework_65_Danil_Tretyakov/src/main/resources/static/images/" + image))));
        Map<String, String> json = new HashMap<>();
        json.put("key", value);

        return objectMapper.writeValueAsString(json);
    }
}
