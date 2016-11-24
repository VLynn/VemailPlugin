package org.elasticsearch.service;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.rest.*;

/**
 * Created by lynn_lin on 2016/11/24.
 */
public class MyAnalysisService implements RestHandler{

    @Override
    public void handleRequest(RestRequest restRequest, RestChannel restChannel) throws Exception {
        String who = restRequest.param("who");
        String whoSafe = (who == null) ? "world" : who;
        restChannel.sendResponse(new BytesRestResponse(RestStatus.OK, "hello, " + whoSafe));
    }

    @Inject
    public void helloRestHandler(RestController restController) {
        restController.registerHandler(RestRequest.Method.GET, "/hello", this);
    }
}
