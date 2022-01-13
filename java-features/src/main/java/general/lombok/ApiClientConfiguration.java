package general.lombok;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Builder(setterPrefix = "i", builderClassName = "Api", builderMethodName = "start", buildMethodName = "run")
@Slf4j
public class ApiClientConfiguration {

    @Singular("sheep")
    private List<Integer> ids;

    private String host;

    private int port;

    private boolean useHttps;

    private long connectTimeout;

    private long readTimeout;

    private String username;

    private String password;

    @SneakyThrows
    public String resourceAsString() {
        log.info("Aaaa");
        @Cleanup InputStream is = this.getClass().getResourceAsStream("sure_in_my_jar.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        return br.lines().collect(Collectors.joining("\n"));
    }

    @Synchronized
    public void putValueInCache(String key, Object value) {
        // whatever here will be thread-safe code
    }

}
