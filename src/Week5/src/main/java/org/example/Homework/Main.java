package org.example.Homework;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.List;
import java.util.stream.*;
import javax.swing.*;
import com.fasterxml.jackson.databind.*;
import freemarker.template.*;


public class Main {
    public static void main(String[] args) throws InvalidDataException, Exception, InvalidDocumentException {
        Catalog catalog = new Catalog("Catalog1");
        List<Document> documents = new ArrayList<>();
        String messageReceived = new String();
        Command command;

        Document document1 = new Document("document1", "Document 1", "/path/to/document1.txt", new String[]{"title", "Document 1 Title", "author", "Author 1", "year", "2022"});
        Document document2 = new Document("document2", "Document 2", "www.google.com", new String[]{"title", "Document 2 Title", "author", "Author 2", "year", "2023"});
        Document document3 = new Document("document3", "Document 3", "www.amazon.com", new String[]{"title", "Document 3 Title", "author", "Author 3", "year", "2023"});
        documents.add(document1);
        documents.add(document2);
        documents.add(document3);


        System.out.println("Enter command: ");
        Scanner scanner = new Scanner(System.in);
        messageReceived = scanner.nextLine();

        while (!messageReceived.contains("exit")) {
            switch (messageReceived) {
                case "add": {
                    System.out.println("Enter document ID: ");
                    scanner = new Scanner(System.in);
                    messageReceived = scanner.nextLine();
                    command = new AddCommand(catalog, messageReceived, documents);
                    command.execute();
                    break;
                }
                case "save": {
                    command = new SaveCommand(catalog, "catalog.json");
                    command.execute();
                    break;
                }
                case "list": {
                    command = new ListCommand(catalog);
                    command.execute();
                    System.out.println(catalog);
                    break;
                }
                case "view": {
                    System.out.println("Enter document ID: ");
                    scanner = new Scanner(System.in);
                    messageReceived = scanner.nextLine();
                    command = new ViewCommand(catalog, messageReceived);
                    command.execute();
                    break;
                }
                case "report": {
                    System.out.println(catalog);
                    command = new ReportCommand(catalog, "catalog.html");
                    command.execute();
                    break;
                }
                default: {
                    throw new CommandNotValidException("Invalid command.");
                }
            }

            System.out.println("Enter command: ");
            scanner = new Scanner(System.in);
            messageReceived = scanner.nextLine();
        }
    }
}