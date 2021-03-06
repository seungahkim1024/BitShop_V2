package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import domain.MemberBean;
import service.MemberService;
import service.MemberServiceImpl;

@WebServlet("/member.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String context = request.getContextPath();
		HttpSession session = request.getSession();
		session.setAttribute("context", context);
		MemberService memberService = MemberServiceImpl.getInstance();
		System.out.println("(1)멤버서블릿!!");
		/**
		 * 디폴트 값
		 * cmd : move
		 * dir : member
		 * page : main
		 * dest : NONE
		 * */
		String cmd = request.getParameter("cmd");
		cmd = (cmd == null) ? "move": cmd;
		System.out.println("(2)cmd :"+ cmd);
		
		String page = request.getParameter("page");
		if(page==null) {page = "main";}
		System.out.println("(3)page :"+page);
		
		String dir = request.getParameter("dir");
		if(dir == null) {
			String sPath = request.getServletPath();
			sPath = sPath.replace(".do", "");
			dir = sPath.substring(1);
		}
		System.out.println("(4)dir :"+dir);
		
		String dest = request.getParameter("dest");
		if(dest==null){
			dest = "NONE";
		}
		request.setAttribute("dest", dest);
		System.out.println("멤버컨트롤에서 dest : " + dest);
		/*
		 if(action==null){
			action = "move";
		}  = (action==null) ? "move":action   
		*/
		
		switch(cmd) {
		case "login":
			System.out.println("로그인 진입");
			MemberBean memberBean = null;
			String id = request.getParameter("uid");
			String pass = request.getParameter("upw");
			memberBean=memberService.findMemberById(id);
			//memberBean=MemberServiceImpl.getInstance().findMemberById(id);
			System.out.println("memberBean======"+memberBean);
			boolean ok = memberService.existMember(id, pass);
			System.out.println("ok======="+ok);
			if(ok) {
				dir = "home";
			}else {
				dir = "";
				page = "index";
				dest = "";
			}
			session.setAttribute("user", memberBean); // 충돌날 수 있어서 이름을 member에서 user로 바꿈.
			request.setAttribute("dest", "welcome");
			System.out.println("dir"+dir);
			System.out.println("dest"+dest);
			break;
		case "move":
			request.setAttribute("dest", dest);
			break;
		case "join":
			memberBean = new MemberBean();
			memberBean.setName(request.getParameter("name"));
			memberBean.setSsn(request.getParameter("ssn"));
			memberBean.setId(request.getParameter("id"));
			memberBean.setPass(request.getParameter("pass"));
			memberService.joinMember(memberBean);
			memberService.findMemberById(request.getParameter("id"));
			System.out.println(">>>> 조회결과" + memberBean.toString());
			session = request.getSession();
			session.setAttribute("user",memberBean);
			request.setAttribute("dest", request.getParameter("dest"));
			break;
		case "logout":
			dir = "";
			page = "index";
			dest = "";
			session.invalidate(); // 세션에서 값을 제거. = 로그아웃
			break;
		case "detail" :
			request.setAttribute("dest", "detail");
			/*id = request.getParameter("id");
			request.setAttribute("member", memberService.findMemberById(id));*/
			break;
		}
		Command.move(request, response, dir, page);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}