package com.ui.automation.selenium.wd.angular.provider;

import com.ui.automation.selenium.wd.angular.AngularModuleProvider;
import org.springframework.stereotype.Component;

/**
 * Created by peere on 08/04/2014.
 */
@Component
public class AngularHttpRequestResponseModuleProvider implements AngularModuleProvider {

    private static final String MODULE_NAME = "httpRequester";

    @Override
    public String getModuleName() {
        return MODULE_NAME;
    }

    @Override
    public String getModuleDefinition() {
        return "angular.module('" + MODULE_NAME + "', [])\n"
                + " .config(['$provide', function ($provide) {\n"
                + "         $provide.decorator('$httpBackend',\n"
                + "            ['$delegate', function ($delegate) {\n"
                + "                 window.automationHTTPList = window.automationHTTPList || {};\n"
                + "                 return function httpBackendDecorator() {\n"
                + "                     var responseCallback = arguments[3];\n"
                + "                     var reqTimestamp = new Date().getTime();\n"
                + "                     arguments[3] = (function (reqMethod,reqUrl,reqData,reqHeaders) {\n"
                + "                         return function(status, response, headerString) {\n"
                + "                             if(!window.automationHttpStart) {\n"
                + "                                 var respTimestamp = new Date().getTime()\n"
                + "                                 var rtt = respTimestamp - reqTimestamp;\n"
                + "                                 console.error('$httpBackend ' + reqMethod + ' ' + status + ' time=' + rtt + 'ms url=[' + reqUrl + ']');\n"
                + "                                 responseCallback.apply(this, arguments);\n"
                + "                             }\n"
                + "                             else {\n"
                + "                                 var respTimestamp = new Date().getTime()\n"
                + "                                 var id = Math.random().toString(36).substr(2, 7);\n"
                + "                                 var rtt = respTimestamp - reqTimestamp;\n"
                + "                                 window.automationHTTPList[id] = {};\n"
                + "                                 window.automationHTTPList[id]['rtt'] = respTimestamp - reqTimestamp;\n"
                + "                                 window.automationHTTPList[id]['requestTimestamp'] = reqTimestamp;\n"
                + "                                 window.automationHTTPList[id]['responseTimestamp'] = respTimestamp;\n"
                + "                                 window.automationHTTPList[id]['requestMethod'] = reqMethod;\n"
                + "                                 window.automationHTTPList[id]['requestUrl'] = reqUrl;\n"
                + "                                 window.automationHTTPList[id]['requestHeadersLength'] = JSON.stringify(reqHeaders).length;\n"
				+ "                                 window.automationHTTPList[id]['requestData'] = reqData!=undefined? reqData:'{}';\n"
				+ "                                 window.automationHTTPList[id]['requestDataLength'] = reqData!=undefined?reqData.length:0;\n"
                + "                                 window.automationHTTPList[id]['responseStatus'] = status;\n"
                + "                                 window.automationHTTPList[id]['responseHeadersLength'] = headerString.length;\n"
                + "                                 window.automationHTTPList[id]['responseDataLength'] = response.length;\n"
                + "                                 console.error('$httpBackend ' + reqMethod + ' ' + status + ' time=[' + rtt + '] url=[' + reqUrl + ']');\n"
                + "                                 responseCallback.apply(this, arguments);\n"
                + "                             }\n"
                + "                         }"
                + "                     })(arguments[0],arguments[1],arguments[2],arguments[4]);\n"
                + "                      $delegate.apply(this, arguments);\n"
                + "                  }"
                + "             }"
                + "         ])"
                + "}]);";
    }
}
