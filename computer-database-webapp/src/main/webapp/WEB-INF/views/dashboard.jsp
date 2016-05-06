<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<c:set var="resourcesUrl"
	value="${pageContext.request.contextPath}/resources" />
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="${resourcesUrl}/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="${resourcesUrl}/css/font-awesome.css" rel="stylesheet"
	media="screen">
<link href="${resourcesUrl}/css/main.css" rel="stylesheet"
	media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> <spring:message
					code="title" /></a> <span style="float: right;"><a
				href="?lang=en"><img src="${resourcesUrl}/icone/en.png"></a> |
				<a href="?lang=fr"><img src="${resourcesUrl}/icone/fr.png"></a>
				<a href="../logout"><img src="${resourcesUrl}/icone/logout.png"></a></span>
		</div>
	</header>
	<section id="main">
		<span class="lang-sm lang-lbl" lang="en"></span>
		<div class="container">
			<h1 id="homeTitle">${pager.totalElements}
				<spring:message code="computer.found" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control"
							placeholder="<spring:message code="placeholder.search"/>" /> <input
							type="submit" id="searchsubmit"
							value="<spring:message code="button.filter"/>"
							class="btn btn-primary" />
					</form>
				</div>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<div class="pull-right">
						<a class="btn btn-success" id="addComputer" href="add"><spring:message
								code="button.add" /></a> <a class="btn btn-default"
							id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message
								code="button.edit" /></a>
					</div>
				</sec:authorize>
			</div>
		</div>
		<form id="deleteForm"
			<f:link pager="${pager}" action="action" type="delete"></f:link>
			method="POST">
			<input type="hidden" name="selection" value=""> <input
				type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><a
							<f:link pager="${pager}" action="href" type="dashboard" sort="name"></f:link>><spring:message
									code="computer.name" /></a></th>
						<th><a
							<f:link pager="${pager}" action="href" type="dashboard" sort="introduced"></f:link>><spring:message
									code="computer.introduced" /></a></th>
						<!-- Table header for Discontinued Date -->
						<th><a
							<f:link pager="${pager}" action="href" type="dashboard" sort="discontinued"></f:link>><spring:message
									code="computer.discontinued" /></a></th>
						<!-- Table header for Company -->
						<th><a
							<f:link pager="${pager}" action="href" type="dashboard" sort="company.name"></f:link>><spring:message
									code="computer.company" /></a></th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">

					<c:forEach var="computer" items="${pager.content}">
						<tr class="computer">
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><sec:authorize access="hasRole('ROLE_ADMIN')">
									<a href="edit?id=${computer.id}" onclick="">
								</sec:authorize> ${computer.name} <sec:authorize access="hasRole('ROLE_ADMIN')">
									</a>
								</sec:authorize></td>
							<c:set var="locale">${pageContext.response.locale}</c:set>
							<c:set var="pattern"
								value="${ ('en' eq locale) ? 'MM-dd-yyyy' : 'dd-MM-yyyy'}" />
							<fmt:parseDate pattern="yyyy-MM-dd"
								value="${computer.introduced}" var="dateIntroduced" />
							<fmt:parseDate pattern="yyyy-MM-dd"
								value="${computer.discontinued}" var="dateDiscontinued" />
							<td><fmt:formatDate value="${dateIntroduced}"
									pattern="${pattern}" /></td>
							<td><fmt:formatDate value="${dateDiscontinued}"
									pattern="${pattern}" /></td>
							<td>${computer.companyName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center"><%@ taglib prefix="sec"
				uri="http://www.springframework.org/security/tags"%>
			<ul class="pagination">
				<li><a
					<f:link pager="${pager}" type="dashboard" action="href" page="0"/>
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<f:printmenupage pager="${ pager}" />
				<li><a
					<f:link pager="${pager}" type="dashboard" action="href" page="${pager.totalPages}"/>
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<a
					<f:link pager="${pager}" type="dashboard" action="href" nbByPage="10"/>
					class="btn btn-default">10</a> <a
					<f:link pager="${pager}" type="dashboard" action="href" nbByPage="50"/>
					class="btn btn-default">50</a> <a
					<f:link pager="${pager}" type="dashboard" action="href" nbByPage="100"/>
					class="btn btn-default">100</a>
			</div>
		</div>
	</footer>
	<script src="${resourcesUrl}/js/jquery.min.js"></script>
	<script src="${resourcesUrl}/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		var translated = new Array();
		translated['button.edit'] = "<spring:message code='button.edit' javaScriptEscape='true' />";
		translated['button.view'] = "<spring:message code='button.view' javaScriptEscape='true' />";
		translated['delete.pop.up'] = "<spring:message code='delete.pop.up' javaScriptEscape='true' />";
	</script>
	<script src="${resourcesUrl}/js/dashboard.js"></script>
</body>
</html>