package rental.price;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceService {

	private final PriceRepository priceRepository;

	@Autowired
	public PriceService(PriceRepository priceRepositoryRepository) {
		this.priceRepository = priceRepositoryRepository;
	}

	public List<Price> getPrice() {
		return priceRepository.findAll();
	}

}
