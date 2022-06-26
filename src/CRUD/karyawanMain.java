/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rama
 */
public class karyawanMain {
    private MongoClient client;
    private DB db;
    private DBCollection collection;
    private Map createMap(karyawan a){
        Map<String, Object> map = new HashMap<>();
        map.put("nik", a.getNik());
        map.put("nama", a.getNama());
        map.put("jk", a.getJk());
        map.put("usia", a.getUsia());
        map.put("alamat",a.getAlamat());
        map.put("gaji", a.getGaji());
        return map;
    }
    private void check(){
        if (client == null) {
            client = new MongoClient("localhost:27017");
            db = (DB) client.getDB("uas"); // database
        }
    }
    
    public List<Object> login()throws Exception{
        check();
        collection =  db.getCollection("user");
        //ambil smua document yg ada di tabel user pkai DBCursor
        DBCursor cursor = collection.find();
        
        String json ="[";
        while (cursor.hasNext()) {
            json += ","+cursor.next();
        }
        json += "]";
        json = json.replaceFirst(",", "");
        
        // pakai bantuan Gson :DDD
        Gson gson = new Gson();
        List<Object> anggotas = gson.fromJson(json, new TypeToken<List<Object>>(){}.getType());
        return anggotas;
        
    }
    public void save(karyawan a) throws Exception{
        check();
        // ini table gan..
        collection =  db.getCollection("karyawan"); 
        
        // ini row/ data yg bakal d insert
        BasicDBObject doc = new BasicDBObject(); 
        Map<String, Object> map = createMap(a);
        doc.putAll(map);
        
        //execute
        collection.insert(doc);
    }
    public void update(karyawan a) throws Exception{
        check();
        collection =  db.getCollection("karyawan");
        // asumsi nim tiap orang berbeda "where clause"
        BasicDBObject query = new BasicDBObject();
        query.put("nik", a.getNik());
        
        //tentukan apa yg ingin di update
        BasicDBObject newDoc = new BasicDBObject();
        Map<String, Object> map = createMap(a);
        newDoc.putAll(map);
        
        //query update ke mongo
        BasicDBObject updateDoc = new BasicDBObject();
        updateDoc.put("$set", newDoc);
        
        //execute
        collection.update(query, updateDoc);
    }
    public void delete(int nik) throws Exception{
        check();
        collection =  db.getCollection("karyawan");
        // "where clause"
        BasicDBObject query = new BasicDBObject();
        query.put("nik", nik);
        
        //execute
        collection.remove(query);
    }
    public List<karyawan> getAnggotas() throws Exception{
        check();
        collection =  db.getCollection("karyawan");
        //ambil smua document yg ada di collection pkai DBCursor
        DBCursor cursor = collection.find();
        
        //save dari cursor ke String
        String json ="[";
        while (cursor.hasNext()) {
            json += ","+cursor.next();
        }
        json += "]";
        json = json.replaceFirst(",", "");
        
        // pakai bantuan Gson :DDD
        Gson gson = new Gson();
        List<karyawan> anggotas = gson.fromJson(json, new TypeToken<List<karyawan>>(){}.getType());
        return anggotas;
    }
}
