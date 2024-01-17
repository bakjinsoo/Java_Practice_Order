# 자바 공부

## [단축키](#intelliJ-단축키)

## [자바개념](#자바-개념)

## [객체지향설계 5가지 원칙](#좋은-객체-지향-설계의-5가지-원칙)

## [IOC, DI](#IOC)

## [싱글톤](#-싱글톤)

### intelliJ 단축키

## 테스트 코드는 항상 실패 코드로

+ Ctrl+Shift+T 테스트코드를 작성하는 단축키이다. 

+ Ctrl+Alt+M 리팩토링 단축키이다. 

+ Ctrl+Shift+Enter : 문장 자동완성 단축키. 중괄호 및 세미콜론 자동완성 

+ 리팩토링이란 외부에서 보는 프로그램 동작은 바꾸지 않고 프로그램 내부개조를 개선하는 것을 의미한다.

  + 예) 변수 할당.

  + 리팩토링이 필요한 코드
    + 중복 코드
    + 너무 긴 메서드
    + 객체지향적이지 않은 코드

+ Alt+Insert 패키지, 클래스 생성 단축키, 생성자,  Getter Setter 단축키
+ Alt+Enter 오류해결
+ Ctrl+Alt+V 객체 자동생성

  
  + 예시


```
new Member(memberId,"memberA", Grade.VIP)
```

를 누르고 이 단축키를 누르면

```
Member member=new Member(memberId,"memberA", Grade.VIP);
```

이렇게 객체를 자동으로 생성해준다.

+ static import

메소드 및 변수를 패키지, 클래스명 없이 접근가능하게 해준다.

코드의 가독성을 높여주게 한다는 장점이 존재한다.

하지만 잘못 사용하는경우에는 가독성을 더 떨어트릴수 있으므로 주의하자.

+ static import를 잘 사용한 예시
  
```
Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
```

static import를 사용하여 코드의 가독성을 높인것을 볼 수있다.

```
import static org.assertj.core.api.Assertions.*;
assertThat(discount).isEqualTo(1000);
```

+ static import 를 잘못 사용한 예시
  
```
import static java.lang.System.out;
out.print(myDoc);   
```

+ Ctrl+e 과거 코드를 고쳤던 히스토리가 나온다.

  ![image](https://github.com/bakjinsoo/Java_Practice_Order/assets/77185565/bc8d08a1-b168-4cd7-b385-edb94826e89f)

+ iter 탭 : iterator 배열이 있으면 for문 자동완성

+ soutv : System.out.println("변수"+변수); 변수 println

+ shift 두번 누르기 : 클래스, 인터페이스 파일 찾기
  
### 자바 개념

+ 다형성
  + 역할, 구현으로 구분 -> 단순하고 유연하게 변경이 가능하다.

+ 역할 - > 인터페이스
+ 구현 - > 클래스, 객체

+ 인터페이스 -> 안정적으로 잘 설계해야한다.

+ 어노테이션 :

  + @로 나타남.
    + 컴파일러에게 문법 에러를 체크하도록 정보를 제공한다.
    + 프로그램을 빌드할 때 코드를 자동으로 생성할 수 있도록 정보를 제공한다.
    + 런타임에 특정 기능을 실행하도록 정보를 제공한다.
      
  예시
  
    ```
    @Test
    ```

## 좋은 객체 지향 설계의 5가지 원칙

### SRP 단일 책임원칙

__"한 클래스는 하나의 책임만 가져야 한다."__

### DIP 의존관계 역전 원칙

__프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다." 의존성 주입은 이 원칙을 따르는 방법 중 하나다.__

### OCP

__소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야한다.__


```
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository=new MemberServiceImpl();
    private final DiscountPolicy discountPolicy=new FixDiscountPolicy();
    
```

OrderServiceImpl은 DIP를 지키며, DiscountPolicy 추상화 인터페이스에 의존하는거 같았지만 FixDiscountPolicy 구체화 구현클래스에도 함께 의존했다.

클라이언트 코드가 DiscountPolicy 추상화 인터페이스에만 의존하도록 코드를 변경했다.

AppConfig가 FixDiscountPolicy 객체 인스턴스를 클라이언트 코드 대신 생성해서 클라이언트 코드에 의존관계를 주입했다.

```
public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    public OrderService orderService() {//생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
```

애플리케이션을 __사용영역__ 과 __구성영역__ 으로 나눔.

AppConfig가 의존관계를 FixDiscountPolicy-> RateDiscountPolicy로 변경해서 클라이언트 코드에 주입하므로 클라이언트 코드는 변경하지 않아도 됨.

이를 통해 __소프트웨어요소를 새롭게 확장해도 사용영역의 변경은 닫혀있다!__ 라는것을 알 수 있음.

클라이언트 코드를 변경할 필요가 없고 외부에서 코드만 변경하면 된다. __역할과 구현의 세분화의 필요성(객체지향설계의 중요성)__

### IOC

제어의 역전

프레임워크랑 유사.

내 코드를 대신 호출함

제어권이 뒤바뀐다. 제어의 역전

__프레임워크 라이브러리 구분__

프레임워크가 내가 작성한 코드를 제어하고, 대신 실행하면 그것은 프레임워크가 맞다

JUnit

```
MemberService memberService;
    OrderService orderService;
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig=new AppConfig();
        memberService=appConfig.memberService();
        orderService=appConfig.orderService();
    }
    @Test
    void createOrder() {
        Long memberId=1L;
        Member member=new Member(memberId,"memberA",Grade.VIP);
        memberService.join(member);

        Order order=orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

```
다음과 같이 BeforeEach를 먼저 실행하고 Test코드를 실행하는. 제어권이 이미 프레임워크에 넘어가 있는 것은 프레임워크.

내가 작성한 코드가 직접 제어의 흐름을 담당한다면 그것은 프레임워크가 아니라 라이브러리다.

Java객체를 XML 또는 JSON으로 바꾸는

![image](https://github.com/bakjinsoo/Java_Practice_Order/assets/77185565/2fd9f1bd-5c73-4ade-a018-89fcce8eca78)


### DI
__의존관계 주입__

OrderServiceImpl은 DicountPolicy 인터페이스에 의존한다. 실제 어떤 구현객체가 사용될지는 모른다.

의존관계는 정적인 클래스 의존관계와 실행시점에 결정되는 동적인 객체(인스턴스)의존관계 들을 분리해서 생각해야한다.


__정적인 클래스 의존관계__

클래스가 사용하는 import코드만 보고 의존관계를 쉽게 판단할 수 있다. 정적인 의존관계는 애플리케이션을 실행하지 않아도 분석할 수 있다. 

__동적인 객체 인스턴스 의존관계__

애플리케이션 실행 시점에 실제 생성된 객체 인스턴스의 참조가 연결된 의존관계다.

```
public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        super();
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

```

이코드만 보면 memberRepository 객체에 어떤 객체가 들어올지 discountPolicy객체에 어떤 할인 정책이 들어올지 코드만 보고서는 알 수 없다. 코드를 실행시켜봐야만 알 수있는데. 이거를 동적인 __객체 인스턴스 의존관계__ 라고 한다.

__객체 다이어그램__

애플리케이션 실행 시점에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계가 연결되는 것을 __의존관계의 주입__ 이라고 한다.

애플리케이션 실행 시점에 의존관계를 주입함.

```
 public MemberService memberService() {//생성자 주입
        return new MemberServiceImpl(memberRepository());
    }
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    public OrderService orderService() {//생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

```

__의존관계 주입을 사용하면 정적인 클래스 의존관계를 변경하지 않고 동적인 객체 인스턴스 의존관계를 쉽게 변경할 수 있다.(중요)__

정적인 클래스 다이어그램을 전혀 손대지 않고 -> 애플리케이션 소스코드는 손대지 않는다

__의존관계를 전혀 손대지 않을수 있다.__

### IOC DI, 컨테이너

AppConfig처럼 객체를 생성하고 관리하면서 의존관계를 연결해줌

어셈블러 : 애플리케이션 전체에대한 구성을 조립을한다.

### 스프링

```
@Bean
    public MemberService memberService() {//생성자 주입
        return new MemberServiceImpl(memberRepository());
    }
```

@Bean을 위에 쓰면 해당 메소드는 스프링 컨테이너라는 곳에 등록이 됨.

```
@Configuration
public class AppConfig {
}
```

annotation기반으로 Config를 하고 있음.

### 스프링 컨테이너

```
ApplicationContext applicationContext= new AnnotationConfigApplicationContext(AppConfig.class);
```

ApplicationContext를 스프링 컨테이너라고 한다.

@Configuration이 붙은 AppConfig를 구성정보로 사용.

@Bean이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너로 등록

이렇게 스프링 컨테이너에 등록된 객체를 __스프링 빈__ 이라 한다.

스프링 컨테이너를 통해 스프링 빈(객체)를 찾아야 함.

### 스프링 컨테이너 생성

![image](https://github.com/bakjinsoo/Java_Practice_Order/assets/77185565/c7a8cc88-962e-4585-9f85-18a6a5123b31)

![image](https://github.com/bakjinsoo/Java_Practice_Order/assets/77185565/259b9955-3614-4f28-a14d-019111447519)

빈 이름은 항상 다른 이름을 사용해야한다.

![image](https://github.com/bakjinsoo/Java_Practice_Order/assets/77185565/7a059e37-e917-4a6d-b905-26a5b3aeb3ea)

스프링 컨테이너는 설정 정보를 참고해서 의존관계를 주입(DI)한다.

단순히 자바코드를 호출하는 것 같지만 차이가 존재.(이 차이는 싱글톤 컨테이너에서 설명)

객체의 참조값들이 연결

빈 조회

```
@Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            //Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole()==BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName+" object = "+bean);
            }
        }
    }
```

ac.getBeanDefinitionNames() 스프링에 등록된 모든 빈이름을 조회한다.

ac.getBean() 빈 이름으로 빈 객체(인스턴스)를 조회한다.

애플리케이션 빈 출력하기

스프링이 내부에서 사용하는 빈은 getRole로 구분

ROLE_APPLICATION : 직접 등록한 애플리케이션 빈

ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈

#### 스프링 빈 조회 - 상속

![image](https://github.com/bakjinsoo/Java_Practice_Order/assets/77185565/b463f698-7c47-4a6b-8803-7dc7ee8469cd)


부모타입으로 조회하면, 자식 타입도 함께 조회한다.

모든 자바 객체의 최고 부모 Object타입으로 조회하면, 모든 스프링 빈을 조회함.

 스프링 빈 조회? 왜 함? 이유가 뭐임? 부모타입으로 조회할때 자식이 어디까지 줘야되나 나중에 자동의정관계주입에서 잘 해결 할 수있음.

의존관계를 보여주기 위해서 AppConfig에서 메소드 명은 인터페이스(추상화) 즉 역할의 이름을 쓰는게 좋다.

나중에 의존관계주입(DI)에서 편하게 의존관계를 보기 위해서

### BeanFactory와 ApplicationContext

![image](https://github.com/bakjinsoo/Java_Practice_Order/assets/77185565/a820343a-3534-4e91-9009-33d278147b1e)

__BeanFactory__

스프링 컨테이너의 최상위 인터페이스

스프링 빈을 관리하고 조회

getBean()을 제공

지금까지 우리가 사용했던 대부분의 기능은 BeanFactory

__ApplicationContext__

BeanFactory기능을 모두 상속받아 제공

빈을 관리하고 검색하는 기능 BeanFactory가 제공.

애플리케이션을 개발할때, 빈은 관리하고 조회하는 기븡은 물론이고, 수많은 부가기능이 필요함.

#### ApplicationContext가 제공하는 부가기능

![image](https://github.com/bakjinsoo/Java_Practice_Order/assets/77185565/6e789687-ec4c-4a45-99e1-b65ca46fb83f)

메시지 소스를 활용한 국제화 기능

예) 한국에서 들어오면 한국어로, 영어권에서 들어오면 영어로 출력

환경변수

로컬, 개발, 운영등을 구분해서 처리

로컬 개발환경 : 내 PC에서 개발한 환경

개발환경 : 테스트 서버에 올려서 여러 시스템을 엮어서 실제 테스트 서버에 띄워두고 테스트 하는 테스트

운영환경 : 실제 프로덕션에 나가는 운영환경

스테이징 환경 : 운영과 가장 비슷한 밀접한 환경을 만들기도 함.

각 환경별로 각각의 데이터베이스에 연결.

애플리케이션 이벤트

이벤트를 발행하고 구독하는 모델을 편리하게 지원

편리한 리소스 조회

파일, 클래스패스, 외부 등에서 리소스를 편리하게 조회.

정리

ApplicationContext는 빈 관리기능 + 편리한 부가기능을 제공한다.

BeanFactory를 직접 사용할 일은 거의 없으며, 부가기능이 포함된 ApplicationContext를 사용.

BeanFactory, ApplicationContext를 스프링 컨테이너라고 함.

#### XML

최근에는 스프링부트를 많이사용하며, XML기반의 설정은 잘 사용하지 않음

아직 *레거시 프로젝트들이 XML로 되어있고, 컴파일 없이 빈 설정 정보를 변경할 수 있는 장점도 있어서, 한번쯤은 배워두는것도 괜찮음.

*레거시 프로젝트

오래된 기술이나 아키텍처를 사용하여 개발되었거나, 현재의 개발 표준이나 기술적 요구사항을 충족시키지 못하는 소프트웨어 프로젝트를 의미


![image](https://github.com/bakjinsoo/Java_Practice_Order/assets/77185565/df296a88-056c-4cca-9076-6d4e07c707e3)

스프링 컨테이너는 BeanDefinition에만 의존을 함.

BeanDefinition자체가 인터페이스

## 싱글톤

싱글톤디자인 패턴의 필요성

```
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        //1.조회 : 호출할 때 마다 객체 생성
        MemberService memberService1 = appConfig.memberService();

        //2.조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        //참조값을 보면 객체를 각각 생성해주는 걸 볼 수 있음.

        //웹 어플리케이션 특징 : 웹 요청이 많이 나옴.
        //요청이 올때마다 객체를 각각 생성해주는 것은 효율적이지 않고, 메모리도 많이 잡아먹음

        //memberService1=! memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);


    }
```

+ 우리가 만들었던 스프링 없는 순수한 DI 컨테이너인 AppConfig는 요청을 할 때 마다 객체를 새로 생성한다.
+ 고객 트래픽이 초당 100이 나오면 초당 100개 객체가 생성되고 소멸된다! __메모리 낭비가 심하다.__
+ 해결방안은 해당 객체가 딱 1개만 생성되고, 공유하도록 설계하면 된다. -> __싱글톤 패턴__

싱글톤패턴

```
//1. static 영역에 객체를 딱 1개만 생성해둔다.
 private static final SingletonService instance = new SingletonService();

 //2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한
다.

 public static SingletonService getInstance() {
 return instance;
 }
 //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.

 private SingletonService() {
 }
 public void logic() {
 System.out.println("싱글톤 객체 로직 호출");
 }
```

### 참고: 싱글톤 패턴을 구현하는 방법은 여러가지가 있다. 여기서는 객체를 미리 생성해두는 가장 단순하고 안전한 방법을 선택했다

싱글톤 패턴을 적용하면 고객의 __요청이 올 때 마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유__ 해서 효율적으로 사용할 수 있다. 하지만 싱글톤 패턴은 다음과 같은 수 많은 문제점들을 가지고 있다

+ 싱글톤 패턴 문제점
  + 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
  + 의존관계상 클라이언트가 구체 클래스에 의존한다. DIP를 위반한다.
  + 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다. 테스트하기 어렵다.
  + 내부 속성을 변경하거나 초기화 하기 어렵다.
  + private 생성자로 자식 클래스를 만들기 어렵다.
  + 결론적으로 유연성이 떨어진다.
  + 안티패턴으로 불리기도 한다

## 싱글톤 방식의 주의점

무상태로 설계하는것이 중요!!

```
public class StatefulService {
    public int order(String name,int price){
        System.out.println("name + price = " + name + price);
        return price;
    }
}
```

무상태로 설계한 예시

특정 클라이언트에 의존적인 필드가 있으면 안된다.

특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다.

가급적 읽기만 가능해야 한다.

필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.

스프링 빈의 필드에 공유 값을 설정하면 정말 큰 장애가 발생할 수 있다!!!

```
package hello.core.singleton;
public class StatefulService {
 private int price; //상태를 유지하는 필드
 public void order(String name, int price) {
 System.out.println("name = " + name + " price = " + price);
 this.price = price; //여기가 문제!
 }
 public int getPrice() {
 return price;
 }
}
```

```
public class StatefulServiceTest {
 @Test
 void statefulServiceSingleton() {
 ApplicationContext ac = new
AnnotationConfigApplicationContext(TestConfig.class);
 StatefulService statefulService1 = ac.getBean("statefulService", 
StatefulService.class);
 StatefulService statefulService2 = ac.getBean("statefulService", 
StatefulService.class);
 //ThreadA: A사용자 10000원 주문
 statefulService1.order("userA", 10000);
 //ThreadB: B사용자 20000원 주문
 statefulService2.order("userB", 20000);
 //ThreadA: 사용자A 주문 금액 조회
 int price = statefulService1.getPrice();
 //ThreadA: 사용자A는 10000원을 기대했지만, 기대와 다르게 20000원 출력
 System.out.println("price = " + price);
 Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
 }
 static class TestConfig {
 @Bean
 public StatefulService statefulService() {
 return new StatefulService();
 }
 }
}
```

ThreadA가 사용자A 코드를 호출하고 ThreadB가 사용자B 코드를 호출한다 가정하자.

StatefulService 의 price 필드는 공유되는 필드인데, 특정 클라이언트가 값을 변경한다.

사용자A의 주문금액은 10000원이 되어야 하는데, 20000원이라는 결과가 나왔다.

실무에서 이런 경우를 종종 보는데, 이로인해 정말 해결하기 어려운 큰 문제들이 터진다.(몇년에 한번씩 꼭 만난
다.)

진짜 공유필드는 조심해야 한다! 스프링 빈은 항상 무상태(stateless)로 설계하자


```
@Test
void configurationDeep() {
 ApplicationContext ac = new
AnnotationConfigApplicationContext(AppConfig.class);
 //AppConfig도 스프링 빈으로 등록된다.
 AppConfig bean = ac.getBean(AppConfig.class);
 
 System.out.println("bean = " + bean.getClass());
 //출력: bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$bd479d70
}
```

사실 AnnotationConfigApplicationContext 에 파라미터로 넘긴 값은 스프링 빈으로 등록된다. 

그래서AppConfig 도 스프링 빈이 된다.

AppConfig 스프링 빈을 조회해서 클래스 정보를 출력해보자.

```
bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$bd479d70
```

순수한 클래스라면 다음과 같이 출력되어야 한다.
```
class hello.core.AppConfig
```

그런데 예상과는 다르게 클래스 명에 __xxxCGLIB__ 가 붙으면서 상당히 복잡해진 것을 볼 수 있다. 
이것은 내가 만든 클래스가 아니라 스프링이 __CGLIB라는 바이트코드 조작 라이브러리__ 를 사용해서 AppConfig 클래스를 상속받은 __임의의 다른 클래스를 만들고,__
그 다른 클래스를 스프링 빈으로 등록한 것이다

![image](https://github.com/bakjinsoo/Java_Practice_Order/assets/77185565/0758bd76-071d-454c-ad91-e2723d95e090)

그 임의의 다른 클래스가 바로 싱글톤이 보장되도록 해준다. 

아마도 다음과 같이 바이트 코드를 조작해서 작성되어 있을
것이다.(실제로는 CGLIB의 내부 기술을 사용하는데 매우 복잡하다.)

```
@Bean
public MemberRepository memberRepository() {
 
 if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?) {
 return 스프링 컨테이너에서 찾아서 반환;
 } else { //스프링 컨테이너에 없으면
 기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록
 return 반환
 }
}
```

@Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 

스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.

덕분에 싱글톤이 보장되는 것이다

@Bean만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다.

memberRepository() 처럼 의존관계 주입이 필요해서 메서드를 직접 호출할 때 싱글톤을 보장하지 않는다.

크게 고민할 것이 없다. 스프링 설정 정보는 항상 @Configuration 을 사용하자





















