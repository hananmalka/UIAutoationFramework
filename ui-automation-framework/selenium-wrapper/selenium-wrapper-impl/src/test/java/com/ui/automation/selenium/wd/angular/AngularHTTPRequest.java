package com.ui.automation.selenium.wd.angular;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 18/05/14
 * Time: 12:01
 * To change this template use File | Settings | File Templates.
 */
public class AngularHTTPRequest {

    private JSONObject json = null;


    public AngularHTTPRequest(JSONObject json) {
        this.json = json;
    }

    public int getTotalRequests(){
        return json.length();
    }

    /**
     * Count the total size of all requests headers
     *
     * @return size in bytes
     */
    public int getTotalRequestsHeaderSize() {
        int headerSize = 0;
        Iterator<?> keys = json.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            JSONObject jobj = null;
            try {
                jobj = (JSONObject) json.get(key);
                headerSize += (int) jobj.get("requestHeadersLength" );
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return headerSize;
    }

    /**
     * Count the total size of all responses headers
     *
     * @return size in bytes
     */
    public int getTotalResponsesHeaderSize() {
        int headerSize = 0;
        Iterator<?> keys = json.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            JSONObject jobj = null;
            try {
                jobj = (JSONObject) json.get(key);
                headerSize += (int) jobj.get("responseHeadersLength" );
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return headerSize;
    }

    /**
     * Count the total size of all responses data
     *
     * @return size in bytes
     */
    public int getTotalResponsesDataSize() {
        int dataSize = 0;
        Iterator<?> keys = json.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            JSONObject jobj = null;
            try {
                jobj = (JSONObject) json.get(key);
                dataSize += (int) jobj.get("responseDataLength" );
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return dataSize;
    }

    /**
     * Count the total size of all requests data (only if it POST method request so it have data)
     *
     * @return size in bytes
     */
    public int getTotalRequestsDataSize() {
        int dataSize = 0;
        Iterator<?> keys = json.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            JSONObject jobj = null;
            try {
                jobj = (JSONObject) json.get(key);
                dataSize += (int) jobj.get("requestDataLength" );
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return dataSize;
    }

    /**
     * Get the total number of the POST Requests
     *
     * @return
     */
    public int getNumberOfPostRequests() {
        int numOfHttp = 0;
        Iterator<?> keys = json.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            JSONObject jobj = null;
            try {
                jobj = (JSONObject) json.get(key);
                if (jobj.get("requestMethod" ).equals("POST" )) {
                    numOfHttp++;
                }
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return numOfHttp;
    }

    /**
     * Get the total number of the GET Requests
     *
     * @return
     */
    public int getNumberOfGetRequests() {
        int numOfHttp = 0;
        Iterator<?> keys = json.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            JSONObject jobj = null;
            try {
                jobj = (JSONObject) json.get(key);
                if (jobj.get("requestMethod" ).equals("GET" )) {
                    numOfHttp++;
                }
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return numOfHttp;
    }

    /**
     * Return the JSON object of the longest RTT (Round Trip Time)
     *
     * @return
     */
    public JSONObject getLongestRTTObj() {
        JSONObject longestRttObj = null;
        int logestRTT = 0;
        Iterator<?> keys = json.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            JSONObject jobj = null;
            try {
                jobj = (JSONObject) json.get(key);
                int rtt = (int) jobj.get("rtt" );
                if (rtt > logestRTT) {
                    logestRTT = rtt;
                    longestRttObj = jobj;
                }
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return longestRttObj;
    }

    /**
     * Return average RTT (Round Trip Time)
     *
     * @return
     */
    public double getAvgRTT() {
        int rtt = 0;
        int rttCount = 0;
        Iterator<?> keys = json.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            JSONObject jobj = null;

            try {
                jobj = (JSONObject) json.get(key);
                rtt += (int) jobj.get("rtt");
                rttCount++;
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        if (rttCount > 0) {
            return rtt / rttCount;
        } else {
            return 0;
        }

    }

    /**
     * Return the average headers size of the requests
     *
     * @return
     */
    public double getAvgRequestsHeaderSize() {
        int reqHeadersSize = 0;
        int reqSum = 0;
        Iterator<?> keys = json.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            JSONObject jobj = null;
            try {
                jobj = (JSONObject) json.get(key);
                reqHeadersSize += (int) jobj.get("requestHeadersLength" );
                reqSum++;
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        if (reqSum > 0) {
            return reqHeadersSize / reqSum;
        } else {
            return 0;
        }


    }

    /**
     * Return the average headers size of the responses
     *
     * @return
     */
    public double getAvgResponsesHeaderSize() {
        int respHeadersSize = 0;
        int resSum = 0;
        Iterator<?> keys = json.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            JSONObject jobj = null;
            try {
                jobj = (JSONObject) json.get(key);
                respHeadersSize += (int) jobj.get("responseHeadersLength" );
                resSum++;
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        if (resSum > 0) {
            return respHeadersSize / resSum;
        } else {
            return 0;
        }

    }

    /**
     * Return the average data size of the responses
     *
     * @return
     */
    public double getAvgResponsesDataSize() {
        int reqPostSize = 0;
        int postSum = 0;
        Iterator<?> keys = json.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            JSONObject jobj = null;
            try {
                jobj = (JSONObject) json.get(key);
                reqPostSize += (int) jobj.get("responseDataLength" );
                postSum++;

            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        if (postSum > 0) {
            return reqPostSize / postSum;
        } else {
            return 0;
        }

    }

    /**
     * Return the average data size of the requests (request have data size only if it is POST)
     *
     * @return
     */
    public double getAvgRequestsDataSize() {
        int reqPostSize = 0;
        int postSum = 0;
        Iterator<?> keys = json.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            JSONObject jobj = null;
            try {
                jobj = (JSONObject) json.get(key);
                String method = (String) jobj.get("requestMethod" );
                if (method.equals("POST" )) {
                    reqPostSize += (int) jobj.get("requestDataLength" );
                    postSum++;
                }
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        if (postSum > 0) {
            return reqPostSize / postSum;
        } else {
            return 0;
        }
    }

	public int getPostRequestCount(String urlRegex,HttpRequestPayloadValidator payloadValidator) {
		int requestsCounter = 0;

		Iterator<?> keys = json.keys();
		while (keys.hasNext()) {
			String key = (String) keys.next();

			JSONObject jobj = null;
			try {
				jobj = (JSONObject) json.get(key);
				String method = jobj.getString("requestMethod" );
				String requestUrl = jobj.getString("requestUrl");
				boolean requestData;
				//running the payload callback
				try {
					requestData = payloadValidator.validate(new JSONObject(jobj.getString("requestData")));
				} catch (JSONException e) {
					requestData = false;
				}

				if (method.equals("POST" ) && requestUrl.matches("(.*)"+urlRegex+"(.*)") && requestData) {
					requestsCounter++;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}


		return requestsCounter;
	}

    /**
     * Return response JSON object of the biggest data size
     *
     * @return
     */
    public JSONObject getBiggestDataResponseObj() {
        JSONObject biggestDataRequest = null;
        int respDataSize = 0;
        Iterator<?> keys = json.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            JSONObject jobj = null;
            try {
                jobj = (JSONObject) json.get(key);
                int dataSize = (int) jobj.get("responseDataLength" );
                if (dataSize > respDataSize) {
                    respDataSize = dataSize;
                    biggestDataRequest = jobj;
                }
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return biggestDataRequest;
    }


    /**
     * get the status codes and the counters of each one
     *
     * @return Map(HttpStatusCode, Count)
     */
    public Map<Integer, Integer> getResponsesStatus() {
        Map<Integer, Integer> statusMap = new HashMap<>();
        Iterator<?> keys = json.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            JSONObject jobj = null;
            try {
                jobj = (JSONObject) json.get(key);
                Integer status = (Integer) jobj.get("responseStatus" );
                Integer keyMap = statusMap.get(status);
                if (keyMap == null) {
                    statusMap.put(status, 0);
                }

                statusMap.put(status, statusMap.get(status) + 1);


            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return statusMap;
    }

}
