package mum.edu.collegeapp.collegeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class College{
    private Integer id;
    private String name;
    private Integer size;
    private String message;

    public College(String errorMsg) {
        this.message = errorMsg;
    }

    public College(Integer id, String name, Integer size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }
}
