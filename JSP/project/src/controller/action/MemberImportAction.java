package controller.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import dto.MemberVO;

import util.DBManager;

public class MemberImportAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "C:\\csvfolder\\" + request.getParameter("path");
		String url = "/product/main.jsp";
		
		
		File file = new File(path);
		MemberDAO mDao=MemberDAO.getInstance();
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String line;
			line = br.readLine();
					while((line = br.readLine()) != null) {
							String[] arr = line.split(",");
							mDao.insertMember(arr);
							System.out.println("import success!");
					}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
