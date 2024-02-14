var fishbowlArrow = document.getElementById("fishbowlArrow");
var fishbowlArrowImg = document.getElementById("fishbowlArrowImg");
var fishbowlInfoInnerFrame = document.getElementsByClassName(
  "fishbowlInfoInnerFrame"
);

var home = document.getElementById("home");
var water = document.getElementById("water");
var light = document.getElementById("light");
var co2 = document.getElementById("co2");
var ph = document.getElementById("ph");
var temp = document.getElementById("temp");

fishbowlArrow.addEventListener("click", function () {
  var splitArrowName = fishbowlArrowImg.src.split("image/");
  if (fishbowlInfoInnerFrame.length > 1) {
    if (splitArrowName[1] == "downArrow.png") {
      console.log("down -> up");
      fishbowlArrowImg.src = splitArrowName[0] + "image/" + "upArrow.png";
    } else if (splitArrowName[1] == "upArrow.png") {
      console.log("up -> down");
      fishbowlArrowImg.src = splitArrowName[0] + "image/" + "downArrow.png";
    }
  }
});

home.addEventListener("click", function () {
  window.location.replace("/");
});
water.addEventListener("click", function () {
  window.location.replace("/filterMain");
});
light.addEventListener("click", function () {
  window.location.replace("/lightMain");
});
co2.addEventListener("click", function () {
  window.location.replace("/co2Main");
});
ph.addEventListener("click", function () {
  window.location.replace("/phMain");
});
temp.addEventListener("click", function () {
  window.location.replace("/temperatureMain");
});
