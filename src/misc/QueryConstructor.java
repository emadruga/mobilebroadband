package misc;
import java.util.Vector;



public class QueryConstructor {
	


	private static String buildOperatorsFilter(Vector<Integer> operators) {
		String query = "";
		for (int i = 0; i < operators.size(); i++) {
			query = query.concat(" `MNC` = "+operators.get(i));
			if (i != operators.size()-1)
				query = query.concat(" OR");
		}
		query="("+query+")";
		return query;

	}
	
	private static String buildCampaignsList(Vector<String> campaigns) {
		String query = "";
		for (int i = 0; i < campaigns.size(); i++) {
			query = query.concat(" DATE(`msgtime`) = '"+campaigns.get(i)+"'");
			if (i != campaigns.size()-1)
				query = query.concat(" OR");
		}
		query="("+query+")";
		return query;
	}
	
	
	public static String queryPrepare(String queryNameUsed, Vector<Integer> operators, Vector<String> campaignsList) {
		String preparedQuery;
		String query = QueryReader.retrieveQueryByName(queryNameUsed);
		
		
		
		
		if (campaignsList != null)
			preparedQuery = query.replace("WHERECLAUSULE", buildOperatorsFilter(operators)+" AND "+buildCampaignsList(campaignsList));
		else
			preparedQuery = query.replace("WHERECLAUSULE", buildOperatorsFilter(operators));
		
		preparedQuery = preparedQuery.replace("TABLENAME", MysqlConnection.table);
		
		
		return preparedQuery;

	}
	
	
}
