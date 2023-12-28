# 8. 빈 생명주기 콜백
___
## 빈 생명주기 콜백 시작

데이터베이스 커넥션 풀, 네트워크 소켓처럼 애플리케이션 시작 시점에 필요한 연결을 미리 해두고,
애플리케이션 종료 시점에 연결을 모두 종료하는 작업을 진행하려면 객체의 초기화 작업이 필요.

`NetworkClient` 는 애플리케이션 시작 시점에 `connect()`를 호출해서 연결, 애플리케이션이 종료되면
`disConnect()`를 호출해서 연결을 끊어야 한다.


```java
package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
```

```java
package hello.core.lifecycle;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }
}
```

실행결과
```
생성자 호출, url = null
connect: null
call: null message = 초기화 연결 메시지
```

@Bean 에서 NetworkClient 생성자 호출 -> setUrl 을 하지 않아 null 표시 -> connect 역시 null -> call message도 null 표시함<br>
생성자 호출 후 setter로 url 등록하였음.

스프링 빈은 간단하게 다음과 같은 라이프 사이클을 가진다.<br>
**객체 생성** -> **의존관계 주입**

스프링 빈은 객체를 생성하고, 의존관계 주입이 다 끝난 다음에 필요한 데이터를 사용할 수 있는 준비가 완료된다.
따라서 초기화 작업은 의존관계 주입이 모두 완료되고 난 다음에 호출해야 한다.<br>
**스프링은 의존관계 주입이 완료되면 스프링 빈에게 콜백 메서드를 통해서 초기화 시점을 알려주는 다양한 기능을 제공**한다.
또한 **스프링은 스프링 컨테이너가 종료되기 직전에 소멸 콜백**을 준다. 따라서 안전하게 종료 작업을 진행할 수 있다.

**스프링 빈의 이벤트 라이프사이클**<br>
**스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료**
- **초기화 콜백** : 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
- **소멸전 콜백** : 빈이 소멸되기 직전에 호출

>**참고 : 객체의 생성과 초기화를 분리하자**<br>
>생성자는 필수 정보(파라미터)를 받고, 메모리를 할당해서 객체를 생성하는 책임을 가진다. 반면에 초기화는
> 어떻게 생성된 값들을 활용해서 외부 커넥션을 연결하는 등 무거운 동작을 수행한다.<br>
> 따라서 생성자 안에서 무거운 초기화 작업을 함께 하는 것 보다는 객체를 생성하는 부분과 초기화 하는 부분을 명확학게
> 나누는 것이 유지보수 관점에서 좋다. 물론 초기화 작업이 내부 값들만 약간 변경하는 정도로 단순한 경우에는 생성자에서
> 한번에 다 처리하는게 더 나을 수 있다.

<br><br><br>

**스프링이 지원하는 빈 생명주기 콜백**
- 인터페이스(InitializeBean, DisposableBean)
- 설정 정보에 초기화 메서드, 종료 메서드 지정
- @PostConstruct, @PreDestroy 애노테이션 지원

<br><br>

### 인터페이스(InitializeBean, DisposableBean)
```java
package week7.seob.lifecycle;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    //의존관계 주입이 끝난 후
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("\nNetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    //Bean이 종료될 때
    @Override
    public void destroy() throws Exception {
        System.out.println("\nNetworkClient.destory");
        disconnect();
    }
}
```

실행결과
```
생성자 호출, url = null

NetworkClient.afterPropertiesSet
connect: http://hello-spring.dev
call: http://hello-spring.dev message = 초기화 연결 메시지

NetworkClient.destory
close: http://hello-spring.dev
```

**초기화, 소멸 인터페이스 단점**
- 이 인터페이스는 스프링 전용 인터페이스. 해당 코드가 스프링 전용 인터페이스에 의존한다.
- 초기화, 소멸 메서드의 이름을 변경할 수 없다.
- 내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다.

>참고 : 인터페이스를 사용하는 초기화, 종료 방법은 스프링 초창기에 나온 방법들이고, 지금은 더 나은 방법들이 있어서 거의 사용하지 않는다.


<br><br>
### 빈 등록 초기화, 소멸 메서드

설정 정보에 `@Bean(initMethod = "init", destroyMethod = "close"` 처럼 초기화, 소멸 메서드 지정

```java
package week7.seob.lifecycle;


public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    //의존관계 주입이 끝난 후
    public void init() {
        System.out.println("\nNetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    //Bean이 종료될 때
    public void close() {
        System.out.println("\nNetworkClient.close");
        disconnect();
    }
}
```


```java
package week7.seob.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {


    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }


    @Configuration
    static class LifeCycleConfig {
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
```
실행결과
```
생성자 호출, url = null

NetworkClient.init
connect: http://hello-spring.dev
call: http://hello-spring.dev message = 초기화 연결 메시지

NetworkClient.close
close: http://hello-spring.dev
```

**설정 정보 사용 특징**
- 메서드 이름을 자유롭게 줄 수 있다.
- 스프링 빈이 스프링 코드에 의존하지 않는다.
- 코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용할 수 있다.

**종료 메서드 추론**
- `@Bean의 destroyMethod` 속성에는 아주 특별한 기능이 있다.
- 라이브러리는 대부분 `close`, `shutdown` 이라는 이름의 종료 메서드를 사용한다.
- @Bean의 `destroyMethod`는 기본값이 `(inferred)`(추론) 으로 등록되어 있다.
- 이 추론 기능은 `close`, `shutdown`라는 이름의 메서드를 자동으로 호출해준다. 이름 그대로 종료 메서드를 추론해서 호출해준다.
- 따라서 직접 스프링 빈으로 등록하면 종료 메서드는 따로 적어주지 않아도 잘 동작한다.
- 추론 기능을 사용하기 싫으면 `destroyMethod=""`처럼 빈 공백을 지정하면 된다.
  
<br><br>

### 애노테이션 @PostConstruct, @PreDestroy
```java
package week7.seob.lifecycle;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    //의존관계 주입이 끝난 후
    @PostConstruct
    public void init() {
        System.out.println("\nNetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    //Bean이 종료될 때
    @PreDestroy
    public void close() {
        System.out.println("\nNetworkClient.close");
        disconnect();
    }
}
```

```java
package week7.seob.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {


    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }


    @Configuration
    static class LifeCycleConfig {
        @Bean //(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
```

실행결과
```
생성자 호출, url = null

NetworkClient.init
connect: http://hello-spring.dev
call: http://hello-spring.dev message = 초기화 연결 메시지

NetworkClient.close
close: http://hello-spring.dev
```

`@PostConstruct`, `@PreDestroy`이 두 애노페이션을 사용하면 가장 편리하게 초기화와 종료를 실행할 수 있다.


**@PostConstruct, @PreDestroy 애노테이션 특징**
- 최신 스프링에서 가장 권장하는 방법.
- 애노테이션 하나만 붙이면 되므로 매우 편리.
- 패키지가 `javax.annotation.PostConstruct`이다. 스프리에 종속적인 기술이 아니라 JSR-250라는 자바 표준이다.
따라서 스프링이 아닌 다른 컨테이너에서도 동작한다.
- 컴포넌트 스캔과 잘 어울린다.
- 유일한 단점 : 외부 라이브러리에 적용하지 못함. 외부 라이브러리 초기화, 종료를 해야 한다면 `@Bean` 의 기능을 사용해야한다.

**정리**
- **@PostConstruct, @PreDestroy 애노테이션을 사용하자**
- 코드를 고칠 수 없는 외부 라이브러리 초기화, 종료를 해야한다면 `@Bean` 의 `initMethod`, `destroyMethod`를 사용하자.



<br><br>
<br><br>

___
<br><br>

# 9. 빈 스코프
___

## 빈 스코프란
스프링 빈이 스프링 컨테이너의 시작과 함께 생성되어서 스프링 컨테이너가 종료될 때 까지 유지된다고 학습함.
이것은 스프링 빈이 기본적으로 싱글톤 스코프로 생성되기 때문이다. 스코프는 번역 그대로 빈이 존재할 수 있는 범위를 뜻한다.

**스프링은 다음과 같은 다양한 스코프를 지원한다.**
- **싱글톤** : 기본 스코프, 스프링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프.
- **프로토타입** : 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입까지만 관여하고 더는 관리하지 않는 매우 짧은 범위의 스코프.
- **웹 관련 스코프** :
    - **request** : 웹 요청이 들어오고 나갈때 까지 유지되는 스코프.
    - **session** : 웹 세션이 생성되고 종료될 때 까지 유지되는 스코프.
    - **application** : 웹의 Servlet Context와 같은 범위로 유지되는 스코프이다.
  
    
<br>
빈 스코프 지정 방법<br><br>

**컴포넌트 스캔 자동 등록**
```java
@Scope("prototype")
@Component
public static HelloBean{}
```


**수동 등록**
```java
@Scope("prototype")
@Bean
PrototypeBean HelloBean(){
    return new HelloBean();
}
```

```java
package week7.seob.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonTest {

    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);

        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        Assertions.assertThat(singletonBean1).isEqualTo(singletonBean2);

        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean{
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }

}
```
실행결과
```
SingletonBean.init
singletonBean1 = week7.seob.scope.SingletonTest$SingletonBean@773f7880
singletonBean2 = week7.seob.scope.SingletonTest$SingletonBean@773f7880
SingletonBean.destroy
```
싱글톤 스코프인 경우 같은 인스턴스를 반환하는것을 확인할 수 있다.

## 프로토타입 스코프
**싱글톤 스코프의 빈을 조회하면 스프링 컨테이너는 항상 같은 인스턴스의 스프링 빈을 반환.** 반면에 
**프로토타입 스코프를 스프링 컨테이너에 조회하면 스프링 컨테이너는 항상 다른 인스턴스를 생성해서 반환**

**싱글톤 빈 요청**
1. 싱글톤 스코프의 빈을 스프링 컨테이너에 요청
2. 스프링 컨테이너는 본인이 관리하는 스프링 빈을 반환
3. 이후에 스프링 컨테이너에 같은 요청이 와도 같은 객체 인스턴스의 스프링 빈을 반환한다.

**프로토타입 빈 요청1**
1. 프로토타입 스코프의 빈을 스프링 컨테이너에 요청.
2. 스프링 컨테이너는 이 시점에 프로토타입 빈을 생성, 필요한 의존관계를 주입.

**프로토타입 빈 요청2**
3. 스프링 컨테이너는 생성한 프로토타입 빈을 클라이언트에 반환.(관리는 X)
4. 이후에 스프링 컨테이너에 같은 요청이 오면 항상 새로운 프로토타입 빈을 생성해서 반환

**정리**<br>
**핵심은 스프링 컨테이너는 프로토타입 빈을 생성, 의존관계 주입, 초기화 까지만 처리** 클라이언트에 빈을 반환하고,
이후 스프링 컨테이너는 생성된 프로토타입 빈을 관리하지 않는다. 프로토타입 빈을 관리할 책임은 프로토타입 빈을
받은 클라이언트에 있다. **그래서 `@PreDestroy`같은 종료 메서드가 호출되지 않는다.**



```java
package week7.seob.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
```
실행결과
```
find prototypeBean1
PrototypeBean.init
find prototypeBean2
PrototypeBean.init
prototypeBean1 = week7.seob.scope.PrototypeTest$PrototypeBean@773f7880
prototypeBean2 = week7.seob.scope.PrototypeTest$PrototypeBean@878452d
```

- 싱글톤 빈은 스프링 컨테이너 생성 시점에 초기화 메서드가 실행되지만, 프로토타입 스코프의 빈은 스프링 컨테이너에서
빈을 조회할 때 생성되고, 초기화 메서드도 실행된다.
- 프로타입 빈을 2번 조회했으므로 완전히 다른 스프링 빈이 생성되고, 초기화도 2번 실행된 것을 확인할 수 있다.
- 싱글톤 빈은 스프링 컨테이너가 관리하기 때문에 스프링 컨테이너가 종료될 때 빈의 종료 메서드가 실행되지만,
**프로토타입 빈은 스프링 컨테이너가 생성과 의존관계 주입 그리고 초기화 까지만 관여하고, 더는 관리하지 않는다.
따라서 프로토타입 빈은 스프링 컨테이너가 종료될 때 `@PreDestroy`같은 종료 메서드가 전혀 실행되지 않는다.**


**프로토타입 빈의 특징 정리**
- 스프링 컨테이너에 요청할 떄 마다 새로 생성
- 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입, 초기화까지만 관여 
- 종료 메서드가 호출되지 않음
- 프로토타입 빈은 프로토타입 빈을 조회한 클라이언트가 관리해야한다. 종료 메서드에 대한 호출도 클라이언트가 직접 해야한다.

```java
prototypeBean1.destoroy();
prototypeBean2.destoroy();
```
위와 같이 직접 destroy()를 호출하여 직접 종료할 수 있음.



## 프로토타입 스코프 - 싱글톤 빈과 함께 사용시 문제 
스프링 컨테이너에 프로토타입 스코프의 빈을 요청하면 항상 새로운 객체 인스턴스를 생성해서 반환<br>
하지만 싱글톤 빈과 함께 사용할 때는 의도한 대로 잘 동작하지 않으므로 주의해야함

### 프르토타입 빈 직접 요청
```java
package week7.seob.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();

        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }


    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {//호출 X
            System.out.println("PrototypeBean.destory "+ this);
        }
    }
}
```
실행결과
```
PrototypeBean.init week7.seob.scope.SingletonWithPrototypeTest1$PrototypeBean@51e8e6e6
PrototypeBean.init week7.seob.scope.SingletonWithPrototypeTest1$PrototypeBean@878452d
```

<br>
스프링 컨테이너에 프로토타입 빈 직접 요청 1.
1. prototypeBean1는 스프링 컨테이너에 스프링 빈을 요청
2. 스프링 컨테이너는 프로토타입 빈을 새로 생성해서 반환(**PrototypeBean@51e8e6e6**)한다. 해당 빈의 count 값은 0.
3. prototypeBean1는 조회한 프로토타입 빈에 `addCount()` 호출 -> count 필드가 1 증가.
<br>
스프링 컨테이너에 프로토타입 빈 직접 요청 2.
1. prototypeBean1는 스프링 컨테이너에 스프링 빈을 요청
2. 스프링 컨테이너는 프로토타입 빈을 새로 생성해서 반환(**PrototypeBean@51e8e6e6**)한다. 해당 빈의 count 값은 0.
3. prototypeBean1는 조회한 프로토타입 빈에 `addCount()` 호출 -> count 필드가 1 증가.

**결론**
- 프로토타입 빈은 요청시 항상 새로운 빈을 생성하기 때문에 서로 다른 필드라 생각하면 됨