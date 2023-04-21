package org.example.Homework;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.List;
import java.util.Map;

public class AddCommand extends Command{
    private Catalog catalog;
    private String id;
    private String name;
    private String path;
    private String[] tags;
    private Document document;

    public AddCommand(Catalog catalog, String idDocument, List<Document> documents) throws InvalidDocumentException {
        this.catalog = catalog;
        try {
            for(Document document : catalog.getDocuments())
            {
                if(document.getId().equals(idDocument))
                {
                    throw new InvalidDocumentException("Document already exists!");
                }
            }
        }catch (Exception e) {
            throw new InvalidDocumentException("Could not create report: " + e.getMessage());
        }

        try {
            for (Document document : documents) {
                if (document.getId().equals(idDocument)) {
                    this.document = document;
                    break;
                }
            }
        } catch (Exception e) {
            throw new InvalidDocumentException("Could not create report: " + e.getMessage());
        }

    }

    @Override
    void execute() throws Exception, InvalidDataException {
        if(document == null) {
            throw new InvalidDataException("Document not found!");
        }
        else
            catalog.addDocument(document);
    }
}
