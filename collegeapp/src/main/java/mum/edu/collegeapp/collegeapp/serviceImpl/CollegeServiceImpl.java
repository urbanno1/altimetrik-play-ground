package mum.edu.collegeapp.collegeapp.serviceImpl;

import mum.edu.collegeapp.collegeapp.model.College;
import mum.edu.collegeapp.collegeapp.model.CollegeComparator;
import mum.edu.collegeapp.collegeapp.model.CollegeSearch;
import mum.edu.collegeapp.collegeapp.service.CollegeService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CollegeServiceImpl implements CollegeService {


    @Override
    public List<College> getListOfCollege(LinkedHashMap map, Integer year) {
        List<College> colleges = new ArrayList<>();
        College college = null;
        List<LinkedHashMap> col = (List<LinkedHashMap>) map.get("results");

        for(LinkedHashMap y: col) {
            Integer id = (Integer) y.get("id");
            String name = (String)y.get("school.name");
            Integer size = (Integer) y.get(year+".student.size");

            college = new College(id, name, size);
            colleges.add(college);
        }
        Collections.sort(colleges, new CollegeComparator());
        return colleges;
    }

    @Override
    public String getUrl(CollegeSearch search) {
        if(search.getPageSize() == null || search.getPageSize() <= 0) search.setPageSize(5);
        return "https://api.data.gov/ed/collegescorecard/v1/schools.json?school.degrees_awarded.predominant="
                + search.getPredominant() + "&fields=id,school.name," + search.getYear() +".student.size&per_page="
                + search.getPageSize() + "&zip=" + search.getZip() + "&distance="
                + search.getDistance() + "&api_key=tZAYe0gMs3L7lvcEy8Juhio5M6k7apCTo77gTjYE";
    }
}
