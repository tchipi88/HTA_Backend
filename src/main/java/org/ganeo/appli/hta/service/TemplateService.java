/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

/**
 *
 * @author tchipi
 */
@Service
public class TemplateService {

    private final Logger log = LoggerFactory.getLogger(TemplateService.class);

    @Autowired
    private SpringTemplateEngine templateEngine;
    @Autowired
    private EntityManager em;
    @Autowired
    private ResourceLoader resourceLoader;

    public void processTemplateEngine(String basePackage) throws Exception {
        log.debug("Start entity templates Rendering");
        cleanUpDirectories();
        Metamodel metamodel = em.getMetamodel();
        for (EntityType et : metamodel.getEntities()) {
            if (!et.getJavaType().getPackage().getName().equalsIgnoreCase(basePackage + ".domain")) {
                continue;
            }

            Context context = new Context();
            context.setVariable("entity", et.getJavaType().getSimpleName());
            context.setVariable("ventity", et.getJavaType().getSimpleName().toLowerCase());
            context.setVariable("entityid", et.getIdType().getJavaType().getSimpleName());
            context.setVariable("entitypackage", et.getJavaType().getCanonicalName());

            context.setVariable("repositorypackage", basePackage.concat(".repository"));
            context.setVariable("controllerpackage", basePackage.concat(".controller"));

            creatingEntityRepositories(context);
            creatingEntityController(context);

        }
        log.debug("End entity templates Rendering");
    }

    public void creatingEntityRepositories(Context context) throws Exception {
        log.debug("Creating " + (String) context.getVariable("entity") + " repositories");
        String content = templateEngine.process("TemplateRepository.txt", context);
        creatingFiles(content, "repository", (String) context.getVariable("entity") + "Repository.java");
    }

    public void creatingEntityController(Context context) throws Exception {
        log.debug("Creating " + (String) context.getVariable("entity") + " controllers ");
        String content = templateEngine.process("TemplateController.txt", context);
        creatingFiles(content, "controller", (String) context.getVariable("entity") + "Controller.java");
    }

    public void creatingFiles(String content, String location, String filename) throws Exception {
        Resource dir = resourceLoader.getResource("classpath:" + location.toLowerCase());
        // create file-in-subdirectory path
        Path file = Paths.get(dir.getFilename() + File.separator + filename);
        Files.write(file, content.getBytes());
    }

    public void cleanUpDirectories() throws IOException {
        Resource rcontroller = resourceLoader.getResource("classpath:controller");
        Resource rrepository = resourceLoader.getResource("classpath:repository");
        deleteAllFilesDirOrCreateDir(Paths.get(rcontroller.getFilename()), false);
        deleteAllFilesDirOrCreateDir(Paths.get(rrepository.getFilename()), false);

    }

    public static void deleteAllFilesDir(Path dir, boolean deletedir) {
        try {
            Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        throws IOException {
                    System.out.println("Deleting file: " + file);
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir,
                        IOException exc) throws IOException {
                    if (deletedir) {
                        System.out.println("Deleting dir: " + dir);
                        if (exc == null) {
                            Files.delete(dir);
                            return FileVisitResult.CONTINUE;
                        } else {
                            throw exc;
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAllFilesDirOrCreateDir(Path dir, boolean deletedir) throws IOException {
        if (Files.exists(dir)) {
            deleteAllFilesDir(dir, deletedir);
        } else {
            Files.createDirectories(dir);
        };
    }

}
