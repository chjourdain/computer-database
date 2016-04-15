<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" tagdir="/WEB-INF/tags/"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<c:set var="resourcesUrl" value="${pageContext.request.contextPath}/resources" />
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="${resourcesUrl}/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="${resourcesUrl}/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="${resourcesUrl}/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${pager.totalCount} Computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="add">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>
		<form id="deleteForm" <f:link pager="${pager}" action="action" type="delete"></f:link> method="POST">
			<input type="hidden" name="selection" value="">
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
						<th><a  <f:link pager="${pager}" action="href" type="dashboard" sort="computer"></f:link> >Computer name</a></th>
						<th><a  <f:link pager="${pager}" action="href" type="dashboard" sort="intro"></f:link> >Introduced date</a></th>
						<!-- Table header for Discontinued Date -->
						<th><a  <f:link pager="${pager}" action="href" type="dashboard" sort="disco"></f:link> >Discontinued date</a></th>
						<!-- Table header for Company -->
						<th><a  <f:link pager="${pager}" action="href" type="dashboard" sort="company"></f:link> >Company</a> </th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">

					<c:forEach var="computer" items="${pager.list}">
						<tr class="computer">
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a href="edit?id=${computer.id}" onclick="">${computer.name}</a>
							</td>
							<td>${computer.introduced}</td>
							<td>${computer.discontinued}</td>
							<td>${computer.companyName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<li><a <f:link pager="${pager}" type="dashboard" action="href" page="1"/> aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<f:printmenupage pager="${ pager}" />
				<li><a <f:link pager="${pager}" type="dashboard" action="href" page="${pager.nbPages+1}"/>     
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<a <f:link pager="${pager}" type="dashboard" action="href" nbByPage="10"/> class="btn btn-default">10</a>
				<a <f:link pager="${pager}" type="dashboard" action="href" nbByPage="50"/> class="btn btn-default">50</a>
				<a <f:link pager="${pager}" type="dashboard" action="href" nbByPage="100"/>  class="btn btn-default">100</a>
			</div>
		</div>
	</footer>
	<script src="${resourcesUrl}/js/jquery.min.js"></script>
	<script src="${resourcesUrl}/js/bootstrap.min.js"></script>
	<script src="${resourcesUrl}/js/dashboard.js"></script>
</body>
</html>