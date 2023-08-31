package ofs.mealtracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ofs.mealtracking.model.Entities.Admins;
import ofs.mealtracking.model.Requests.SignInRequestJson;
import ofs.mealtracking.model.Responses.AuthorizationResponseJson;
import ofs.mealtracking.repositories.AdminRepository;
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
            // Validating customer details in Authenticator class
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
       System.out.println("here---------------------------------------"); 
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //String encryptedPassword = passwordEncoder.encode(signInRequestJson.getPassword());
        Admins newAdmin = new Admins();
        newAdmin.setUsername(signInRequestJson.getUsername());
        //newAdmin.setPassword(encryptedPassword);
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

}
