package com.notificationappservice.contactservice.contactservice.entities;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * User: edward <br/>
 * Date: 11/25/18 7:52 AM <br/>
 */
public class ConatactInfoTest {

    private ContactInfo conatactInfo;

    private Long id = 11L;
    private String name = "Edward";
    private Long clientId = 12L;
    private String phoneNumber = "+123777777777";
    private String email = "example@example.com";
    private Date createdAt = new Date();
    private Date updatedAt = new Date();

    @Before
    public void setUp(){
        conatactInfo = new ContactInfo();
    }

    @Test
    public void test_getters_setters(){
        conatactInfo.setId(id);
        conatactInfo.setName(name);
        conatactInfo.setClientId(clientId);
        conatactInfo.setPhoneNumber(phoneNumber);
        conatactInfo.setEmail(email);
        conatactInfo.setCreatedAt(createdAt);
        conatactInfo.setUpdatedAt(updatedAt);

        assertThat(conatactInfo.getId()).isEqualTo(id);
        assertThat(conatactInfo.getName()).isEqualTo(name);
        assertThat(conatactInfo.getClientId()).isEqualTo(clientId);
        assertThat(conatactInfo.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(conatactInfo.getEmail()).isEqualTo(email);
        assertThat(conatactInfo.getCreatedAt()).isEqualTo(createdAt);
        assertThat(conatactInfo.getUpdatedAt()).isEqualTo(updatedAt);
    }
}
