package com.notificationappservice.contactservice.contactservice.controllers;

import com.notificationappservice.contactservice.contactservice.entities.ContactInfo;
import com.notificationappservice.contactservice.contactservice.exceptions.ResourceNotFoundException;
import com.notificationappservice.contactservice.contactservice.services.ContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * User: edward <br/>
 * Date: 11/25/18 8:39 AM <br/>
 */
@RestController
@RequestMapping("/contacts")
public class ContactInfoController {


    @Autowired
    private ContactInfoService contactInfoService;

    @GetMapping()
    public ResponseEntity<List<ContactInfo>> getContactInfos(){
        List<ContactInfo> contactInfos = contactInfoService.getContactInfos();
        return new ResponseEntity<>(contactInfos, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ContactInfo> getContactInfo(@PathVariable("id") Long contactInfoId){
        Optional<ContactInfo> contactInfoOptional = contactInfoService.getContactInfo(contactInfoId);
        if(!contactInfoOptional.isPresent()){
            throw new ResourceNotFoundException("ContactInfo with ID: "+contactInfoId+" not found!");
        }
        return new ResponseEntity<>(contactInfoOptional.get(), HttpStatus.OK);
    }

    @GetMapping("clients/{clientId}")
    public ResponseEntity<ContactInfo> getContactInfoByClientId(@PathVariable("clientId") Long clientId){
        Optional<ContactInfo> contactInfoOptional = contactInfoService.getContactInfoByClientId(clientId);
        return new ResponseEntity<>(contactInfoOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeContactInfo(@PathVariable("id") Long contactInfoId){
        contactInfoService.removeContactInfo(contactInfoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = {""},method = {RequestMethod.POST,RequestMethod.PUT})
    public ResponseEntity<ContactInfo> saveUpdateContactInfo(@RequestBody ContactInfo contactInfo){
        ContactInfo savedContactInfo = contactInfoService.saveContactInfo(contactInfo);
        return new ResponseEntity<>(savedContactInfo,HttpStatus.OK);
    }
}
