package org.example.Homework;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class SaveCommand extends Command{
    private Catalog catalog;
    private String filename;

    public SaveCommand(Catalog catalog, String filename) {
        this.catalog = catalog;
        this.filename = filename;
    }

    public void execute() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filename), catalog);
    }
}
