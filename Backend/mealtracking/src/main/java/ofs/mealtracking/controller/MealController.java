package ofs.mealtracking.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ofs.mealtracking.model.Entities.Mealcount;
import ofs.mealtracking.model.Entities.Siteusers;
import ofs.mealtracking.model.Requests.MealCountRequestJson;
import ofs.mealtracking.model.Responses.MealOperationsResponseJson;
import ofs.mealtracking.repositories.MealCountRepository;
import ofs.mealtracking.repositories.SiteusersRepository;



import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;


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

    @Autowired
    private EntityManager entityManager;

    

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

@GetMapping(path = "/admin/getMealCounts")
@SuppressWarnings("unchecked")
public List<Map<String, Object>> adminGetMealCounts(
    @RequestParam(required = false) String siteUserId,
    @RequestParam(required = false) String mealType,
    @RequestParam(required = false) String date,
    @RequestParam(required = false) String program
) {
    try {
        String tableName = "mealcount";
        String query = "SELECT * FROM " + tableName + " WHERE 1=1";
        
        if (siteUserId != null && !siteUserId.isEmpty()) {
            query += " AND siteuserid = :siteUserId";
        }

        if (mealType != null && !mealType.isEmpty()) {
            query += " AND mealtype = :mealType";
        }

        if (date != null && !date.isEmpty()) {
            query += " AND mealdate = :date";
        }

        if (program != null && !program.isEmpty()) {
            query += " AND program = :program";
        }
        
        Query nativeQuery = entityManager.createNativeQuery(query);

        if (siteUserId != null && !siteUserId.isEmpty()) {
            nativeQuery.setParameter("siteUserId", siteUserId);
        }

        if (mealType != null && !mealType.isEmpty()) {
            nativeQuery.setParameter("mealType", mealType);
        }

        if (date != null && !date.isEmpty()) {
            nativeQuery.setParameter("date", date);
        }

        if (program != null && !program.isEmpty()) {
            nativeQuery.setParameter("program", program);
        }

        List<Object[]> results = nativeQuery.getResultList();
        List<Map<String, Object>> responseList = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("id", row[0]);
            responseMap.put("siteuserid", row[1]);
            responseMap.put("mealdate", row[2]);
            responseMap.put("mealtype", row[3]);
            responseMap.put("program", row[4]);
            responseMap.put("mealcount", row[5]);
            responseMap.put("comment", row[6]);
            responseList.add(responseMap);
        }
        return responseList;
    } catch (Exception e) {
        return new ArrayList<>();
    }
}
    

}





