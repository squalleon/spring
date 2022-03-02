
### References
https://howtodoinjava.com/spring-mvc/spring-mvc-hello-world-example/

### Table Of Contents
- What is MVC Framework?
- Dispatcher Servlet (Spring Controller)
- Spring MVC Hello World Example
    - Runtime Dependencies
    - Configuration Files web.xml and spring-servlet.xml
    - Request Handler EmployeeController.java
    - View Model EmployeeVO.java
    - Dao Classes
    - Service layer Classes
    - View employeesListDisplay.jsp

### 
```xml
<mvc:resources location="/WEB-INF/resources/" mapping="/**"></mvc:resources>
```

### Interceptor 추가
```xml
<mvc:interceptors>
		<mvc:interceptor>
			<mvc:mapping path="/*"/>
			<mvc:exclude-mapping path="/login"/>
			<bean class="com.howtodoinjava.demo.cmmn.AuthInterceptor"
		</mvc:interceptor>
	</mvc:interceptors>
```

- AuthInterceptor.java 추가
```java
public class AuthInterceptor extends WebContentInterceptor {

}
```

>'preHandle(HttpServletRequest, HttpServletResponse, Object)' in 'org.springframework.web.servlet.mvc.WebContentInterceptor' clashes with 'preHandle(HttpServletRequest, HttpServletResponse, Object)' in 'org.springframework.web.servlet.HandlerInterceptor'; overridden method does not throw 'javax.servlet.ServletException'

### [Spring]Interceptor를 이용하여 세션 및 권한 체크 하기
> https://eastglow.github.io/back-end/2018/11/05/Spring-Interceptor%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%98%EC%97%AC-%EC%84%B8%EC%85%98-%EB%B0%8F-%EA%B6%8C%ED%95%9C-%EC%B2%B4%ED%81%AC-%ED%95%98%EA%B8%B0.html


GET http://localhost:8080/springmvc/employee-module/getAllEmployees
 
http://localhost:8080/springmvcexample/employee-module/getAllEmployees 
