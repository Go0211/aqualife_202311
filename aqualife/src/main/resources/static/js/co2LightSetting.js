var onBtn = document.getElementById("onBtn");
var offBtn = document.getElementById("offBtn");

var startHourUp = document.getElementById("startHourUp");
var startMinuteUp = document.getElementById("startMinuteUp");
var startHourNum = document.getElementById("startHourNum");
var startMinuteNum = document.getElementById("startMinuteNum");
var startAMPM = document.getElementById("startAMPM");
var startHourDown = document.getElementById("startHourDown");
var startMinuteDown = document.getElementById("startMinuteDown");

var endHourUp = document.getElementById("endHourUp");
var endMinuteUp = document.getElementById("endMinuteUp");
var endHourNum = document.getElementById("endHourNum");
var endMinuteNum = document.getElementById("endMinuteNum");
var endAMPM = document.getElementById("endAMPM");
var endHourDown = document.getElementById("endHourDown");
var endMinuteDown = document.getElementById("endMinuteDown");

startHourUp.addEventListener("click", function () {
  hoursValues = parseInt(startHourNum.value);
  if (!(hoursValues >= 11)) {
    startHourNum.value = hoursValues + 1;
  }
});
startMinuteUp.addEventListener("click", function () {
  minuteValues = parseInt(startMinuteNum.value);
  if (!(minuteValues >= 59)) {
    startMinuteNum.value = minuteValues + 1;
  }
});
startHourDown.addEventListener("click", function () {
  hoursValues = parseInt(startHourNum.value);
  if (!(hoursValues <= 0)) {
    startHourNum.value = hoursValues - 1;
  }
});
startMinuteDown.addEventListener("click", function () {
  minuteValues = parseInt(startMinuteNum.value);
  if (!(minuteValues <= 0)) {
    startMinuteNum.value = minuteValues - 1;
  }
});
startAMPM.addEventListener("click", function () {
  console.log("Start")
  if (startAMPM.textContent == "am") {
    startAMPM.textContent = "pm";
    document.getElementById("startTimeHidden").value = "pm";
  } else if (startAMPM.textContent == "pm") {
    startAMPM.textContent = "am";
    document.getElementById("startTimeHidden").value = "am";
  }
});

endHourUp.addEventListener("click", function () {
  hoursValues = parseInt(endHourNum.value);
  if (!(hoursValues >= 11)) {
    endHourNum.value = hoursValues + 1;
  }
});
endMinuteUp.addEventListener("click", function () {
  minuteValues = parseInt(endMinuteNum.value);
  if (!(minuteValues >= 59)) {
    endMinuteNum.value = minuteValues + 1;
  }
});
endHourDown.addEventListener("click", function () {
  hoursValues = parseInt(endHourNum.value);
  if (!(hoursValues <= 0)) {
    endHourNum.value = hoursValues - 1;
  }
});
endMinuteDown.addEventListener("click", function () {
  minuteValues = parseInt(endMinuteNum.value);
  if (!(minuteValues <= 0)) {
    endMinuteNum.value = minuteValues - 1;
  }
});
endAMPM.addEventListener("click", function () {
  console.log("End")
  if (endAMPM.textContent == "am") {
    endAMPM.textContent = "pm";
    document.getElementById("endTimeHidden").value = "pm";
  } else if (endAMPM.textContent == "pm") {
    endAMPM.textContent = "am";
    document.getElementById("endTimeHidden").value = "am";
  }
});
