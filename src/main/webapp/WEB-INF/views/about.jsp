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
            <li><a href="/" class="btn btn--without-border">Home</a></li>
        </ul>
    </nav>
</header>
<section>
    <h2>So what is TFIDF?</h2>
    <img src="../../resources/images/tfidf.png"
         style="width: 50%;display: block; margin-left: auto;margin-right: auto;">
    <p style="font-size: 23px; text-indent: 50px; width: 80%;margin: auto;">In information retrieval, tf–idf, TF*IDF, or
        TFIDF, short for term frequency–inverse document frequency, is a numerical statistic that is intended to reflect
        how important a word is to a document in a collection or corpus.[1] It is often used as a weighting factor in
        searches of information retrieval, text mining, and user modeling. The tf–idf value increases proportionally to
        the number of times a word appears in the document and is offset by the number of documents in the corpus that
        contain the word, which helps to adjust for the fact that some words appear more frequently in general. tf–idf
        is one of the most popular term-weighting schemes today. A survey conducted in 2015 showed that 83% of
        text-based recommender systems in digital libraries use tf–idf.[2]

        Variations of the tf–idf weighting scheme are often used by search engines as a central tool in scoring and
        ranking a document's relevance given a user query. tf–idf can be successfully used for stop-words filtering in
        various subject fields, including text summarization and classification.

        One of the simplest ranking functions is computed by summing the tf–idf for each query term; many more
        sophisticated ranking functions are variants of this simple model.
    </p>
    <br><br>
    <p style="font-size: 23px; width: 80%; margin: auto;">You can find more info on wikipedia -> <a style="color: red" href="https://en.wikipedia.org/wiki/Tf%E2%80%93idf">tf–idf</a>
    </p>
</section>
</body>
</html>