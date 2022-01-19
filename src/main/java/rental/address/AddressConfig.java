package rental.address;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rental.enumeration.Country;
import rental.mainTenant.MainTenant;
import rental.mainTenant.MainTenantRepository;
import rental.renter.Renter;
import rental.renter.RenterRepository;

@Configuration
public class AddressConfig {

	/* Test set */ 
	@Bean
	CommandLineRunner commandLineRunner(AddressRepository repAd,MainTenantRepository repMT, RenterRepository repRT) {
		return arg -> {
			List<Address> newListAddress = new ArrayList<>();
			MainTenant mT = new MainTenant("fhfh");
			Renter rT = new Renter("hello");
			repRT.save(rT);
			repMT.save(mT);
		
			Address newAdress = new Address("test", Country.FRANCE, true, mT,rT);
			newListAddress.add(newAdress);
			repAd.saveAll(newListAddress);
		};
	}
}
