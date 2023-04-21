package org.example.Homework;

import com.github.javafaker.Cat;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.example.Compulsory.CatalogManager;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportCommand extends Command{
    private String fileName;
    private Template template;
    private Catalog catalog;

    public ReportCommand(Catalog catalog, String fileName) {
        this.catalog = catalog;
        this.fileName = fileName;
    }

    @Override
    public void execute() throws Exception, InvalidDataException {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            cfg.setDirectoryForTemplateLoading(new File("templates"));
            Template template = cfg.getTemplate("report.ftl");
            Map<String, Object> data = new HashMap<>();
            data.put("name", catalog.getName());
            data.put("documents", catalog.getDocuments());

            Writer fileWriter = new FileWriter(new File(fileName));
            template.process(data, fileWriter);
            fileWriter.flush();
            fileWriter.close();

            Desktop.getDesktop().open(new File(fileName));
            } catch (Exception e) {
                throw new InvalidDataException("Could not create report: " + e.getMessage());
            }
    }
}
