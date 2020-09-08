package com.nagarro.nagp.nagpjavamohit3150271;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class NagpJavaMohit3150271Application {

//	public static void main(String[] args) {
//		SpringApplication.run(NagpJavaMohit3150271Application.class, args);
//	}




	private static final String HTTP_HUNT_HOST = "https://http-hunt.thoughtworks-labs.net";
//	public static void main(String[] args) throws JSONException {
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("userId", "-0tL3mKDd");
//		HttpEntity request = new HttpEntity(headers);
//		RestTemplate template = new RestTemplate();
//		final ResponseEntity<SampleInput> forEntity = template.exchange(HTTP_HUNT_HOST + "/challenge/input", HttpMethod.GET, request, SampleInput.class);
//		System.out.println(forEntity.getBody().text);
//		JSONObject data = new JSONObject();
//		data.put("wordCount", forEntity.getBody().text.split("[.?!]").length);
//		HttpEntity entity = new HttpEntity(data.toString(), headers);
//		ResponseEntity<Void> response = template.postForEntity(HTTP_HUNT_HOST + "/challenge/output", entity, Void.class);
//	}

	public static void main(String[] args) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("userId", "-0tL3mKDd");
		HttpEntity request = new HttpEntity(headers);
		RestTemplate template = new RestTemplate();
		final ResponseEntity<SampleInput> forEntity = template.exchange(HTTP_HUNT_HOST + "/challenge/input", HttpMethod.GET, request, SampleInput.class);
		System.out.println(forEntity.getBody().text);
		Map<String, Integer> map = new HashMap<>();
        for(String s : forEntity.getBody().text.split("")){
            if("AEIOUaeiou".contains(s)) {
                map.put(s.toLowerCase(), map.getOrDefault(s.toLowerCase(), 0) + 1);
            }
        }
		HttpEntity entity = new HttpEntity(map, headers);
		ResponseEntity<Void> response = template.postForEntity(HTTP_HUNT_HOST + "/challenge/output", entity, Void.class);
	}

}
class SampleOutput {
	Integer count;
	SampleOutput(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
class SampleInput {
	String text;

	public SampleInput() {}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}