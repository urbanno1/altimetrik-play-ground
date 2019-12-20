package mum.edu.collegeapp.collegeapp.controller;

import mum.edu.collegeapp.collegeapp.model.College;
import mum.edu.collegeapp.collegeapp.model.CollegeSearch;
import mum.edu.collegeapp.collegeapp.service.CollegeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class CollegeControllerTest {

    @InjectMocks
    CollegeController collegeController;
    @Mock
    RestTemplate restTemplate;
    @Mock
    CollegeService collegeService;

    @Test
    public  void searchCollege() {

        CollegeSearch search = new CollegeSearch("2,3", 52557, "100mi", 2017, 5);
        String url = collegeService.getUrl(search);

        College college = new College(138761, "Andrew College", 265);
        College college2 = new College(138901, "Atlanta Metropolitan State College", 2238);
        College college3 = new College(139311, "Clayton  State University", 5754);
        College college4 = new College(139861, "Georgia College & State University", 17062);

        List<College> colleges = new ArrayList<>();
        colleges.addAll(Arrays.asList(college, college2, college3, college4));
        LinkedHashMap map = new LinkedHashMap();

        Mockito.when(collegeService.getUrl(search)).thenReturn(url);
        Mockito.when(restTemplate.getForEntity(url, Object.class)).thenReturn(new ResponseEntity(map, HttpStatus.OK));
        Mockito.when(collegeService.getListOfCollege(map, search.getYear())).thenReturn(colleges);

        assertEquals(collegeController.searchCollege(search), new ResponseEntity(colleges, HttpStatus.OK));
    }



}
