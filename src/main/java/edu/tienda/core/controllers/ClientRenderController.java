package edu.tienda.core.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientRenderController {

//    @GetMapping(path = "/signup")
//    public String signUpForm() {
//        // Render form located on src/resources/templates/signup.html
//        return "signup";
//    }
//
//    @GetMapping(path = "/login")
//    public String loginForm() {
//        return "login";
//    }

    @GetMapping(value = "/clients-html", produces = MediaType.TEXT_HTML_VALUE)
    public String getClientsAsHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<body>");
        sb.append("<div><h1>Clients</h1>");
        sb.append("<ul>");
        sb.append("<li>John</li>");
        sb.append("<li>Mary</li>");
        sb.append("<li>Peter</li>");
        sb.append("</ul>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    @GetMapping(value = "/clients-xml", produces = MediaType.APPLICATION_XML_VALUE)
    public String getClientsAsXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<clients>");
        sb.append("<client>");
        sb.append("<name>John</name>");
        sb.append("</client>");
        sb.append("<client>");
        sb.append("<name>Mary</name>");
        sb.append("</client>");
        sb.append("<client>");
        sb.append("<name>Peter</name>");
        sb.append("</client>");
        sb.append("</clients>");
        return sb.toString();
    }
}
