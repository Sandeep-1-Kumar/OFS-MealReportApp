package ofs.mealtracking.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

import ofs.mealtracking.model.Entities.Mealcount;
import ofs.mealtracking.model.Entities.Siteusers;
import ofs.mealtracking.model.Requests.AddMealsDeliveredRequestJson;
//import ofs.mealtracking.model.Requests.MealCountRequestJson;
import ofs.mealtracking.model.Responses.MealOperationsResponseJson;
import ofs.mealtracking.model.Responses.ReviewFinalMealStatusResponseJson;
import ofs.mealtracking.model.Responses.ReviewMealCountResponse;
//import ofs.mealtracking.model.Requests.SiteUserRequestJson;
//import ofs.mealtracking.model.Responses.AddMealCountResponseJson;
//import ofs.mealtracking.model.Responses.MealOperationsResponseJson;
//import ofs.mealtracking.model.Responses.ReviewMealCountResponse;
//import ofs.mealtracking.model.Requests.MealCountRequestJson;
//import ofs.mealtracking.model.Responses.MealOperationsResponseJson;
import ofs.mealtracking.repositories.MealCountRepository;
import ofs.mealtracking.repositories.SiteusersRepository;

import java.sql.Time;

//import java.math.BigDecimal;
//import java.sql.Time;
//import java.sql.Date;

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
    
    @Autowired
    private SiteusersRepository siteusersRepository;

    @Autowired
    private MealCountRepository mealCountRepository;    

    @Autowired
    private EntityManager entityManager;


@PostMapping(path = "/siteuser/addMealsDelivered")
public @ResponseBody ReviewMealCountResponse
addMealsDelivered(@RequestBody AddMealsDeliveredRequestJson addMealsDeliveredRequestJson)
    {
    ReviewMealCountResponse reviewMealCountResponse = new ReviewMealCountResponse();
    try {
        Siteusers siteUser = siteusersRepository.findByUsername
        (addMealsDeliveredRequestJson.getUsername());
        if (siteUser == null) {
            throw new Exception("Site User Not Found for the particular meal count");
        }
        Mealcount mealCount = new Mealcount();
        mealCount.setSiteuser(siteUser);
        mealCount.setMealDate(addMealsDeliveredRequestJson.getMealDate());
        mealCount.setMealType(addMealsDeliveredRequestJson.getMealType());
        mealCount.setPersonName(addMealsDeliveredRequestJson.getPersonName());
        mealCount.setMealDeliveryStatus(addMealsDeliveredRequestJson.getMealsDeliveryStatus());
        mealCount.setMealsDelivered(addMealsDeliveredRequestJson.getMealsDeliveredCount()); 
        mealCount.setDeliveryTime(addMealsDeliveredRequestJson.getTimeOfMealsDelivered());
        mealCount.setTemperatureOfMeals(addMealsDeliveredRequestJson.getTemperatureOfMeals());
        mealCount.setMealServiceStatus("IN PROGRESS");
        mealCount.setMealServiceStartTime(addMealsDeliveredRequestJson.getMealServiceStartTime());
        int mealsCountFromPreviousDay = getMealCountFromPreviousDay(siteUser.getId());
        mealCount.setPreviousDayMeals(mealsCountFromPreviousDay);
        mealCountRepository.save(mealCount);

        reviewMealCountResponse.setMealId(String.valueOf(mealCount.getId()));
        reviewMealCountResponse.setDate(String.valueOf(addMealsDeliveredRequestJson.getMealDate()));
        reviewMealCountResponse.setDeliveryStatus(addMealsDeliveredRequestJson.getMealsDeliveryStatus());
        reviewMealCountResponse.setMealtemperature(String.valueOf(addMealsDeliveredRequestJson.getTemperatureOfMeals()));
        reviewMealCountResponse.setMealType(addMealsDeliveredRequestJson.getMealType());
        reviewMealCountResponse.setMealServiceTime(String.valueOf(addMealsDeliveredRequestJson.getMealServiceStartTime()));
        reviewMealCountResponse.setMealsAvailableFromPreviousDay(
            String.valueOf(mealsCountFromPreviousDay));
        reviewMealCountResponse.setMealsDelivered(String.valueOf(addMealsDeliveredRequestJson.getMealsDeliveredCount()));
        int mealsAvailableToStartService = mealsCountFromPreviousDay + addMealsDeliveredRequestJson.getMealsDeliveredCount();
        reviewMealCountResponse.setMealsAvailableToStartMealService(String.valueOf(mealsAvailableToStartService));
        return reviewMealCountResponse;
    } catch (Exception e) {
        e.printStackTrace();
        return reviewMealCountResponse;
    }
}

@GetMapping(path = "/admin/getMealCountOfPreviousDay/{siteUserId}")
public int getMealCountFromPreviousDay(@PathVariable(name = "siteUserId", required = false) Long siteUserId) {
    String query = "SELECT carryovermeals FROM Mealcount WHERE siteuserid = :siteUserId ORDER BY id DESC LIMIT 1";
    
    Query nativeQuery = entityManager.createNativeQuery(query);
    
    nativeQuery.setParameter("siteUserId", (siteUserId != null) ? siteUserId : -1);

    List<?> resultList = nativeQuery.getResultList();

    if (!resultList.isEmpty()) {
        Object result = resultList.get(0);
        if (result instanceof Number) {
            return ((Number) result).intValue();
        } else {
            return 0;
        }
    } else {
        return 0;
    }
}
    

@PutMapping(path = "/siteuser/reviewMealUpdateSignature/{mealCountId}")
public @ResponseBody MealOperationsResponseJson 
updateMealCountSignature(
    @PathVariable Long mealCountId,
    @RequestParam("personSignature") MultipartFile personSignatureFile) {
    MealOperationsResponseJson mealOperationsResponseJson = new MealOperationsResponseJson();
    try {
        Optional<Mealcount> mealCountOptional = 
        mealCountRepository.findById(mealCountId);
        if (mealCountOptional.isPresent()) {
            Mealcount mealCount = mealCountOptional.get();
            byte[] personSignatureData = personSignatureFile.getBytes();
            mealCount.setPersonSignature(personSignatureData);
            mealCountRepository.save(mealCount);
            mealOperationsResponseJson.setStatusCode(MEAL_UPDATE_SUCCESS_CODE);
            mealOperationsResponseJson.setStatusMessage
            ("person signature updated successfully");
            mealOperationsResponseJson.setMealId(String.valueOf(mealCountId));
        } else {
            mealOperationsResponseJson.setStatusCode(MEAL_UPDATE_FAILURE_CODE);
            mealOperationsResponseJson.setStatusMessage("person Signature not updated");
        }
        
    } catch (Exception e) {
        mealOperationsResponseJson.setStatusCode(MEAL_UPDATE_FAILURE_CODE);
        mealOperationsResponseJson.setStatusMessage(e.getMessage());
    }
    
    return mealOperationsResponseJson;
}




@PutMapping(path = "/siteuser/updateMealServedAndDamaged/{mealCountId}")
public @ResponseBody MealOperationsResponseJson 
updateMealServedAndDamaged(
    @PathVariable Long mealCountId,
    @RequestParam("mealsServed") int mealsServed,
    @RequestParam("damagedMeals") int damagedMeals,
    @RequestParam("damagedMealsComment") String damagedMealsComment,
    @RequestParam("damagedMealsImage") MultipartFile damagedMealsImageFile)
    {
    MealOperationsResponseJson mealOperationsResponseJson = new MealOperationsResponseJson();
    try {

        
        Optional<Mealcount> mealCountOptional = 
        mealCountRepository.findById(mealCountId);
        if (mealCountOptional.isPresent()) {
            Mealcount mealCount = mealCountOptional.get();
            int mealsAvailableToServe = mealCount.getPreviousDayMeals() + mealCount.getMealsDelivered();
            
            if(mealsAvailableToServe < (mealsServed + damagedMeals))
            {
                throw new Exception("The !meals served! + !meals damaged! should not be greater than meals available to serve");
            }
            mealCount.setMealsServed(mealsServed);
            mealCount.setDamagedMeals(damagedMeals);
            mealCount.setDamagedMealsComment(damagedMealsComment);
            byte[] damagedMealsImageData = damagedMealsImageFile.getBytes();
            mealCount.setDamagedMealsImage(damagedMealsImageData);
            mealCountRepository.save(mealCount);
            mealOperationsResponseJson.setStatusCode(MEAL_UPDATE_SUCCESS_CODE);
            mealOperationsResponseJson.setStatusMessage
            ("served meals and damaged meals count updated successfully");
            mealOperationsResponseJson.setMealId(String.valueOf(mealCountId));
        } else {
            mealOperationsResponseJson.setMealId(String.valueOf(mealCountId));
            mealOperationsResponseJson.setStatusCode(MEAL_UPDATE_FAILURE_CODE);
            mealOperationsResponseJson.setStatusMessage("served meals and damaged meals not updated");
        }
        
    } catch (Exception e) {
         mealOperationsResponseJson.setMealId(String.valueOf(mealCountId));
        mealOperationsResponseJson.setStatusCode(MEAL_UPDATE_FAILURE_CODE);
        mealOperationsResponseJson.setStatusMessage(e.getMessage());
    }
    
    return mealOperationsResponseJson;
}



@PutMapping(path = "/siteuser/updateMealsExpiredCountEndMealService/{mealCountId}")
public @ResponseBody ReviewFinalMealStatusResponseJson 
updateMealsExpiredCountEndMealService(
    @PathVariable Long mealCountId,  
    @RequestParam("expiredMealsCount") int expiredMeals,
    @RequestParam("expiredMealsComment") String expiredMealsComment,
    @RequestParam("expiredMealsImage") MultipartFile expiredMealsImageFile,
    @RequestParam("mealServiceEndTime") Time mealServiceEndTime)
    {
    ReviewFinalMealStatusResponseJson reviewFinalMealStatusResponseJson =
    new ReviewFinalMealStatusResponseJson();
    try {
        Optional<Mealcount> mealCountOptional = 
        mealCountRepository.findById(mealCountId);
        if (mealCountOptional.isPresent()) {
            Mealcount mealCount = mealCountOptional.get();
            mealCount.setExpiredMeals(expiredMeals);
            mealCount.setExpiredMealsComment(expiredMealsComment);
            byte[] expiredMealsImageData = expiredMealsImageFile.getBytes();
            mealCount.setExpiredMealsImage(expiredMealsImageData);
            mealCount.setMealServiceEndTime(mealServiceEndTime);

            int mealsAvailableToServe = mealCount.getPreviousDayMeals() + mealCount.getMealsDelivered();
            
            if(mealsAvailableToServe < (mealCount.getMealsServed() + mealCount.getDamagedMeals() + expiredMeals))
            {
                throw new Exception("The !meals served! + !meals damaged! + !expired meals! should not be greater than meals available to serve");
            }
            //int previousDayMeals = getMealCountFromPreviousDay(mealCount.getSiteuser().getId());
            int carryOverMeals = (mealCount.getPreviousDayMeals() + mealCount.getMealsDelivered()) -
            (mealCount.getMealsServed() +mealCount.getDamagedMeals() + mealCount.getExpiredMeals());        
            mealCount.setCarryOverMeals(carryOverMeals);

            mealCountRepository.save(mealCount);
            reviewFinalMealStatusResponseJson.setMealsAvailableFromPreviousDay(
                String.valueOf(mealCount.getPreviousDayMeals()));

            reviewFinalMealStatusResponseJson.setMealsDelivered(
                String.valueOf(mealCount.getMealsDelivered())  
            );

            reviewFinalMealStatusResponseJson.setMealsAvailableToStartMealService(
                String.valueOf(mealCount.getPreviousDayMeals() + mealCount.getMealsDelivered())
            );
 
           reviewFinalMealStatusResponseJson.setMealsServed(
            String.valueOf(mealCount.getMealsServed()));
            
           reviewFinalMealStatusResponseJson.setDamagedMeals(
            String.valueOf(mealCount.getDamagedMeals())
           );
 
           reviewFinalMealStatusResponseJson.setExpiredMeals(
            String.valueOf(mealCount.getExpiredMeals())
           );

           reviewFinalMealStatusResponseJson.setMealsElgibleToCarryOverToNextDay(
            String.valueOf(mealCount.getCarryOverMeals())
           );

           reviewFinalMealStatusResponseJson.setStatusCode(MEAL_ADD_IN_SUCCESS_CODE);

        }
    } catch (Exception e) 
    {
        reviewFinalMealStatusResponseJson.setStatusCode(MEAL_ADD_IN_FAILURE_CODE);
        e.printStackTrace();
    }
    
    return reviewFinalMealStatusResponseJson;
}

@GetMapping(path = "/siteuser/submitMealCount/{mealCountId}")
public ResponseEntity<Map<String, Object>> submittingMealCount(@PathVariable Long mealCountId) {
    try {
            Optional<Mealcount> mealCountOptional = 
            mealCountRepository.findById(mealCountId);
            if (mealCountOptional.isPresent()) {
            Mealcount mealCount = mealCountOptional.get();
            mealCount.setMealServiceStatus("COMPLETED");
            mealCountRepository.save(mealCount);
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("id", mealCount.getId());
            responseMap.put("siteName", mealCount.getSiteuser().getSitename());
            responseMap.put("deliveryTime",mealCount.getDeliveryTime());
            responseMap.put("address", mealCount.getSiteuser().getSiteaddress());
            responseMap.put("mealTemperature",mealCount.getTemperatureOfMeals());
            responseMap.put("siteSupervisor", mealCount.getSiteuser().getSitesupervisor());
            responseMap.put("mealType", mealCount.getMealType());
            responseMap.put("date", mealCount.getMealDate());
            responseMap.put("mealServiceTime", String.valueOf(mealCount.getMealServiceStartTime())
            + "-" + String.valueOf(mealCount.getMealServiceEndTime()));

            // adding logic that if this site user has only the present mealcount row and there are no other mealcount rows in table for this particular siteuser then 
            //mealCount.getSiteuser().getId()

            int previousDayMeals = mealCount.getPreviousDayMeals();
            
            int carryOverMeals = (previousDayMeals + mealCount.getMealsDelivered()) -
            (mealCount.getMealsServed() +mealCount.getDamagedMeals() + mealCount.getExpiredMeals());        
            mealCount.setCarryOverMeals(carryOverMeals);
            responseMap.put("mealsFromPreviousDay",previousDayMeals);
            responseMap.put("mealsDelivered",mealCount.getMealsDelivered());
            responseMap.put("mealsAvailable",previousDayMeals + mealCount.getMealsDelivered());
            responseMap.put("mealsServed",mealCount.getMealsServed());
            responseMap.put("damagedMeals",mealCount.getDamagedMeals());
            responseMap.put("damagedMealsComment",mealCount.getDamagedMealsComment());
            responseMap.put("expiredMeals",mealCount.getExpiredMeals());
            responseMap.put("expiredMealsComment",mealCount.getExpiredMealsComment());
            responseMap.put("carryOverMeals",mealCount.getCarryOverMeals());
            byte[] personSignature = mealCount.getPersonSignature();
            if (personSignature != null) {
                String encodedSignature = Base64.getEncoder().encodeToString(personSignature);
                responseMap.put("personSignature", encodedSignature);
            } else {
                responseMap.put("personSignature", null);
            }
            return ResponseEntity.ok(responseMap);
        }else
        {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyMap());

        }
        
    }
     catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyMap());
    }
}



@GetMapping(path = "/admin/getMealCounts")
@SuppressWarnings("unchecked")
public List<Map<String, Object>> adminGetMealCounts(
    @RequestParam(required = false) String siteName,
    @RequestParam(required = false) String date,
    @RequestParam(required = false) String status,
    @RequestParam(required = false) String mealType,
    @RequestParam(required = false) String mealsAvailableFromPreviousDay,
    @RequestParam(required = false) String mealsDelivered,
    @RequestParam(required = false) String mealsServed,
    @RequestParam(required = false) String damagedMeals,
    @RequestParam(required = false) String expiredMeals,
    @RequestParam(required = false) String eligibleCarryOverMeals,
    @RequestParam(required = false) String comments
) {
    try {
        String mealCountTableName = "mealcount";
        //String siteUserTableName = "siteusers";


        String mealCountQuery = "SELECT id,mealdate,mealtype,mealsdelivered,mealsserved,damagedmeals,expiredMeals,comment FROM "
        + mealCountTableName + " WHERE 1=1";        

        if (date != null && !date.isEmpty()) {
            mealCountQuery += " AND mealdate = :date";
        }

        if (mealType != null && !mealType.isEmpty()) {
            mealCountQuery += " AND mealtype = :mealType";
        }

        if (mealsDelivered != null && 
            !mealsDelivered.isEmpty()) {
            mealCountQuery += " AND mealsdelivered = :mealsDelivered";
        }

        if (mealsServed != null && 
            !mealsServed.isEmpty()) {
            mealCountQuery += " AND mealsserved = :mealsServed";
        }

        if (damagedMeals != null && 
            !damagedMeals.isEmpty()) {
            mealCountQuery += " AND damagedmeals = :damagedMeals";
        }
        if (expiredMeals != null && 
            !expiredMeals.isEmpty()) {
            mealCountQuery += " AND expiredmeals = :expiredMeals";
        }
        Query nativeQuery = entityManager.createNativeQuery(mealCountQuery);

        if (date != null && !date.isEmpty()) {
            nativeQuery.setParameter("date", date); 
        }

        if (mealType != null && !mealType.isEmpty()) {
            nativeQuery.setParameter("mealType", mealType);
        }

        if (mealsDelivered != null && !mealsDelivered.isEmpty()) {
            nativeQuery.setParameter("mealsDelivered", mealsDelivered);
        }

        if (mealsServed != null && !mealsServed.isEmpty()) {
            nativeQuery.setParameter("mealsServed", mealsServed);
        }

        if (damagedMeals != null && !damagedMeals.isEmpty()) {
            nativeQuery.setParameter("damagedMeals", damagedMeals); 
        }

        if (expiredMeals != null && !expiredMeals.isEmpty()) {
            nativeQuery.setParameter("expiredMeals", expiredMeals);
        }

        List<Object[]> results = nativeQuery.getResultList();
        List<Map<String, Object>> responseList = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("id", row[0]);
            responseMap.put("date", row[1]);
            responseMap.put("mealType", row[2]);
            responseMap.put("mealsDelivered", row[3]);
            responseMap.put("mealsServed", row[4]);
            responseMap.put("mealsDamaged", row[5]);
            responseMap.put("mealsExpired", row[6]);
            responseMap.put("comment", row[7]);

            responseList.add(responseMap);
        }
        return responseList;
    } catch (Exception e) {
        e.printStackTrace();
        return new ArrayList<>();
    }
}



}