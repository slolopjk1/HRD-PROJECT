package controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import dto.MemberVO;

public class LoginAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String url = "/product/login.jsp";
		String _enumber = request.getParameter("enumber");
		System.out.println(_enumber);
		int enumber = Integer.parseInt(_enumber);
		String password = request.getParameter("password");
		MemberDAO mDao=MemberDAO.getInstance();
		int result=mDao.userCheck(enumber, password);
		
		if(result==1) {
			MemberVO mVo = mDao.getMember(enumber);
			System.out.println(mVo.getName());
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mVo);
			url = "/product/main.jsp";
			request.setAttribute("message", "로그인에 성공했습니다.");
		}else if(result==0) {
			request.setAttribute("message", "비밀번호가 맞지 않습니다.");
		}else if(result==-1) {
			request.setAttribute("message", "존재하지 않는 회원입니다.");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
