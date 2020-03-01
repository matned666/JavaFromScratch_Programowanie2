package pl.sda.rafal.zientara.programowanie2.lesson6;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class ParametersTest {

//    @ParameterizedTest(name = "Checking class {0}")
    @Test
    public void test() {
        try {
            Collection<Object[]> clazzes = clazzes();
            System.out.println(clazzes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Collection<Object[]> clazzes() throws IOException, JSONException {
        List<Object[]> testData = new ArrayList<>();
        String jsonString = toString(new File("test.json").toURL());
//        String jsonString = toString(ParametersTest.class.getResource("test.json"));
        if (jsonString != null) {
            JSONArray classesArray = new JSONArray(jsonString);
            for (int i = 0; i < classesArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) classesArray.get(i);
                String clazzName = jsonObject.getString("dupa");
                JSONObject methods = jsonObject.getJSONObject("methods").getJSONObject("main");
                testData.add(new Object[]{clazzName, jsonObject, methods});
            }
        }
        return testData;
    }

    private static String toString(URL resource) {
        StringBuilder builder = new StringBuilder();
        try {
            InputStream inputStream = resource.openStream();
            Scanner scanner = new Scanner(inputStream);
            String line = scanner.nextLine();
            while (line != null) {
                builder.append(line);
                System.out.println(line);
                try {
                    line = scanner.nextLine();
                } catch (Exception e) {
                    line = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
