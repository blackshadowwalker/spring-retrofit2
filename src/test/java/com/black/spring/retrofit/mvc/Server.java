package com.black.spring.retrofit.mvc;

import com.alibaba.fastjson.JSON;
import com.black.spring.retrofit.bean.Page;
import com.black.spring.retrofit.bean.ResponseDto;
import com.black.spring.retrofit.bean.User;
import com.google.common.collect.Lists;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 9/2 0002.
 */
public class Server {
    private static Logger log = LoggerFactory.getLogger(Server.class);

    MockWebServer server1 = new MockWebServer();
    MockWebServer server2 = new MockWebServer();

    public void start() throws Exception {
        Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest req) throws InterruptedException {
                try {
                    Map<String, String> param = parseParameters(req);
                    String requestURI = req.getPath();
                    int idx = requestURI.indexOf("?");
                    if (idx > 0) {
                        requestURI = requestURI.substring(0, idx);
                    }

                    if ("GET".equalsIgnoreCase(req.getMethod()) && "/api/users".equals(requestURI)) {
                        String _pageNum = param.get("pageNum");
                        String _pageSize = param.get("pageSize");
                        Assert.notNull(_pageNum);
                        Assert.notNull(_pageSize);
                        Integer pageNum = Integer.parseInt(_pageNum);
                        Integer pageSize = Integer.parseInt(_pageSize);

                        //return JSON(ResponseDto<Page<List<User>>>)
                        List<User> users = Lists.<User>newArrayList(new User(1L, "a", "a@m.com"), new User(2L, "b", "b@m.com"));
                        Page<List<User>> page = new Page<List<User>>();
                        page.setPageNum(pageNum);
                        page.setPageSize(pageSize);
                        page.setResult(users);
                        String body = JSON.toJSONString(new ResponseDto<Page<List<User>>>(page));
                        return new MockResponse().setResponseCode(200).setBody(body);

                    } else if ("GET".equalsIgnoreCase(req.getMethod()) && "/api/hello".equals(requestURI)) {
                        //return JSON<ResponseDto<String>>
                        String data = "Hello " + param.get("name");
                        String body = JSON.toJSONString(new ResponseDto<String>(data));
                        return new MockResponse().setResponseCode(200).setBody(body);
                    }

                } catch (Exception e) {
                    log.error(req.getPath(), e);
                    return new MockResponse().setResponseCode(500).setStatus(e.getMessage());
                }
                return new MockResponse().setResponseCode(404);
            }
        };

        server1.setDispatcher(dispatcher);
        server1.start(8081);
        server2.setDispatcher(dispatcher);
        server2.start(8082);
    }

    private Map<String, String> parseParameters(RecordedRequest request) throws URISyntaxException {
        Map<String, String> parameters = new HashMap<String, String>();
        HttpUrl url = HttpUrl.parse("http://127.0.0.1" + request.getPath());
        for (String name : url.queryParameterNames()) {
            parameters.put(name, url.queryParameter(name));
        }
        return parameters;
    }

    public void stop() throws Exception {
        server1.shutdown();
        server2.shutdown();
    }


}
