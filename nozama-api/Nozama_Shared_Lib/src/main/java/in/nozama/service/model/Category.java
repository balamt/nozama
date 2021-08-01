package in.nozama.service.model;

public enum Category {

	APPS("APPS"),
	BABY("BABY"),
	BEAUTY("BEAUTY"),
	CAR("CAR"),
	CLOTHING("CLOTHING"),
	COMPUTER("COMPUTER"),
	ELECTRONICS("ELECTRONICS"),
	FOOD("FOOD"),
	FURNITURE("FURNITURE"),
	GAMES("GAMES"),
	GROCERIES("GROCERIES"),
	HOME_APPLIANCES("HOME APPLIANCES"),
	JEWELLERY("JEWELLERY"),
	MOBILE("MOBILE"),
	MOTOR_BIKE("MOTOR BIKE"),
	MOVIES("MOVIES"),
	OTHERS("OTHERS"),
	SOFTWARE("SOFTWARE"),
	TOY("TOY"),
	WATCHES("WATCHES");


	private final String code;

	Category(String code) {
		this.code = code;
	}

	public String code() {
		return this.code;
	}

	public static Category getCategory(String code) {
		String catId = code.replace('_',' ');
		for (Category category : values()) {
			if (category.code.equals(catId)) {
				return category;
			}
		}
		return null;
	}

}
