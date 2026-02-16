package Login_and_MypageService;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import javax.naming.*;
import javax.sql.DataSource;

//ログインチェックDAOクラス
public class LoginCheckDao{
	static DataSource ds;
	public boolean r;

	public Connection getConnection() throws Exception{
		if(ds == null){
			InitialContext ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:/comp/env/jdbc/resource");
		}
		return ds.getConnection();
	}

    //ログインチェックするメソッド
    public Map<String, String> LoginCheck(String login_id, String password_hash) throws Exception{
        //ユーザの情報を保存するMap
        Map<String, String> userInfoDao = new HashMap<>();

        String sql = "SELECT \r\n" +
                     "c.customer_id, \r\n" +
                     "c.login_id, \r\n" +
                     "c.last_name, \r\n" +
                     "c.first_name, \r\n" +
                     "c.address, \r\n" +
                     "c.telephone_number,\r\n" +
                     "a.account_number, \r\n" +
                     "a.deposit\r\n" +
                     "FROM \r\n" +
                     "customer c\r\n" +
                     "JOIN \r\n" +
                     "account a ON c.customer_id = a.customer_id\r\n" +
                     "WHERE \r\n" +
                     "c.login_id = ? AND c.login_password_hash = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, login_id);
            ps.setString(2, password_hash);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //ユーザ情報を取得してMapに保存
                userInfoDao.put("login_id", rs.getString("login_id"));
                userInfoDao.put("last_name", rs.getString("last_name"));
                userInfoDao.put("first_name", rs.getString("first_name"));
                userInfoDao.put("address", rs.getString("address"));
                userInfoDao.put("telephone_number", rs.getString("telephone_number"));

                //口座情報を取得してMapに保存
                userInfoDao.put("account_number", rs.getString("account_number"));
                userInfoDao.put("deposit", rs.getString("deposit"));

                
                return userInfoDao; //認証成功
            }
        }
        return userInfoDao; //認証失敗
    }
}