package in.nozama.service.model;

public enum Category {

	FOOD("FOOD"), TOY("TOY"), FURNITURE("FURNITURE"), OTHERS("OTHERS");

	private String code;

	Category(String code) {
		this.code = code;
	}

	public String code() {
		return this.code;
	}

}
