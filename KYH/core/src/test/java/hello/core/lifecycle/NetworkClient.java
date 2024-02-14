package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {

    private String url;     // 접속해야 할 url

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    // url 은 외부에서 넣을 수 있도록 setter 이용
    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스를 시작할 때 호출하는 메서드
    public void connect() {
        System.out.println("connect: " + url);
    }

    // 연결이 된 후, 연결한 서버에 던질 메세지
    public void call(String message) {
        System.out.println("call: " + url + ", message = " + message);
    }

    // 서비스 종료 시 호출할 메서드
    public void disconnect() {
        System.out.println("close: " + url);
    }

    // 의존 관계 주입이 끝나면 호출되는 메서드
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    // 빈이 종료될 때 호출되는 메서드
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}