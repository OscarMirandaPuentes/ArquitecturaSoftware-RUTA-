document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('crear').addEventListener('click', function () {
        window.location.href = 'newGame.html';
    });

    document.getElementById('unirse').addEventListener('click', function () {
        window.location.href = 'games.html'; 
    });
});