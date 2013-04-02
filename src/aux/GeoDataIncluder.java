package aux;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GeoDataIncluder {
	public static void main (String[] args){
		MysqlConnection connDB1 = new MysqlConnection("arquivos_de_qualipoc");
		MysqlConnection connDB2 = new MysqlConnection("cqi_ecio_rscp_ftp");
		
		
		try {
			connDB1.conectar();
			connDB2.conectar();
			
			Statement stm1 = connDB1.getConexao().createStatement();
			Statement stm2 = connDB2.getConexao().createStatement();
			ResultSet resultsDB1 = stm1.executeQuery(
					"SELECT position.PosId," +
					"position.latitude," +
					"position.longitude," +
					"position.speed " +
					"FROM position"
					);
			resultsDB1.last();
			System.out.println("Resultados: "+resultsDB1.getInt("msgId"));
			resultsDB1.beforeFirst();
			while (resultsDB1.next()) {
				stm2.executeUpdate("UPDATE cqi_ecio_rscp_ftp SET latitude="+resultsDB1.getDouble("latitude")+", longitude="+resultsDB1.getDouble("longitude")+", speed="+resultsDB1.getInt("speed")+" WHERE cqi_ecio_rscp_ftp.`PosId` = "+resultsDB1.getInt("PosId"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
