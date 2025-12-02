package br.fai.lds.frontendspringlds.controller.impl;

import br.fai.lds.domain.UserModel;
import br.fai.lds.frontend.usecases.port.AccountRestService;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


public class AccountRestApiController implements AccountRestService {
    @Override
    public UserModel validateCredentials(String email, String password) {
        try {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();
            httpHeaders.set("email", email);
            httpHeaders.set("password", password);

            final HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
            final String endpoint = "http://localhost:8081/api/authentication/v1/login";
            ResponseEntity<UserModel> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, UserModel.class);

            if(responseEntity.getStatusCode() != HttpStatus.OK){
                return null;
            }

            UserModel user = responseEntity.getBody();
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserModel getUser() {
        final String endpoint = "http://localhost:8081/api/authentication/logged";
        final RestTemplate restTemplate = new RestTemplate();
        final HttpEntity<String> httpEntity = new HttpEntity<>("");

        ResponseEntity<UserModel> responseEntity = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, UserModel.class);


        UserModel user = responseEntity.getBody();

        return user;
    }

    @Override
    public boolean delete() {
        try {
            final String endpoint = "http://localhost:8081/api/authentication/logout";
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> httpEntity = new HttpEntity<>("");

            final ResponseEntity<String> responseEntity = restTemplate.exchange(endpoint,
                    HttpMethod.DELETE,
                    httpEntity,
                    String.class);

            return responseEntity.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
