package com.megagold.mongoennube_vuno.Core;

import com.megagold.mongoennube_vuno.Model.SCP;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class ConexionMongo {

    private static MongoCollection<Document> collection;

    public static void inicializarConexion() {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://megagold:gold0851@cluster0.jz5qik5.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("Mongo_Atlas_SCP");
        collection = database.getCollection("SCP");
    }

    public static List<SCP> obtenerSCP() {
        if (collection == null) {
            inicializarConexion();
        }

        List<SCP> listaSCP = new ArrayList<>();

        MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            SCP scp = new SCP(
                    doc.getString("nombreSCP"),
                    doc.getString("claseObjeto"),
                    doc.getString("procedimientosDeContencion"),
                    doc.getInteger("identificador"),
                    doc.getObjectId("_id").toString()
            );
            listaSCP.add(scp);
        }

        cursor.close();

        return listaSCP;
    }

    public static void insertarSCP(String nombreSCP, int identificador, String procedimientosDeContencion, String opcionSeleccionada) {
        if (collection == null) {
            inicializarConexion();
        }

        System.out.println("Insertando...");
        Document document = new Document()
                .append("_id", new ObjectId())
                .append("nombreSCP", nombreSCP)
                .append("identificador", identificador)
                .append("claseObjeto", opcionSeleccionada)
                .append("procedimientosDeContencion", procedimientosDeContencion);

        collection.insertOne(document);
    }

    public static void actualizarSCP(String id, String nombreSCP, int identificador, String procedimientosDeContencion, String opcionSeleccionada) {
        if (collection == null) {
            inicializarConexion();
        }


        System.out.println("Actualizando..."+id);
        Document filter = new Document("_id", new ObjectId(id));
        Document update = new Document("$set", new Document()
                .append("nombreSCP", nombreSCP)
                .append("identificador", identificador)
                .append("claseObjeto", opcionSeleccionada)
                .append("procedimientosDeContencion", procedimientosDeContencion));

        collection.updateOne(filter, update);
    }

    public static void eliminarSCP(String id) {
        if (collection == null) {
            inicializarConexion();
        }
        System.out.println("Eliminando...");

        Document filter = new Document("_id", new ObjectId(id));


        collection.deleteOne(filter);
    }

}
