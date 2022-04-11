package rental;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rental.address.Address;
import rental.address.AddressRepository;
import rental.contract.Contract;
import rental.contract.ContractRepository;
import rental.enumeration.CommonStatus;
import rental.enumeration.ContractType;
import rental.enumeration.Country;
import rental.enumeration.Gender;
import rental.enumeration.PaymentType;
import rental.mainTenant.MainTenant;
import rental.mainTenant.MainTenantRepository;
import rental.price.PriceRepository;
import rental.renter.Renter;
import rental.renter.RenterRepository;
import rental.scheduledPayment.ScheduledPayment;
import rental.scheduledPayment.ScheduledPaymentRepository;

@Configuration
public class AppConfig {

	/** Test sets */
	@Bean
	CommandLineRunner commandLineRunner(PriceRepository repPr,ScheduledPaymentRepository repPay,AddressRepository repAd, MainTenantRepository repMT, RenterRepository repRT, ContractRepository repCon) {
		return arg -> {
			List<Address> newListAddress = new ArrayList<>();

			MainTenant mT = new MainTenant("Dupond", "Eddy", Gender.M, "Eddy@gmail.com");
					
			
			Renter rT = new Renter("Pierre", "Dupond", Gender.M,"test@tes.com","26655555","665446546");
			repRT.save(rT);
			repMT.save(mT);
			
			Contract contr = new Contract(mT,ContractType.STOCKAGE_MOTO, CommonStatus.ACTIF);
			Contract contr2 =new Contract(new Date(),new Date(),mT, ContractType.STOCKAGE_BATEAU,CommonStatus.ACTIF);
			repCon.save(contr);
			repCon.save(contr2);
			
			ScheduledPayment payment = new ScheduledPayment(new Date(), PaymentType.ESPECE,contr);
			
			repPay.save(payment);
			
			
			
			for (int i = 0; i < 15; i++) {
				MainTenant mTtest = new MainTenant("Nom N°" + i, "Prénom N°" + i, Gender.M, "email" + i + "@gmail.com");
				repMT.save(mTtest);
			}
				
			Address newAdress = new Address("2 rue Paul Bert", "", "56100", "LORIENT", Country.FRANCE, true, mT);

			for (int i = 0; i < 5; i++) {
				Address adtest = new Address("Adresse N°" + i, "", "29100", "DOUARNENEZ", Country.FRANCE, true, mT);
				repAd.save(adtest);
			}
			Address mTadress = new Address("2 rue Dupond","", "75100", "PARIS", Country.FRANCE, true, rT);
			repAd.save(mTadress);

			newListAddress.add(newAdress);
			repAd.saveAll(newListAddress);
		};
	}

}
