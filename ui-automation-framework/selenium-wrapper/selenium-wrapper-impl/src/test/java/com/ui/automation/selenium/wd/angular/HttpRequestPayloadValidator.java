package com.ui.automation.selenium.wd.angular;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by katzirn on 06/01/2015.
 */
public interface HttpRequestPayloadValidator {
	boolean validate(JSONObject payload) throws JSONException;
}
