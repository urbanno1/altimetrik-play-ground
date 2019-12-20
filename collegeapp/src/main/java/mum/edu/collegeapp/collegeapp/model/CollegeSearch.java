package mum.edu.collegeapp.collegeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollegeSearch {
    private String predominant;
    private Integer zip;
    private String distance;
    private Integer year;

    private Integer pageSize;

}