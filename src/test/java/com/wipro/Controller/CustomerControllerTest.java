//package com.wipro.Controller;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.wipro.wipromart.controller.CustomerController;
//import com.wipro.wipromart.service.CustomerService;
//
//@WebMvcTest(CustomerController.class)
//public class CustomerControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    
//    @MockBean
//    private CustomerService customerService;
//
//    @Test
//    public void testSaveCustomer() throws Exception {
//        String customerJson = """
//            {
//                "firstName": "John",
//                "lastName": "Doe",
//                "email": "john.doe@example.com",
//                "mobile": "1234567890",
//                "city": "New York"
//            }
//        """;
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/customer/save")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(customerJson))
//                .andExpect(status().isCreated());
//    }
//}
