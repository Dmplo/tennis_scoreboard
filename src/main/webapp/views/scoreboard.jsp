<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Tennis scoreboard</title>
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
                    <span class="base">Result of the last match played:</span>
                </h1>
                <c:if test="${exception != null}">
                    <h1 class="invalid"><c:out value="${exception}"/></h1>
                </c:if>
                <c:if test="${exception == null}">
                    <div>
                        <h1 id="msg"><c:out value="${match.getMessage()}"/></h1>
                        <table>
                            <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">First Player</th>
                                <th scope="col">Second Player</th>
                                <th scope="col">Winner</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td data-label="Id"><c:out value="${lastMatch.getId()}"/></td>
                                <td data-label="First Player"><c:out value="${lastMatch.getFirstPlayer().getName()}"/></td>
                                <td data-label="Second Player"><c:out value="${lastMatch.getSecondPlayer().getName()}"/></td>
                                <td data-label="Winner"><c:out value="${lastMatch.getWinner().getName()}"/></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
            <div class="sidebar sidebar-main">
                <div class="block">
                    <div class="content">
                        <div class="left-info">
                            <div class="left-info__info left_flex">
                                <div class="left-info__name">
                                    <p>Tennis match</p>
                                    <p>scoreboard</p>
                                </div>
                                <div class="left-info__b">
                                    <div class="left-info__b__item">
                                        <a href="/scoreboard/prepare-match">
                                            <button class="button">NEW MATCH</button>
                                        </a>
                                    </div>
                                    <div class="left-info__b__item">
                                        <a href="/scoreboard/matches">
                                            <button class="button">END MATCHES</button>
                                        </a>
                                    </div>

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
            <span class="d-block text-center">Tennis match scoreboard</span>
        </div>
    </div>
</footer>
</body>
</html>