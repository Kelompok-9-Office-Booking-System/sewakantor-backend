package com.kampusmerdeka.officeorder.util;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class Helpers {
    private Helpers() {
    }

    @SneakyThrows
    public static String resourceToBase64(Resource resource) {
        byte[] fileContent = FileUtils.readFileToByteArray(resource.getFile());
        return Base64.getEncoder().encodeToString(fileContent);
    }
}