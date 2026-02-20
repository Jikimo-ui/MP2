package Servlets;

import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private String user;
    private String pass;

    @Override
    public void init() throws ServletException {
        user = getServletConfig().getInitParameter("username");
        pass = getServletConfig().getInitParameter("password");

        System.out.println("Configured username: " + user);
        System.out.println("Configured password: " + pass);

        //TODO: get the user and pass from the database, not the xml (might remove lowkey)
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private static final String URL = "jdbc:derby://localhost:1527/LoginDB";
    private static final String USERNAME = "app";
    private static final String PASSWORD = "app";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String validUser = request.getParameter("username");
        String validPass = request.getParameter("password");

        if ((validUser == null || validUser.trim().isEmpty())
                && (validPass == null || validPass.trim().isEmpty())) {
            request.setAttribute("ErrorMessage", "Username and password is left blank!");
            request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
            return;
        } else if (validUser == null || validUser.trim().isEmpty()) {
            request.setAttribute("ErrorMessage", "Username is left blank!");
            request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
            return;
        } else if (validPass == null || validPass.trim().isEmpty()) {
            request.setAttribute("ErrorMessage", "Password is left blank!");
            request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
            return;
        }

        //Validation..
        //TODO: check if password and user is in the DB
        //TODO: check if password and user exists
        try {
            String driver = "org.apache.derby.jdbc.ClientDriver";
            Class.forName(driver);

            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String checkQry = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
            PreparedStatement psC = con.prepareStatement(checkQry);
            psC.setString(1, validUser);
            psC.setString(2, validPass);
            
            ResultSet rsC = psC.executeQuery();
            
            if(rsC.next()){
                String role = rsC.getString("ROLE");
                
                if ("Guest".equalsIgnoreCase(role)) {
                    
                } else if ("Admin".equalsIgnoreCase(role)) {
                    
                } else {
                    
                }
                
            }else{
                //TODO: IMPLEMENT ATTEMPTS
            }
        } catch (ClassNotFoundException ex) {
            
        } catch (SQLException ex) {
            
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
