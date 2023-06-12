const api_base_url = "http://localhost:9123";

async function updateLineChart() {
  const response = await fetch(`${api_base_url}/Emotion/lineChart`, {
    method: "GET"
  });
  const lineChartData = JSON.parse(await response.text());
  
  const data = {
    labels: lineChartData.dates,
    datasets: [
      {
        label: "Feel Good Score",
        data: lineChartData.feelGoodScores,
        fill: false,
        borderColor: "rgb(75, 192, 192)",
        tension: 1.0,
      },
    ],
  };
  
  // Configuration options for the chart
  const options = {
    responsive: true,
    scales: {
      y: {
        beginAtZero: true,
      },
    },
  };
  
  // Get the canvas element
  const canvas = document.getElementById("lineChart");
  
  // Create the chart using Chart.js
  const chart = new Chart(canvas, {
    type: "line",
    data: data,
    options: options,
  });
}


async function updateHappyPieChart() {

  const response = await fetch(`${api_base_url}/Emotion/HappyPieChart`, {
    method: "GET"
  });
  const pieChartData = JSON.parse(await response.text());

  const PieData = {
    labels: pieChartData.labels,
    datasets: [
      {
        label: "Contribution weightage",
        data: pieChartData.count,
        backgroundColor: pieChartData.colors,
        hoverOffset: 4,
      },
    ],
  };
  
  // Get the canvas element
  const happyPieCanvas = document.getElementById("happyPieChart");
  
  // Create the chart using Chart.js
  const pieChart = new Chart(happyPieCanvas, {
    type: "doughnut",
    data: PieData,
  });
  

}

async function updateSadPieChart() {

  const response = await fetch(`${api_base_url}/Emotion/SadPieChart`, {
    method: "GET"
  });
  const pieChartData = JSON.parse(await response.text());
  
const SadPieData = {
  labels: pieChartData.labels,
  datasets: [
    {
      label: "Contribution Weightage",
      data: pieChartData.count,
      backgroundColor: pieChartData.colors,
      hoverOffset: 4,
    },
  ],
};

// Get the canvas element
const SadPieCanvas = document.getElementById("sadPieChart");

// Create the chart using Chart.js
const SadPieChart = new Chart(SadPieCanvas, {
  type: "doughnut",
  data: SadPieData,
});
}

window.addEventListener('DOMContentLoaded', async function() {
  await Promise.all([updateLineChart(), updateHappyPieChart(), updateSadPieChart()]);
});




