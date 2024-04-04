var a = document.getElementById("loginBtn");
var b = document.getElementById("registerBtn");
var x = document.getElementById("loginc");
var y = document.getElementById("registerc");

function goLogin() {
    x.style.left = "4px";
    y.style.right = "-520px";
    b.className += " white-btn";
    a.className = "btn";
    x.style.opacity = 1;
    y.style.opacity = 0;
}

function goRegister() {
    x.style.left = "-510px";
    y.style.right = "5px";
    b.className = "btn";
    a.className += " white-btn";
    x.style.opacity = 0;
    y.style.opacity = 1;
}

