package RegistrationService;

import java.io.*;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/InputCheck"}) 
public class RegistrationServlet extends HttpServlet
{	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//セッション用意
		HttpSession session = request.getSession();
		
		Map <String, String> map = new HashMap<>();
		
		//入力項目をまとめたもの
		String[] para_list = {"lastname", "firstname","address","tel_part1","tel_part2","tel_part3"};
		
		String error_msg = ""; //エラー内容をまとめる
		boolean empty_flg = false; //true:未入力あり　false：未入力なし //電話番号項目用エラー判定　true:エラーあり　false：エラーなし
		boolean tel_empty_error = false; //電話番号が未入力→true
		boolean tel_format_error = false; //電話番号が数字ではない→true
		int label = 1; //各項目(情報入力の順番)
		
		//getParameterでまとめて取得
		for(String name:para_list){
			map.put(name,request.getParameter(name));
			
			if(map.get(name) == null || map.get(name).trim().isEmpty()){
				empty_flg = true;
				
				switch(label){
		    		case 1: 
		        		error_msg += "【姓】<br>";
		        		break;
		        	case 2:
		        		error_msg += "【名】<br>";
		        		break;
		        	case 3:
		        		error_msg += "【住所】<br>";
		        		break;
		          /*case 4:
		          		000-xxxx-xxxx　000の部分は固定のためチェックなし
		          */
		          	case 5:
		          	case 6:
		          	if(tel_empty_error == false){
		          			tel_empty_error = true;
		          			error_msg += "【電話番号】<br>";
		        			break;
		        		}
		        }
			}
			else{
		    	switch(label){
		    		case 1: 
		    			if(!map.get(name).matches("^[ぁ-んァ-ヶ一-龥・ー]+$")){
		        			error_msg += "【姓】に不正な文字が含まれています。<br>";
		        		}
		        		break;
		        	case 2:
		        		if(!map.get(name).matches("^[ぁ-んァ-ヶ一-龥・ー]+$")){
		        			error_msg += "【名】に不正な文字が含まれています。<br>";
		        		}
		        		break;
		          /*case 3:
		          	住所の項目は選択式のためチェック不要
		          */
		          	case 5:
		          	case 6:
			          	if(!map.get(name).matches("\\d{4}") && tel_format_error == false){
		          			tel_format_error = true;
		   			 		error_msg += ("【電話番号】は数字4桁ずつで入力してください<br>");		   			 		
		   			 	}
		   			 		break;
		        }
		    }
		    label++;
		}
		
		if(!error_msg.trim().isEmpty()){ //入力時エラーあり
			request.setAttribute("map",map);
			
			if(empty_flg == true){
				error_msg += "上記の項目が未入力です。";
			}
			request.setAttribute("error_msg",error_msg);
			request.getRequestDispatcher("/Registration/user_registration.jsp").forward(request, response);
		    return;
		}
		else{ //入力時エラーなし

			//電話番号を結合し、mapにtelとして追加
			map.put("tel", map.get("tel_part1") + map.get("tel_part2") + map.get("tel_part3"));
			
			//DBで電話番号の重複チェックを行う
			RegistrationDao dao = new RegistrationDao();
			try {
				if(dao.telCheck(map.get("tel"))){
					request.setAttribute("map",map);
					request.setAttribute("error_msg","入力された電話番号は既に登録されています");
					request.getRequestDispatcher("/Registration/user_registration.jsp").forward(request, response);
					return;
			    }
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("map",map);
				request.setAttribute("error_msg","電話番号の重複チェックに失敗しました。");
				request.getRequestDispatcher("/Registration/user_registration.jsp").forward(request, response);
				return;
			}

			session.setAttribute("map",map);
			request.getRequestDispatcher("/Registration/confirmation.jsp").forward(request, response);
			return;
		}
	}
}


