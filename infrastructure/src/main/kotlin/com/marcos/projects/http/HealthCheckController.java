package com.marcos.projects.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

  @GetMapping("/health")
  public void healthCheck() {
    // Empty Method to Return Empty Body :D
  }

}