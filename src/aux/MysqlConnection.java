package aux;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class MysqlConnection {
    
    private Connection conexao = null;
	private String servidor = "localhost";
	private String banco = "";
	private String usuario = "root";
	private String senha = "";
	// private String porta="3316";
	private boolean estaConectado = false;

	public Connection getConexao() {
		return this.conexao;
	}

	public boolean getStatus() {
		return this.estaConectado;
	}

	public void conectar() {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			System.out.println("Erro de Inicializacao");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("Acesso ilegal");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Classe nao encontrada");
			e.printStackTrace();
		}

		try {
			this.conexao = DriverManager.getConnection("jdbc:mysql://"
					+ this.servidor + "/" + this.banco + "?user="
					+ this.usuario + "&password=" + this.senha);
		} catch (SQLException e) {
			// TODO tratar os tipos diferentes de erro SQL
			System.out.println("--erro de SQL--");
			System.out.println("Codigo: " + e.getErrorCode() + "      ");
			System.out.println("SQL State: " + e.getSQLState());

			e.printStackTrace();

		}

		this.estaConectado = true;
	}

	public void desconectar() {
		try {
			this.conexao.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public MysqlConnection(String servidor, String banco, String usuario,
			String senha){
		this.servidor = servidor;
		this.banco = banco;
		this.usuario = usuario;
		this.senha = senha;
	}

	public MysqlConnection(String banco){
		this.banco = banco;
	}
    
    
    
    
    
    





    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
