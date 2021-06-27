package connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection faz_conexao() throws SQLException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			return DriverManager.getConnection("jdbc:mysql://localhost/{database_name}?useTimezone=true&serverTimezone=UTC","{database_user}","{database_password}");
			
		} catch (ClassNotFoundException e) {
			
			throw new SQLException(e.getException());
			
		}
		
		
	}

}

