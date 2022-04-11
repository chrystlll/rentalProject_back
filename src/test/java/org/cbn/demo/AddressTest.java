package org.cbn.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import rental.RentalApplication;
import rental.address.Address;
import rental.address.AddressRepository;
import rental.address.AddressService;
import rental.enumeration.Country;

@SpringBootTest(classes = RentalApplication.class)
class AddressTest {
	@Autowired
	AddressService addServ;

	@MockBean
	private AddressRepository addRepository;

	@Test
	void insertAddress() {
		Address add = new Address("1 rue de test", Country.FRANCE, true);
		// CALL service
		ResponseEntity<Address> response = addServ.addAddress(add);
		assertEquals(response.getBody().getCountry(), add.getCountry());
	}

}
