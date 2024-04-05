var a = document.getElementById("loginBtn");
var b = document.getElementById("registerBtn");
var x = document.getElementById("loginc");
var y = document.getElementById("registerc");
const btnLogin = document.getElementById('login');
const btnRegister = document.getElementById('register');


function goLogin() {
    x.style.left = "4px";
    y.style.right = "-520px";
    a.className += " white-btn";
    b.className = "btn";
    x.style.opacity = 1;
    y.style.opacity = 0;
}

function goRegister() {
    x.style.left = "-510px";
    y.style.right = "5px";
    a.className = "btn";
    b.className += " white-btn";
    x.style.opacity = 0;
    y.style.opacity = 1;
}

btnLogin.addEventListener('click', function(event) {
    event.preventDefault(); 
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    if (!email.trim() || !password.trim()) {
        alert('Por favor, complete todos los campos.');
        return;
    }
    window.location.href = 'home.html';
});

btnRegister.addEventListener('click', function(event) {
    event.preventDefault(); 
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    if (!name.trim() || !email.trim() || !password.trim()) {
        alert('Por favor, complete todos los campos.');
        return;
    }
    //Agregar encriptación de contraseña
    goLogin();
});
