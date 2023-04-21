package org.example.Homework;

import java.util.HashMap;
import java.util.Map;

public class CatalogManager {
    private  Catalog catalog;
    private final Map<String, Command> commands;

    public CatalogManager(Catalog catalog) {
        this.catalog = catalog;
        this.commands = new HashMap<>();
        commands.put("add", null);
        commands.put("list", null);
        commands.put("view", null);
        commands.put("report", null);
    }

    public void setCommand(String commandName, Command command) throws CommandNotValidException {
        if (!commands.containsKey(commandName)) {
            throw new CommandNotValidException("Invalid command name: " + commandName);
        }
        commands.put(commandName, command);
    }

    public void loadDocuments(Catalog loadCatalog)
    {
        this.catalog = loadCatalog;
    }

    public void executeCommand(String commandName, String[] commandArgs) throws InvalidDataException, CommandNotValidException {
        if (!commands.containsKey(commandName)) {
            throw new CommandNotValidException("Invalid command name: " + commandName);
        }
        Command command = commands.get(commandName);
        if (command == null) {
            throw new CommandNotValidException("Command not implemented: " + commandName);
        }
        try {
            command.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Catalog getCatalog() {
        return catalog;
    }
}