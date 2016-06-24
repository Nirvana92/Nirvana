package com.nirvana.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HttpUtil {
	public static String LOGIN_URL = "http://192.168.1.131:8080/";

	public static String BASE_URL = "http://121.196.212.43:8080/";

	public static String getLoginUrl(String method) {
		return LOGIN_URL+"ajax/app_"+method+".jsp";
	}
	
	/**
	 * 返回每个方法请求的url
	 * @param method
	 * @return
	 */
	public static String getRequestUrl(String method) {
		return BASE_URL+"ajax/app_"+method+".jsp";
	}
	
	// 获得Get请求对象request
	/*
	public static HttpGet getHttpGet(String url) {
		HttpGet request = new HttpGet(url);
		return request;
	}


	// 获得Post请求对象request

	public static HttpPost getHttpPost(String url) {
		HttpPost request = new HttpPost(url);
		return request;
	}

	// 根据请求获得响应对象response
	public static HttpResponse getHttpResponse(HttpGet request)
			throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT,
				15000); // 超时设置
		client.getParams().setIntParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);// 连接超时
		HttpResponse response = client.execute(request);
		return response;
	}

	public static HttpResponse getHttpResponse(HttpPost request)
			throws ClientProtocolException, IOException {
		if (!"".equals(AppInstance.getInstance().getSessionContext())) {
			request.setHeader("Cookie", "JSESSIONID="
					+ AppInstance.getInstance().getSessionContext());
		}
		HttpClient client = new DefaultHttpClient();
		client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT,
				15000); // 超时设置
		client.getParams().setIntParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);// 连接超时

		HttpResponse response = client.execute(request);

		return response;
	}

	// 发送Post请求，获得响应查询结果
	public static String queryStringForPost(String url) {
		// 根据url获得HttpPost对象
		HttpPost request = HttpUtil.getHttpPost(url);
		String result = null;
		try {
			// 获得响应对象
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// 判断是否请求成功
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获得响应
				result = EntityUtils.toString(response.getEntity());
				if (result == null) {
					return "result = null";
				}
				byte[] buffer = result.getBytes("iso8859-1");
				result = new String(buffer, "GBK");
			} else if (response.getStatusLine().getStatusCode() == 404) {
				result = "404错误, 您所请求的页面不存在或链接错误!";
			} else if (response.getStatusLine().getStatusCode() == 400) {
				result = "400错误, 您的请求无效!";
			} else {
				result = "请求错误！";
			}
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
			result = "链接超时！";
			return result;
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			result = "读取数据超时！！";
			return result;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			result = "请求的网址不存在！";
			return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "非法网址！";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "网络异常！";
		}
		return null;
	}

	// 获得响应查询结果
	public static String queryStringForPost(HttpPost request) {
		String result = null;
		try {
			InputStream is = request.getEntity().getContent();
			String requeststr = inputStream2String(is);
			System.out.println("requeststr:" + requeststr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// 获得响应对象
			System.out.println(new SimpleDateFormat("HH:mm:ss")
					.format(new Date()));
			HttpResponse response = HttpUtil.getHttpResponse(request);
			System.out.println(new SimpleDateFormat("HH:mm:ss")
					.format(new Date()));
			// 判断是否请求成功
			if (response.getStatusLine().getStatusCode() == 200) {
				if ("".equals(AppInstance.getInstance().getSessionContext()) || request.getURI().toString().contains("innerLogin")) {
					Header[] headers = response.getAllHeaders();
					for (Header header : headers) {
						if (header.getName().equals("Set-Cookie")) {
							AppInstance.getInstance()
									.setSessionContext(
											header.getValue().split(";")[0]
													.split("=")[1]);
						}
					}
				}

				// 获得响应
				result = EntityUtils.toString(response.getEntity());
				if ("尚未登录！".equals(result.trim())) {
					return result.trim();
				}
				//Log.i(Config.LOG, ">>result: "+result);
				result = DES.decryptDES(result);
				System.out.println("result:" + result);
				if (result == null || result.trim().equals("")) {
					return null;
				}
				byte[] buffer = result.getBytes("UTF-8");
				result = new String(buffer, "UTF-8");
				// System.out.println("httputil result =" + result);
			} else if (response.getStatusLine().getStatusCode() == 404) {
				result = "404错误, 您所请求的页面不存在或链接错误!";
			} else if (response.getStatusLine().getStatusCode() == 400) {
				result = "400错误, 您的请求无效!";
			} else {
				result = "请求错误！";
			}
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
			result = "链接超时！";
			return result;
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			result = "读取数据超时！！";
			return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "您的请求不符合HTTP协议！";
			// return null;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			result = "请求的网址不存在！";
			return result;
		} catch (HttpHostConnectException e) {
			e.printStackTrace();
			result = "无法到连接您请求的地址！";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "网络异常！";
			// return null;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "非法网址！";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "网络异常！";
		}
		return result;
	}

	// 发送Get请求，获得响应查询结果
	public static String queryStringForGet(String url) {
		// 获得HttpGet对象
		HttpGet request = HttpUtil.getHttpGet(url);
		String result = null;
		try {
			// 获得响应对象

			HttpResponse response = HttpUtil.getHttpResponse(request);

			// 判断是否请求成功
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获得响应
				result = EntityUtils.toString(response.getEntity());
				if (result == null) {
					return "result = null";
				}
				byte[] buffer = result.getBytes("iso8859-1");
				result = new String(buffer, "GBK");
			} else if (response.getStatusLine().getStatusCode() == 404) {
				result = "404错误, 您所请求的页面不存在或链接错误!";
			} else if (response.getStatusLine().getStatusCode() == 400) {
				result = "400错误, 您的请求无效!";
			} else {
				result = "请求错误！";
			}
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
			result = "链接超时！";
			return result;
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			result = "读取数据超时！！";
			return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "您的请求不符合HTTP协议！";
			// return null;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			result = "请求的网址不存在！";
			return result;
		} catch (HttpHostConnectException e) {
			e.printStackTrace();
			result = "无法到连接您请求的地址！";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "网络异常！";
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "非法网址！";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "网络异常！";
		}
		return result;
	}

	public static Bitmap getFoodImage(String url) {
		Bitmap foodImag = null;
		try {
			URL imageurl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) imageurl
					.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream in = conn.getInputStream();
			foodImag = BitmapFactory.decodeStream(in);
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return foodImag;
	}
	*/

	public static boolean isConn(Context context) {
		boolean bisConnFlag = false;
		ConnectivityManager conManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo network = conManager.getActiveNetworkInfo();
		if (network != null) {
			bisConnFlag = conManager.getActiveNetworkInfo().isAvailable();
		}
		return bisConnFlag;
	}

	/*
	public static void setNetworkMethod(final Context context) {
		
		AlertView alertView = new AlertView("网络设置提示", "网络连接不可用,是否进行设置?", "取消", new String[]{"设置"}, null, context, AlertView.Style.Alert, new OnItemClickListener() {
			@Override
			public void onItemClick(Object o, int position) {
				// TODO Auto-generated method stub
				if(position == 0) {
					Intent intent = null;
					// 判断手机系统的版本 即API大于10 就是3.0或以上版本
					if (android.os.Build.VERSION.SDK_INT > 10) {
						intent = new Intent(
								android.provider.Settings.ACTION_WIRELESS_SETTINGS);
					} else {
						intent = new Intent();
						ComponentName component = new ComponentName(
								"com.android.settings",
								"com.android.settings.WirelessSettings");
						intent.setComponent(component);
						intent.setAction("android.intent.action.VIEW");
					}
					
					context.startActivity(intent);
				}
			}
		});
		
		alertView.show();
	}

	public static String inputStream2String(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}

	public static String getAddr(String url) {
		String result = "";
		StringBuffer buffer = new StringBuffer();

		try {
			URL u = new URL(url);
			InputStream is = u.openStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			while (true) {
				String s = reader.readLine();
				if (s == null) {
					break;
				} else {
					result += s;
				}
			}
			Document document = DocumentHelper.parseText(result);
			String province = document.getRootElement().element("result")
					.element("addressComponent").elementText("province");
			String city = document.getRootElement().element("result")
					.element("addressComponent").elementText("city");
			String district = document.getRootElement().element("result")
					.element("addressComponent").elementText("district");
			String formatted_address = document.getRootElement()
					.element("result").elementText("formatted_address");

			buffer.append("省：");
			buffer.append(province);
			buffer.append("\n市：");
			buffer.append(city);
			buffer.append("\n区/县：");
			buffer.append(district);
			buffer.append("\n详址 : ");
			buffer.append(formatted_address);

			if (("".equals(province) || province == null)
					&& ("".equals(city) || city == null)
					&& ("".equals(district) || district == null)
					&& ("".equals(formatted_address) || formatted_address == null)) {
				result = "";
			} else {
				result = buffer.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return result;
	}
	*/
}
