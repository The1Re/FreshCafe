package com.cafemanagement.freshcafe.util;

import com.cafemanagement.freshcafe.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private static final File userdb = new File("src/main/resources/com/cafemanagement/freshcafe/database/userdb.txt");
    public static void updateUser(List<User> data) throws IOException {
        Writer writer = new FileWriter(userdb);
        for (User obj : data){
            writer.write(String.format("%s$%s$%s\n",
                    obj.getUsername(),
                    obj.getPassword(),
                    obj.getEmail()));
        }
        writer.close();
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


}
