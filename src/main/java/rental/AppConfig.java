package rental;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rental.address.AddressRepository;
import rental.contract.ContractRepository;
import rental.enumeration.CommonStatus;
import rental.mainTenant.MainTenant;
import rental.mainTenant.MainTenantRepository;
import rental.price.PriceRepository;
import rental.renter.RenterRepository;
import rental.scheduledPayment.ScheduledPaymentRepository;

@Configuration
public class AppConfig {

	/** Test sets */
	@Bean
	CommandLineRunner commandLineRunner(PriceRepository repPr,ScheduledPaymentRepository repPay,AddressRepository repAd, MainTenantRepository repMT, RenterRepository repRT, ContractRepository repCon) {
		return arg -> {
			
			//MainTenant mT = new MainTenant("Dupond", "lolo","lolo@gmail.com",CommonStatus.ACTIF);
			
			//repMT.save(mT);
			
		};
	}

}
