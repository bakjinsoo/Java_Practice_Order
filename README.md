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

static import

메소드 및 변수를 패키지, 클래스명 없이 접근가능하게 해준다.

코드의 가독성을 높여주게 한다는 장점이 존재한다.

하지만 잘못 사용하는경우에는 가독성을 더 떨어트릴수 있으므로 주의하자.

+ static import를 잘 사용한 예시
  ```
Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
  ```

```
import static org.assertj.core.api.Assertions.*;
assertThat(discount).isEqualTo(1000);
```

+ static import 를 잘못 사용한 예시
```
import static java.lang.System.out;
out.print(myDoc);   
```
