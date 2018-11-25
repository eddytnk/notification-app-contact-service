package com.notificationappservice.contactservice.contactservice.services;

import com.notificationappservice.contactservice.contactservice.entities.ContactInfo;
import com.notificationappservice.contactservice.contactservice.repositories.ContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * User: edward <br/>
 * Date: 11/25/18 8:22 AM <br/>
 */
@Service
public class ContactInfoService {

    @Autowired
    private ContactInfoRepository contactInfoRepository;

    public List<ContactInfo> getContactInfos() {
        return contactInfoRepository.findAll();
    }

    public Optional<ContactInfo> getContactInfo(Long id) {
        return contactInfoRepository.findById(id);
    }

    public ContactInfo saveContactInfo(ContactInfo contactInfo) {
        return contactInfoRepository.save(contactInfo);
    }

    public void removeContactInfo(Long contactInfoId) {
        contactInfoRepository.deleteById(contactInfoId);
    }

    public Optional<ContactInfo> getContactInfoByClientId(Long clientId) {
        return contactInfoRepository.findByClientId(clientId);
    }
}
