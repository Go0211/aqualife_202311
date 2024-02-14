var plusTemp = document.getElementById("plusTemp");

var settingTemp = document.getElementById("settingTemp");

var minusTemp = document.getElementById("minusTemp");

plusTemp.addEventListener("click", function () {
  tempValues = parseInt(settingTemp.value);
  if (!(tempValues >= 99)) {
    settingTemp.value = tempValues + 1;
  }
});
minusTemp.addEventListener("click", function () {
  tempValues = parseInt(settingTemp.value);
  if (!(tempValues <= 0)) {
    settingTemp.value = tempValues - 1;
  }
});
