package org.example;

// GSON, Jackson

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

class Address {
    String city;
    String street;

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}

class Person {
    @SerializedName(value = "personName")
    String name;

    int age;
    String[] phones;
    Address address;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phones=" + Arrays.toString(phones) +
                ", address=" + address +
                '}';
    }
}

public class App {
    static String JSON_TEXT = """
                            {
                                "personName": "Vsevolod",
                                "age": 35,
                                "phones": ["0501111111", "0957777777"],
                                "address" : {
                                    "city": "Kyiv",
                                    "street": "xxxxx"
                                }
                            }
            """;


    public static void main( String[] args ) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Person person = gson.fromJson(JSON_TEXT, Person.class);
        System.out.println(person);

        String json = gson.toJson(person);
        System.out.println(json);
    }
}
