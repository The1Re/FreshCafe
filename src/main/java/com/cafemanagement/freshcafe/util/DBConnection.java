package com.cafemanagement.freshcafe.util;

import com.cafemanagement.freshcafe.model.Product;
import com.cafemanagement.freshcafe.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DBConnection {
    private static final File userdb = new File(GlobalVar.RESOURCE_PATH + "database/userdb.txt");
    private static final File productdb = new File(GlobalVar.RESOURCE_PATH + "database/productdb.txt");
    public static void updateUser(List<User> data, boolean append) throws IOException {
        Writer writer = new FileWriter(userdb, append);
        for (User obj : data){
            writer.write(String.format("%s$%s$%s\n",
                    obj.getUsername(),
                    obj.getPassword(),
                    obj.getEmail()));
        }
        writer.close();
    }

    public static void updateUser(List<User> data) throws IOException {
        updateUser(data, false);
    }

    public static ArrayList<User> getUserData() throws IOException {
        ArrayList<User> data = new ArrayList<>();
        Reader reader = new FileReader(userdb);
        BufferedReader bf = new BufferedReader(reader);
        String line;

        while ((line = bf.readLine()) != null){
            String[] list = line.split("\\$");
            data.add(new User(
               list[0],
               list[1],
               list[2]
            ));
        }

        reader.close();
        bf.close();
        return data;
    }

    public static void updateProduct(List<Product> data, Boolean append) throws IOException {
        Writer writer = new FileWriter(productdb, append);
        for (Product obj : data){
            writer.write(String.format("%s$%s$%.2f$%d$%s$%s\n",
                    obj.getId(),
                    obj.getName(),
                    obj.getPrice(),
                    obj.getQuantity(),
                    obj.getCategory(),
                    obj.getStatus()
            ));
        }
        writer.close();
    }

    public static void updateProduct(List<Product> data) throws IOException {
        updateProduct(data, false);
    }

    public static void updateProduct(Product... value) throws IOException {
        ArrayList<Product> data = new ArrayList<>(List.of(value));
        updateProduct(data, true);
    }

    public static ArrayList<Product> getProductData() throws IOException{
        ArrayList<Product> data = new ArrayList<>();
        Reader reader = new FileReader(productdb);
        BufferedReader bf = new BufferedReader(reader);
        String line;

        while ((line = bf.readLine()) != null){
            String[] list = line.split("\\$");
            data.add(new Product(
                    list[0],
                    list[1],
                    Double.parseDouble(list[2]),
                    Integer.parseInt(list[3]),
                    list[4],
                    list[5]
            ));
        }

        reader.close();
        bf.close();
        return data;
    }

    public static void printObject(List<Object> data){
        for (Object d : data){
            System.out.println(d);
        }
    }
}
