package dataForTests;

import java.util.HashMap;

public interface Headers {
    HashMap<String, String> defaultHeaders = new HashMap() {{
        put("Accept-Encoding", "gzip, deflate, br");
        put("Connection", "keep-alive");
        put("Content-Type", "application/json");
        put("Accept", "*/*");
    }};
}
