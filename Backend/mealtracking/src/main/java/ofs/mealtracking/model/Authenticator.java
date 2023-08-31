package ofs.mealtracking.model;

import ofs.mealtracking.model.Entities.Admins;
import ofs.mealtracking.model.Requests.SignInRequestJson;

//This class deal with the Authentication logic
public class Authenticator {
    //This method validates customer  details
    public boolean adminDetailsValidator
    (SignInRequestJson signInRequestJson, Admins admins){
        boolean flag = false;
        if(signInRequestJson.getUsername().toLowerCase().
        equals(admins.getUsername().toLowerCase()) &&
         signInRequestJson.getPassword().equals(admins.getPassword())){
            flag = true;
        }
        return flag;
    }
}
