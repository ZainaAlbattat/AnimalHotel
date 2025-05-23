package org.ics.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import isproject.ejb.ics.Animal;
import isproject.facade.ics.FacadeLocal;

@WebServlet("/Animals/*")
public class Animals extends HttpServlet {

    @EJB
    FacadeLocal facade;

    private static final long serialVersionUID = 1L;
//REST gjordes enligt labb
    public Animals() {
        super();
    }

    // doGet 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            // Hämta alla djur
            System.out.println("Alla");
            System.out.println(pathInfo);
            List<Animal> allAnimals = facade.findAllAnimals();
            sendAsJson(response, allAnimals);
            return;
        }
        
        String[] splits = pathInfo.split("/");
        if (splits.length != 2) {
            
            System.out.println("Alla2");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        String id = splits[1];
        Animal animal = facade.findByAnimalId(Integer.parseInt(id));
        sendAsJson(response, animal);
    }

    // dodelete
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        String[] splits = pathInfo.split("/");
        if (splits.length != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        String id = splits[1];
        Animal animal = facade.findByAnimalId(Integer.parseInt(id));
        if (animal != null) {
            facade.deleteAnimal(Integer.parseInt(id));
        }
        sendAsJson(response, animal);
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        response.setStatus(HttpServletResponse.SC_OK);
    }

    // json ett djur
    private void sendAsJson(HttpServletResponse response, Animal animal) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        
        if (animal != null) {
            // Manual JSON construction like in the lab
            out.print("{\"id\":");
            out.print("\"" + animal.getAnimalID() + "\"");
            out.print(",\"name\":");
            out.print("\"" + animal.getAnimalName() + "\"");
            out.print(",\"species\":");
            out.print("\"" + animal.getSpecies() + "\"}");
        } else {
            out.print("{ }");
        }
        out.flush();
    }

    // json lisa
    private void sendAsJson(HttpServletResponse response, List<Animal> animals) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        
        if (animals != null) {
            JsonArrayBuilder array = Json.createArrayBuilder();
            for (Animal a : animals) {
                JsonObjectBuilder o = Json.createObjectBuilder();
                o.add("id", String.valueOf(a.getAnimalID()));
                o.add("name", a.getAnimalName());
                o.add("species", a.getSpecies());
                array.add(o);
            }
            JsonArray jsonArray = array.build();
            System.out.println("Animal Rest: " + jsonArray);
            out.print(jsonArray);
        } else {
            out.print("[]");
        }
        out.flush();
    }
}