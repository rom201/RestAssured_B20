package pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;



@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Department {

    private  int department_id;
    private String department_name ;
    private int manager_id;
    private int location_id;



}
