package com.timklug.demo.controller;

import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class AbstractController {

  private OAuth2RestTemplate restTemplate;
  private final Logger logger = LogManager.getLogger(this.getClass());

  public AbstractController(OAuth2RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  protected <T> Optional<T> exchange(UriComponentsBuilder uriBuilder, HttpMethod method, @Nullable HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType) {
    try {
      var result = restTemplate.exchange(uriBuilder.toUriString(), method, requestEntity, responseType);
      return result.getStatusCode().isError() && !result.hasBody() ? Optional.empty() : Optional.of(result.getBody());
    } catch (RestClientException ex) {
      logger.error(ex.getMessage(), ex);
      return Optional.empty();
    }
  }
}
