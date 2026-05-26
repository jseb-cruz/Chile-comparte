package com.usta.chilecomparte.services.interfaces;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Map;

@Service
public class ImgBBService {

    @Value("${imgbb.api.key}")
    private String apiKey;

    public String subirImagen(MultipartFile archivo) throws Exception {

        String base64 =
                Base64.getEncoder()
                        .encodeToString(archivo.getBytes());

        String url =
                "https://api.imgbb.com/1/upload?key="
                        + apiKey;

        MultiValueMap<String,String> body =
                new LinkedMultiValueMap<>();

        body.add("image", base64);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<MultiValueMap<String,String>> request =
                new HttpEntity<>(body, headers);

        RestTemplate rest = new RestTemplate();

        ResponseEntity<Map> response =
                rest.postForEntity(
                        url,
                        request,
                        Map.class
                );

        Map data =
                (Map) response.getBody().get("data");

        return data.get("url").toString();
    }
}