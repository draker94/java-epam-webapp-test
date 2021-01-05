<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 02.01.2021
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/implicit.tld" prefix="tag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="by/training/resources/translate"/>

<fmt:message key="assignment.enroll.label.title" var="title"/>
<tag:head title="${title} ${student.surname} ${student.name}">
    <c:url var="assignmentSaveUrl" value="/assignment/save.html"/>
    <form action="${assignmentSaveUrl}" method="post">
        <input type="hidden" name="studentId" value="${student.id}">
        <p><fmt:message key="assignment.enroll.label.student"/> ${student.surname} ${student.name}</p>
        <p><fmt:message key="assignment.enroll.label.course"/>
            <select name="courseId">
                <c:forEach var="course" items="${courseList}">
                        <option value="${course.id}">${course.name}</option>
                </c:forEach>
            </select>
        </p>
        <p><fmt:message key="assignment.enroll.label.start"/>
            <input type="date" name="beginDate" value="${assignment.beginDate}">
        </p>
        <p><fmt:message key="assignment.enroll.label.end"/>
            <input type="date" name="endDate" value="${assignment.endDate}">
        </p>
        <button type="submit"><fmt:message key="label.save"/></button>
    </form>
    <c:url var="back" value="/course/list.html"/>
    <a href="${back}"><fmt:message key="label.back"/></a>
</tag:head>