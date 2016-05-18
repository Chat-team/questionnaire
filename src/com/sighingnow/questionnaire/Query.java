package com.sighingnow.questionnaire;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Query", urlPatterns = {"/query"})
public class Query extends HttpServlet {

     public void doPost(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         try {
             Writer writer = new Writer(response.getOutputStream());
             writer.add("data", Sqlite3.query(getServletContext().getRealPath("/"))).write();
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         doPost(request, response);
    }
}