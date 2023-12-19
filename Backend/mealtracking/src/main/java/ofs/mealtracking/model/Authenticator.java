package ofs.mealtracking.model;

import ofs.mealtracking.model.Entities.Admins;
import ofs.mealtracking.model.Entities.Siteusers;
//import ofs.mealtracking.model.Entities.Siteusers;
import ofs.mealtracking.model.Requests.SignInRequestJson;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Authenticator {

    private final BCryptPasswordEncoder passwordEncoder;

    public Authenticator() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    // This method validates admin details
    public boolean adminDetailsValidator(SignInRequestJson signInRequestJson, Admins admins) {
        if (signInRequestJson == null || admins == null) {
            return false;
        }
        // Compare the provided plain-text password with the stored encrypted password
        return signInRequestJson.getUsername().equalsIgnoreCase(admins.getUsername()) &&
                passwordEncoder.matches(signInRequestJson.getPassword(), admins.getPassword());
    }
    public boolean siteusersDetailsValidator(SignInRequestJson signInRequestJson, Siteusers siteusers) {
        if (signInRequestJson == null || siteusers == null) {
            return false;
        }
        // Compare the provided plain-text password with the stored encrypted password
        return signInRequestJson.getUsername().equalsIgnoreCase(siteusers.getUsername()) &&
                passwordEncoder.matches(signInRequestJson.getPassword(), siteusers.getPassword());
    }
}
