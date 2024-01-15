# 자바 공부

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












