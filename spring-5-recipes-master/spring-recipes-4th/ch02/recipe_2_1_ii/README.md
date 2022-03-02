
## 0. 호출 구조
### ApplicationContext
### @Configuration, @ComponentScan
### @Component
### context.getBean(SequenceDao.class)
### sequenceDao.getSequence("IT"), sequenceDao.getNextValue("IT")

```mermaid
graph LR;

A[ApplicationContext] --> B[@Configuration, @ComponentScan]
```   

### 1. ApplicationContext 선언 : BasePackages

- com.apress.springrecipes.sequence.Main

```java
    public static void main(String[] args) {

        ApplicationContext context =
        new AnnotationConfigApplicationContext("com.apress.springrecipes.sequence");

        SequenceDao sequenceDao = context.getBean(SequenceDao.class);

        System.out.println("start");
        for (int i = 0; i <10; i++) {
            System.out.println(sequenceDao.getSequence("IT"));
            System.out.println(sequenceDao.getNextValue("IT"));
        }
        System.out.println("end");
    }
```

### 2. @Configuration, @ComponentScan 선언
- com.apress.springrecipes.sequence.config.SequenceGeneratorConfiguration
```java
@Configuration
@ComponentScan(
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = {"com.apress.springrecipes.sequence.*Dao", "com.apress.springrecipes.sequence.*Service"})
        },
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = {org.springframework.stereotype.Controller.class}) }
                )
public class SequenceGeneratorConfiguration {

}
```

### 3. @Component 호출

- com.apress.springrecipes.sequence.SequenceDaoImpl
```java
@Component("sequenceDao")
public class SequenceDaoImpl implements SequenceDao {

    private final Map<String, Sequence> sequences = new HashMap<>();
    private final Map<String, AtomicInteger> values = new HashMap<>();

    public SequenceDaoImpl() {
        sequences.put("IT", new Sequence("IT", "30", "A"));
        values.put("IT", new AtomicInteger(10000));
    }

    public Sequence getSequence(String sequenceId) {
        return sequences.get(sequenceId);
    }

    public int getNextValue(String sequenceId) {
        AtomicInteger value = values.get(sequenceId);
        return value.getAndIncrement();
    }
}
```

- AtomicInteger
  
  An int value that may be updated atomically.
  See the java.util.concurrent.atomic package specification for description of the properties of atomic variables.
  An AtomicInteger is used in applications such as atomically incremented counters, and cannot be used as a replacement for an Integer.
  However, this class does extend Number to allow uniform access by tools and utilities that deal with numerically-based classes.

원자적으로 업데이트될 수 있는 int 값입니다.
원자 변수의 특성에 대한 설명은 java.util.cocurrent.atomic 패키지 사양을 참조하십시오.
원자 정수는 원자증 카운터와 같은 응용 분야에서 사용되며 정수의 대체품으로 사용할 수 없습니다.
그러나 이 클래스는 숫자 기반 클래스를 처리하는 도구 및 유틸리티별로 균일한 액세스를 허용하도록 번호를 확장합니다.

### 4. context로 SequenceDao 클래스 빈 선언

```java
    SequenceDao sequenceDao = context.getBean(SequenceDao.class);
```

### 5. sequenceDao 인터페이스 Method 호출 : getSequence("IT"), getNextValue("IT")

```java
    System.out.println(sequenceDao.getSequence("IT"));
    System.out.println(sequenceDao.getNextValue("IT"));
```
