/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
/**
 *
 * @author Rama
 */
public class config {
   public static MongoDatabase sambungDB(){
        try {
            MongoClient client = MongoClients.create();
            MongoDatabase database = client.getDatabase("uas");
            System.out.println("Koneksi Sukses");
            return database;
        } catch (Exception e){

        }
        
        return null;
    }
}
