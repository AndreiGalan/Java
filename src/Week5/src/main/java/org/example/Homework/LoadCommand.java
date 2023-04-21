package org.example.Homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Cat;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class LoadCommand extends Command{
    private Catalog catalog;
    private String filename;

    public LoadCommand(Catalog catalog, String filename) {
        this.catalog = catalog;
        this.filename = filename;
    }

    public void execute() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Catalog myData=mapper.readValue(new File(filename), Catalog.class);
        for( Document loadDocument : myData.getDocuments()){
            Document document = new Document(loadDocument.getId(),loadDocument.getName(),loadDocument.getPath());
            for(Map.Entry<String,String> entry : loadDocument.getTags().entrySet())
            {
                String key = entry.getKey();
                String value = entry.getValue();
                document.addTag(key,value);
            }
            catalog.addDocument(document);
        }
    }
}
