package com.notificationappservice.contactservice.contactservice.repositories;

import com.notificationappservice.contactservice.contactservice.entities.ContactInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * User: edward <br/>
 * Date: 11/25/18 8:09 AM <br/>
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class ContactInfoRepositoryTest {

    @Autowired
    ContactInfoRepository contactInfoRepository;

    @Test
    public void findByCLientId_return_a_ContactInfo_when_called_with_clientId(){
        Long id = 1L;
        Long clientId = 11L;

        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setId(1L);
        contactInfo.setClientId(clientId);

        ContactInfo savedContactInfo = contactInfoRepository.save(contactInfo);

        assertThat(savedContactInfo).isEqualToComparingFieldByField(contactInfo);
    }
}
