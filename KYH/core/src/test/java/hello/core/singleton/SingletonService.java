package hello.core.singleton;

public class SingletonService {

    // 자기 자신을 내부에 private static 으로 가진다. 이렇게 하면 클래스 레벨에 올라가기 때문에, 딱 하나만 존재하게 된다.
    // 여기서 new SingletonService() 객체를 딱 한 번 생성해서 가진다.
    private static final SingletonService instance = new SingletonService();

    // 조회하려면 아래처럼 만들면 됨. (필요하면 사용할 수 있도록)
    // 즉, 위에서 만든 객체를 꺼낼 수 있는 방법은 이 방법 밖에 없음. (getInstance() 메서드 호출)
    // getInstance() 메서드를 호출하면, 항상 같은 인스턴스를 return 함.
    public static SingletonService getInstance() {
        return instance;
    }

    // ** 외부에서 new 키워드를 이용하여 객체를 여러 개 만들지 못하게 private 으로 기본 생성자 만들어두기
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}