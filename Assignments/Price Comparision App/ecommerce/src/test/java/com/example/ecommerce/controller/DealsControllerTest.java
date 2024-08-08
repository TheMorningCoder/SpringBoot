package com.example.ecommerce.controller;

import com.example.ecommerce.model.DealItem;
import com.example.ecommerce.model.ResponseWrapper;
import com.example.ecommerce.service.DealsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DealsControllerTest {

	//@Mock annotation is used to create a mock object of the DealsService class
    @Mock
    private DealsService dealsService;

    //@InjectMocks annotation is used to inject the mock DealsService into the DealsController.
    @InjectMocks
    private DealsController dealsController;

    //MockMvc is a Spring class used for testing the web layer of Spring applications.
    private MockMvc mockMvc;

    /*
     The setUp method is annotated with @BeforeEach, meaning it will run before each test method.
	 MockitoAnnotations.openMocks(this) initializes the mock objects.
	 mockMvc = MockMvcBuilders.standaloneSetup(dealsController).build(); 
	 sets up the MockMvc instance with the dealsController.
     
     */
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(dealsController).build();
    }

    @Test
    public void testGetDeals_Success() throws Exception {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setCategoryName("electronics");
        responseWrapper.setDealItems(Arrays.asList(
                new DealItem("1", "Laptop", "15 inches", "BrandA", "imageUrl", 1000.0, 10.0, 900.0, 10, "2024-01-01", "2024-01-10"),
                new DealItem("2", "Phone", "6 inches", "BrandB", "imageUrl", 500.0, 5.0, 475.0, 5, "2024-01-01", "2024-01-10")
        ));

        when(dealsService.getDeals("electronics")).thenReturn(responseWrapper);

        mockMvc.perform(get("/deals/electronics")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json(
                	    "{"
                	            + "\"categoryName\":\"electronics\","
                	            + "\"dealItems\":["
                	                + "{"
                	                    + "\"itemid\":\"1\","
                	                    + "\"productTitle\":\"Laptop\","
                	                    + "\"size\":\"15 inches\","
                	                    + "\"brand\":\"BrandA\","
                	                    + "\"imageUrl\":\"imageUrl\","
                	                    + "\"originalPrice\":1000.0,"
                	                    + "\"discountPercentage\":10.0,"
                	                    + "\"price\":900.0,"
                	                    + "\"stock\":10,"
                	                    + "\"dealStartDate\":\"2024-01-01\","
                	                    + "\"dealEndDate\":\"2024-01-10\""
                	                + "},"
                	                + "{"
                	                    + "\"itemid\":\"2\","
                	                    + "\"productTitle\":\"Phone\","
                	                    + "\"size\":\"6 inches\","
                	                    + "\"brand\":\"BrandB\","
                	                    + "\"imageUrl\":\"imageUrl\","
                	                    + "\"originalPrice\":500.0,"
                	                    + "\"discountPercentage\":5.0,"
                	                    + "\"price\":475.0,"
                	                    + "\"stock\":5,"
                	                    + "\"dealStartDate\":\"2024-01-01\","
                	                    + "\"dealEndDate\":\"2024-01-10\""
                	                + "}"
                	            + "]"
                	        + "}"
                	    ));

                //.andExpect(content().json("{\"categoryName\":\"electronics\",\"dealItems\":[{\"itemid\":\"1\",\"productTitle\":\"Laptop\",\"size\":\"15 inches\",\"brand\":\"BrandA\",\"imageUrl\":\"imageUrl\",\"originalPrice\":1000.0,\"discountPercentage\":10.0,\"price\":900.0,\"stock\":10,\"dealStartDate\":\"2024-01-01\",\"dealEndDate\":\"2024-01-10\"},{\"itemid\":\"2\",\"productTitle\":\"Phone\",\"size\":\"6 inches\",\"brand\":\"BrandB\",\"imageUrl\":\"imageUrl\",\"originalPrice\":500.0,\"discountPercentage\":5.0,\"price\":475.0,\"stock\":5,\"dealStartDate\":\"2024-01-01\",\"dealEndDate\":\"2024-01-10\"}]}"));
    }

    @Test
    public void testGetDeals_EmptyDeals() throws Exception {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setCategoryName("clothing");
        responseWrapper.setDealItems(Arrays.asList());

        when(dealsService.getDeals("clothing")).thenReturn(responseWrapper);

        mockMvc.perform(get("/deals/clothing")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"categoryName\":\"clothing\",\"dealItems\":[]}"));
    }

    @Test
    public void testGetDeals_NoCategoryName() throws Exception {
        // This is an invalid URL as the controller does not handle it
        mockMvc.perform(get("/deals/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
