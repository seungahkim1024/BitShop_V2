package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/home.do","/login.do"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*HttpSession session = request.getSession();
		session.setAttribute("context", request.getContextPath());*/
		request.getSession()
				.setAttribute("context", request.getContextPath());
		/*String sPath = request.getServletPath();
        sPath = sPath.replace(".do", "");
        String dir = sPath.substring(1);
        String dest = "";
        if(dir.equals("home")) {
            dest = "/WEB-INF/view/home/main.jsp";
        }else {
            dest = "/WEB-INF/view/home/login.jsp";
        }
        request.getRequestDispatcher(dest)
            .forward(request, response);*/
		request.getRequestDispatcher((request.getServletPath().replace(".do", "")
				.substring(1).equals("home")) 
				? "/WEB-INF/view/home/main.jsp"
						:
			"/WEB-INF/view/home/login.jsp")
			.forward(request, response);
	}
}
