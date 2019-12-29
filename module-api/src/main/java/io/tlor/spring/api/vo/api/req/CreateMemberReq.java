package io.tlor.spring.api.vo.api.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberReq {

    private String name;
    private String city;
    private String street;
    private String zipcode;

}
