const api_base_url = "http://localhost:9123";

window.addEventListener("DOMContentLoaded",async function () {
  var dateInput = document.getElementById("dateInput");
  var currentDate = document.getElementById("currentDate");

  // Function to update the selected date
  async function updateSelectedDate() {
    var selectedDate = dateInput.value;
    currentDate.textContent = new Date(selectedDate).toDateString();
    await fetchFormData();
  }

  // Event listener for date selection
  dateInput.addEventListener("input", updateSelectedDate);

  // Set today's date as the default when the page loads
  var today = new Date();
  var day = String(today.getDate()).padStart(2, "0");
  var month = String(today.getMonth() + 1).padStart(2, "0");
  var year = today.getFullYear();
  var todayFormatted = year + "-" + month + "-" + day;
  dateInput.value = todayFormatted;
  currentDate.textContent = today.toDateString();
  await fetchFormData();
});

async function fetchFormData() {
  var dateInput = document.getElementById("dateInput");
  const response = await fetch(
    `${api_base_url}/Emotion/findByDate?date=${dateInput.value}`,
    {
      method: "GET",
    }
  );
  const formData = JSON.parse(await response.text());
  var happyText = document.getElementById("happydesc");
  var sadText = document.getElementById("saddesc");
  happyText.value = formData.happyDescription;
  sadText.value = formData.sadDescription;
  highlightButton(formData.feelGoodScore);
}

// Get all the buttons
var buttons = document.querySelectorAll(".btn-scale");

// Variable to store the last clicked button number
var lastClickedButton = null;

// Add click event listener to each button
buttons.forEach(function (button) {
  button.addEventListener("click", function () {
    event.preventDefault();
    // Get the button number from the button text
    var buttonNumber = parseInt(button.innerText);
    // Update the last clicked button variable
    lastClickedButton = buttonNumber;
  });
});

async function addEntry() {
  event.preventDefault();
  var selectedDate = document.getElementById("dateInput").value;
  var happyText = document.getElementById("happydesc").value;
  var sadText = document.getElementById("saddesc").value;
  let headersList = {
    Accept: "*/*",
    "Content-Type": "application/json",
  };

  let bodyContent = JSON.stringify({
    date: selectedDate,
    feelGoodScore: lastClickedButton,
    happyDescription: happyText,
    sadDescription: sadText,
  });

  let response = await fetch(`${api_base_url}/Emotion/add`, {
    method: "POST",
    body: bodyContent,
    headers: headersList,
  });

  let data = await response.text();
  console.log(data);
}

const submitButton = document.getElementById("submitBtn");

// Add event listener to the button
submitButton.addEventListener("click", addEntry);

let selectedButton = null;

function highlightButton(buttonNumber) {
  const buttons = document.getElementsByClassName("btn-scale");

  if (selectedButton) {
    selectedButton.classList.remove("active");
  }

  selectedButton = buttons[buttonNumber - 1];
  selectedButton.classList.add("active");
}

function showPopup() {
  // Show the popup message box
  const popup = document.getElementById("popup");
  popup.style.display = "block";
}

function closePopup() {
  // Close the popup message box
  const popup = document.getElementById("popup");
  popup.style.display = "none";

  const form = document.getElementById("myForm");
  form.reset();
  var currentDate = document.getElementById("currentDate");
  currentDate.textContent = "";
}
