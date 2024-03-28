<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="nextCondition" value="${pagination.getCurrentPageNumber() < pagination.getLastPageNumber()}"/>
<c:set var="prevCondition" value="${pagination.getCurrentPageNumber() > 1}"/>

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
                    <span class="base">Results of the finished matches:</span>
                </h1>
                <form action="/scoreboard/matches" method="GET">
                    <div class="flex">
                        <div class="flex">
                            <div class="styled-input">
                                <input id="player_name" type="text" maxlength="35" class="m-input"
                                       placeholder="ENTER THE PLAYER NAME" name="filter_by_player_name"
                                       value="<c:out value="${playerName}"/>">
                            </div>
                            <div>
                                <button type="submit" class="button btn-radius">SEARCH</button>
                            </div>
                        </div>
                        <div>
                            <a href="${pageContext.request.contextPath}/matches" class="a-flex">
                                <span class="button btn-radius btn-width span-set">X</span>
                            </a>
                        </div>
                    </div>
                    <c:if test="${exception != null || violations != null}">
                        <h1 class="invalid text-center"><c:out value="${exception}"/></h1>
                    </c:if>
                    <c:if test="${exception == null && violations == null}">
                        <div>
                            <table>
                                <thead>
                                <tr>
                                    <th scope="col">Id</th>
                                    <th scope="col">First Player</th>
                                    <th scope="col">Second Player</th>
                                    <th scope="col">Winner</th>
                                </tr>
                                </thead>
                                <c:forEach items="${matches}" var="match">
                                <tbody>
                                <tr>
                                    <td data-label="Id"><c:out value="${match.getId()}"/></td>
                                    <td data-label="First Player"><c:out
                                            value="${match.getFirstPlayer().getName()}"/></td>
                                    <td data-label="Second Player"><c:out
                                            value="${match.getSecondPlayer().getName()}"/></td>
                                    <td data-label="Winner"><c:out value="${match.getWinner().getName()}"/></td>
                                </tr>
                                </tbody>
                                <tbody>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div class="wrapper">
                                <section class="pagination">
                                    <button type="${prevCondition ? 'submit' : 'button'}"
                                            name="page" value="${pagination.getCurrentPageNumber() - 1}"
                                            class="${prevCondition ? "": "disabled"} pagination-btn">Prev
                                    </button>
                                    <div class="pagination-state">
                                <span class="counter-current"><c:out
                                        value="${pagination.getCurrentPageNumber()}"/></span>
                                        <span class="pagination-separator">/</span>
                                        <span class="counter-total"><c:out
                                                value="${pagination.getLastPageNumber()}"/></span>
                                    </div>
                                    <button type="${nextCondition ? 'submit' : 'button'}"
                                            name="page" value="${pagination.getCurrentPageNumber() + 1}"
                                            class="${nextCondition ? "" : "disabled"} pagination-btn">Next
                                    </button>
                                </section>
                            </div>
                        </div>
                    </c:if>
                </form>
                <c:if test="${violations != null}">
                    <c:forEach items="${violations}" var="violation">
                        <h1 class="invalid text-center"><c:out value="${violation}"/></h1>
                    </c:forEach>
                </c:if>
                    <c:if test="${violations != null || exception != null}">
                        <a href="/scoreboard/matches">
                            <button class="button ">SHOW ALL MATCHES</button>
                        </a>
                    </c:if>

            </div>
            <div class="sidebar sidebar-main">
                <div class="block">
                    <div class="content">
                        <div class="left-info">
                            <div class="left-info__info left_flex">
                                <div class="left-info__name">
                                    <p>End Matches</p>
                                </div>
                                <div class="left-info__b">
                                    <div class="left-info__b__item">
                                        <a href="/scoreboard/prepare-match">
                                            <button type="submit" class="button">NEW MATCH</button>
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
            <span class="d-block text-center">End Matches</span>
        </div>
    </div>
</footer>
</body>
</html>