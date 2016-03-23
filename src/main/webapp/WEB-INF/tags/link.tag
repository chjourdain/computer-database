<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="pager" required="true"
	type="com.excilys.formation.computerdatabase.model.Pager"%>

<%@ attribute name="type" required="true" type="java.lang.String"%>
<%@ attribute name="name" required="false" type="java.lang.String"%>
<%@ attribute name="page" required="false" type="java.lang.String"%>
<%@ attribute name="nbParPage" required="false" type="java.lang.String"%>
<%@ attribute name="search" required="false" type="java.lang.String"%>
<%@ attribute name="action" required="false" type="java.lang.String"%>
<%@ attribute name="orderBy" required="false" type="java.lang.String"%>

<c:if test="${empty page}">
	<c:set var="page" value="${pager.pageActuelle }" />
</c:if>
<c:if test="${empty nbParPage}">
	<c:set var="nbParPage" value="${pager.nbParPage}" />
</c:if>
<c:if test="${empty search}">
	<c:set var="search" value="${pager.search}" />
</c:if>
<c:if test="${empty orderBy}">
	<c:set var="orderBy" value="${pager.orderBy}" />
</c:if>

<c:choose>
	<c:when test="${action == 'action'}">
	<c:out value="action='${type}?Page=${page}&Nb=${nbParPage}&search=${search}&order=${orderBy}'" escapeXml="false"/>
	</c:when>
	<c:when test="${action == 'href'}">href="${type}?Page=${page}&Nb=${nbParPage}&search=${search}&order=${orderBy}"</c:when>
	<c:otherwise>
	<a href="${type}?Page=${page}&Nb=${nbParPage}&search=${search}&order=${orderBy}">${name}</a>
	</c:otherwise>
</c:choose>

