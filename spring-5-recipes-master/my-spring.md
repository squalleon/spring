
- AtomicInteger
  An int value that may be updated atomically.
  See the java.util.concurrent.atomic package specification for description of the properties of atomic variables.
  An AtomicInteger is used in applications such as atomically incremented counters, and cannot be used as a replacement for an Integer.
  However, this class does extend Number to allow uniform access by tools and utilities that deal with numerically-based classes.

원자적으로 업데이트될 수 있는 int 값입니다.
원자 변수의 특성에 대한 설명은 java.util.cocurrent.atomic 패키지 사양을 참조하십시오.
원자 정수는 원자증 카운터와 같은 응용 분야에서 사용되며 정수의 대체품으로 사용할 수 없습니다.
그러나 이 클래스는 숫자 기반 클래스를 처리하는 도구 및 유틸리티별로 균일한 액세스를 허용하도록 번호를 확장합니다.

- Bean

Indicates that a method produces a bean to be managed by the Spring container.
Overview
The names and semantics of the attributes to this annotation are intentionally similar to those of the <bean/> element in the Spring XML schema. For example:
       @Bean
       public MyBean myBean() {
           // instantiate and configure MyBean obj
           return obj;
       }
   
Bean Names
While a name attribute is available, the default strategy for determining the name of a bean is to use the name of the @Bean method. This is convenient and intuitive, but if explicit naming is desired, the name attribute (or its alias value) may be used. Also note that name accepts an array of Strings, allowing for multiple names (i.e. a primary bean name plus one or more aliases) for a single bean.
       @Bean({"b1", "b2"}) // bean available as 'b1' and 'b2', but not 'myBean'
       public MyBean myBean() {
           // instantiate and configure MyBean obj
           return obj;
       }
   
Profile, Scope, Lazy, DependsOn, Primary, Order
Note that the @Bean annotation does not provide attributes for profile, scope, lazy, depends-on or primary. Rather, it should be used in conjunction with @Scope, @Lazy, @DependsOn and @Primary annotations to declare those semantics. For example:
       @Bean
       @Profile("production")
       @Scope("prototype")
       public MyBean myBean() {
           // instantiate and configure MyBean obj
           return obj;
       }
   
The semantics of the above-mentioned annotations match their use at the component class level: @Profile allows for selective inclusion of certain beans. @Scope changes the bean's scope from singleton to the specified scope. @Lazy only has an actual effect in case of the default singleton scope. @DependsOn enforces the creation of specific other beans before this bean will be created, in addition to any dependencies that the bean expressed through direct references, which is typically helpful for singleton startup. @Primary is a mechanism to resolve ambiguity at the injection point level if a single target component needs to be injected but several beans match by type.
Additionally, @Bean methods may also declare qualifier annotations and @Order values, to be taken into account during injection point resolution just like corresponding annotations on the corresponding component classes but potentially being very individual per bean definition (in case of multiple definitions with the same bean class). Qualifiers narrow the set of candidates after the initial type match; order values determine the order of resolved elements in case of collection injection points (with several target beans matching by type and qualifier).
NOTE: @Order values may influence priorities at injection points, but please be aware that they do not influence singleton startup order which is an orthogonal concern determined by dependency relationships and @DependsOn declarations as mentioned above. Also, javax.annotation.Priority is not available at this level since it cannot be declared on methods; its semantics can be modeled through @Order values in combination with @Primary on a single bean per type.
@Bean Methods in @Configuration Classes
Typically, @Bean methods are declared within @Configuration classes. In this case, bean methods may reference other @Bean methods in the same class by calling them directly. This ensures that references between beans are strongly typed and navigable. Such so-called 'inter-bean references' are guaranteed to respect scoping and AOP semantics, just like getBean() lookups would. These are the semantics known from the original 'Spring JavaConfig' project which require CGLIB subclassing of each such configuration class at runtime. As a consequence, @Configuration classes and their factory methods must not be marked as final or private in this mode. For example:
   @Configuration
   public class AppConfig {
  
       @Bean
       public FooService fooService() {
           return new FooService(fooRepository());
       }
  
       @Bean
       public FooRepository fooRepository() {
           return new JdbcFooRepository(dataSource());
       }
  
       // ...
   }
@Bean Lite Mode
@Bean methods may also be declared within classes that are not annotated with @Configuration. For example, bean methods may be declared in a @Component class or even in a plain old class. In such cases, a @Bean method will get processed in a so-called 'lite' mode.
Bean methods in lite mode will be treated as plain factory methods by the container (similar to factory-method declarations in XML), with scoping and lifecycle callbacks properly applied. The containing class remains unmodified in this case, and there are no unusual constraints for the containing class or the factory methods.
In contrast to the semantics for bean methods in @Configuration classes, 'inter-bean references' are not supported in lite mode. Instead, when one @Bean-method invokes another @Bean-method in lite mode, the invocation is a standard Java method invocation; Spring does not intercept the invocation via a CGLIB proxy. This is analogous to inter-@Transactional method calls where in proxy mode, Spring does not intercept the invocation — Spring does so only in AspectJ mode.
For example:
   @Component
   public class Calculator {
       public int sum(int a, int b) {
           return a+b;
       }
  
       @Bean
       public MyBean myBean() {
           return new MyBean();
       }
   }
Bootstrapping
See the @Configuration javadoc for further details including how to bootstrap the container using AnnotationConfigApplicationContext and friends.
BeanFactoryPostProcessor-returning @Bean methods
Special consideration must be taken for @Bean methods that return Spring BeanFactoryPostProcessor (BFPP) types. Because BFPP objects must be instantiated very early in the container lifecycle, they can interfere with processing of annotations such as @Autowired, @Value, and @PostConstruct within @Configuration classes. To avoid these lifecycle issues, mark BFPP-returning @Bean methods as static. For example:
       @Bean
       public static PropertySourcesPlaceholderConfigurer pspc() {
           // instantiate, configure and return pspc ...
       }
   
By marking this method as static, it can be invoked without causing instantiation of its declaring @Configuration class, thus avoiding the above-mentioned lifecycle conflicts. Note however that static @Bean methods will not be enhanced for scoping and AOP semantics as mentioned above. This works out in BFPP cases, as they are not typically referenced by other @Bean methods. As a reminder, a WARN-level log message will be issued for any non-static @Bean methods having a return type assignable to BeanFactoryPostProcessor.
Since:
3.0
See Also:
Configuration, Scope, DependsOn, Lazy, Primary, org.springframework.stereotype.Component, org.springframework.beans.factory.annotation.Autowired, org.springframework.beans.factory.annotation.Value
  Gradle: org.springframework:spring-context:5.0.5.RELEASE