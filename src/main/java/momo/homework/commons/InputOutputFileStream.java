package momo.homework.commons;

import momo.homework.objects.Customers;

import java.io.*;

public class InputOutputFileStream {

    public static Object getCustomersFromFile() {
        try {
            FileInputStream inputStream = new FileInputStream("../customer.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            Object object = objectInputStream.readObject();

            objectInputStream.close();
            inputStream.close();
            return object;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Get data got an error " + e);
        }
        return null;
    }

    public static void storeCustomersToFile(Customers customers){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("../customer.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(customers);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("Storing file got an error " + e);
        }

    }

}
