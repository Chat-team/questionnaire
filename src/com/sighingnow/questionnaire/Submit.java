package com.sighingnow.questionnaire;

import javax.servlet.ServletException;
 import javax.servlet.annotation.WebServlet;
 import javax.servlet.http.*;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.util.LinkedList;
 import java.util.List;

@WebServlet(name = "Submit", urlPatterns = {"/submit"})
public class Submit extends HttpServlet {

     public void doPost(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         List<String> form = new LinkedList<>();
         for (String s: Sqlite3.attrs) {
             String t = request.getParameter(s);
             form.add(t != null ? t : "");
         }
         Sqlite3.insert(form.toArray(new String[0]), getServletContext().getRealPath("/"));
         response.sendRedirect("/statistics.html");
     }
}
