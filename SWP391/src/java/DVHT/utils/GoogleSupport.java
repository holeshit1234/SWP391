package DVHT.utils;
import DHTV.googlesignin.GoogleDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

public class GoogleSupport {
     public static String getToken(String code) throws ClientProtocolException, IOException {
		// call api to get token
		String response = Request.Post(Constant.GOOGLE_LINK_GET_TOKEN)
				.bodyForm(Form.form().add("client_id", Constant.GOOGLE_CLIENT_ID)
						.add("client_secret", Constant.GOOGLE_CLIENT_SECRET)
						.add("redirect_uri", Constant.GOOGLE_REDIRECT_URI).add("code", code)
						.add("grant_type", Constant.GOOGLE_GRANT_TYPE).build())
				.execute().returnContent().asString();

		JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
		String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
		return accessToken;
	}

	public static GoogleDTO getUserInfo(final String accessToken) 
                throws ClientProtocolException, IOException {
		String link = Constant.GOOGLE_LINK_GET_USER_INFO + accessToken;
		String response = Request.Get(link).execute().returnContent().asString();

		GoogleDTO user = new Gson().fromJson(response, GoogleDTO.class);

		return user;
	}
}
