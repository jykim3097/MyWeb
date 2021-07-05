# Spring
spring framework ⚙

21.06.11   
## 여는 말
### 스프링 웹 프로젝트는 두 가지 방식
* 참고) https://developsd.tistory.com/68
* Spring starter project : Spring Boot를 이용하는 프로젝트
	* 최소한의 개발을 해야 하는 경우 사용
* Spring regacy project : 스프링 템플릿 프로젝트를 이용하는 프로젝트
	* 가장 기본이 되는 스프링 프레임워크
	* 설정 작업이 조금 복잡하지만 실제 개발 업무에서 많이 사용하는 방식
	* boot에 비해 내용이 방대하다
	* xml로 작성
	* ex) 전자 정부 프레임워크(Spring regacy 버전 3 사용)
* 두개가 완전히 다른 건 아니다

## 개요
### 스프링 프레임워크란? 
* 프레임워크 : 뼈대를 이루는 코드 묶음
* 방향성을 제시하고, 원하는 기능을 빠르게 만들 수 있게 한다
* 라이브러리랑 다르다, 라이브러리가 모인 전체적인 큰 묶음이 프레임워크
	* 라이브러리 = 모듈 : 프레임워크 안에 미리 만들어져 있는 기능
* 스프링 프레임워크의 주요 기능 : DI, AOP, MVC, JDBC 등 제공

### 스프링 프레임워크의 특징
#### 1. POJO(Plain Old Java Object) 기반의 프레임워크
* 자바 객체의 라이프사이클을 스프링 컨테이너가 직접 관리
* 스프링 컨테이너로부터 필요한 객체를 얻어올 수 있다
* getter, setter 같이 기본적인 기능만 가진 자바 객체
* 상속받아 사용하는 것이 아니라 어노테이션을 통해 객체를 주입 받는다
* 이렇게 작성하면 클래스 간의 결합도가 낮아져 다른 솔루션으로 변경할 때 어노테이션만 변경하면 되므로 유지 보수에 있어 좀 더 유용하게 활용할 수 있다.
* 참고) https://velog.io/@galaxy/Spring%EC%9D%98-%EA%B8%B0%EB%B3%B8-%ED%8A%B9%EC%A7%95-POJO   

#### 2. DI(Dependency Injection, 의존성 주입)을 지원
* 각 계층, 서비스, 객체 사이의 의존성이 존재할 경우 스프링 프레임워크가 연결시킨다
* 클래스 간의 약한 결합을 가능케 한다
* 주입? 외부에서 객체를 생성해서 넣어주는 것
* DI는 의존성을 분리시켜 사용한다    

#### 3. AOP(Aspect Oriented Programming)을 지원
* 관점 지향 프로그래밍
* 로직을 기준으로 핵심 관점과 부차 관점으로 나누어 그 관점을 기준으로 각각 모듈화하겠다는 것
* 트랜잭션, 로깅, 보안 등 여러 모듈에서 공통적으로 지원하는 기능을 분리해 사용할 수 있다.    

#### 4. 확장성이 높다
* 스프링 프레임워크의 소스는 모두 라이브러리로 분리시켜져 있어서 필요한 라이브러리만 가져다 쓸 수 있다.    

#### 5. Model2 방식의 MVC Framework를 지원

### 스프링 프레임워크 모듈
 
### 스프링 컨테이너 (IoC) - Core Container
* 스프링에서 제공하고 있는 모듈 중 spring-core는 DI와 IoC를 제공한다
* 객체를 생성하고 조립하고, **컨테이너를 통해 생성된 객체를 Bean이라고 부른다**
* IoC : Inversion of Control (제어의 역전)
	* 객체를 필요할 때 생성해서 사용하던 방식을 **객체를 미리 생성해놓고 꺼내서 사용한다**

### Maven
* 원격에 있는 라이브러리를 네트워크를 통해 다운받을 수 있게 하는 플랫폼
* pom.xml은 메이븐 설정 파일로, 메이븐은 라이브러리를 연결해주고 빌드를 위한 플랫폼이다
* 메이븐을 사용하면 라이브러리를 작성해서 바로 사용할 수 있다

### 실습
* 의존성 주입을 실습하려면, Main에서 SpringTest 객체를 직접 생성하지 않고 스프링 설정 파일을 이용한다 - xml
* Java 파일에서 new 연산자를 사용하지 않고 xml 문서를 이용한다
* xml에서 bean을 선언한 다는 것은 new로 객체를 선언하는 것과 같은 의미를 갖는다
```java
<bean id="객체 이름" class="java 파일 경로" />
```
#### bean의 범위(scope)
* singleton, prototype
* 스프링은 기본적으로 모든 bean을 singleton으로 생성해 관리한다
* singleton : 스프링 컨테이너에서 생성된 bean은 동일한 타입에 대해 한 개만 생성되고, getBean() 메소드로 호출될 때 동일한 객체가 반환된다
* prototype : 하나의 bean에 대해 여러개의 객체가 존재할 수 있다, 싱글톤과 반대되는 개념
	* 설정 파일에서 bean을 정의할 때 scope 속성을 명시해주어야 한다
	* <bean id="id" class="class" scope="prototype" />

## DI와 IoC
* 스프링은 DI와 IoC를 강력하게 지원하는 프레임워크이다
* 그래서 스프링을 DI Framework 또는 IoC Framework라고 부른다
* DI는 IoC 패턴의 구현 방법 중 하나이다
* DI에 따라 프로그램의 흐름이 완전히 변경된다
* 스프링 컨테이너에 만들어둔 각종 클래스(bean)들은 서로 의존적이다
* 의존적이다? 객체 안에 객체가 저장된다

### DI 설정 방법
* 2가지 의존성 주입 방법
```java
// 1. 생성자를 통한 의존성 주입
<bean id="chef" class="ex02.Chef" />

<bean id="hotel" class="ex02.Hotel">
	<constructor-arg ref="chef" />
</bean>

// 2. setter를 통한 의존성 주입
<bean id="dev" class="ex03.DatabaseDev">
	<property name="url" value="데이터베이스 주소" />
	<property name="uid" value="데이터베이스 아이디" />
	<property name="upw" value="데이터베이스 비밀번호" />
</bean>

<bean id="dao" class="ex03.MemberDAO">
	// bean을 값으로 주입할 때는 ref 속성을 이용한다
	<property name="dev" ref="dev" />
</bean> 
```
21.06.14   
### 의존 객체 자동 주입
* 스프링 컨테이너가 자동으로 의존 대상 객체를 찾아서 의존 대상 객체가 필요한 객체에 주입해 주는 기능
* 어노테이션을 이용해 쉽게 구현할 수 있다
* 스프링 컨텍스트의 참조가 필요하다 <context:annotation-config />
#### 구현 방법 
* @Au**to**wired(required = false)
	* 주입하려고 하는 객체의 타입이 일치하는 객체를 자동으로 주입한다
	* **타입**을 기준으로 의존성 주입
	* 같은 타입의 bean이 두개 이상 있으면 **이름**으로 bean을 찾음
	* *생성자, setter, 멤버변수*에 적용 가능
	* required = false 속성
		* 스프링은 주입할 bean이 없으면 에러를 발생시키는데, 이 때 이를 무시하고 넘어가는 속성이다
		* **단, 기본 생성자가 반드시 필요하다**
		* 값이 없는데 부르면 null point error 가 발생한다.
	* spring annotation
* @Qualifier : 모호한 빈의 강제 연결
	* 같은 타입의 bean 객체가 여러개 존재하면 어떤 bean을 사용해야할 지 몰라 에러가 발생한다.
	* 이 때 **bean의 이름으로 직접 의존성을 주입**할 수 있다
	* 매개변수로 강제 연결할 bean 이름을 적는다
	* @Autowired와 같이 작성한다
* @Resource
	* 주입하려고 하는 객체의 이름이 일치하는 객체를 자동으로 주입한다
	* name 속성을 이용해 빈의 이름을 직접 지정한다
	* 이름으로 찾아서 주입하고, 이름이 없으면 자동으로 타입을 찾아서 주입한다
	* 같은 타입의 bean 객체가 여러개 존재하면 name 속성으로 강제 주입한다.
	* Qualifier 를 사용할 수 없다
	* JavaSE의 annotation, java 8버전 이하에서만 사용할 수 있다
	* *setter, 멤버변수*에 적용 가능

## XML 파일을 Java 파일로 변경하기
* @Configuration : 스프링 컨테이너를 대신 생성하는 어노테이션
* @Bean : bean으로 등록하는 어노테이션

## 스프링 MVC 프레임워크 
### 동작 구조
* 스프링은 MVC2 방식을 사용
* 참고) https://tinkerbellbass.tistory.com/40
1. DispatcherServlet 
	* Dispatcher : 분배하다, 보급하다 > Dispatcher Servlet : 서블릿을 분배하다, 보급하다
	* 모든 요청을 받는다 > 요청을 처리하기 위해 HandlerMapping 객체에 컨트롤러 검색 요청
	* web.xml에서 설정한다
2. HandlerMapping
	* 어떤 컨트롤러를 사용할 것인가를 결정 
	* HTTP 요청 정보를 이용해 컨트롤러 객체를 찾아 DispatcherServlet에 리턴한다
	* url 주소를 분리해 DispatcherServlet으로 반환한다
3. HandlerAdapter
	* 리턴을 받은 DispatcherServlet는 HandlerAdapter 객체에 적합한 메서드를 찾아달라고 요청하고,
	* HandlerAdapter가 요청 처리를 Controller에게 위임한다
4. Controller
	* Controller는 데이터와 view에 대한 정보(어느 화면으로 갈 것인가)를 model 객체에 담아 DispatcherServlet에 리턴한다
5. ViewResolver
	* model 객체를 반환 받은 DispatcherServlet은 ViewResolver(뷰 합성기)로 값을 전달한다
	* model 객체에 담긴 view에 대한 정보(jsp파일)를 이용해 view 객체를 찾거나 생성해서 리턴하고,
	* 화면으로 갈 정보를 다시 DispatcherServlet으로 전달한다 
6. View
	* DispatcherServlet은 ViewResolver가 반환한 view 객체에게 응답 결과 생성을 요청한다

### web.xml
* 사용자의 모든 요청을 받는 DispatcherServlet을 등록한다
* 모든 요청을 받기 위해 url pattern은 '/'로 설정한다

### servlet-context.xml
* DispatcherServlet 설정 파일
	* 스프링 설정 파일은 bean 객체를 생성해 조립하므로 bean 객체를 생성한다
* HandlerMapping, HandlerAdapter, ViewResolver를 설정한다
* <mvc:annotation-driven />
	* 반드시 선언되어야 한다
	* HandlerMapping, HandlerAddapter를 객체로 생성하고
	* annotation 등의 기능을 활성화한다
* <resources mapping="/resources/**" location="/resources/" />
	* resources 폴더 아래 파일에 맵핑해라
	* css, js 파일(resource)을 사용할기 위한 경로 설정
* ViewResolver 설정
	* prefix와 surfix를 설정해 이름만으로 맵핑을 가능하게 한다
* <context:component-scan base-package="com.myweb.xxxx" /> 
	* 패키지를 자동으로 스캔해 패키지 아래에 있는 파일을 bean 객체로 생성한다

### Controller 
* DispatcherServlet → HandlerAdapter → **Controller → Service → DAO** → Database
	* 사용자의 요청을 실제로 처리하는 객체들
```java
@RequestMapping("/success")
public String success(Model model) {
	return "success";
}
```
* Model 객체는 success 메서드 실행 후 view(jsp)에서 활용될 객체를 담고 있다

### View
* 사용자 응답 브라우저
* controller의 메서드에서 반환되는 값
* 이 값을 viewResolver가 jsp파일로 조립한다
* Url 맵핑값(클라이언트 요청 정보)에 해당하는 jsp 파일 

## 구동 과정 정리
1. 클라이언트가 페이지를 요청한다
2. web.xml에서 dispatcherServlet이 요청을 받는다 (Handler)
3. servlet-context.xml이 요청에 대한 컨트롤러를 검색한다
	* Handler Mapping으로 컨트롤러를 검색한다
4. 컨트롤러 요청 처리 후 이름을 리턴한다
5. view resolver가 받은 이름을 찾아서 처리한다

21.06.15
* mvc 방식은 기본적으로 주소가 요청으로 남는 포워드 방식을 사용하기 때문에 리다이렉트 방식도 사용할 수 있다.

### 요청 파라미터(request)
* 스프링은 사용하고자하는 객체가 있다면 메서드의 매개변수에 처리한다 (의존성 주입)
	* 사용하고자하는 객체를 매개변수에 선언해라

* 어노테이션 처리
	* 단일 값을 처리할 때 사용
	* 값이 없으면 에러 발생
		1. 값을 1개 이상 선택하도록  처리
		2. required = false로 설정

* 커멘드객체(클래스)를 통한 처리
	* 들어오는 여러 객체를 한 번에 맵핑시켜 받는다

* 사소한 요청도 다 컨트롤러에 만든다고 생각하면 된다


## 스프링 조립
### JSP에 빌드 툴을 사용하는 방법
1. Maven을 사용하는 방법
2. Gradle을 사용하는 방법

### 스프링 설정파일을 사용하는 방법
1. xml을 이용하는 방법
2. 자바 코드를 이용하는 방법

21.06.17    
# 7강 스프링 MVC 웹 서비스

## 전통적인 JDBC 프로그래밍
* 드라이버 로딩 > DB 연결 > SQL 작성 및 전송 > 자원해제
* Connection 객체 생성 > PrepareStatement 객체 생성 > SQL문 실행 > ResultSet 객체 생성 결과 처리
* 반복적인 작업이 계속 되는 단점이 있다

## Spring JDBC
* JDBC의 장점을 유지하면서 단점을 극복하고, 간결한 형태의 API 사용법을 제공해 기존 방식에서 지원하지 않는 편리한 기능 제공
* 반복적진 작업을 대신한다
* SQL에 바인딩할 값만 지정해주면 된다
* DB 커넥션을 가져오는 DataSource가 강제화된다

## Connection Pool과 DataSource

### Connection Pool
* 여러 사용자를 **동시에** 처리하는 웹 어플리케이션
* DB에 연결할 때 매번 연결하는 방식이 아닌 **미리 연결해서 사용하는 기법**
* 이를 통해 성능을 향상시킨다
* 미리 정해진 개수만큼 DB 커넥션을 풀에 준비해두고
* 어플리케이션이 요청할 때마다 풀에서 꺼내서 할당하고
* 다시 돌려 받아 Pool에 넣는 기법이다
* 속도 면에서 빠르고, 최근 유행하는 HikariCP 라이브러리를 사용한다

### DataSource
* DB에 이용되는 URL, id, pw DriverClass를 미리 정의해놓고 사용하는 객체
* Spring-jdbc에서 기본적으로 제공한다
* 여러 커넥션풀 라이브러리에서 기본으로 제공한다
* 여기서는 HikariCP에서 지원하는 객체를 사용한다

## 연결 작업
* 필요한 라이브러리를 Maven Repository에 들어가서 다운받는다
#### 1. Oracle Connector
	* oracle jdbc를 검색한다
	* 버전에 따라 다 커넥터가 다르기 때문에 버전을 잘 확인해야한다!!
	* ojdbc8 19.7.0.0 버전의 dependency를 복사해 pom.xml에 붙인다.
#### 2. hikariCP
	* 상위 버전은 오류가 있을 수 있어서 적당한 버전을 선택한다
	* 3.3.1 버전 사용
#### 3. Spring jdbc
	* 스프링과 같은 버전으로 다운받는다
	* 5.0.7 버전 다운
#### 4. Spring test
	* DB 연결을 테스트하기 위해 사용한다
	* junit 단위 테스트
	* junit 버전을 4.12 이상 사용해야한다

### DB 구축
1. sql developer에서 스프링에서 사용할 계정 만들기 
2. DB 연결은 root-context에서 작업한다
	* web.xml에 보면 가장 위에 작성되어있다
	* 다운받은 라이브러리를 참조해서 작성하는 방식을 사용한다
		* 아래 Namespaces 탭에서 필요한 라이브러리를 선택하면 자동으로 추가된다
	* 아래 코드를 나중에는 보안을 위해 properties에 넣는다
```
<bean id="hikariConfig" class="com.zaxxer.hikari.HikariConfig">
	<property name="driverClassName" value="oracle.jdbc.driver.OracleDriver" />
	<property name="jdbcUrl" value="url" />
	<property name="username" value="name" />
	<property name="password" value="pw" /> 
</bean>
```

# 코딩팁!
* DB가 있어야 다른 것도 연결할 수 있기 때문에 DB 연결 작업은 프로젝트 초기에 진행한다!
	* 디스패쳐서블릿이 연결되기 전에 처리되어야하는 작업이기 때문 

21.06.18   
## MyBatis
* 개발자가 지정한 SQL, 고급 매핑을 지원하는 프레임워크
* JDBC 코드와 파라미터, 결과 맵핑을 대신한다
	* JDBC 코드를 걷어내 깔끔한 소스코드를 유지할 수 있다.
* DAO 계층을 대신한다
	* 기존 DAO의 interface의 구현 클래스를 xml로 대신한다 (mapper.xml)
* 전통적 JDBC 프로그래밍에 비해
	* **자동으로** Connection을 생성한다
	* **자동으로** Close()를 처리한다
	* **자동으로** PrepareStatement를 처리한다.
	* **자동으로** 리턴타입으로 ResultSet을 처리한다
	* #{name}을 통해 '?'를 처리한다

### SqlSessionFactory
* **myBatis 핵심 객체**
* spring-jdbc 라이브러리를 꼭 추가해야한다.
* 사용방법
	* 스프링 컨테이너에 SqlSessionFactoryBean 클래스를 sqlSessionFactory 이름으로 생성한다
	* 생성한 dataSource를 주입한다. (ref 속성 사용)
	* mybatis-spring:scan을 추가해 해당 패키지를 스캔해 xml 파일을 객체로 생성한다
* mapper.xml 에서 반환 타입을 추가할 때 패키지 alias를 만들어 관리할 수 있다. 
* configuration 파일을 만들어 저장하고 스프링 컨테이너에 파일 경로를 추가한다.
	* sqlSessionFactory 의 property로 추가한다

### MyBatis Mapper XML의 주요 속성

#### mapper 태그
* namespace : mapper 인터페이스의 전체 경로 작성

#### select 태그
* id : 인터페이스 메서드명과 똑같이 작성, 메서드를 찾기 위한 구분자
* parameterType : 패키지 경로를 포함한 클래스명을 작성한다.
* resultType : 결과 반환 타입 작성
	* 패키지 경로를 포함한 전체 클래스명을 적는다
	* config 파일을 생성했다면 alias 명을 작성할 수 있다
* resultMap : 외부 map 타입을 이용한 반환 타입
	* 조인이 걸렸을 때 가지고 나갈 수 있다.

#### insert, update, delete 태그
* id
* parameterType

#### cdata 속성
* mybatis가 <>을 태그로 인식하기 때문에 이를 문자열로 인식시키게 한다


21.07.05    
# 파일 업로드
* 일반 업로드와 비동기 업로드, 크게 두 가지 방식이 있다
* 파일이 많아지면 파일을 하드디스크에 저장하고 파일 경로를 DB에 저장한다.

## 일반 업로드
* 크게 3가지 방식이 있는데
* 스프링에서 일반적으로 가장 많이 사용되는 것은 commons-fileupload 라이브러리를 이용하는 것이다
* 이 라이브러리를 pom.xml에 작성해 다운받고 스프링 설정파일(servlet-context.xml)에 설정한다
	* 이 때 bean의 id를 꼭 multipartResolver로 선언해야 인식한다
	* 파일 사이즈와 인코딩 타입도 적는다

### 파일 업로드
* path는 상수로 설정해서 사용하면 편하다
#### 단일 파일 업로드
* jsp에서 하는 설정
	* form의 속성에 enctype="multipart/form-data"를 반드시 설정해야 한다
	* input 태그의 name을 컨트롤러에서 RequestParam으로 받기 때문에 name을 설정해야 한다
* Controller에서 하는 설정
	* RequestParam으로 form input의 name 속성에 접근하고 이를 file 타입으로 받는다 (import MultipartFile)
	* 매개변수로 받아온 파일을 파일 객체에 담아 **transferTo 메서드**를 이용해 로컬에 저장한다
		* 이 메서드가 FileWriter 작업을 한 방에 처리해주는 셈이다
	* 이때 모든 코드는 에러가 발생할 수 있기 때문에 try-catch문 안에 작성한다
#### 다중 파일 업로드
* 한 번에 여러 개를 선택해서 업로드 하는 방법
	* MultipartHttpServletRequest로 파일을 여러 개 받아 list에 파일을 담고 for문을 이용한다
* 한 번에 한 개씩 업로드하는 방법
	* 받아온 파일을 담는 리스트로 파일을 받아서 for문을 이용한다
* 가변적인 폼 형식의 업로드
	* 업로드될 파일을 객체에 담고 이를 list 객체에 담아 컨트롤러로 보낸다
	* 컨트롤러에서는 리스트에 있는 값을 꺼내 업로드한다

## 비동기 업로드