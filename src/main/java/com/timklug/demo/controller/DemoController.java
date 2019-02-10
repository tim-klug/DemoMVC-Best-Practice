package com.timklug.demo.controller;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class DemoController extends AbstractController {

  protected DemoController(OAuth2RestTemplate restTemplate) {
    super(restTemplate);
  }
}
