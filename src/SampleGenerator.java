import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import aux.MysqlConnection;
import aux.QueryReader;
import dataModel.Sample;

public class SampleGenerator {

	private static String queryNameUsed = "query4";

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
		oneSample.setCqi(resultSet.getInt("cqi"));
		oneSample.setRscp(resultSet.getInt("avgrscp"));
		oneSample.setEcio(resultSet.getInt("avgecio"));
		// oneSample.setPrimScCode(resultSet.getInt("primScCode"));
		return oneSample;
	}

	private static String queryPrepare(String startTime, String endTime)
			throws IOException, SQLException {
		String preparedQuery = "";
		String query = QueryReader.retrieveQueryByName(queryNameUsed);
		preparedQuery = query.concat(" WHERE ");

		if ((startTime != "" || startTime != null)
				&& (endTime != "" || endTime != null))
			preparedQuery = preparedQuery.concat(" `msgtime` BETWEEN '"
					+ startTime + " 00:00:00' AND '" + endTime + " 23:59:59'");
		return preparedQuery;

	}

	public static Vector<Sample> buscarAmostras(String startTime,
			String endTime, String location, String company)
			throws IOException, SQLException {

		// Variables Declaration
		Vector<Sample> vectorOfSamples = new Vector<Sample>();
		MysqlConnection connection = new MysqlConnection(
				"config/dbProperties.xml");

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
