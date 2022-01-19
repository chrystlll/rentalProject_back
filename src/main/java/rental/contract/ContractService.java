package rental.contract;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService {

	private final ContractRepository contractRepository;

	@Autowired
	public ContractService(ContractRepository contractRepository) {
		super();
		this.contractRepository = contractRepository;
	}

	public List<Contract> getContract() {
		return contractRepository.findAll();
	}

}
