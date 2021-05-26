<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>TFIDF Calculator</title>
    <link rel="stylesheet" href="<c:url value="../../resources/css/style.css"/>"/>
</head>
<body>

<header>
    <nav class="container container--70">
        <ul class="nav--actions">
            <li><a href="#" class="btn btn--small btn--without-border">Hello user!</a></li>
        </ul>

        <ul>
            <li><a href="/calculator" class="btn btn--without-border">To Calcualtor</a></li>
            <li><a href="/about" class="btn btn--without-border">What is TFIDF?</a></li>
        </ul>
    </nav>
</header>

<section>
    <h2>About</h2>
    <p style="font-size: 23px; text-indent: 50px; width: 80%;margin: auto;">This is a test exercise requested by <span
            style="color: red">GlobalLogic</span> for junior java developer position,
        the purpose of the app is to calculate the tfidf in a given document (text) looking by searched word.</p>
    <img src="../../resources/images/java.jpg" style="width: 50%;display: block; margin-left: auto;margin-right: auto;">
    <h2>Bartłomiej Grabowski</h2>
</section>
</body>
</html>
