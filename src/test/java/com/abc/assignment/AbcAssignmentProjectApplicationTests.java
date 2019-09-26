package com.abc.assignment;

import java.net.URI;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import junit.framework.Assert;



@RunWith(SpringRunner.class)
@SpringBootTest
public class AbcAssignmentProjectApplicationTests {

	public static final String REST_SERVICE_URI = "http://localhost:8080/";
	    

	   
	
	
    @Test
	public void contextLoads() {
    //	createUser();
    	
	}
    
    //@Test
    private static void createUser() {
		System.out.println("------ Testing create Person API----------");
    	RestTemplate restTemplate = new RestTemplate();
    	try {
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("firstName", "Yuvan");
        personJsonObject.put("lastName", "Reddy");
        personJsonObject.put("birthDate", "2013-03-03");
        String createPersonUrl = REST_SERVICE_URI+"person/create";
        HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
        
        String personResultAsJsonStr = restTemplate.postForObject(createPersonUrl, request, String.class);
        //JsonNode root = personJsonObject.readTree(personResultAsJsonStr);
    	
			
       // URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/person/create", headers, personJsonObject);
       // System.out.println("Location : "+uri.toASCIIString());
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
   // @Test
    public void getOne() {
    	 RestTemplate restTemplate = new RestTemplate();
         try {
    	    final String baseUrl = "http://localhost:8080/person/1";
    	    URI uri = new URI(baseUrl);
    	 
    	    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
    	     System.out.println(result);
    	    //Verify request succeed
    	    Assert.assertEquals(200, result.getStatusCodeValue());
    	    Assert.assertEquals(true, result.getBody().contains("employeeList"));
         }catch(Exception e) {
        	 e.printStackTrace();
         }
    }

}
