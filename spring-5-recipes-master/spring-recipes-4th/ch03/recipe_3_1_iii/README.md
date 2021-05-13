## ReservationServiceImpl.java, reservationQuery.jsp 

- GregorianCalendar() 함수와 fmt:formatDate 는 바로 사용 가능하다.
- 이유는 GregorianCalendar() 리턴이 Date 형이기 때문이다.

```java
public ReservationServiceImpl() {
reservations = new ArrayList<>();
reservations.add(new Reservation("Tennis #1",
new GregorianCalendar(2008, 0, 14).getTime(), 16,
new Player("Roger", "N/A"), TENNIS));
reservations.add(new Reservation("Tennis #2",
new GregorianCalendar(2008, 0, 14).getTime(), 20,
new Player("James", "N/A"), TENNIS));
```

```html
    <td>
        <fmt:formatDate value="${reservation.date}" pattern="yyyy-MM-dd"/>
    </td>
```

- LocalDate.of() 함수는 fmt:parseDate, fmt:formatDate 로 변환해서 사용해야 한다.

```java
public ReservationServiceImpl() {
    reservations.add(new Reservation("Tennis #1", LocalDate.of(2008, 1, 14), 16,
            new Player("Roger", "N/A"), TENNIS));
    reservations.add(new Reservation("Tennis #2", LocalDate.of(2008, 1, 14), 20,
            new Player("James", "N/A"), TENNIS));
}
```

```html
    <td>
        <fmt:parseDate value='${reservation.date}' var='r_date' pattern='yyyy-MM-dd'/>
        <fmt:formatDate value="${r_date}" pattern="yyyy-MM-dd"/>
    </td>
```