package ofs.mealtracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ofs.mealtracking.model.Entities.Admins;
import ofs.mealtracking.model.Entities.Siteusers;
import ofs.mealtracking.model.Requests.SignInRequestJson;
import ofs.mealtracking.model.Requests.SiteUserRequestJson;
import ofs.mealtracking.model.Requests.SiteUserUpdatePassword;
import ofs.mealtracking.model.Responses.AdminsResponseJson;
import ofs.mealtracking.model.Responses.AuthorizationResponseJson;
import ofs.mealtracking.model.Responses.SiteUserResponseJson;
import ofs.mealtracking.repositories.AdminRepository;
import ofs.mealtracking.repositories.SiteusersRepository;
import ofs.mealtracking.model.Authenticator;
import java.util.*;


@CrossOrigin
@RestController
@RequestMapping(path="/OFS")

public class AuthController
{
    //Defining all response attributes
    private AuthorizationResponseJson authorizationResponseJson;
    public static final String SIGN_IN_SUCCESS_CODE = "200";
    public static final String SIGN_IN_FAILURE_CODE = "404";
    public static final String SIGN_UP_SUCCESS_CODE = "201";
    public static final String SIGN_UP_FAILURE_CODE = "409";
    public static final String
    SIGN_IN_SUCCESS_DESCRIPTION = "User sucessfully validated";
    public static final String
    SIGN_IN_FAILURE_DESCRIPTION = "Please check the login credentials";
    public static final String
    SIGN_UP_SUCCESS_DESCRIPTION = "User sucessfully Created";
    public static final String
    SIGN_UP_FAILURE_DESCRIPTION = "User already exsists";
    private Map<String, String> responseValuesMap = 
    new HashMap<String, String>(){{
        put(SIGN_IN_SUCCESS_CODE, SIGN_IN_SUCCESS_DESCRIPTION);
        put(SIGN_IN_FAILURE_CODE, SIGN_IN_FAILURE_DESCRIPTION);
        put(SIGN_UP_SUCCESS_CODE, SIGN_UP_SUCCESS_DESCRIPTION);
        put(SIGN_UP_FAILURE_CODE, SIGN_UP_FAILURE_DESCRIPTION);
    }};
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SiteusersRepository siteusersRepository;

    //This method takes care of the admin sign in part
    @PostMapping(path="/admin/signIn")
    public @ResponseBody AuthorizationResponseJson signInAdmins
    (@RequestBody SignInRequestJson signInRequestJson){
        try{
            Admins admins;
            admins = adminRepository.findByUsername
            (signInRequestJson.getUsername());
            authorizationResponseJson = new AuthorizationResponseJson
            (SIGN_IN_FAILURE_CODE,
            responseValuesMap.get(SIGN_IN_FAILURE_CODE));
            Authenticator authenticator = new Authenticator();
            // Validating admin details in Authenticator class
            if(admins != null && authenticator.adminDetailsValidator
            (signInRequestJson,admins)){
                authorizationResponseJson = new AuthorizationResponseJson
                (SIGN_IN_SUCCESS_CODE,
                responseValuesMap.get(SIGN_IN_SUCCESS_CODE),
                admins.getUsername(),
                admins.getId());
            }
            return authorizationResponseJson;
        }
        catch(Exception e){
            authorizationResponseJson = new AuthorizationResponseJson
            (responseValuesMap.get(SIGN_IN_FAILURE_CODE),
            e.getMessage());
            return authorizationResponseJson;
        }
    }


@PostMapping(path = "/admin/create")
public @ResponseBody AuthorizationResponseJson createAdmin(@RequestBody SignInRequestJson signInRequestJson) {
    try {
        Admins newAdmin = new Admins();
        newAdmin.setUsername(signInRequestJson.getUsername());
        newAdmin.setPassword(signInRequestJson.getPassword());
        adminRepository.save(newAdmin);
        authorizationResponseJson = new AuthorizationResponseJson(
            SIGN_UP_SUCCESS_CODE,
            responseValuesMap.get(SIGN_UP_SUCCESS_CODE),
            newAdmin.getUsername(),
            newAdmin.getId()
        );
        return authorizationResponseJson;
    } catch (Exception e) {
        authorizationResponseJson = new AuthorizationResponseJson(
            responseValuesMap.get(SIGN_UP_FAILURE_CODE),
            e.getMessage()
        );

        return authorizationResponseJson;
    }
}
@PostMapping(path="/siteuser/signIn")
    public @ResponseBody AuthorizationResponseJson signInSiteUsers
    (@RequestBody SignInRequestJson signInRequestJson){
        try{
            Siteusers siteusers;
            siteusers = siteusersRepository.findByUsername
            (signInRequestJson.getUsername());
            authorizationResponseJson = new AuthorizationResponseJson
            (SIGN_IN_FAILURE_CODE,
            responseValuesMap.get(SIGN_IN_FAILURE_CODE));
            Authenticator authenticator = new Authenticator();
            // Validating admin details in Authenticator class
            if(siteusers != null && authenticator.siteusersDetailsValidator
            (signInRequestJson,siteusers)){
                authorizationResponseJson = new AuthorizationResponseJson
                (SIGN_IN_SUCCESS_CODE,
                responseValuesMap.get(SIGN_IN_SUCCESS_CODE),
                siteusers.getUsername(),
                siteusers.getId());
            }
            return authorizationResponseJson;
        }
        catch(Exception e){
            authorizationResponseJson = new AuthorizationResponseJson
            (responseValuesMap.get(SIGN_IN_FAILURE_CODE),
            e.getMessage());
            return authorizationResponseJson;
        }
    }

@PostMapping(path = "/siteuser/create")
public @ResponseBody AuthorizationResponseJson createSiteUsers(@RequestBody SiteUserRequestJson siteUserRequestJson) {
    try {
        Siteusers siteusers = new Siteusers();
        siteusers.setUsername(siteUserRequestJson.getUsername());
        siteusers.setPassword(siteUserRequestJson.getPassword());
        siteusers.setEmail(siteUserRequestJson.getEmail());
        siteusers.setSitename(siteUserRequestJson.getSiteName());
        siteusers.setSitesupervisor(siteUserRequestJson.getSiteSupervisor());
        siteusers.setSiteaddress(siteUserRequestJson.getSiteAddress());
        siteusers.setBreakfasttime(siteUserRequestJson.getBreakfastTime());
        siteusers.setBreakfastendtime(siteUserRequestJson.getBreakfastEndTime());
        siteusers.setLunchtime(siteUserRequestJson.getLunchTime());
        siteusers.setLunchendtime(siteUserRequestJson.getLunchEndTime());
        siteusers.setSuppertime(siteUserRequestJson.getSupperTime());
        siteusers.setSupperendtime(siteUserRequestJson.getSupperEndTime());
        siteusers.setSnacktime(siteUserRequestJson.getSnackTime());
        siteusers.setSnackendtime(siteUserRequestJson.getSnackEndTime());
        siteusers.setMealdays(siteUserRequestJson.getMealDays());
        siteusers.setProgram(siteUserRequestJson.getProgram());
        Optional<Admins> adminsOptional = adminRepository.findById(Long.parseLong(siteUserRequestJson.getAdminid()));
        if (adminsOptional.isPresent()) {
            Admins admins = adminsOptional.get();
            siteusers.setAdmins(admins);
        } else {
            authorizationResponseJson = new AuthorizationResponseJson(
            responseValuesMap.get(SIGN_UP_FAILURE_CODE),
            "The admin account is invalid not present");
            return authorizationResponseJson;
        }
        siteusersRepository.save(siteusers);
        authorizationResponseJson = new AuthorizationResponseJson(
            SIGN_UP_SUCCESS_CODE,
            responseValuesMap.get(SIGN_UP_SUCCESS_CODE),
            siteusers.getUsername(),
            siteusers.getId()
        );
        return authorizationResponseJson;
    } catch (Exception e) {
        authorizationResponseJson = new AuthorizationResponseJson(
            responseValuesMap.get(SIGN_UP_FAILURE_CODE),
            e.getMessage()
        );
        return authorizationResponseJson;
    }
}
@GetMapping(path = "admin/getAllSiteUsers")
    public List<SiteUserResponseJson> getAllSiteUsersWithUsernames() {
        List<SiteUserResponseJson> usersList = new ArrayList<SiteUserResponseJson>();
        try {
            Iterable<Siteusers> siteUsers = siteusersRepository.findAll();
            for (Siteusers user : siteUsers) {
            SiteUserResponseJson siteUserResponseJson
             = new SiteUserResponseJson();
            siteUserResponseJson.setId(user.getId());
            siteUserResponseJson.setUsername(user.getUsername());
            usersList.add(siteUserResponseJson);
            }
            return usersList;
        } catch (Exception e) {
            e.printStackTrace();
            return usersList;
        }
    }

@PutMapping(path = "/siteuser/updatePassword/{userId}")
public @ResponseBody AuthorizationResponseJson updateSiteUserPassword(
    @PathVariable Long userId,
    @RequestBody SiteUserUpdatePassword siteUserUpdatePassword) {
    try {
        Optional<Siteusers> siteUserOptional = siteusersRepository.findById(userId);
        if (siteUserOptional.isPresent()) {
            Siteusers siteUser = siteUserOptional.get();
            siteUser.setPassword(siteUserUpdatePassword.getNewPassword());
            siteusersRepository.save(siteUser);
            authorizationResponseJson = new AuthorizationResponseJson(
                SIGN_UP_SUCCESS_CODE,
                "Password updated successfully",
                siteUser.getUsername(),
                siteUser.getId()
            );
            return authorizationResponseJson;
        } else {
            authorizationResponseJson = new AuthorizationResponseJson(
                SIGN_IN_FAILURE_CODE,
                "User with ID " + userId + " not found"
            );
            return authorizationResponseJson;
        }
    } catch (Exception e) {
        authorizationResponseJson = new AuthorizationResponseJson(
            SIGN_UP_FAILURE_CODE,
            e.getMessage()
        );
        return authorizationResponseJson;
    }
}

@DeleteMapping(path = "/siteuser/delete/{userId}")
public @ResponseBody AuthorizationResponseJson deleteSiteUser(
    @PathVariable Long userId) {
    try {
        Optional<Siteusers> siteUserOptional = siteusersRepository.findById(userId);
        if (siteUserOptional.isPresent()) {
            siteusersRepository.deleteById(userId);
            authorizationResponseJson = new AuthorizationResponseJson(
                SIGN_UP_SUCCESS_CODE,
                "User with ID " + userId + " deleted successfully"
            );
            return authorizationResponseJson;
        } else {
            authorizationResponseJson = new AuthorizationResponseJson(
                SIGN_IN_FAILURE_CODE,
                "User with ID " + userId + " not found"
            );
            return authorizationResponseJson;
        }
    } catch (Exception e) {
        authorizationResponseJson = new AuthorizationResponseJson(
            SIGN_UP_FAILURE_CODE,
            e.getMessage()
        );
        return authorizationResponseJson;
    }
}



@DeleteMapping(path = "/admin/delete/{adminId}")
public @ResponseBody AuthorizationResponseJson deleteAdmin(
    @PathVariable Long adminId) {
    try {
        Optional<Admins> adminOptional = adminRepository.findById(adminId);
        if (adminOptional.isPresent()) {
            adminRepository.deleteById(adminId);
            authorizationResponseJson = new AuthorizationResponseJson(
                SIGN_UP_SUCCESS_CODE,
                "Admin with ID " + adminId + " deleted successfully"
            );
            return authorizationResponseJson;
        } else {
            authorizationResponseJson = new AuthorizationResponseJson(
                SIGN_IN_FAILURE_CODE,
                "Admin with ID " + adminId + " not found"
            );
            return authorizationResponseJson;
        }
    } catch (Exception e) {
        authorizationResponseJson = new AuthorizationResponseJson(
            SIGN_UP_FAILURE_CODE,
            e.getMessage()
        );
        return authorizationResponseJson;
    }
}

@GetMapping(path = "admin/getAllAdmins/{myAdminId}")
    public List<AdminsResponseJson> getAllAdmins(
        @PathVariable Long myAdminId
    ) {
        List<AdminsResponseJson> adminsList = new ArrayList<AdminsResponseJson>();
        try {
            Iterable<Admins> adminsItr = adminRepository.findAll();
            for (Admins admin : adminsItr) {
                if(admin.getId().equals(myAdminId))
                {
                    //ignore
                }
                else
                {
                    AdminsResponseJson adminsResponseJson
                    = new AdminsResponseJson();
                    adminsResponseJson.setId(admin.getId());
                    adminsResponseJson.setUsername(admin.getUsername());
                    adminsList.add(adminsResponseJson);
                }
            }
            return adminsList;
        } catch (Exception e) {
            e.printStackTrace();
            return adminsList;
        }
    }

    @PutMapping(path = "/admin/updatePassword/{adminId}")
public @ResponseBody AuthorizationResponseJson updateAdminPassword(
    @PathVariable Long adminId,
    @RequestBody SiteUserUpdatePassword adminUpdatePassword) {
    try {
        Optional<Admins> adminOptional = adminRepository.findById(adminId);
        if (adminOptional.isPresent()) {
            Admins admin = adminOptional.get();
            admin.setPassword(adminUpdatePassword.getNewPassword());
            adminRepository.save(admin);
            authorizationResponseJson = new AuthorizationResponseJson(
                SIGN_UP_SUCCESS_CODE,
                "Password updated successfully",
                admin.getUsername(),
                admin.getId()
            );
            return authorizationResponseJson;
        } else {
            authorizationResponseJson = new AuthorizationResponseJson(
                SIGN_IN_FAILURE_CODE,
                "Admin with ID " + adminId + " not found"
            );
            return authorizationResponseJson;
        }
    } catch (Exception e) {
        authorizationResponseJson = new AuthorizationResponseJson(
            SIGN_UP_FAILURE_CODE,
            e.getMessage()
        );
        return authorizationResponseJson;
    }
}

}
