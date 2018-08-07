package com.healthcare.provider.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.Matchers.*;
import org.junit.runners.MethodSorters;

import com.healthcare.provider.Application;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProviderControllerTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	
	@Test
	public void index() throws Exception {
		this.mockMvc.perform(get("/@index"));
	}
	
	@Test
	public void retrieveDataWithAllFields() throws Exception {

		this.mockMvc.perform(get("/providers"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(greaterThan(0))))
		.andExpect(jsonPath("$[0]").value(hasKey("providerName")))
		.andExpect(jsonPath("$[0]").value(hasKey("providerStreetAddress")))
		.andExpect(jsonPath("$[0]").value(hasKey("providerCity")))
		.andExpect(jsonPath("$[0]").value(hasKey("providerState")))
		.andExpect(jsonPath("$[0]").value(hasKey("providerZipCode")))
		.andExpect(jsonPath("$[0]").value(hasKey("hospitalReferralRegionDescription")))
		.andExpect(jsonPath("$[0]").value(hasKey("totalDischarges")))
		.andExpect(jsonPath("$[0]").value(hasKey("averageCoveredCharges")))
		.andExpect(jsonPath("$[0]").value(hasKey("averageMedicarePayments")));

	}

	@Test
	public void retrieveDataWithStateAndMinAndMaxDischarges() throws Exception {

		this.mockMvc.perform(get("/providers").
				param("state", "AL").
				param("max_discharges","40").
				param("min_discharges","30").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$..providerState",hasItems("AL")))
		.andExpect(jsonPath("$..totalDischarges",hasItems(allOf(greaterThanOrEqualTo(30),lessThanOrEqualTo(40)))));

	}
	
	@Test
	public void retrieveDataWithAverageCoveredAndMedicarePaymentcharges() throws Exception {

		this.mockMvc.perform(get("/providers").
				param("max_average_covered_charges", "7777").
				param("min_average_covered_charges","1111").
				param("max_average_medicare_payments","5555").
				param("min_average_medicare_payments","2222").
				contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$..averageCoveredCharges",hasItems(allOf(greaterThanOrEqualTo(1111.00d),lessThanOrEqualTo(6666.00d)))))
		.andExpect(jsonPath("$..averageMedicarePayments",hasItems(allOf(greaterThanOrEqualTo(2222.00d),lessThanOrEqualTo(5555.00d)))));

	}
	
	
	@Test
	public void retrieveDataWithAllFilter() throws Exception {

		this.mockMvc.perform(get("/providers").
				param("state", "FL").
				param("max_discharges","29").
				param("min_discharges","20").
				param("max_average_covered_charges", "39643.55").
				param("min_average_covered_charges","31148.91").
				param("max_average_medicare_payments","4854.17").
				param("min_average_medicare_payments","4701.7").
				contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$..providerState",hasItems("FL")))
		.andExpect(jsonPath("$..totalDischarges",hasItems(allOf(greaterThanOrEqualTo(20),lessThanOrEqualTo(29)))))
		.andExpect(jsonPath("$..averageCoveredCharges",hasItems(allOf(greaterThanOrEqualTo(31148.91d),lessThanOrEqualTo(39643.55d)))))
		.andExpect(jsonPath("$..averageMedicarePayments",hasItems(allOf(greaterThanOrEqualTo(4701.7d),lessThanOrEqualTo(4854.17d)))));
	
	}

	@Test
	public void retrieveDataWithSelectedFields() throws Exception {

		this.mockMvc.perform(get("/providers").contentType(MediaType.APPLICATION_JSON).
				param("fields", "providerState").
				param("fields", "providerStreetAddress").
				param("fields", "providerCity").
				param("fields", "providerState").
				param("fields", "providerZipCode"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(greaterThan(0))))
		.andExpect(jsonPath("$[0].providerState").exists())
		.andExpect(jsonPath("$[0].providerStreetAddress").exists())
		.andExpect(jsonPath("$[0].providerCity").exists())
		.andExpect(jsonPath("$[0].providerState").exists())
		.andExpect(jsonPath("$[0].providerZipCode").exists())
		.andExpect(jsonPath("$[0].hospitalReferralRegionDescription").doesNotExist())
		.andExpect(jsonPath("$[0].totalDischarges").doesNotExist())
		.andExpect(jsonPath("$[0].averageCoveredCharges").doesNotExist())
		.andExpect(jsonPath("$[0].averageTotalPayments").doesNotExist())
		.andExpect(jsonPath("$[0].averageMedicarePayments").doesNotExist())
		.andExpect(jsonPath("$[0].providerId").doesNotExist())
		.andExpect(jsonPath("$[0].id").doesNotExist());

	}
}
