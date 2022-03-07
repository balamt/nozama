package in.nozama.service.api.automation.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.nozama.service.api.automation.model.UserRequest;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class LoadJsonToObject {

    public static File openResourceFileFrom(String fileName) throws URISyntaxException {
        return new File(
                LoadJsonToObject.class.getClassLoader().getResource(fileName).toURI()
        );
    }

    public static Object getObject(File file, Class instance) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(file, instance);
    }

    public static UserRequest getUserRequestFrom(String filename) throws URISyntaxException, IOException{
        return (UserRequest) LoadJsonToObject.getObject(LoadJsonToObject.openResourceFileFrom(filename), UserRequest.class);
    }
}
