import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.w3c.dom.NodeList;

import us.monoid.web.Resty;
import us.monoid.web.XMLResource;

public class RestyTest {
	
	@Test
	public void testJsonResponse() throws Exception {
		assertEquals( "Rochester", new Resty().json("http://ws.geonames.org" + RestyHelper.createParamsWithResource("postalCodeLookupJSON", "postalcode", "14623")).get("postalcodes[0].placeName") );
	}
	
	@Test
	public void testXmlResponse() throws Exception {
		XMLResource data = new Resty().xml( "http://graphical.weather.gov/xml/sample_products/browser_interface/ndfdXMLclient.php?lat=38.99&lon=-77.01&product=time-series&begin=2004-01-01T00:00:00&end=2013-04-20T00:00:00&maxt=maxt&mint=mint");
		NodeList nameValues = data.get( "//data/parameters/*/name" );
		assertEquals( "Daily Maximum Temperature", nameValues.item(0).getTextContent() );
		assertEquals( "Daily Minimum Temperature", nameValues.item(1).getTextContent() );
	}
}