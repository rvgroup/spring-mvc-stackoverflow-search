package helper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

import org.apache.commons.io.IOUtils;

public class HttpHelper {
	public static String getUrlResponse(String url) throws Exception {
		InputStream in = null;
		
		URL urlObj = new URL(url);
		URLConnection con = urlObj.openConnection();
		
		String s = con.getContentEncoding();

		in = con.getInputStream();
		
		String encoding = con.getContentEncoding();
		encoding = encoding == null ? "UTF-8" : encoding;
		
		String body;
		
		if (encoding.equals("gzip"))
		{
			byte[] bodyArr = IOUtils.toByteArray(in);
			
			body = getGZipArrayString(bodyArr);
		}
		else {
			body = IOUtils.toString(in, encoding);
		}
		
		
		return body;
	}
	
	private static String getGZipArrayString(byte[] bodyArr) throws Exception 
	{
		ByteArrayInputStream bais = new ByteArrayInputStream(bodyArr);
		GZIPInputStream gzis = new GZIPInputStream(bais);
		
		Reader reader = new InputStreamReader(gzis, "UTF-8");
	    Writer writer = new StringWriter();
				
		char[] buffer = new char[10240];
	    for (int length = 0; (length = reader.read(buffer)) > 0;) {
	        writer.write(buffer, 0, length);
	    }
	    
	    return writer.toString();
	}
}
