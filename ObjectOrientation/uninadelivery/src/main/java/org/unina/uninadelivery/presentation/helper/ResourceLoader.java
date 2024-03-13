package org.unina.uninadelivery.presentation.helper;

import org.unina.uninadelivery.presentation.app.UninaApplication;

import java.io.InputStream;
import java.net.URL;

public class ResourceLoader {

    public ResourceLoader() {
    }

    public static URL loadURL(String path) {
        URL url = ResourceLoader.class.getResource(path);
        if (url == null) {
            url = ResourceLoader.class.getResource("/org/unina/uninadelivery/presentation" + (path.startsWith("/") ? "" : "/") + path);
        }

        return url;
    }

    public static String load(String path) {
        return loadURL(path).toString();
    }

    public static InputStream loadStream(String name) {
        return UninaApplication.class.getResourceAsStream(name);
    }

}
