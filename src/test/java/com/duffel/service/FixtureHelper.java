package com.duffel.service;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

public class FixtureHelper {

    public static String readFixture(Class<?> clazz, String fixture) {
        String text;
        try (InputStream stream = clazz.getResourceAsStream(fixture)) {
            try (Reader reader = new InputStreamReader(Objects.requireNonNull(stream))) {
                text = IOUtils.toString(reader);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return text;
    }

}
