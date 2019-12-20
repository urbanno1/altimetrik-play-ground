package mum.edu.collegeapp.collegeapp.service;

import mum.edu.collegeapp.collegeapp.model.College;
import mum.edu.collegeapp.collegeapp.model.CollegeSearch;

import java.util.LinkedHashMap;
import java.util.List;

public interface CollegeService {
    public List<College> getListOfCollege(LinkedHashMap map, Integer year);
    public String getUrl(CollegeSearch search);
}
