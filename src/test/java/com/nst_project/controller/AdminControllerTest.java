/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nst_project.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nst_project.project.dtos.AdminDto;
import com.nst_project.project.exception.AdminException;
import com.nst_project.project.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author nodas
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=AdminController.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private AdminService adminService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private AdminDto adminDto;
    
     @BeforeEach
    public void setUp() {
        adminDto = new AdminDto();
        adminDto.setAdminID(1);
        adminDto.setName("Admin 1");
        adminDto.setUsername("admin 1");
        adminDto.setPassword("admin 1");
    }
    
    @Test
    public void customerLoginFail() throws Exception {
        when(adminService.login(adminDto)).thenThrow(AdminException.class);
        mockMvc.perform(post("/admin/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(adminDto)))
                .andExpect(status().isBadRequest());
    }


}
