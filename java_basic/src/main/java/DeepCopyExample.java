import com.google.gson.Gson;

import java.io.*;
public class DeepCopyExample {
    public static void main(String[] args) {
        // Tạo một đối tượng Person với đối tượng Address
        Address address = new Address("123 Main St", "City");
        Person originalPerson = new Person("John", 30, address);

        // Khởi tạo Gson
        Gson gson = new Gson();

        // Chuyển đổi đối tượng Person thành JSON
        String personJson = gson.toJson(originalPerson);

        // Chuyển JSON thành đối tượng Person
        Person copiedPerson = gson.fromJson(personJson, Person.class);

        // Kiểm tra deep copy
        System.out.println("Original Name: " + originalPerson.getName());
        System.out.println("Copied Name: " + copiedPerson.getName());

        // Thay đổi tên của copiedPerson và đối tượng Address
        copiedPerson.setName("Alice");
        copiedPerson.getAddress().setStreet("456 Elm St");

        System.out.println("Original Name: " + originalPerson.getName());
        System.out.println("Copied Name after modification: " + copiedPerson.getName());
        System.out.println("Original Street: " + originalPerson.getAddress().getStreet());
        System.out.println("Copied Street after modification: " + copiedPerson.getAddress().getStreet());




        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(originalPerson);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            Person copiedPerson1 = (Person) ois.readObject();

            // Kiểm tra deep copy
            System.out.println("Original Name: " + originalPerson.getName());
            System.out.println("Copied Name: " + copiedPerson1.getName());

            // Thay đổi tên của copiedPerson và đối tượng Address
            copiedPerson1.setName("Alice");
            copiedPerson1.getAddress().setStreet("456 Elm St");

            System.out.println("Original Name: " + originalPerson.getName());
            System.out.println("Copied Name after modification: " + copiedPerson.getName());
            System.out.println("Original Street: " + originalPerson.getAddress().getStreet());
            System.out.println("Copied Street after modification: " + copiedPerson.getAddress().getStreet());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}

