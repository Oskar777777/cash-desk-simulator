package com.cds.controller.util;

import lombok.Getter;
import lombok.extern.java.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

@Log
public class SystemProperties {
    private static SystemProperties systemProperties;

    @Getter
    private static Properties config;

    static {
        initializeConfig();
    }

    private SystemProperties() {}

    public static SystemProperties getInstance() {
        if(Objects.isNull(systemProperties))
            systemProperties = new SystemProperties();
        return systemProperties;
    }

    private static void initializeConfig() {
        config = new Properties();
        try (InputStream inputStream
                     = SystemProperties.class.getClassLoader().getResourceAsStream("application.properties")) {
            config.load(inputStream);
        } catch (IOException e) {
            log.severe(e.getMessage());
        }
    }
}
