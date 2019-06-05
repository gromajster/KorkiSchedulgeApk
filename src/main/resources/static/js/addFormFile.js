$(document).ready(function(){
    $('form input').change(function () {
      $('form p').text(this.files.length + " Wybrano");
    });
  });
  $(document).ready(function () {
    $('.on').click(function (e) {
        e.preventDefault();
        $('.addFile').removeClass('active');
        $('.addFile').addClass('active');
    });
    $('.exit').click(function (e) {
        e.preventDefault();
        $('.addFile').removeClass('active');

    });
});
const exit = document.querySelector(".exit2");
const addV = document.querySelector(".on2");
const place =document.querySelector(".off");
addV.addEventListener("click",function(){
  place.classList.add("active2");
});
exit.addEventListener("click",function(){
  place.classList.remove("active2");
});
var fu1 = document.getElementById("FileUpload1");
console.log("dodano"+fu1.value) ;