package com.notificationappservice.contactservice.contactservice.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notificationappservice.contactservice.contactservice.entities.ContactInfo;
import com.notificationappservice.contactservice.contactservice.services.ContactInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * User: edward <br/>
 * Date: 11/25/18 8:36 AM <br/>
 */

@RunWith(SpringRunner.class)
@WebMvcTest(ContactInfoController.class)
public class ContactInfoControllerTest {


    @MockBean
    private ContactInfoService contactInfoService;
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getContactInfos_returns_a_list_of_all_contactInfos() throws Exception {
        when(contactInfoService.getContactInfos()).thenReturn(Arrays.asList(new ContactInfo()));

        MvcResult mvcResult = getMvcResult("/contacts",status().isOk());
        List<ContactInfo> contactInfos = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(),
                new TypeReference<List<ContactInfo>>(){});

        verify(contactInfoService).getContactInfos();
        assertThat(contactInfos.size()).isEqualTo(1);
    }


    @Test
    public void getContactInfo_returns_a_ContactInfo_when_called_with_contactInfoId() throws Exception {
        Long contactInfoId = 11L;
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setId(contactInfoId);

        when(contactInfoService.getContactInfo(contactInfoId)).thenReturn(Optional.of(contactInfo));

        MvcResult mvcResult = getMvcResult("/contacts/"+contactInfoId,status().isOk());
        ContactInfo aContactInfo = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),ContactInfo.class);

        verify(contactInfoService).getContactInfo(contactInfoId);
        assertThat(aContactInfo).isEqualTo(contactInfo);
    }

    @Test
    public void getContactInfoByClientId_returns_a_ContactInfo_when_called_with_contactInfoId() throws Exception {
        Long clientId = 11L;
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setClientId(clientId);

        when(contactInfoService.getContactInfoByClientId(clientId)).thenReturn(Optional.of(contactInfo));

        MvcResult mvcResult = getMvcResult("/contacts/clients/"+clientId,status().isOk());
        ContactInfo aContactInfo = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),ContactInfo.class);

        verify(contactInfoService).getContactInfoByClientId(clientId);
        assertThat(aContactInfo).isEqualTo(contactInfo);
    }

    @Test
    public void getContactInfo_returns_ErrorMessage_when_called_with_wrongContactInfoId() throws Exception {
        Long wrongContactInfoId = 11L;
        when(contactInfoService.getContactInfo(wrongContactInfoId)).thenReturn(Optional.empty());

        MvcResult mvcResult = getMvcResult("/contacts/"+wrongContactInfoId,status().isNoContent());
        verify(contactInfoService).getContactInfo(wrongContactInfoId);
    }

    @Test
    public void deleteContactInfo_remove_aContactInfo_when_call_with_contactInfoId() throws Exception {

        Long id = 10L;
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/contacts/"+id)
                .accept(MediaType.APPLICATION_JSON);

        doNothing().when(contactInfoService).removeContactInfo(id);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
        verify(contactInfoService).removeContactInfo(id);
    }

    @Test
    public void saveContactInfo_add_and_returns_a_new_ContactInfo() throws Exception {

        Long contactInfoId = 11L;
        MockHttpServletRequestBuilder mockRequestBuilder = MockMvcRequestBuilders.put("/contacts");
        putPostTest(mockRequestBuilder,contactInfoId);
    }
    @Test
    public void updateConatctInfo_updates_and_returns_a_new_ContactInfo() throws Exception {

        Long contactInfoId = 11L;
        MockHttpServletRequestBuilder mockRequestBuilder = MockMvcRequestBuilders.post("/contacts");
        putPostTest(mockRequestBuilder,contactInfoId);
    }


    private void putPostTest(MockHttpServletRequestBuilder mockRequestBuilder, Long contactInfoId) throws Exception {

        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setId(contactInfoId);
        when(contactInfoService.saveContactInfo(contactInfo)).thenReturn(contactInfo);

        RequestBuilder requestBuilder = mockRequestBuilder
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(contactInfo))
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult =  mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        ContactInfo aContactInfo = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),ContactInfo.class);

        verify(contactInfoService).saveContactInfo(contactInfo);
        assertThat(aContactInfo).isEqualTo(contactInfo);
    }


    private MvcResult getMvcResult(String url, ResultMatcher status) throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON);
        return mockMvc.perform(requestBuilder)
                .andExpect(status)
                .andReturn();
    }

}
