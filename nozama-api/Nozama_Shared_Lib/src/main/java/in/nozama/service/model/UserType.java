package in.nozama.service.model;

public enum UserType {

	BASIC("BASIC"),
	PRIME("PRIME"), 
	THIRD_PARTY("THIRD_PARTY"),
	BOT("BOT"),
	ADMIN("ADMIN");

	private String code;

	UserType(String code) {
		this.code = code;
	}

	public String code() {
		return this.code;
	}

}
