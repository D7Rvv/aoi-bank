package RegistrationService;

import java.io.IOException;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/RegistrationComplete"}) 
public class RegistrationCompleteServlet extends HttpServlet
{	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String pass_set = request.getParameter("pass_set");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		
		if(pass_set != null){//パスワード設定ボタンが押された時
			if(password1.length() < 8 || password1.length() > 16){
				request.setAttribute("error_msg","パスワードは8桁以上16桁以内で設定してください");
				request.getRequestDispatcher("/Registration/pass_set.jsp").forward(request, response);
				return;
			}
			else if(!password1.equals(password2)){ //確認用パスワードと一致しない場合
				request.setAttribute("error_msg","確認用パスワードと一致しません");
				request.getRequestDispatcher("/Registration/pass_set.jsp").forward(request, response);
				return;
			}
		}
		
		boolean res;
		
		//前回セッションのみ取得
		HttpSession session = request.getSession(false);

		if (session == null) {
		    response.sendRedirect(
		    request.getContextPath() + "/Registration/confirmation.jsp"
		    );
		    return;
		}

		// 前回セッションの Map を取得
        @SuppressWarnings("unchecked") // ジェネリクスキャスト警告を抑制
        Map<String, String> map = (Map<String, String>) session.getAttribute("map");

        if (map == null) {
            response.sendRedirect(request.getContextPath() + "/Registration/confirmation.jsp");
            return;
        }
		
		//パスワードをMapに追加
		map.put("password",password1);

		//顧客情報を登録するDAOクラス
		RegistrationDao dao = new RegistrationDao();

		try {
		    res = dao.userInfoReg(map); //DBに入力情報を登録するメソッド
		}
		catch (Exception e) {
			System.out.println("ユーザー情報の登録処理中にエラーが発生しました\n" + e.getMessage());
		    res = false;
		}
		
		if(!res){ //DB登録失敗
			session.setAttribute("map",map);

			System.out.println("ユーザー情報の登録に失敗しました");
			session.setAttribute("error_msg","サーバー側でエラーが発生しましたよん");
			request.getRequestDispatcher("/Registration/user_registration.jsp").forward(request, response);
		}
		else{//DB登録成功

			//customer_idとlogin_idを取得するMap用意
			Map <String, String> allGetId = new HashMap<>();
			
			try {
				//customer_idとlogin_idを取得するメソッド
				System.out.println("取得する電話番号: " + map.get("tel"));
				allGetId = dao.allGetId(map.get("tel"));

				if (allGetId == null) {
					// 取得失敗時の処理（ロールバックやエラー画面へ）
					throw new Exception("ユーザーIDの取得に失敗しました");
				}

				request.setAttribute("allGetId", (allGetId));
			} 
			catch (Exception e) {
				System.out.println("ユーザーIDの取得処理中にエラーが発生しました\n" + e.getMessage());
				request.setAttribute("error_msg","サーバー側でエラーが発生しました!!!!");
				request.getRequestDispatcher("/Registration/user_registration.jsp").forward(request, response);
				return;
			}

			try {
				//口座番号を発行するメソッド
				dao.accountNumberIssue(allGetId.get("customer_id"));
				System.out.println("口座番号発行成功");

				//登録完了画面に遷移
				request.getRequestDispatcher("/Registration/complete.jsp").forward(request, response);
			}
			catch (Exception e) {
				System.out.println("口座番号発行処理中にエラーが発生しました\n" + e.getMessage());
				request.setAttribute("error_msg","サーバー側でエラーが発生しましたたあたたた");
				request.getRequestDispatcher("/Registration/user_registration.jsp").forward(request, response);
				return;
			}
			session.removeAttribute("map");
    		session.removeAttribute("error_msg");
		}
	}
}