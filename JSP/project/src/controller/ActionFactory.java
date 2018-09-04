package controller;

import controller.action.*;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();
	
	private ActionFactory() {
		super();
	}
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		System.out.println("ActionFactory :" + command);
		if(command.equals("product_login")){ // 로그인
			action = new Login();
		}else if(command.equals("product_loginaction")) { //로그인 인증
			action = new LoginAction();
		}else if(command.equals("product_main")) { // 메인
			action = new Main();
		}else if(command.equals("product_logoutaction")) { //로그 아웃
			action = new LogoutAction();
		}else if(command.equals("product_authority")) { // 권한 부여 창
			action = new Authority();
		}else if(command.equals("product_setapproval")) { // 권한 부여
			action = new SetApproval();
		}else if(command.equals("product_dropapproval")) { // 권한 취소
			action = new DropApproval();
		}else if(command.equals("product_memberimport")) { // import 창
			action = new MemberImport();
		}else if(command.equals("product_memberimportaction")) { // 회원추가 실행
			action = new MemberImportAction();
		}else if(command.equals("product_search")) { //전체 검색 창
			action = new Search();
		}else if(command.equals("product_searchaction")) { // 전체 검색
			action = new SearchAction();
		}else if(command.equals("product_depsearch")) { //부서별 검색 창
 			action = new DepSearch();
		}else if(command.equals("product_depsearchaction")) { //부서별 검색
			action = new DepSearchAction();
		}
		return action;
	}
	

}
