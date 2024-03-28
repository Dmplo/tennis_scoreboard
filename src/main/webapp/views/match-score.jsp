<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="uuid" value="${param.uuid}"/>
<c:set var="match" value="${mapMatches[uuid]}"/>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Live Stream</title>
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
                    <span id="stream" class="base">Live Stream</span>
                </h1>
                <c:if test="${exception != null}">
                    <h1 class="invalid"><c:out value="${exception}"/></h1>
                </c:if>
                <c:if test="${exception == null}">
                    <div id="main-table">
                        <h1 id="msg"><c:out value="${match.getMessage()}"/></h1>
                        <table>
                            <thead>
                            <tr>
                                <th scope="col">Player</th>
                                <th scope="col">Sets</th>
                                <th scope="col">Games</th>
                                <th scope="col">Points</th>
                                <th scope="col" class="${match.getOver() ? 'hide' : 'show'} over-hide">Action</th>
                                <th scope="col" class="${match.getTieBreak() ? 'show' : 'hide'} tie_break">Tie-break
                                </th>
                                <th scope="col" class="${match.getDeuce() ? 'show' : 'hide'} deuce">40:40</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${match.getMatchScore()}" var="match_">
                                <tr>
                                    <td data-label="Player"><c:out value="${match_.key}"/></td>
                                    <td data-label="Sets" id="sets_${match_.key}"><c:out
                                            value="${match_.value.getSets()}"/></td>
                                    <td data-label="Games" id="games_${match_.key}"><c:out
                                            value="${match_.value.getGames()}"/></td>
                                    <td data-label="Points" id="points_${match_.key}"><c:out
                                            value="${match_.value.getPoints()}"/></td>
                                    <td data-label="Action" class="${match.getOver() ? 'hide' : 'show'} over-hide">
                                        <button id="btn_<c:out value="${match_.key}"/>" class="button observer"
                                                data-player_name=<c:out
                                                value="${match_.key}"/>>Player win point
                                        </button>
                                    </td>
                                    <td data-label="Tie-break"
                                        class="${match.getTieBreak() ? 'show' : 'hide'} tie_break"
                                        id=pointsTieBreak_<c:out
                                            value="${match_.key}"/>><c:out
                                            value="${match_.value.getPointsTieBreak()}"/></td>
                                    <td data-label="40:40" class="${match.getDeuce() ? 'show' : 'hide'} deuce"
                                        id=pointsDeuce_<c:out
                                            value="${match_.key}"/>><c:out
                                            value="${match_.value.getPointsDeuce()}"/></td>
                                </tr>
                            </c:forEach>
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
                                    <p id="msg-info">Let's play!</p>
                                </div>
                                <div class="control-btn left-info__b ${match.getOver() || exception != null ? '' : 'hide'} over-show">
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
            <span class="d-block text-center">Live Stream</span>
        </div>
    </div>
</footer>
</body>
</html>