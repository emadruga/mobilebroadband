import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import aux.MysqlConnection;
import aux.QueryReader;
import dataModel.Sample;

public class SampleGenerator {

	private static String queryNameUsed = "query4";
	private static String queriesFile = "config/dbProperties.xml";

	/**
	 * Populates <code>Sample</code>'s data 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private static Sample generateSample(ResultSet resultSet)
			throws SQLException {

		Sample oneSample = new Sample();
		oneSample.setMsgId(resultSet.getInt("id"));
		oneSample.setSessionId(resultSet.getInt("SessionId"));
		oneSample.setMsgTime(resultSet.getTimestamp("MsgTime"));
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

	/**
	 * Put other filter parameters in original SQL query
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	private static String queryPrepare(String startTime, String endTime) {
		String preparedQuery = "";
		String query = QueryReader.retrieveQueryByName(queryNameUsed);
		preparedQuery = query.concat(" WHERE ");

		if ((startTime != "" || startTime != null)
				&& (endTime != "" || endTime != null))
			preparedQuery = preparedQuery.concat(" `msgtime` BETWEEN '"
					+ startTime + " 00:00:00' AND '" + endTime + " 23:59:59'");
		return preparedQuery;

	}

	
	/**
	 * Retrieves all samples described in SQL query used and put them in a Vector of Samples
	 * @param startTime
	 * @param endTime
	 * @param location
	 * @param company
	 * @return
	 */
	public static Vector<Sample> findSamples(String startTime, String endTime, String location, String company){

		// Variables Declaration
		Vector<Sample> vectorOfSamples = new Vector<Sample>();
		MysqlConnection connection = new MysqlConnection(queriesFile);

		String query = queryPrepare(startTime, endTime);
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
