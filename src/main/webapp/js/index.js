async function handleFormSubmit(event) {
    event.preventDefault()
    let playerName = event.target.dataset.player_name;
    let uuid = getUrlParam('uuid');
    const data = new FormData();
    data.append('playerName', playerName);
    data.append('uuid', uuid);
    event.target.disabled = true;
    sendData(data).then(response => {
        setParams(playerName, response, event.target);
    });

}

function toggleStyle(className, paramOne, paramTwo) {
    document.querySelectorAll("." + className).forEach((el) => {
        el.classList.remove(paramOne);
        el.classList.add(paramTwo);
    })
}

function setParams(id, response, button) {
    if ('error' in response) {
        renderErrResponce(response.error)
    } else {
        button.disabled = false;
        const scoreCurrentPlayer = response.matchScore[id];
        showMessage('msg', response.message);
        if (response.tieBreak) {
            toggleStyle('tie_break', 'hide', 'show');
        } else if (response.over) {
            toggleStyle('over-hide', 'show', 'hide');
            toggleStyle('over-show', 'hide', 'empty');
            showMessage('msg-info', "Actions:");
            showWinner(id, response.winner.name);
        } else if (response.deuce) {
            toggleStyle('deuce', 'hide', 'show');
        } else {
            toggleStyle('tie_break', 'show', 'hide');
            toggleStyle('deuce', 'show', 'hide');
        }
        updateOtherPlayerScore(response.matchScore, id);
        updatePlayerParams(id, scoreCurrentPlayer);
    }
}

function getOtherPlayer(score, id) {
    for (const item in score) {
        if (item !== id) {
            return item;
        }
    }
}

function showMessage(id, message) {
    let el = document.getElementById(id);
    if (el) {
        el.innerText = message;
    }
}

function updateOtherPlayerScore(score, id) {
    const otherPlayerId = getOtherPlayer(score, id);
    updatePlayerParams(otherPlayerId, score[otherPlayerId]);

}

function renderErrResponce(str) {
    let el = document.getElementById("main-table");
    if (el) {
        let message = `<h1 class="invalid">${str}</h1>`;
        el.insertAdjacentHTML("afterend", message);
        el.remove();
    }
    toggleStyle('control-btn', 'hide', 'empty');
}

function showWinner(id, name) {
    let el = document.getElementById("stream");
    if (el) {
        el.innerText = `Winner is: ${name}`;
    }
}

function updatePlayerParams(id, param) {
    ['sets', 'games', 'points', 'pointsTieBreak', 'pointsDeuce'].forEach(value => {
        let el = document.getElementById(value + '_' + id);
        if (el) {
            el.innerText = param[value];
        }
    })
}

async function sendData(data) {
    let response = await fetch('/scoreboard/match-score?uuid=' + data.get('uuid'), {
        method: 'POST',
        body: data,
    })
    return await response.json();
}

function getUrlParam(param) {
    const queryString = window.location.search;
    const urlParam = new URLSearchParams(queryString);
    return urlParam.get(param);
}

const buttons = document.querySelectorAll(".observer");

buttons.forEach((button) => {

    button.addEventListener("click", (event) => {
        handleFormSubmit(event);
    })
})

const paginationBtn = document.querySelectorAll(".pagination-btn");

paginationBtn.forEach((button) => {
    button.addEventListener("click", (event) => {
        let el = document.getElementById("player_name");
        let playerName = getUrlParam('filter_by_player_name');
        if (el) {
            if (!!el.getAttribute('name') && !playerName) {
                el.setAttribute('name', "");
            }
        }
    })
})

const clearBtn = document.getElementById("clear");
if (clearBtn) {
    clearBtn.addEventListener("click", (e) => {
        window.location.href = "/matches";
    })
}

