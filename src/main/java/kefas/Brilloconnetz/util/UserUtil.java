package kefas.Brilloconnetz.util;

import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserUtil {

    public String getAuthenticatedUserEmail(){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loggedEmail = userDetails.getUsername();
        System.out.println(loggedEmail);
        return userDetails.getUsername();
    }
}
