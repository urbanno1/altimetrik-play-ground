package mum.edu.collegeapp.collegeapp.controller;

import mum.edu.collegeapp.collegeapp.model.College;
import mum.edu.collegeapp.collegeapp.model.CollegeSearch;
import mum.edu.collegeapp.collegeapp.service.CollegeService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.*;

@RestController
@RequestMapping("/api/college")
public class CollegeController {
    private static  final Log log = LogFactory.getLog(CollegeController.class);
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private CollegeService collegeService;


    @PostMapping("/search")
    public ResponseEntity<List<College>> searchCollege(@RequestBody CollegeSearch search) {
        try {
            //This is the url merged with the request coming in to the system;
            String url = collegeService.getUrl(search);
            //Here I get the response entity as an Object value
            ResponseEntity responseEntity = restTemplate.getForEntity(url, Object.class);
            //Here I get the body of the responseEntity;
            LinkedHashMap map = (LinkedHashMap)responseEntity.getBody();

            return new ResponseEntity(collegeService.getListOfCollege(map, search.getYear()), HttpStatus.OK);
        } catch (Exception e) {
            log.warn(e);
            return new ResponseEntity(new College("The request returned no value"), HttpStatus.BAD_REQUEST);
        }
    }


    //This resolves cross origin
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
}
