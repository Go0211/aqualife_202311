var minPhUp = document.getElementById("minPhUp");
var maxPhUp = document.getElementById("maxPhUp");

var minPhNum = document.getElementById("minPhNum");
var maxPhNum = document.getElementById("maxPhNum");

var minPhDown = document.getElementById("minPhDown");
var maxPhDown = document.getElementById("maxPhDown");

minPhUp.addEventListener("click", function () {
  phValues = parseFloat(minPhNum.value);
  if (!(phValues >= 9.9)) {
    minPhNum.value = (phValues + 0.1).toFixed(1);
  }
});
maxPhUp.addEventListener("click", function () {
  phValues = parseFloat(maxPhNum.value);
  if (!(phValues >= 9.9)) {
    maxPhNum.value = (phValues + 0.1).toFixed(1);
  }
});
minPhDown.addEventListener("click", function () {
  phValues = parseFloat(minPhNum.value);
  if (!(phValues <= 0)) {
    minPhNum.value = (phValues - 0.1).toFixed(1);
  }
});
maxPhDown.addEventListener("click", function () {
  phValues = parseFloat(maxPhNum.value);
  if (!(phValues <= 0)) {
    maxPhNum.value = (phValues - 0.1).toFixed(1);
  }
});
