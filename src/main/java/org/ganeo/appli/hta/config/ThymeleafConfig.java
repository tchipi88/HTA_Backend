/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.config;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
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
    @Description("Thymeleaf template resolver serving HTML 5 emails")
    public ClassLoaderTemplateResolver emailTemplateResolver() {
        ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
        emailTemplateResolver.setPrefix("mails/");
        emailTemplateResolver.setSuffix(".html");
        emailTemplateResolver.setTemplateMode("HTML5");
       // emailTemplateResolver.setCharacterEncoding(CharEncoding.UTF_8);
        emailTemplateResolver.setOrder(1);
        return emailTemplateResolver;
    }
}
