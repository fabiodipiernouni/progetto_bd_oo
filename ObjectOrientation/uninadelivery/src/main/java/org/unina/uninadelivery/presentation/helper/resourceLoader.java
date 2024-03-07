package org.unina.uninadelivery.presentation.helper;

import org.unina.uninadelivery.presentation.app.UninaApplication;

import java.io.InputStream;
import java.net.URL;

public class resourceLoader {

    public resourceLoader() {
    }

    public static URL loadURL(String path) {
        return UninaApplication.class.getResource(path);
    }

    public static String load(String path) {
        return loadURL(path).toString();
    }

    public static InputStream loadStream(String name) {
        return UninaApplication.class.getResourceAsStream(name);
    }

}
