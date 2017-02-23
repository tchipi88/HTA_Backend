/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.config;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.codec.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 *
 * @author tchipnangngansopa
 */
@Configuration
public class ThymeleafConfig {

    @SuppressWarnings("unused")
    private final Logger log = LoggerFactory.getLogger(ThymeleafConfig.class);

    @Bean
    @Description("Thymeleaf template resolver serving Java Templates")
    public ClassLoaderTemplateResolver javaTemplateResolver() {
        ClassLoaderTemplateResolver javaTemplateResolver = new ClassLoaderTemplateResolver();
        javaTemplateResolver.setPrefix("templates/");
//        javaTemplateResolver.setSuffix(".txt");
        javaTemplateResolver.setTemplateMode(TemplateMode.TEXT);
        javaTemplateResolver.setCharacterEncoding(CharEncoding.UTF_8);
        javaTemplateResolver.setOrder(2);
        Set<String> patterns = new HashSet();
        patterns.add("Template*");
        javaTemplateResolver.setResolvablePatterns(patterns);
        return javaTemplateResolver;
    }

    @Bean
    public TemplateEngine myTemplateEngine() {
        TemplateEngine te = new SpringTemplateEngine();
        te.addTemplateResolver(javaTemplateResolver());
        return te;

    }
}
