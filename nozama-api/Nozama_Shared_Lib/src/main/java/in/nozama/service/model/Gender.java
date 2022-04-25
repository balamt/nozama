package in.nozama.service.model;

public enum Gender {
	
	NOT_DISCLOSED("N","NOT_DISCLOSED"),
	GENERAL("G","GENERAL"),
	MALE("M","MALE"),
	FEMALE("F","FEMALE");
	
	private String code;
	private String description;

	Gender(String code, String description){
		this.code = code;
		this.description =  description;
	}
	
	public String code() {
		return this.code;
	}
	
	public String description() {
		return this.description;
	}
	
}
