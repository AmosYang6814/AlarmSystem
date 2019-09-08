package Util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.*;
import org.apache.http.HttpEntity;

import org.apache.http.entity.StringEntity;

import com.google.gson.Gson;

import android.os.AsyncTask;
import android.util.Log;

public abstract class Net extends AsyncTask<String,String,String> {

	private String uri="";
	private String String_resposes="";
	private HttpEntity entity;
	private String ObjectGson;
	private String sentity;
	private Response response;
	private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

	private int classfy;
	
	public String getString_respose(){
		return String_resposes;
		
	}
	public Response getrespose(){
		return response;
	}
	
	public Net(String uri,HttpEntity entity){
		this.uri=uri;
		this.entity=entity;
		this.classfy=classfy;
		Log.i("Net_link", "uri="+uri+",entity="+entity.toString());
		Log.i("Net_link",entity.getContentType().toString());
	}
	
	public Net(String uri,Object object) throws UnsupportedEncodingException{
		this(uri, new Gson().toJson(object));
	}
	
	public Net(String uri,String sentity) throws UnsupportedEncodingException{
		this(uri,new StringEntity(sentity));
		this.ObjectGson=sentity;
	}
	
	@Override
	protected String doInBackground(String... params) {
		// TODO �Զ����ɵķ������

		Log.i("Net跟踪","进入Net方法");
		Log.i("Net追踪",ObjectGson);
			//采用OKHttp进行封装

			OkHttpClient client=new OkHttpClient.Builder()
					.cookieJar(new CookieJar() {
						@Override
						public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
							cookieStore.put(httpUrl.host(), list);
						}

						@Override
						public List<Cookie> loadForRequest(HttpUrl httpUrl) {
							List<Cookie> cookies = cookieStore.get(httpUrl.host());
							return cookies != null ? cookies : new ArrayList<Cookie>();
						}
					}).build();

			MediaType JSON=MediaType.parse("application/Json; charset=utf-8");
			RequestBody requestBody=RequestBody.create(JSON,ObjectGson);
			Request request = new Request.Builder()
					.url(uri)
					.post(requestBody)
					.build();

			client.newCall(request).enqueue(
					new Callback() {
						@Override
						public void onResponse(Call call, Response response) throws IOException {
							OnResponse();
						}

						@Override
						public void onFailure(Call call, IOException e) {
							OnFailure();

							Log.i("Net追踪",e.toString());

						}
					});//此处省略回调方法。
		return getString_respose();
	}
	
	@Override
	protected  void onPostExecute(String result){}

	public abstract void OnResponse();
	public abstract void OnFailure();

}
