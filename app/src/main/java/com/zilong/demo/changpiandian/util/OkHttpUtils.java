package com.zilong.demo.changpiandian.util;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpUtils {

	private static final OkHttpClient okHttpClient = new OkHttpClient();
	
	/**
	 * GET方式请求网络，获取Request请求对象
	 * @param urlString
	 * @return
	 */
	private static Request buildGetRequest(String urlString){
		Request request = new Request.Builder()
			.url(urlString)
			.build();
		
		return request;
		
	}
	
	/**
	 * 访问网络，获取Response响应对象
	 * @param urlString
	 * @return
	 * @throws IOException
	 */
	private static Response buildResponse(String urlString) throws IOException{
		Request request  = buildGetRequest(urlString);
		Response response = okHttpClient.newCall(request).execute();
		return response;
		
	}
	
	/**
	 * 获取ResponseBody对象
	 * @param urlString
	 * @return
	 * @throws IOException
	 */
	private static ResponseBody buildResponseBody(String urlString) throws IOException{
		Response response = buildResponse(urlString);
		if (response.isSuccessful()) {
			return response.body();
		}
		return null;
	}
	
	/**
	 * GET方式请求网络，返回字符串
	 * @param urlString
	 * @return
	 * @throws IOException
	 */
	public static String getStringFromUrl(String urlString) throws IOException{
		ResponseBody responseBody = buildResponseBody(urlString);
		if (responseBody != null) {
			return responseBody.string();
		}
		return null;
	}
	
	/**
	 * GET方式请求网络，返回字节数组
	 * @param urlString
	 * @return
	 * @throws IOException
	 */
	public static byte[] getByteFromUrl(String urlString) throws IOException{
		ResponseBody responseBody = buildResponseBody(urlString);
		if (responseBody != null) {
			return responseBody.bytes();
		}
		return null;
	}
	
	/**
	 * GET方式请求网络，返回输入流
	 * @param urlString
	 * @return
	 * @throws IOException
	 */
	public static InputStream getStreamFromUrl(String urlString) throws IOException{
		ResponseBody responseBody = buildResponseBody(urlString);
		if (responseBody != null) {
			return responseBody.byteStream();
		}
		return null;
		
	}
}
