package mum.edu.collegeapp.collegeapp.model;

import java.util.Comparator;

public class CollegeComparator implements Comparator<College> {
    @Override
    public int compare(College o1, College o2) {
        return o1.getSize().compareTo(o2.getSize());
    }
}
