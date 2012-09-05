import java.util.HashMap;
import java.util.Map;

/**
 * Helper class to assist in making URL strings and other nice things.
 *  
 * @author <a href="mailto:ryan.schneider@level3.com">Ryan Schneider</a>
 *
 */
public class RestyHelper {

	public static String createParams( Map<String,?> queryParams ) {
		StringBuilder str = new StringBuilder("?");
		if( queryParams != null ) {
			for( String key : queryParams.keySet() ) {
				str.append( key + "=" + queryParams.get(key) + "&" );
			}
		}
		return (str.equals("?") ? "" : str.deleteCharAt(str.length()-1)).toString();
	}
	
	public static Map<String,?> createMapFromVariableArgs( Object ... args) {
		if( args.length % 2 != 0 ) {
			throw new IllegalArgumentException( "Must be even number of query/values" );
		}
		
		Map<String,Object> queryMap = new HashMap<String,Object>();
		for( int i=0; i < args.length; i++ ) {
			queryMap.put( args[i].toString(), args[++i] );
		}
		return queryMap;
	}
	
	public static String createParamsWithResource( String resource, Map<String,?> queryParams ) {
		return "/" + resource + createParams( queryParams );
	}
	
	public static String createParams( Object ... queryValueParams ) {
		return createParams( createMapFromVariableArgs(queryValueParams) );
	}
	
	public static String createParamsWithResource( String resource, Object ... queryValueParams ) {
		return createParamsWithResource( resource, createMapFromVariableArgs(queryValueParams) );
	}
}
