# 자바 공부

### intelliJ 단축키

+ Ctrl+Shift+T 테스트코드를 작성하는 단축키이다. 

+ Ctrl+Alt+M 리팩토링 단축키이다. 

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

### 자바 개념

+ 다형성
  + 역할, 구현으로 구분 -> 단순하고 유연하게 변경이 가능하다.

+ 역할 - > 인터페이스
+ 구현 - > 클래스, 객체

+ 인터페이스 -> 안정적으로 잘 설계해야한다.

#### 좋은 객체 지향 설계의 5가지 원칙

#### SRP 단일 책임원칙

__"한 클래스는 하나의 책임만 가져야 한다."__

#### DIP 의존관계 역전 원칙

__프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다." 의존성 주입은 이 원칙을 따르는 방법 중 하나다.__

#### OCP

__소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야한다.__

