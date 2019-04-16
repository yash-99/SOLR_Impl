package upload;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public class UploadData {

	public void uploadToSolr(String id, String textData) throws Exception {

		JSONObject fileDetails = new JSONObject();
		JSONObject descr = new JSONObject();
		JSONObject textDetails = new JSONObject();

		textDetails.put("id", id);
		textDetails.put("text", textData);
		descr.put("doc", textDetails);
		descr.put("overwrite", true);
		descr.put("commitWithin", 5000);
		fileDetails.put("add", descr);
		System.out.println("data " + textData);
		System.out.println(fileDetails.toString());

		StringEntity entity = new StringEntity(fileDetails.toString(), ContentType.APPLICATION_JSON);

		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost("http://victadpst-08:8983/solr/solr_sample/update");
		request.setEntity(entity);

		HttpResponse response = httpClient.execute(request);
		System.out.println("Status " + response.getStatusLine().getStatusCode());
	}

}
