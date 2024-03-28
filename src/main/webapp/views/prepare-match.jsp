<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>New Match</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital@0;1&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="js/index.js" defer></script>

</head>

<body>
<header>
    <div class="wrap">
        <div class="flex-a mb">
            <a href="/scoreboard">HOME</a>
            <a href="/scoreboard/prepare-match">NEW MATCH</a>
            <a href="/scoreboard/matches">END MATCHES</a>
        </div>
    </div>
</header>
<div class="account">
    <main class="page-main">
        <div class="columns p-col">
            <div class="column main">
                <h1 class="page-title">
                    <span class="base">Select the players:</span>
                </h1>
                    <div>
                        <form action="/scoreboard/new-match" method="post">
                            <div class="styled-input">
                                <span class="pr-2">FIRST PLAYER:</span>
                                <input id="firstplayer" name="firstplayer" value="${firstPlayer}" type="text"
                                       maxlength="35"
                                       class="m-input" placeholder="ENTER THE PLAYER NAME">
                            </div>
                            <div class="styled-input">
                                <span class="pr-2">SECOND PLAYER:</span>
                                <input id="secondplayer" name="secondplayer" value="${secondPlayer}" type="text"
                                       maxlength="35" class="m-input" placeholder="ENTER THE PLAYER NAME">
                            </div>
                            <button id="start" type="submit" class="button">START MATCH</button>
                        </form>

                        <c:if test="${violations != null}">
                            <c:forEach items="${violations}" var="violation">
                                <h1 class="invalid text-center"><c:out value="${violation}"/></h1>
                            </c:forEach>
                        </c:if>

                    </div>
                <c:if test="${exception != null}">
                    <h1 class="invalid text-center"><c:out value="${exception}"/></h1>
                </c:if>
            </div>
            <div class="sidebar sidebar-main">
                <div class="block">
                    <div class="content">
                        <div class="left-info">
                            <div class="left-info__info left_flex">
                                <div class="left-info__name">
                                    <p>New Match</p>
                                </div>
                            </div>
                            <div>
                                <img class="title-img img-fluid" src="images/score_board.jpg"
                                     alt="score_board">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>
<footer>
    <div class="wrap">
        <div class="d-sm-flex justify-content-center justify-content-sm-between">
            <span class="text-center d-block">Copyright © 2024</span>
            <span class="d-block text-center">New Match</span>
        </div>
    </div>
</footer>
</body>
</html>