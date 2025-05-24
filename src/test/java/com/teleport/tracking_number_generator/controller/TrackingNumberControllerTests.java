package com.teleport.tracking_number_generator.controller;

import com.teleport.tracking_number_generator.TrackingNumberGeneratorApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TrackingNumberControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testValidRequest_returnsTrackingNumber() throws Exception {
        String originCountryId = "MY";
        String destinationCountryId = "ID";
        String weight = "1.234";
        String createdAt = "2018-11-20T19:29:32+08:00";
        String customerId = "de619854-b59b-425e-9db4-943979e1bd49";
        String customerName = "RedBox Logistics";
        String customerSlug = "redbox-logistics";

        mockMvc.perform(get("/api/next-tracking-number")
                        .queryParam("originCountryId", originCountryId)
                        .queryParam("destinationCountryId", destinationCountryId)
                        .queryParam("weight", weight)
                        .queryParam("createdAt", createdAt)
                        .queryParam("customerId", customerId)
                        .queryParam("customerName", customerName)
                        .queryParam("customerSlug", customerSlug))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trackingNumber").value(org.hamcrest.Matchers.matchesRegex("^[A-Z0-9]{1,16}$")))
                .andExpect(jsonPath("$.createdAt").isNotEmpty());
    }
}
