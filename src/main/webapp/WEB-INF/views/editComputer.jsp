<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" tagdir="/WEB-INF/tags/"%>
<!DOCTYPE html>
<c:set var="resourcesUrl" value="${pageContext.request.contextPath}/resources" />
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${resourcesUrl}/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="${resourcesUrl}/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="${resourcesUrl}/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computer.id}</div>
					<h1>Edit Computer</h1>

					<form action="edit" method="POST">
						<input type="hidden" name="id" value="${computer.id}" />
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									name="computerName" type="text" class="form-control"
									id="computerName" data-validation="length"
									data-validation-length="min3" value="${computer.name}">
								<span class="help-block form-error"> ${error['name']} </span>
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="date" class="form-control" id="introduced"
									name="introduced" data-validation="custom"
									data-validation-regexp="^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$|^$"
									value="${computer.introduced}"> <span
									class="help-block form-error">${error['introduced']}</span>
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="date" class="form-control" id="discontinued"
									name="discontinued" data-validation="custom"
									data-validation-regexp="^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$|^$"
									value="${computer.discontinued }"> <span
									class="help-block form-error">${error['discontinued']}</span>
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									<option value="0">--</option>
									<c:forEach items="${map}" var="entry">
										<option
											<f:selected stringTest="${entry.value}" stringRef="${computer.companyName}"></f:selected>
											value="${entry.key}">${entry.value}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
					<script type="text/javascript" src="${resourcesUrl}/js/jquery.min.js"></script>
					<script type="text/javascript"
						src="${resourcesUrl}/js/jquery-form-validator.js"></script>
					<script>
						$.validate();
					</script>
				</div>
			</div>
		</div>
	</section>
</body>
</html>