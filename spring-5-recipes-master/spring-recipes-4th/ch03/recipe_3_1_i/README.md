## reservationQuery.jsp 수정 
- fmt:formatDate 단독으로 쓰면 에러나고, parseDate, formatDate 두 개 같이 사용해야 함
- parseDate 는 String 을 Date 형으로 변환
- formatDate 는 Date 형을 지정된 pattern으로 보여준다.
  
      <td>
        <%--<fmt:formatDate value="${reservation.date}" pattern="yyyy-MM-dd"/></td>--%>
        <fmt:parseDate value='${reservation.date}' var='r_date' pattern='yyyy-MM-dd'/>
            <fmt:formatDate value="${r_date}" pattern="yyyy-MM-dd"/>
      </td>

## ReservationServiceImpl.java 수정
- Query 값이 없으면 전체 조회, 있으면 대로 조회
