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

### 싱글톤 빈에서 프로토타입 빈 사용

**싱글톤에서 프로토타입 빈 사용1**
- `clientBean`은 싱글톤임. 스프링 컨테이너 생성 시점에 함께 생성, 의존관계 주입도 발생
- 1. `clientBean`은 의존관계 자동 주입 사용. 주입 시점에 스프링 컨테이너에 프로토타입 빈 요청
- 2. 스프링 컨테이너는 프로토타입 빈을 생성해서 `clientBean`에 반환. -> 프로토타입 빈의 count 필드 값 = 0
- `clientBean`은 프로토타입 빈을 내부 필드에 보관(참조값 보관)

**싱글톤에서 프로토타입 빈 사용2**
- 클라이언트A가 `clientBean`을 스프링 컨테이너에 요청. 싱글톤이므로 항상 같은 `clientBean` 반환
- 3. 클라이언트A는 `clientBean.logic()`호출
- 4. `clientBean`은 prototypeBean의 `addCount()`호출해서 프로토타입 빈의 count를 증가시킴. -> count = 1

**싱글톤에서 프로토타입 빈 사용3**
- 클라이언트A가 `clientBean`을 스프링 컨테이너에 요청. 싱글톤이므로 항상 같은 `clientBean` 반환
- **clientBean이 내부에 가지고 있는 프로토타입 빈은 이미 과거에 주입이 끝난 빈. 주입 시점에 스프링 컨테이너에 
요청해서 프로토타입이 새로 생성된 것이지, 사용할 때마다 생성되는것이 아님.**
- 3. 클라이언트A는 `clientBean.logic()`호출
- 4. `clientBean`은 prototypeBean의 `addCount()`호출해서 프로토타입 빈의 count를 증가시킴. -> count = 2

코드를 확인해보자
```java
package week7.seob.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(2);

    }

    @Scope("singleton")
    static class ClientBean{
        private final PrototypeBean prototypeBean; //생성 시점에 주입

        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
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
PrototypeBean.init week7.seob.scope.SingletonWithPrototypeTest1$PrototypeBean@25748410
```

스프링은 일반적으로 싱글톤 빈을 사용하므로, 싱글톤 빈이 프로토타입 빈을 사용하게 된다. 
그런데 싱글톤 빈은 생성 시점에만 의존관계 주입을 받기 때문에 프로토타입 빈이 새로 생성되기는 하지만,
싱글톤 빈과 함꼐 계속 유지되는것이 문제이다

> **참고** : 여러번 빈에서 같은 프로토타입 빈을 주입 받으면, **주입 받는 시점에 각각 새로운 프로토타입 빈이 생성**<br>
> clientA. clientB가 각각 의존관계 주입을 받으면 각각 다른 인스턴스의 프로토타입 빈을 주입 받는다.<br>
> clientA -> prototypeBean@x01
> clientB -> prototypeBean@x02<br>
> 물론 사용할 때 마다 새로 생성되는 것은 아니다.




## 프로토타입 스코프 - 싱글톤 빈과 함께 사용시 Provider로 문제 해결
싱글톤 빈과 프로토타입 빈을 함께 사용할때 Provider를 사용하면 항상 새로운 프로토타입 빈을 생성할 수 있다.


가장 간단한 방법은 싱글톤 빈이 프로토타입 빈을 사용할 때 마다 스프링 컨테이너에 새로 요청하는 것이다.
```java
    @Scope("singleton")
    static class ClientBean{
    
        @Autowired
        private ApplicationContext ac;

        public int logic() {
            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }
```
실행결과
```
PrototypeBean.init week7.seob.scope.SingletonWithPrototypeTest1$PrototypeBean@77602954
PrototypeBean.init week7.seob.scope.SingletonWithPrototypeTest1$PrototypeBean@6fff253c
```

- `ac.getBean()`을 통해 항상 새로운 프로토타입 빈을 불러오는 것을 확인할 수 있다.
- 의존관계를 외부에서 주입(DI)받는게 아니라 위처럼 직접 필요한 의존관계를 찾는 것을 
**Dependency Lookup(DL)** 의존관계 조회(탐색) 이라고 한다.
- 그런데 스프링의 애플리케이션 컨텍스트 전체를 주입받게 되면, 스프링 컨테이너에 종속적인 코드가 되고, 단위 테스트가 어려워진다.
- 현재 필요한 기능 -> 지정한 프로토타입 빈을 컨테이너에서 대신 찾아주는 DL 정도의 기능만 제공하는 무언가가 있으면 된다.


### ObjectFactory, ObjectProvider
지정한 빈을 컨테이너에서 대신 찾아주는 DL 서비스를 제공하는 것이 `ObjectProvider`이다. <br>
과거에는 `ObjectFactory`가 존재했는데, 여기에 편의 기능을 추가한 `ObjectProvider`가 만들어졌다.

```java
    @Scope("singleton")
    static class ClientBean{

        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }
```
실행결과
```java
PrototypeBean.init week7.seob.scope.SingletonWithPrototypeTest1$PrototypeBean@10fde30a
PrototypeBean.init week7.seob.scope.SingletonWithPrototypeTest1$PrototypeBean@72f46e16
```
- `prototypeBeanProvider.getObject()`를 통해서 항상 새로운 프로토타입 빈이 생성된다.
- `ObjectProvider`의 `getObject()`를 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아 반환한다.(**DL**)
- 기능이 단순하므로 단위테스트 및 mock 코드를 만들기 훨씬 쉽다.<br>
mock : 실제 객체를 만들기에는 비용과 시간이 많이 들거나 의존성이 크게 걸쳐져 있어서 테스트 시 제대로 구현하기 어려울 경우 가짜 객체를 만들어서 사용하는 기술.
- `ObjectProvider`는 지금 딱 필요한 DL정도의 기능만 제공한다.

**특징**
- ObjectFactory : 기능이 단순,별도의 라이브러리 필요 없음, 스프링에 의존
- ObjectProvider : ObjectFactory 상속, 옵션, 스트림 처리등 편의 기능이 많고, 별도의 라이브러리 필요 없음, 스프링에 의존


### JSR-330 Provider 
`javax.inject.Provider`라는 JSR-300 자바 표준을 사용하는 방법이 있다. **스프링에 의존하지 않는다**
<br> 이 방법을 사용하려면 `javax.inject:javax.inject:1` 라이브러리를 gradle에 추가해야 한다.


```java
//build.gradle에 추가 -> implementation 'javax.inject:javax.inject:1'  
@Scope("singleton")
    static class ClientBean{

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }
```
실행결과 
```
PrototypeBean.init week7.seob.scope.SingletonWithPrototypeTest1$PrototypeBean@7b64240d
PrototypeBean.init week7.seob.scope.SingletonWithPrototypeTest1$PrototypeBean@46e8a539
```

- 실행해보면 `provider.get()`을 통해서 항상 새로운 프로토타입 빈이 생성되는 것을 확인할 수 있다.
- `provider`의 `get()`을 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아서 반환한다(DL)
- 자바표준, 기능이 단순해 단위테스트를 만들거나 mock 코드를 만들기는 훨씬 쉬워진다
- `provider`는 현재 필요한 DL정도의 기능을 제공한다

**특징**
- `get()` 메서드 하나로 기능이 매우 단순하다.
- 별도의 라이브러리가 필요하다.
- 자바 표준이므로 스프링이 아닌 다른 컨테이너에서도 사용할 수 있다.


**정리**
- 프로토타입 빈은 매번 사용할 때 마다 의존관계 주입이 완료된 새로운 객체가 필요하면 사용하면 된다.
하지만 실무에선 싱글톤 빈으로 대부분의 문제를 해결할 수 있기 때문에 프로토타입 빈을 직접적으로 사용하는 일이 매우 드물다고 한다
- `ObjectProvider`, `JSR330 Provider`등은 프로토타입 뿐만 아니라 DL이 필요한 경우는 언제든지 사용할 수 있다.

> **참고** : 스프링이 제공하는 메서드에 `@Lookup`애노테이션을 사용하는 방법도 있지만, 이전 방법들로 충분하고,
고려해야할 내용도 많다.


> **참고** : ObjectProvider는 DL을 위한 편의 기능을 많이 제공해주고 스프링 외에 별도의 의존관계 추가가 
> 필요 없기 때문에 편리하다. 
> 
> 스프링을 사용하다보면 다른 기능들도 자바 표준과 스프링이 제공하는 기능이 겹칠 때가 있다. 대부분 스프링이 더 다양하고
> 편리한 기능을 제공해주기 때문에, 특별히 다른 컨테이너를 사용할 일이 없다면, 스프링이 제공하는 기능을 쓰자!




## 웹 스코프
싱글톤은 스프링 컨테이너의 시작과 끝까지 함꼐하는 매우 긴 스코프, 프로토타입은 생성과 의존관계 주입, 그리고 초기화 까지만 진행하는 특별한 스코프

**웹 스코프의 특징**
- 웝 스코프는 웹 환경에서만 동작
- 웹 스코프는 프로토타입과 다르게 스프링이 해당 스코프의 종료시점까지 관리. 따라서 종료 메서드 호출

**웹 스코프 종류**
- **request** : HTTP요청 하나가 들어오고 나갈 때 까지 유지되는 스코프, 각각의 HTTP요청마다 별도의 빈 인스턴트가 생성, 관리된다.
- **session** : HTTP Session과 동일한 생명주기를 가지는 스코프
- **application** : 서블릿 컨텍스트(`ServletContext`) 와 동일한 생명주기를 갖는 스코프
- **websocket** : 웹 소켓과 동일한 생명주기를 가지는 스코프


## request 스코프 예제

### 웹 환경 추가 
웹 스코프는 웹 환경에서만 동작함. 따라서 web환경이 동작하도록 라이브러리를 추가한다.
```groovy
	implementation 'org.springframework.boot:spring-boot-starter-web'
```
> **참고** : `spring-boot-starter-web` 라이브러리를 추가하면 스프링 부트는 내장 톰켓 서버를 활용해 웹 서버와 스프링을 함께 실행시킨다.

> **참고** : 스프링 부트는 웹 라이브러리가 없으면 `AnnotationConfigApplicationContext`를 기반으로
> 웹 애플리케이션을 구동한다. 웹 라이브러리가 추가되면 웹과 관련된 추카 설정과 환경들이 필요하므로
> `AnnotationConfigServletWebServerApplicationContext`를 기반으로 애플리케이션을 구동한다.

### request 스코프 예제 개발
동시에 여러 HTTP 요청이 오면 정확히 어떤 요청이 남긴 로그인지 구분하기 어렵다. <br>
이럴 때 사용하기 좋은 것이 request 스코프이다.

```java
package week7.seob.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request")
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "][" + requestURL + "]" + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create : " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("");
        System.out.println("[" + uuid + "] request scope bean close : " + this);
    }
}
```
- 로그를 출력하기 위한 `MyLogger` 클래스
- `@Scope(value="request)"`를 사용해 request 스코프를 지정. 이 빈은 HTTP 요청 당 하나씩 생성되고,
요청이 끝나는 시점에 소멸된다.
- 이 빈이 생성되는 시점에 자동으로 `@PostConstruct`초기화 메서드를 사용해 uuid를 생성 및 저장한다. 
이 빈은 HTTP 요청 당 하나씩 생성되기 때문에 uud를 저장해두면 다른 HTTP요청과 구분이 가능하다.
- 이 빈이 소멸되는 시점에 `@PreDestroy`를 사용해 종료 매시지를 남긴다.
- `requestURL`은 이 빈이 생성되는 시점에는 알 수 없어 외부에서 setter로 입력받는다.


*MyLogger*
```java
package week7.seob.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request")
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "][" + requestURL + "]" + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create : " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("");
        System.out.println("[" + uuid + "] request scope bean close : " + this);
    }
}
```
- 로그를 출력하기 위한 클래스.
- `@Scope(value = "request")`로 requset 스코프 지정. 이 빈은 HTTP 요청 당 하나씩 생성되고, HTTP 요청이 끝나는 시점에 소멸
- 이 빈이 생성되는 시점에 자동으로 `@PostConstruct` 초기화 메서드를 사용해 uuid를 생성해서 저장.
이 빈은 HTTP 요총 당 하나씩 생성되므로 uuid를 저장해두면 다른 HTTP 요청과 구분할 수 있다.
- 이 빈이 소멸되는 시점에 `@PreDestroy`를 사앵해서 종료 메시지를 남긴다.
- `requestURL`은 이 빈이 생성되는 시점에는 알 수 없으므로, 외부에서 setter로 입력받는다.


*LogDemoController*
```java
package week7.seob.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import week7.seob.common.MyLogger;

@Controller
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @Autowired
    public LogDemoController(LogDemoService logDemoService, MyLogger myLogger) {
        this.logDemoService = logDemoService;
        this.myLogger = myLogger;
    }

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }

}
```
- 로거가 잘 작동하는 확인하는 테스트용 컨트롤러 
- 여기서 HttpServletRequest를 통해서 요청 URL을 받았다.
  - requestURL 값 `http://localhost:8080/log-demo`
- 이렇게 받은 requestURL 값을 myLogger에 저장한다. myLogger는 HTTP 요청 당 각각 구분되므로
다른 HTTP요청 때문에 값이 섞이는 걱정은 하지 않아도 된다.
- 컨트롤러에서 controller test라는 로그를 남긴다.


*LogDemoService*
```java
package week7.seob.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import week7.seob.common.MyLogger;

@Service
public class LogDemoService {

    private final MyLogger myLogger;

    @Autowired
    public LogDemoService(MyLogger myLogger) {
        this.myLogger = myLogger;
    }

    public void logic(String id) {
        myLogger.log("service id = " + id);
    }
}
```
- request scope를 사용하지 않고 파라미터로 이 모든 정보를 서비스 계층에 넘긴다면, 파라미터가 많아
지저분해진다. 더 문제는 requestURL 같은 웹과 관련된 정보가 웹과 관련없는 서비스 계층까지 넘어가게 된다.
웹과 관련된 부분은 컨트롤러 까지만 사용해야 한다. 서비스 계층은 웹 기술에 종속되지 않고, 가급적 순수하게 유지하는 것이
유지보수 과점에서 좋다.
- request scope의 MyLogger 덕분에 이런 부분을 파라미터로 넘기지 않고, MyLogger의 멤버변수에 저장해서
코드와 계층을 깔끔하게 유지할 수 있다.

실행 시
```
Error creating bean with name 'myLogger': Scope 'request' is not active for the current thread; consider defining a scoped proxy for this bean if you intend to refer to it from a singleton
```

오류가 발생한다. 왜냐하면 request 가 와야 myLogger를 사용할 수 있지만 아직 request가 오지도 않은 상태에서 스프링이 myLogger를 요청했기 때문이다.
<br>
스프링 애플리케이션을 실행하는 시점에 싱글톤 빈은 생성해서 주입이 가능하지만, request 스코프 빈은 아직 생성되지 않는다.
이 빈은 실제 고객의 요청이 와야 생성할 수 있다.


## 스코프와 Provider
위의 문제의 첫 번째 해결방법은 Provider를 사용하는 것 이다.

*LogDemoController*
```java
package week7.seob.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import week7.seob.common.MyLogger;

@Controller
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @Autowired
    public LogDemoController(LogDemoService logDemoService, ObjectProvider<MyLogger> myLogger) {
        this.logDemoService = logDemoService;
        this.myLoggerProvider = myLogger;
    }

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }

}
```

*LogDemoService*
```java
package week7.seob.web;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import week7.seob.common.MyLogger;

@Service
public class LogDemoService {

    private final ObjectProvider<MyLogger> myLoggerProvider;

    @Autowired
    public LogDemoService(ObjectProvider<MyLogger> myLogger) {
        this.myLoggerProvider = myLogger;
    }

    public void logic(String id) {
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id = " + id);
    }
}

```


실행결과 (두 번 요청)
```
[ae14a061-798d-4f36-a0a4-12e304ed743f] request scope bean create : week7.seob.common.MyLogger@10437b22
[ae14a061-798d-4f36-a0a4-12e304ed743f][http://localhost:8080/log-demo]controller test
[ae14a061-798d-4f36-a0a4-12e304ed743f][http://localhost:8080/log-demo]service id = testId
[ae14a061-798d-4f36-a0a4-12e304ed743f] request scope bean close : week7.seob.common.MyLogger@10437b22

[ba090d99-d6c4-4708-8fac-a74c01dd6efe] request scope bean create : week7.seob.common.MyLogger@5f0bf03
[ba090d99-d6c4-4708-8fac-a74c01dd6efe][http://localhost:8080/log-demo]controller test
[ba090d99-d6c4-4708-8fac-a74c01dd6efe][http://localhost:8080/log-demo]service id = testId
[ba090d99-d6c4-4708-8fac-a74c01dd6efe] request scope bean close : week7.seob.common.MyLogger@5f0bf03
```

- `@ObjectProvider` 덕분에 `ObjectProvider.getObject()`를 호출하는 시점까지 request scope 
**빈의 생성을 지연**할 수 있다.
- `ObjectProvider.getObject()`를 호출하는 시점에는 HTTP요청이 진행중이므로 request scope 빈의 생성이 정상 처리된다.
- `ObjectProvider.getObject()`를 `LogDemoController`, `LogDemoService`에서 각각 한 번씩 따로 호출해도
같은 HTTP 요청이면 같은 스프링이 반환된다.



## Scope & Proxy
```java
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
}
```
- `proxyMode = ScopedProxyMode.TARGET_CLASS` 추가.
  - 적용 대상이 클래스 일 때 `TARGET_CLASS`
  - 적용 대상이 인터페이스 일 때 `INTERFACE`
- 이렇게 하면 MyLogger의 가짜 프록시 클래스를 만들어두고 HTTP request와 상관 없이 가짜 프록시 클래스를 다른 빈에 주입해 둘 수 있다.

코드를 Provider 사용 이전으로 변경한다.
```java
@Controller
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @Autowired
    public LogDemoController(LogDemoService logDemoService, MyLogger myLogger) {
        this.logDemoService = logDemoService;
        this.myLogger = myLogger;
    }

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }

}
```

```java
@Service
public class LogDemoService {

    private final MyLogger myLogger;

    @Autowired
    public LogDemoService(MyLogger myLogger) {
        this.myLogger = myLogger;
    }

    public void logic(String id) {
        myLogger.log("service id = " + id);
    }
}
```
실행결과
```java
[bbb44759-be28-4fa0-8307-a8a983c9d824] request scope bean create : week7.seob.common.MyLogger@21cd3a44
[bbb44759-be28-4fa0-8307-a8a983c9d824][http://localhost:8080/log-demo]controller test
[bbb44759-be28-4fa0-8307-a8a983c9d824][http://localhost:8080/log-demo]service id = testId
[bbb44759-be28-4fa0-8307-a8a983c9d824] request scope bean close : week7.seob.common.MyLogger@21cd3a44

[99a82c02-0a83-4ada-933f-a31d238811fc] request scope bean create : week7.seob.common.MyLogger@6a45bcb6
[99a82c02-0a83-4ada-933f-a31d238811fc][http://localhost:8080/log-demo]controller test
[99a82c02-0a83-4ada-933f-a31d238811fc][http://localhost:8080/log-demo]service id = testId
[99a82c02-0a83-4ada-933f-a31d238811fc] request scope bean close : week7.seob.common.MyLogger@6a45bcb6
```

### 웹 스코프 & 프록시 동작 원리

```java
System.out.println("myLogger = " + myLogger.getClass());
```
실행결과
```java
myLogger = class week7.seob.common.MyLogger$$SpringCGLIB$$0
```

**CGLIB 라는 라이브러리로 내 클래스를 상속 받은 가짜 프록시 개체를 만들어 주입한다.**
- `@Scope`의 `proxyMode` 를 설정하면 스프링 컨테이너는 CGLIB라는 바이트 코드를 조작하는 라이브러리를 사용해 
MyLogger를 상속받은 가짜 프록시 객체를 생성한다.
- 결과를 확인해보면 우리가 등록한 순수한 MyLogger클래스가 아니라 `MyLogger$$SpringCGLIB$$0`이라는 클래스로 만들어진
객체가 대신 등록된 것을 확인할 수 있다.
- 그리고 스프링 컨테이너에 "myLogger"라는 이름으로 진짜 대신에 이 가짜 프록시 객체를 등록한다.
- `ac.getBean("MyLogger", MyLogger.class)`로 조회해도 프록세 객체가 조회된다.
- 따라서 의존관계 주입도 이 가짜 프록시 객체가 주입된다.


**가짜 프록시 객체는 요청이 오면 그때 내부에서 진짜 빈을 요청하는 위임 로직이 들어있다.**
- 가짜 프록시 객체는 내부에 진짜 myLogger를 찾는 방법을 알고 있다.
- 클라이언트가 `myLogger.logic()`을 호출하면 사실은 가짜 프록시 객체의 메서드를 호출한 것이다.
- 가짜 프록시 객체는 request스코프의 진짜 `myLogger.logic()`를 호출한다.
- 가짜 프록시 객체는 원본 클래스를 상속 받아 생성됐기 때문에 이 객체를 사용하는 클라이언트 입장에서는 사실
원본인지 아닌지도 모르게, 동일하게 사용할 수 있다.(다형성)



> **동작 정리**
> - CGLIB라는 라이브러리로 내 클래스를 상속 받은 가짜 프록시 객체를 만들어 주입
> - 이 가짜 프록시 객체는 실제 요청이 들어오면 그때 내부에서 실제 빈을 요청하는 위임 로직이 들어있음.
> - 가짜 프록시 객체는 실제 request scope와 관계 없음. 단순히 가짜이고, 내부에 위임 로직만 존재, 싱글톤 처럼 동작.


**특징 정리**
- 프록시 객체 덕분에 클라이언트는 마치 싱글톤 빈을 사용하듯이 편리하게 request scope를 사용할 수 있다.
- Provider를 사용하든, proxy를 사용하든 핵심 아이디어는 진짜 객체 조회를 꼭 필요한 시점까지 지연처리 한다는 점이다.
- 단지 애노테이션 설정 변경만으로 원본 객체를 proxy 객체로 대체할 수 있다. 이것이 바로 다형성과 DI 컨테이너가 가진 큰 장점이다.
- 또한 웹 스코프가 아니어도 프록시는 사용할 수 있다.


**주의점**
- 마치 싱글톤을 사용하는 것 같지만 다르게 동작하기 때문에 주의해서 사용해야함
- 이런 특별한 scope는 꼭 필요한 곳에만 최소화해서 사용, 무분별하게 사용시 유지보수하기 어려움