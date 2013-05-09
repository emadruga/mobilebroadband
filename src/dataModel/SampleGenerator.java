package dataModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import misc.MysqlConnection;
import misc.QueryConstructor;

public class SampleGenerator {

	private static String queryNameUsed = "query4";
	private static String dbParametersFile = "config/dbProperties.xml";


	/**
	 * Populates <code>Sample</code>'s data 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private static Sample generateSample(ResultSet resultSet)
			throws SQLException {

		Sample oneSample = new Sample();
		oneSample.setMsgId(resultSet.getInt("ftp_msgid"));
		oneSample.setSessionId(resultSet.getInt("sessionid"));
		oneSample.setMsgTime(resultSet.getTimestamp("msgtime"));
		oneSample.setThroughput(resultSet.getInt("throughput"));
		oneSample.setLatitude(resultSet.getInt("latitude"));
		oneSample.setLongitude(resultSet.getInt("longitude"));
		oneSample.setSpeed(resultSet.getInt("speed"));
		oneSample.setCqi(resultSet.getDouble("cqi"));
		oneSample.setRscp(resultSet.getDouble("avgrscp"));
		oneSample.setEcio(resultSet.getDouble("avgecio"));
		oneSample.setPrimScCode(resultSet.getInt("primScCode"));
		oneSample.setMnc(resultSet.getInt("MNC"));
		oneSample.setSc1(resultSet.getInt("ni_sc1"));
		oneSample.setSc2(resultSet.getInt("ni_sc2"));
		oneSample.setSc3(resultSet.getInt("ni_sc3"));
		return oneSample;
	}

	public static Vector<Sample> findSamples(Vector<Integer> operators, Vector<String> campaingsList){

		// Variables Declaration
		Vector<Sample> vectorOfSamples = new Vector<Sample>();
		MysqlConnection connection = new MysqlConnection(dbParametersFile);

		String query = QueryConstructor.queryPrepare(queryNameUsed, operators, campaingsList);
		System.out.println("Sample Generator -- Query: " + query);
		connection.conectar();

		try {
			Statement stm = connection.getConexao().createStatement();
			ResultSet result = stm.executeQuery(query);

			while (result.next())
				vectorOfSamples.add(generateSample(result));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.desconectar();
		}
		System.out.println("Sample Generator -- Total of Samples: "
				+ vectorOfSamples.size());
		return vectorOfSamples;
	}

}
