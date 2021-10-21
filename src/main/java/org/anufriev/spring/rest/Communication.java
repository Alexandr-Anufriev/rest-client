package org.anufriev.spring.rest;

import org.anufriev.spring.rest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Communication {
    @Autowired
    private RestTemplate restTemplate;
    private static final String URL = "http://91.241.64.178:7081/api/users";
    String cookies;

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<User>>() {});
        cookies = responseEntity.getHeaders().get("Set-Cookie").stream().collect(Collectors.joining(";"));
        System.out.println(cookies);
        return responseEntity.getBody();
    }

    public String saveUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookies);
        headers.set("Content-Type", "application/json");
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        HttpEntity<String> rssResponse = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        return rssResponse.getBody();
    }

    public String updateUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookies);
        headers.set("Content-Type", "application/json");
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        HttpEntity<String> rssResponse = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        return rssResponse.getBody();
    }

    public String deleteUser(long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookies);
        headers.set("Content-Type", "application/json");
        HttpEntity<User> entity = new HttpEntity<>(headers);
        HttpEntity<String> rssResponse = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class);
        return rssResponse.getBody();
    }
}
