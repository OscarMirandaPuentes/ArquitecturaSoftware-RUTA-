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

btnLogin.addEventListener('click', async function(event) {
    event.preventDefault(); 
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    if (!email.trim() || !password.trim()) {
        alert('Por favor, complete todos los campos.');
        return;
    }

    password2 =await calcularHash(password);
    ajaxLogin("LOGIN", email, password2)
    .then(function(response) {
        console.log('Inicio exitoso:', response);
        window.location.href = 'home.html';
    })
    .catch(function(error) {
        console.error('Error en la solicitud:', error);
    });
    const combinedHash = await calcularHash(email);
    document.cookie = `user=${response.nickname}; hash=${combinedHash}; path=/`;
});

btnRegister.addEventListener('click', async function(event) {
    event.preventDefault(); 
    const name = document.getElementById('namer').value;
    const nickname = document.getElementById('nicknamer').value;
    const email = document.getElementById('emailr').value;
    const password = document.getElementById('passwordr').value;
    if (!name.trim() || !nickname.trim() || !email.trim() || !password.trim()) {
        alert('Por favor, complete todos los campos.');
        return;
    }

    password2 =await calcularHash(password);
    console.log(password2)
    ajaxRegister("CREATE",name, nickname, email, password2)
    .then(function(response) {
        console.log('Registro exitoso:', response);
        goLogin();
    })
    .catch(function(error) {
        console.error('Error en la solicitud:', error);
    });
    
});


function ajaxRegister(action, name, nickname, email, password) {
    return new Promise((resolve, reject) => {
        let myData = {
            action: action,
            name: name,
            nickname: nickname,
            email: email,
            password: password
        };
        $.ajax({
            url: '/ruta/usuario',
            type: 'POST',
            data: myData,
            success: function (r) {
                resolve(r);
            },
            error: function (err) {
                reject(err);
            }
        });
    });
}

function ajaxLogin(action, email, password) {
    return new Promise((resolve, reject) => {
        let myData = {
            action: action,
            email: email,
            password: password
        };
        $.ajax({
            url: '/ruta/usuario',
            type: 'POST',
            data: myData,
            success: function (r) {
                resolve(r); 
            },
            error: function (err) {
                reject(err);
            }
        });
    });
}
