package com.ruta.rutaarch.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ruta.rutaarch.controllers.ServletCommunicate;
import com.ruta.rutaarch.controllers.ServletInfoU;
import com.ruta.rutaarch.controllers.ServletPlay;
import com.ruta.rutaarch.controllers.ServletStart;

import jakarta.servlet.http.HttpServlet;

@Configuration
public class WebConfig {

   @Bean    
   public ServletRegistrationBean<HttpServlet> startServlet() {
       ServletRegistrationBean<HttpServlet> servRegBean = new ServletRegistrationBean<>();
       servRegBean.setServlet(new ServletStart());
       servRegBean.addUrlMappings("/api/start");
       servRegBean.setLoadOnStartup(1);
       return servRegBean;
   }

   @Bean    
   public ServletRegistrationBean<HttpServlet> communicateServlet() {
       ServletRegistrationBean<HttpServlet> servRegBean = new ServletRegistrationBean<>();
       servRegBean.setServlet(new ServletCommunicate());
       servRegBean.addUrlMappings("/api/communicate");
       servRegBean.setLoadOnStartup(7);
       return servRegBean;
   }
  
   @Bean    
   public ServletRegistrationBean<HttpServlet> playServlet() {
       ServletRegistrationBean<HttpServlet> servRegBean = new ServletRegistrationBean<>();
       servRegBean.setServlet(new ServletPlay());
       servRegBean.addUrlMappings("/api/jugar");
       servRegBean.setLoadOnStartup(7);
       return servRegBean;
   }
}
