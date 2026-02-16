package Login_and_MypageService;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import PasswordService.*;
import java.util.*;

@WebServlet(urlPatterns={"/LoginCheck"}) 
public class LoginCheckServlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		
		//LoginCheckDaoからユーザ情報を保存するMap
		Map <String, String> userInfo = new HashMap<>();

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		
		if(login_id == null || login_id.trim().isEmpty() || password == null || password.trim().isEmpty()){
			request.setAttribute("error_msg","ログインIDまたはパスワードが未入力です");
			request.getRequestDispatcher("/Login_and_Mypage/login_page.jsp").forward(request, response);
			return;
		}
		
		String password_hash;
		
		try{
			//パスワードハッシュ化
			password_hash = PasswordUtil.hashPassword(password);
		}
		catch(Exception e){
			System.out.println("パスワードハッシュ化の処理中にエラーが発生しました\n" + e.getMessage());
			request.setAttribute("error_msg","サーバー側でエラーが発生しました");
			request.getRequestDispatcher("/Login_and_Mypage/login_page.jsp").forward(request, response);
			return;
		}

		//ログインチェックDAOクラス
		LoginCheckDao dao = new LoginCheckDao();

		try {
			//ログインチェックするメソッド
			userInfo = dao.LoginCheck(login_id, password_hash); 
			
			if(userInfo != null && !userInfo.isEmpty()){
				//セッション用意
				HttpSession session = request.getSession();

				//セッションにログインIDを保存
				session.setAttribute("userInfo", userInfo);
				request.getRequestDispatcher("/Login_and_Mypage/mypage.jsp").forward(request, response);
				return;
			}
			else{
				request.setAttribute("error_msg","ログインIDまたはパスワードが違います");
				request.getRequestDispatcher("/Login_and_Mypage/login_page.jsp").forward(request, response);
				return;
			}
		}
		catch (Exception e) {
			System.out.println("ログイン認証中にエラーが発生しました\n" + e.getMessage());
			request.setAttribute("error_msg","サーバー側でエラーが発生しました");
			request.getRequestDispatcher("/Login_and_Mypage/login_page.jsp").forward(request, response);
			return;
		}
	}
}