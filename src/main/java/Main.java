import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

public class Main {

    private static final String SOURCE_FILE = "C:\\Users\\dmitr\\IdeaProjects\\Homerwork10.2\\file.txt";
    private static final String DESTINATION_FILE = "C:\\Users\\dmitr\\IdeaProjects\\Homerwork10.2\\user.json";


    public static void main(String[] args) {
        List<User> users = readFile();
        writeToJsonFile(users);
    }
    public static List<User> readFile() {
        List<User> users = new ArrayList<>();
        File file = new File("file.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; 
                }

                String[] data = line.split(" ");
                String name = data[0];
                int age = Integer.parseInt(data[1]);

                User user = new User(name, age);
                users.add(user);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return users;
    }

    private static void checkIfFileExists(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void writeToJsonFile(List<User> users) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter fileWriter = new FileWriter("user.json")) {
            gson.toJson(users, fileWriter);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

}



