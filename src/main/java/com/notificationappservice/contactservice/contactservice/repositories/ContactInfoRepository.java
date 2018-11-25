package com.notificationappservice.contactservice.contactservice.repositories;

import com.notificationappservice.contactservice.contactservice.entities.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * User: edward <br/>
 * Date: 11/25/18 8:16 AM <br/>
 */
@Repository
@Transactional
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {
    Optional<ContactInfo> findByClientId(Long clientID);
}
