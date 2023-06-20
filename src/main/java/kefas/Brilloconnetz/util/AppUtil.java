package kefas.Brilloconnetz.util;

import org.springframework.stereotype.Service;

@Service
public class AppUtil {

    public boolean validEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
