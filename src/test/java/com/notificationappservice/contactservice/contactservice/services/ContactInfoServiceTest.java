package com.notificationappservice.contactservice.contactservice.services;

import com.notificationappservice.contactservice.contactservice.entities.ContactInfo;
import com.notificationappservice.contactservice.contactservice.repositories.ContactInfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * User: edward <br/>
 * Date: 11/25/18 8:20 AM <br/>
 */
@RunWith(MockitoJUnitRunner.class)
public class ContactInfoServiceTest {


        @Mock
        private ContactInfoRepository contactInfoRepository;

        @InjectMocks
        private ContactInfoService contactInfoService;

        @Test
        public void getContactInfo_returns_a_list_of_all_contacts(){
            when(contactInfoRepository.findAll()).thenReturn(Arrays.asList(new ContactInfo()));

            List<ContactInfo> contactInfos = contactInfoService.getContactInfos();
            verify(contactInfoRepository).findAll();
            assertThat(contactInfos.size()).isEqualTo(1);
        }

        @Test
        public void getContactInfo_return_a_contact_when_called_with_id(){
            Long id = 10L;
            ContactInfo contactInfo = new ContactInfo();
            contactInfo.setId(id);

            when(contactInfoRepository.findById(id)).thenReturn(Optional.of(contactInfo));

            Optional<ContactInfo> aContactInfo = contactInfoService.getContactInfo(id);

            verify(contactInfoRepository).findById(id);
            assertThat(aContactInfo.get()).isNotNull();
            assertThat(aContactInfo.get()).isEqualTo(contactInfo);
        }

        @Test
        public void getContactInfoByClientId_return_a_contact_when_called_with_clientId(){
        Long clientId = 10L;
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setClientId(clientId);

        when(contactInfoRepository.findByClientId(clientId)).thenReturn(Optional.of(contactInfo));

        Optional<ContactInfo> aContactInfo = contactInfoService.getContactInfoByClientId(clientId);

        verify(contactInfoRepository).findByClientId(clientId);
        assertThat(aContactInfo.get()).isNotNull();
        assertThat(aContactInfo.get()).isEqualTo(contactInfo);
    }

        @Test
        public void saveContactInfo_add_and_return_a_new_ContactInfo(){
            Long id = 10L;
            ContactInfo contactInfo = new ContactInfo();
            contactInfo.setId(id);

            when(contactInfoRepository.save(contactInfo)).thenReturn(contactInfo);

            ContactInfo newContactInfo = contactInfoService.saveContactInfo(contactInfo);

            verify(contactInfoRepository).save(contactInfo);
            assertThat(newContactInfo).isEqualTo(contactInfo);
        }

        @Test
        public void removeContactInfo_deletes_a_contactInfo_when_called_with_contactInfoId(){
            Long contactInfoId = 1L;

            doNothing().when(contactInfoRepository).deleteById(contactInfoId);

            contactInfoService.removeContactInfo(contactInfoId);
            verify(contactInfoRepository).deleteById(contactInfoId);
        }

}
