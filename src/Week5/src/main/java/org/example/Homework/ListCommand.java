package org.example.Homework;

import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class ListCommand extends Command {
    private Catalog catalog;

    public ListCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    public void execute() throws Exception, InvalidDataException {
        this.catalog = new Catalog();
        Command command = new LoadCommand(catalog, "catalog.json");
        command.execute();
        System.out.println(catalog);
    }
}

