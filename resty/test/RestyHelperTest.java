import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;


public class RestyHelperTest {

	@Test
	public void testCreateParams() {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put( "query1", "value1" );
		paramMap.put( "query2", "value2 with space" );
		paramMap.put( "query3", 2 );
		
		assertEquals( "?query1=value1&query2=value2 with space&query3=2", RestyHelper.createParams(paramMap) );
		assertEquals( "Null should return empty string", "", RestyHelper.createParams((Map<String,String>)null) );
		assertEquals( "Empty map should return empty string", "", RestyHelper.createParams(new HashMap<String,String>()) );
	}
	
	@Test
	public void testCreateParamsVariableArgs() throws Throwable {
		RestyHelper.createParams( "query1", "value2", "query2", "value3" );

		try {
			RestyHelper.createParams( "query1", "value2", "query2" );
			fail( "No exception thrown for odd number of parameters" );
		}
		catch( IllegalArgumentException e ) {}
		catch( Throwable e ) { throw e; }
	}
}
