package in.nozama.service.api.automation.model;

import lombok.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest implements Serializable
{
    private String email;
    private String fullname;
    private String mobile;
    private String password;
    private String usertype;
    private String gender;
    private Address address;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 1L;
}