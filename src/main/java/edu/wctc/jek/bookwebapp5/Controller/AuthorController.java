
package edu.wctc.jek.bookwebapp5.Controller;

import edu.wctc.jek.bookwebapp5.Model.Author;
import edu.wctc.jek.bookwebapp5.Model.AuthorDao;
import edu.wctc.jek.bookwebapp5.Model.AuthorDaoStrategy;
import edu.wctc.jek.bookwebapp5.Model.AuthorService;
import edu.wctc.jek.bookwebapp5.Model.DBStrategy;
import edu.wctc.jek.bookwebapp5.Model.MySqlDbStrategy;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kowal_000
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {
    
 private static final String NO_PARAM_ERR_MSG = "No request parameter identified";

    private static final String LIST_PAGE = "/listAuthors.jsp";
    private static final String LIST_CONTROL = "AuthorController?action=list";
    private static final String ADD_PAGE = "/addAuthor.jsp";
    private static final String DELETE_PAGE = "/deleteAuthor.jsp";
    private static final String UPDATE_PAGE = "/updateAuthor.jsp";
    private static final String LIST_ACTION = "list";
    private static final String ADD_ACTION = "add";
    private static final String ADD_PAGE_ACTION = "addPage";
    private static final String UPDATE_PAGE_ACTION = "updatePage";
    private static final String DELETE_PAGE_ACTION = "deletePage";
    private static final String UPDATE_ACTION = "update";
    private static final String DELETE_ACTION = "delete";
    private static final String ACTION_PARAM = "action";
  
    private static final String ID_PARAM = "inputId";
    private static final String NAME_PARAM = "authorName";

    private int col_affected;

    

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String dbClassName = this.getServletContext().getInitParameter("dbStrategy");
        String dbDriver = this.getServletContext().getInitParameter("driverClass");
        String dbUrl = this.getServletContext().getInitParameter("booksDbUrl");
        String dbUser = this.getServletContext().getInitParameter("booksDbLogin");
        String dbPswd = this.getServletContext().getInitParameter("booksDbPswd");
        String daoClassName = this.getServletContext().getInitParameter("authorDao");
        AuthorDaoStrategy authorDao = null;
        try{
        Class dbStrategy = Class.forName(dbClassName);
        DBStrategy db = (DBStrategy)dbStrategy.newInstance();
        Class daoClass = Class.forName(daoClassName);
                
         Constructor constructor = daoClass.getConstructor(new Class[] {
                        DBStrategy.class,String.class,String.class,String.class,String.class
            });
          if(constructor != null) { 
                Object[] constructorArgs = new Object[] {
                    db,dbDriver,dbUrl, dbUser, dbPswd};
                
                authorDao = (AuthorDaoStrategy)constructor
                        .newInstance(constructorArgs);
                
            }
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException
                | NoSuchMethodException | SecurityException | IllegalArgumentException 
                | InvocationTargetException e){
                System.out.println(e.getMessage());
         }
        String destination = LIST_PAGE;
        String action = request.getParameter(ACTION_PARAM);
    
        AuthorService authorService = new AuthorService(authorDao);

        try {
          
//            Context ctx = new InitialContext();
//            DataSource ds = (DataSource)ctx.lookup("jdbc/book2"); <-- THIS SHOULDN'T BE HARDCODED
//            AuthorDaoStrategy authDao = new ConnPoolAuthorDao(ds, new MySqlDbStrategy());
//            AuthorService authService = new AuthorService(authDao);
            switch (action) {
                case LIST_ACTION:
                    List<Author> authors = authorService.getAllAuthors();
//                    System.out.println(authors.size() + " Records");
                    request.setAttribute("authors", authors);
                    request.setAttribute("count", authors.size());
                    destination = LIST_PAGE;
                    break;
                case ADD_ACTION:
                    try{
                        this.col_affected = authorService.insertAuthor( request.getParameter(NAME_PARAM));
                        request.setAttribute("updated", col_affected + " Records Added");
                    }catch(Exception e){
                        request.setAttribute("updated", "An error occured");
                    }   destination = ADD_PAGE;
                    break;
                case DELETE_ACTION:
                    try{
                        this.col_affected = authorService.deleteAuthor(request.getParameter(ID_PARAM));
                        request.setAttribute("updated", col_affected + " Records Deleted");
                    }catch(Exception ex){
                        request.setAttribute("updated", "An error occured");
               }    destination = DELETE_PAGE;
                    break;
                case UPDATE_ACTION:
                    try{
                        this.col_affected = authorService.updateAuthor(request.getParameter(ID_PARAM), request.getParameter(NAME_PARAM));
                        request.setAttribute("updated", col_affected + " Records Updated");
                    }catch(Exception e){
                        request.setAttribute("updated", "An error occured " + e.getMessage());
                }   destination = UPDATE_PAGE;
                break;
                case ADD_PAGE_ACTION:
                    destination = ADD_PAGE;
                break;
                case UPDATE_PAGE_ACTION:
                    destination = UPDATE_PAGE;
                break;
                case DELETE_PAGE_ACTION:
                    destination = DELETE_PAGE;
                break;
                default:
                    // no param identified in request, must be an error
                    request.setAttribute("errMsg", NO_PARAM_ERR_MSG);
                    destination = LIST_PAGE;
                    break;
            }
            
        } catch (Exception e) {
            request.setAttribute("errMsg", e.getCause().getMessage());
        }

        // Forward to destination page
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
