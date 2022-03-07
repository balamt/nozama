package in.nozama.service.api.automation.util;

import in.nozama.service.api.automation.model.UserRequest;

import java.util.*;

public class NozamaApiAutomationUtil {

    public static final String NAME = "name";
    public static final String EMAIL = "email";

    public static UserRequest randomizeUserRequest(UserRequest request){
        Map<String, String> data = NozamaApiAutomationUtil.getRandomNameAndEmailId();
        request.setEmail(data.get(EMAIL));
        request.setFullname(data.get(NAME));
        return request;
    }

    public static Map<String, String> getRandomNameAndEmailId(){
        String name = randomizeNameString();
        String email = String.format("%s@%s", name, randomEmailDomain());
        Map<String, String> data = new HashMap();
        data.put(NAME, name);
        data.put(EMAIL, email);
        return data;
    }

    protected static String randomEmailDomain(){
        List<String> domain = new ArrayList<>();
        domain.add("test-gmail.com");
        domain.add("test-ymail.com");
        domain.add("test-yahoo.co.in");
        domain.add("test-outlook.in");
        domain.add("test-mailserver.co");
        Random random = new Random();

        int selectedDomainIndex = (int) (random.nextFloat() * domain.size());
        if(selectedDomainIndex < domain.size()) {
            return domain.get(selectedDomainIndex);
        }
        return domain.get(0);
    }

    protected static String randomizeNameString() {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 12) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

}
