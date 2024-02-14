var hourUp = document.getElementById("hourUp");
var minuteUp = document.getElementById("minuteUp");

var hourNum = document.getElementById("hourNum");
var minuteNum = document.getElementById("minuteNum");
var ampm = document.getElementById("chooseAMPM");

var hourDown = document.getElementById("hourDown");
var minuteDown = document.getElementById("minuteDown");

var percent100 = document.getElementById("percent100");
var percent75 = document.getElementById("percent75");
var percent50 = document.getElementById("percent50");
var percent25 = document.getElementById("percent25");

var sun = document.getElementById("sun");
var mon = document.getElementById("mon");
var tue = document.getElementById("tue");
var wed = document.getElementById("wed");
var thu = document.getElementById("thu");
var fri = document.getElementById("fri");
var sat = document.getElementById("sat");

hourUp.addEventListener("click", function () {
  hoursValues = parseInt(hourNum.value);
  if (!(hoursValues >= 11)) {
    hourNum.value = hoursValues + 1;
  }
});
minuteUp.addEventListener("click", function () {
  minuteValues = parseInt(minuteNum.value);
  if (!(minuteValues >= 59)) {
    minuteNum.value = minuteValues + 1;
  }
});
hourDown.addEventListener("click", function () {
  hoursValues = parseInt(hourNum.value);
  if (!(hoursValues <= 0)) {
    hourNum.value = hoursValues - 1;
  }
});
minuteDown.addEventListener("click", function () {
  minuteValues = parseInt(minuteNum.value);
  if (!(minuteValues <= 0)) {
    minuteNum.value = minuteValues - 1;
  }
});

ampm.addEventListener("click", function () {
  if (ampm.innerText == "am") {
    ampm.innerText = "pm";
  } else if (ampm.innerText == "pm") {
    ampm.innerText = "am";
  }
});

sun.addEventListener("click", function () {
  daySetting("sun");
});
mon.addEventListener("click", function () {
  daySetting("mon");
});
tue.addEventListener("click", function () {
  daySetting("tue");
});
wed.addEventListener("click", function () {
  daySetting("wed");
});
thu.addEventListener("click", function () {
  daySetting("thu");
});
fri.addEventListener("click", function () {
  daySetting("fri");
});
sat.addEventListener("click", function () {
  daySetting("sat");
});

percent100.addEventListener("click", function () {
  filterSize(4);
});
percent75.addEventListener("click", function () {
  filterSize(3);
});
percent50.addEventListener("click", function () {
  filterSize(2);
});
percent25.addEventListener("click", function () {
  filterSize(1);
});

function daySetting(dayCode) {
  var dateValue = document.getElementById("dateValue");
  var settingTime = document.getElementById("settingTime");

  if (dayCode == "mon") {
    if (mon.className != "choose") {
      dateValue.value = 64 + Number(dateValue.value);
      mon.className = "choose";
    } else {
      dateValue.value = Number(dateValue.value) - 64;
      mon.className = "";
    }
  } else if (dayCode == "tue") {
    if (tue.className != "choose") {
      dateValue.value = 32 + Number(dateValue.value);
      tue.className = "choose";
    } else {
      dateValue.value = Number(dateValue.value) - 32;
      tue.className = "";
    }
  } else if (dayCode == "wed") {
    if (wed.className != "choose") {
      dateValue.value = 16 + Number(dateValue.value);
      wed.className = "choose";
    } else {
      dateValue.value = Number(dateValue.value) - 16;
      wed.className = "";
    }
  } else if (dayCode == "thu") {
    if (thu.className != "choose") {
      dateValue.value = 8 + Number(dateValue.value);
      thu.className = "choose";
    } else {
      dateValue.value = Number(dateValue.value) - 8;
      thu.className = "";
    }
  } else if (dayCode == "fri") {
    if (fri.className != "choose") {
      dateValue.value = 4 + Number(dateValue.value);
      fri.className = "choose";
    } else {
      dateValue.value = Number(dateValue.value) - 4;
      fri.className = "";
    }
  } else if (dayCode == "sat") {
    if (sat.className != "choose") {
      dateValue.value = 2 + Number(dateValue.value);
      sat.className = "choose";
    } else {
      dateValue.value = Number(dateValue.value) - 2;
      sat.className = "";
    }
  } else if (dayCode == "sun") {
    if (sun.className != "choose") {
      dateValue.value = 1 + Number(dateValue.value);
      sun.className = "choose";
    } else {
      dateValue.value = Number(dateValue.value) - 1;
      sun.className = "";
    }
  }

  console.log(dateValue.value);
}

function filterSize(size) {
  var filterRange = document.getElementById("filterRange");

  if (size == 1) {
    filterRange.value = "1";
    percent25.className = "choose";
    percent50.className = "waterPercent";
    percent75.className = "waterPercent";
    percent100.className = "waterPercent";
  } else if (size == 2) {
    filterRange.value = "2";
    percent25.className = "choose";
    percent50.className = "choose";
    percent75.className = "waterPercent";
    percent100.className = "waterPercent";
  } else if (size == 3) {
    filterRange.value = "3";
    percent25.className = "choose";
    percent50.className = "choose";
    percent75.className = "choose";
    percent100.className = "waterPercent";
  } else if (size == 4) {
    filterRange.value = "4";
    percent25.className = "choose";
    percent50.className = "choose";
    percent75.className = "choose";
    percent100.className = "choose";
  }
  console.log(percent25.className);
  console.log(percent50.className);
  console.log(percent75.className);
  console.log(percent100.className);
}
