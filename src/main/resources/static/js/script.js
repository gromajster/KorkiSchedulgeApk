// wydruk liter

const tetx = "Korki Schedule - to witryna internetowa pozwalająca na szybkie i wygodne znalezienie korepetytorów z konkretnych przedmiotów w danym regionie. Zarejestruj się jako Uczeń, aby umówić się na korepetycje w celu poprawienia swoich umiejętności oraz pomóż innym, oceniając swoich korepetytorów.";
const display = document.querySelector(".paragraf");
let indexP = 0;
const setLetter = setInterval(printL, 50);

function printL() {

    display.textContent += tetx[indexP];
    indexP++;
    if (indexP === tetx.length) clearInterval(setLetter);
}

function myFunction() {
    // Get the snackbar DIV
    var x = document.getElementById("snackbar");
    console.log(x);
    if (x === null) return;
    // Add the "show" class to DIV
    x.className = "show";

    // After 3 seconds, remove the show class from DIV
    setTimeout(function () {
        x.className = x.className.replace("show", "");
    }, 3000);
}

$(document).ready(myFunction());
const subject = document.querySelectorAll("div.subject a");
console.log(subject)
subject.forEach((item) => {
    const items = item.text
    if (items == "FIZYKA") {
        item.classList.add('Fizyka');
    } else if (items == "MATEMATYKA") {
        item.classList.add('Matematyka');
    } else if (items == "GEOGRAFIA") {
        item.classList.add('Geografia');
    } else if (items == "ANGIELSKI") {
        item.classList.add('Angielski');
    } else if (items == "HISTORIA") {
        item.classList.add('Historia');
    } else if (items == "INFORMATYKA") {
        item.classList.add('Informatyka');
    } else if (items == "ROSYJSKI") {
        item.classList.add('Rosyjski');
    } else if (items == "POLSKI") {
        item.classList.add('Polski');
    } else if (items == "NIEMIECKI") {
        item.classList.add('Niemiecki');
    } else if (items == "CHEMIA") {
        item.classList.add('Chemia');
    }


});


function checkPassword() {
    var password = document.getElementById('password');
    var repeatpassword = document.getElementById('pwrepeat');
    if (password.value !== repeatpassword.value) {
        error = "Hasła nie są takie same.";
        document.getElementById("errorid2").innerHTML = error;
        return false;
    }
}
const barrsShow = document.querySelector(".display-ofert");
barrsShow.addEventListener("click", function () {
    document.querySelector(".popular-ofert").classList.add("act2");
});

function checkPassword2() {
    var password = document.getElementById('password');
    var pattern = new RegExp("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,20}$");
    if (pattern.test(password.value))
        return true;
    else {
        error = "Hasło nie spełnia wymagań: Przynajmniej jedna duża i mała litera, cyfra i znak specjalny. Min 8 znakow";
        document.getElementById("errorid1").innerHTML = error;
        return false;
    }

}
const barrsShow2 = document.querySelector(".display-ofert");
barrsShow2.addEventListener('click', function () {
    document.querySelector(".off-clas").classList.toggle("ct2");
    document.querySelector(".fa-arrow-right").classList.toggle("act1");
});


$(document).ready(function () {
    $('.contact').click(function (e) {
        e.preventDefault();
        $('#pop').removeClass('active');
        $('#pop').addClass('active');
    });
    $('#exit').click(function (e) {
        e.preventDefault();
        $('#pop').removeClass('active');

    });
});
$(document).ready(function () {
    $("#arrow").click(function () {
        $('html, body').animate({
            scrollTop: $("#one").offset().top - 120
        }, 1000);
    });
});
$(window).on("load resize ", function () {
    var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
    $('.tbl-header').css({
        'padding-right': scrollWidth
    });
}).resize();