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
            <li><a href="/" class="btn btn--without-border">Home</a></li>
            <li><a href="/about" class="btn btn--without-border">What is TFIDF?</a></li>
        </ul>
    </nav>
</header>

<h2>Calculate the TFIDF</h2>
<section class="login-page">
    <form method="post" enctype="multipart/form-data">
        <p style="font-size: 15px;">Word to search:</p>
        <div class="form-group">
            <input type="text" name="wordToSearch" placeholder="search..."/>
        </div>
        <p style="font-size: 15px;">Paste the document to look through:</p>
        <div class="form-group">
            <input type="file" name="file" placeholder="Your document"/>
        </div>
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Submit</button>
        </div>
    </form>
</section>

</body>
</html>
