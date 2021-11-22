package com.sophos.board.cliente;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.sophos.board.modelo.UserIdentityDTO;


@Repository
public class IdentityCliente {
	

    private final Logger logger = LogManager.getLogger(IdentityCliente.class);

    @Value("${identity.endpoint.url}")
    private String endpoint;
    
    public UserIdentityDTO obtenerPorId(int id) {
        try {
            String url = endpoint + "/consulta/" + id;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            //headers.add("Authorization", "Bearer " + token);
            ResponseEntity<UserIdentityDTO> modelo = new RestTemplate()
                    .exchange(url, HttpMethod.GET,
                            new HttpEntity<String>(headers), UserIdentityDTO.class);
            return modelo.getBody();
        } catch (RestClientResponseException e) {
            logger.warn("Error el id de usuario no existe ");
            if (e.getRawStatusCode() == 403) {
                return null;
            }
            throw new RuntimeException("Error el codCiudad no existe", e);
        }
    }

}
