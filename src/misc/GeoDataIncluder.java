package misc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GeoDataIncluder {
	public static void main (String[] args){
		MysqlConnection connDB1 = new MysqlConnection("config/tabelao.xml");
		MysqlConnection connDB2 = new MysqlConnection("config/cqi_ecio_rscp_ftp.xml");
		
		
		try {
			connDB1.conectar();
			connDB2.conectar();
			
			Statement stm1 = connDB1.getConexao().createStatement();
			Statement stm2 = connDB2.getConexao().createStatement();
			ResultSet resultsDB1 = stm1.executeQuery(
					"SELECT tabelao.MNC, " +
					"tabelao.PrimScCode, "+
					"tabelao.msgTime "+
					"FROM tabelao"
					);
			
			
			while (resultsDB1.next()) {
				String query="UPDATE cqi_ecio_rscp_ftp SET MNC="+resultsDB1.getInt("MNC")+", PrimScCode="+resultsDB1.getInt("PrimScCode")+" WHERE cqi_ecio_rscp_ftp.`msgTime` = '"+resultsDB1.getTimestamp("msgTime")+"'";
				stm2.executeUpdate(query);
				System.out.println(query);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
