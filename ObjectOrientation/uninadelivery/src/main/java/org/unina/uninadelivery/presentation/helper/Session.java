package org.unina.uninadelivery.presentation.helper;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;

import java.util.Hashtable;

public class Session {
    private static Session instance;

    private Hashtable<String, Object> sessionData = new Hashtable<String, Object>();

    private Property<UtenteDTO> user;

    public Property<UtenteDTO> getUserDto() {
        if(user == null)
            user = new SimpleObjectProperty<>(null);

        return user;
    }
    public void addUserDto(Property<UtenteDTO> user) {
        getUserDto().setValue(user.getValue());
    }

    public void addSessionData(String key, Object value) {
        if(sessionData.containsKey(key)) {
            sessionData.remove(key);
        }

        sessionData.put(key, value);
    }

    public Object getSessionData(String key) {
        return sessionData.get(key);
    }

    public static Session getInstance() {
        if(instance == null) {
            instance = new Session();
        }
        return instance;
    }
}
