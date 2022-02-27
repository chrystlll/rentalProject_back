package rental.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ContractType {

	LOGEMENT("CODE_1"), 
	AUTRE_STOCKAGE("CODE_2"), 
	STOCKAGE_BATEAU("CODE_3"), 
	STOCKAGE_VOITURE("CODE_4"),
	STOCKAGE_CAMPING_CAR("CODE_5"),
	STOCKAGE_CARAVANE("CODE_6"),
	STOCKAGE_MOTO("CODE_7");

	private String code;
	
	private ContractType(String code) {
		this.code=code;
	}
	
	
	@JsonValue
	public String getCode() {
		return code;
	}
}
