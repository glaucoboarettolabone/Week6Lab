package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 815000
 */
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username;
        final String LOGOUT = "logout";
                
        HttpSession session = request.getSession();
        
        String param = request.getParameter("action");
        if (param != null) {
            if (param.equalsIgnoreCase(LOGOUT)) {
                logout(request, response);
                return;
            }
        }
        
        try {
            username = (String) session.getAttribute("username");
        } catch(NullPointerException e) {
            username = null;
        }
        
        if (username == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);  
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);            
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String REGISTER = "register";
        final String ADD = "add";
        final String DELETE = "delete";
        
        String param = request.getParameter("action");
        if (param == null) {
            param = "";
        }
        
        switch (param) {
            case REGISTER:
                register(request, response);
                break;
            case ADD:
                addItem(request, response);
                break;
            case DELETE:
                deleteItem(request, response);
                break;
        }
            
        
    }
    
    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
       
        if (username == null || username.equals("")) {
            request.setAttribute("error", true);
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);  
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    private void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> list;

        HttpSession session = request.getSession();

        String item = request.getParameter("item");                
        if (item == null || item.equals("")) {
            request.setAttribute("error", true);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        }
        
        Object objList = (Object) session.getAttribute("list");        
        if (objList == null) {
            list = new ArrayList<>();
        } else {
            list = (ArrayList<String>) objList;
        }
        
        list.add(item);

        session.setAttribute("list", list);
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> list;

        HttpSession session = request.getSession();

        String deleteItem = request.getParameter("deleteitem");                
        if (deleteItem == null || deleteItem.equals("")) {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        }
        
        Object objList = (Object) session.getAttribute("list");        
        if (objList == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        }
        
        list = (ArrayList<String>) objList;
        
        list.remove(deleteItem);

        session.setAttribute("list", list);
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
    }
}
