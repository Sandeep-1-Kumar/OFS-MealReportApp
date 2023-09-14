package ofs.mealtracking.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ofs.mealtracking.model.Entities.Mealcount;
import ofs.mealtracking.model.Entities.Siteusers;
import ofs.mealtracking.model.Requests.MealCountRequestJson;
import ofs.mealtracking.model.Responses.MealOperationsResponseJson;
import ofs.mealtracking.repositories.MealCountRepository;
import ofs.mealtracking.repositories.SiteusersRepository;

import java.util.*;


@CrossOrigin
@RestController
@RequestMapping(path="/OFS")
public class MealController {
    public static final String MEAL_ADD_IN_SUCCESS_CODE = "201";
    public static final String MEAL_ADD_IN_FAILURE_CODE = "409";
    public static final String MEAL_UPDATE_SUCCESS_CODE = "204";
    public static final String MEAL_UPDATE_FAILURE_CODE = "400";
    public static final String
    MEAL_ADD_IN_SUCCESS_DESCRIPTION = "Meal added successfully";
    public static final String
    MEAL_ADD_IN_FAILURE_DESCRIPTION = "Failed to add meal";
    public static final String 
    MEAL_UPDATE_SUCCESS_DESCRIPTION = "Meal updated successfully";
    public static final String 
    MEAL_UPDATE_FAILURE_DESCRIPTION = "Failed to update meal"; 
    private Map<String, String> responseValuesMap = 
    new HashMap<String, String>(){{
        put(MEAL_ADD_IN_SUCCESS_CODE, MEAL_ADD_IN_SUCCESS_DESCRIPTION);
        put(MEAL_ADD_IN_FAILURE_CODE, MEAL_ADD_IN_FAILURE_DESCRIPTION);
        put(MEAL_UPDATE_SUCCESS_CODE, MEAL_UPDATE_SUCCESS_DESCRIPTION);
        put(MEAL_UPDATE_FAILURE_CODE, MEAL_UPDATE_FAILURE_DESCRIPTION);
    }};


    @Autowired
    private SiteusersRepository siteusersRepository;

    @Autowired
    private MealCountRepository mealCountRepository;

    @PostMapping(path = "/siteuser/addMealCount")
    public @ResponseBody MealOperationsResponseJson 
    addMealCountForSiteUser(@RequestBody MealCountRequestJson mealCountRequestJson) {
    MealOperationsResponseJson mealOperationsResponseJson = new MealOperationsResponseJson();
    try {
        Siteusers siteUser = siteusersRepository.findByUsername(mealCountRequestJson.getUsername());
        if (siteUser == null) {
            mealOperationsResponseJson.setStatusCode(MEAL_ADD_IN_FAILURE_CODE);
            mealOperationsResponseJson.setStatusMessage("Site user not found -" 
            + responseValuesMap.get(MEAL_ADD_IN_FAILURE_DESCRIPTION));
            return mealOperationsResponseJson;
        }
        Mealcount mealCount = new Mealcount();
        mealCount.setSiteuser(siteUser);
        mealCount.setMealDate(mealCountRequestJson.getMealDate());
        mealCount.setMealType(mealCountRequestJson.getMealType());
        mealCount.setProgram(mealCountRequestJson.getProgram());
        mealCount.setMealCount(mealCountRequestJson.getMealCount());
        mealCountRepository.save(mealCount);
        mealOperationsResponseJson.setStatusCode(MEAL_ADD_IN_SUCCESS_CODE);
        mealOperationsResponseJson.setStatusMessage
        (responseValuesMap.get(MEAL_ADD_IN_SUCCESS_CODE));
        return mealOperationsResponseJson;
    } catch (Exception e) {
        mealOperationsResponseJson.setStatusCode(MEAL_ADD_IN_FAILURE_CODE);
        mealOperationsResponseJson.setStatusMessage(e.getMessage());
        return mealOperationsResponseJson;
    }
}

@PutMapping(path = "/siteuser/updateMealCount/{mealCountId}")
public @ResponseBody MealOperationsResponseJson 
updateMealCount(@PathVariable Long mealCountId, @RequestBody MealCountRequestJson mealCountRequestJson) {
    MealOperationsResponseJson mealOperationsResponseJson = new MealOperationsResponseJson();
    
    try {
        Optional<Mealcount> mealCountOptional = 
        mealCountRepository.findById(mealCountId);
        if (mealCountOptional.isPresent()) {
            Mealcount mealCount = mealCountOptional.get();
            mealCount.setMealDate(mealCountRequestJson.getMealDate());
            mealCount.setMealType(mealCountRequestJson.getMealType());
            mealCount.setProgram(mealCountRequestJson.getProgram());
            mealCount.setMealCount(mealCountRequestJson.getMealCount());
            mealCount.setComment(mealCountRequestJson.getComment());
            mealCountRepository.save(mealCount);
            mealOperationsResponseJson.setStatusCode(MEAL_UPDATE_SUCCESS_CODE);
            mealOperationsResponseJson.setStatusMessage
            (responseValuesMap.get(MEAL_UPDATE_SUCCESS_CODE));
        } else {
            mealOperationsResponseJson.setStatusCode(MEAL_UPDATE_FAILURE_CODE);
            mealOperationsResponseJson.setStatusMessage("Meal count not found - " 
                + responseValuesMap.get(MEAL_UPDATE_FAILURE_CODE));
        }
        
    } catch (Exception e) {
        mealOperationsResponseJson.setStatusCode(MEAL_UPDATE_FAILURE_CODE);
        mealOperationsResponseJson.setStatusMessage(e.getMessage());
    }
    
    return mealOperationsResponseJson;
}




}





