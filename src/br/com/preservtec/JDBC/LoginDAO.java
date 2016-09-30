package br.com.preservtec.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.preservtec.model.Login;

public class LoginDAO {
	private Connection connection;
	
	public LoginDAO(){
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public Login verificaLogin(String user, String senha) {
		//boolean flag =true;
		Login loginProcurado = null;
		
		String sql = "select * from bookodex.login "
				+ "where userLogin = ? and passwordLogin = ?";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, user);
			stmt.setString(2, senha);			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				loginProcurado = new Login();
				loginProcurado.setUserLogin(rs.getString("userLogin"));
				loginProcurado.setPasswordLogin(rs.getString("passwordLogin"));
				loginProcurado.setNivelLogin(rs.getString("nivelLogin"));
				loginProcurado.setNomeLogin(rs.getString("NomeLogin"));
				
			}
			/*if (loginProcurado == null) {
				flag = false;
			}*/
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getErrorCode());
		}
		return loginProcurado;
	}
	
}
