package com.application.vocealuga.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

@Controller
public class ImageController {
    private final ServletContext servletContext;

    public ImageController(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @GetMapping(
            value = "/getNota",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public void getTransaction(HttpServletResponse response) throws IOException {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/static/nota.jpeg");
            OutputStream outputStream = response.getOutputStream();
            if (outputStream != null && inputStream != null) {
                response.setContentType(MediaType.IMAGE_JPEG_VALUE);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.flush();
            } else {
                response.setStatus(404);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.setStatus(500);
        }
    }
}
