package org.example.Homework;

import com.github.javafaker.Cat;

import java.awt.*;
import java.io.File;
import java.net.URI;
import java.util.Optional;

public class ViewCommand extends Command{
    private String id;
    private Catalog catalog;
    public ViewCommand(Catalog catalog, String  id) {
        this.catalog = catalog;
        this.id = id;
    }

    @Override
    void execute() throws Exception, InvalidDataException {
        Optional<Document> optionalDocument = catalog.getDocuments().stream()
                .filter(document -> document.getId().equals(id))
                .findFirst();
        if(!optionalDocument.isEmpty())
        {
            Document document = optionalDocument.get();
            if(Desktop.isDesktopSupported())
                if(document.getPath() != null)
                    if(document.getPath().contains("www."))
                        Desktop.getDesktop().browse(new URI(document.getPath()));
                    else
                        Desktop.getDesktop().open(new File(document.getPath()));
        }
        else
        {
            throw new InvalidDataException("Document with ID: " + id + " not found in catalog.");
        }
    }
}
