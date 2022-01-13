package general;

import general.lombok.ApiClientConfiguration;

public class MAIN {

    public static void main(String[] args) {
        ApiClientConfiguration config = ApiClientConfiguration.start()
                                                              .iHost("api.server.com")
                                                              .iPort(443)
                                                              .iUseHttps(true)
                                                              .iConnectTimeout(15_000L)
                                                              .iReadTimeout(5_000L)
                                                              .iUsername("myusername")
                                                              .iPassword("secret")
                                                              .run();
    }

}
