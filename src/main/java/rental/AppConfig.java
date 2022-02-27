package rental;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rental.address.Address;
import rental.address.AddressRepository;
import rental.enumeration.Country;
import rental.enumeration.Gender;
import rental.mainTenant.MainTenant;
import rental.mainTenant.MainTenantRepository;
import rental.renter.Renter;
import rental.renter.RenterRepository;

@Configuration
public class AppConfig {

	/** Test sets */ 
	@Bean
	CommandLineRunner commandLineRunner(AddressRepository repAd,MainTenantRepository repMT, RenterRepository repRT) {
		return arg -> {
			List<Address> newListAddress = new ArrayList<>();
			
			MainTenant mT = new MainTenant("Toto",Gender.M,"tenant@gmail.com");
			
			for (int i = 0; i < 25; i++) {
				MainTenant mTtest = new MainTenant("Test "+i,Gender.M,"email" +i+"@gmail.com");
				repMT.save(mTtest);
			}
					
			Renter rT = new Renter("My renter",Gender.M);
			repRT.save(rT);
			repMT.save(mT);
		
			Address newAdress = new Address("Adresse 123", Country.FRANCE, true);
			newListAddress.add(newAdress);
			repAd.saveAll(newListAddress);
		};
	}
	
	
}
