package com.workshop.utility;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by mijo on 2016-05-19.
 */
public class Utility {


    /**
     * This method will load properties file
     *
     * @return
     */
    public static Properties loadProperties() {

        Properties properties = new Properties();
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("/workshop_rest.properties");
        if (in == null) {
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream("workshop_rest.properties");
        }
        try {
            properties.load(in);
            in.close();
        } catch (IOException e) {
            RuntimeException exception = new RuntimeException(e.getMessage());
            e.printStackTrace();
            throw exception;
        }
        return properties;
    }

    public static JsonObject convertFileToJSON(String fileName) {

        JsonObject jsonObject = null;
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());

            InputStream is = new FileInputStream(file);
            StringBuilder sb = new StringBuilder();
            Scanner scanner = new Scanner(is);
            while (scanner.hasNext()) {
                sb.append(scanner.next());
            }
            scanner.close();

            jsonObject = new JsonParser().parse(sb.toString()).getAsJsonObject();
            System.out.println(jsonObject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }

        return jsonObject;
    }


}
