<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="static/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="static/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard.html"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
   <h4 align="center" style="color:${service.color};"><c:out value="${service.resultat}"/></h4> 
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Add Computer</h1>
                    <form action="create" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName" name="computerName" placeholder="Computer name">
                                <b style="color:'red'"> ${service.erreur['name']} </b>
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>&emsp;  <b style="color:red;">    ${service.erreur['introduced']}</b>
                                <input type="date" class="form-control" id="introduced" name="introduced" placeholder="Introduced date">
                                 
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>&emsp;  <b style="color:red;">    ${service.erreur['discontinued']}</b>
                                <input type="date" class="form-control" id="discontinued" name="discontinued" placeholder="Discontinued date">
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="companyId">
                                ${map}${map.value}
                                    <option value="0">--</option>
                                    <c:forEach items="${map}" var="entry">
                                    <option value="${entry.key}">${entry.value}</option>
                                    </c:forEach>
                                    
                                    
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Add" class="btn btn-primary">
                            or
                            <a href="dashboard.html" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>