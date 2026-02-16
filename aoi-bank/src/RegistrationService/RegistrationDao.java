package RegistrationService;

import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;
import java.util.*;
import PasswordService.*;

//登録処理関連のDAOクラス
public class RegistrationDao{
	static DataSource ds;
	
	public Connection getConnection() throws Exception{
		if(ds == null){
			InitialContext ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:/comp/env/jdbc/resource");
		}
		return ds.getConnection();
	}

    //電話番号の重複チェックメソッド
    public boolean telCheck(String tel){
        String sql = "SELECT telephone_number FROM customer WHERE telephone_number = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tel);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true; //重複あり
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // 重複がなし
    }

    //DBにユーザー情報を登録するメソッド
	public boolean userInfoReg(Map <String, String> map)throws Exception{
		
		String sql = "INSERT INTO customer(login_id, last_name, first_name, address, telephone_number, login_password_hash) "
                   + "VALUES (LPAD(login_id_seq.NEXTVAL, 6, '0'), ?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Mapから情報取得
            String lastname = map.get("lastname");
            String firstname = map.get("firstname");
            String address = map.get("address");
            String tel = map.get("tel");

            // パスワードハッシュ化
            String password = map.get("password");
            String hashedPassword = PasswordUtil.hashPassword(password);

            // PreparedStatementにセット
            ps.setString(1, lastname);
            ps.setString(2, firstname);
            ps.setString(3, address);
            ps.setString(4, tel);
            ps.setString(5, hashedPassword);

            // 実行
            int rows = ps.executeUpdate();
            if (rows == 1) {
            System.out.println("成功：ユーザー情報をDBに登録しました。(" + map.get("tel") + ")");
            return true;
            }
            else {
                System.out.println("失敗：登録件数が0件です。");
                return false;
            }

        }
        catch (SQLException e) {
            // ★ここが最重要！原因を表示する★
            System.err.println("DB登録エラーが発生しました。");
            System.err.println("エラーコード: " + e.getErrorCode());
            System.err.println("SQL状態: " + e.getSQLState());
            System.err.println("メッセージ: " + e.getMessage());
            
            // 原因を上に伝えるために再スロー（または return false）
            throw e; 
        }
    }

    //customer_idとlogin_idを取得するメソッド
    public Map<String, String> allGetId(String tel) throws Exception {
        
        //Map用意
        Map<String, String> allGetId = new HashMap<>();

        // この電話番号に紐づく情報でlogin_idとcustmer_idを取得
        String sql = "SELECT customer_id, login_id FROM customer WHERE telephone_number = ?";

        try (Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tel);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    allGetId.put("customer_id", rs.getString("customer_id"));
                    allGetId.put("login_id", rs.getString("login_id"));
                    return allGetId;
                }
            }
        }
        return null;
    }
    //口座番号を発行するメソッド
    public void accountNumberIssue(String customer_id) throws Exception {
        String sql = "INSERT INTO account (account_number, customer_id) VALUES (account_number_seq.NEXTVAL, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, customer_id);
            ps.executeUpdate();
        }
    }
}