// Get the JSON data from the JSP file
const jsonChartData = document.getElementById("json-chart-data").textContent;

// Parse the JSON data
const chartData = JSON.parse(jsonChartData);

// Create the chart
const chartContainer = document.getElementById("chart-container");
new Chart(chartContainer, {
    type: "bar",
    data: {
        labels: chartData.map(dataPoint => dataPoint.product),
        datasets: [{
            label: "Total Quantity Sold",
            data: chartData.map(dataPoint => dataPoint.quantity),
            backgroundColor: "rgb(54, 162, 235)"
        }]
    },
    options: {
        responsive: true,
        title: {
            display: true,
            text: "Top 10 Products by Total Quantity Sold"
        },
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                },
                scaleLabel: {
                    display: true,
                    labelString: "Total Quantity Sold"
                }
            }],
            xAxes: [{
                scaleLabel: {
                    display: true,
                    labelString: "Product Name"
                }
            }]
        }
    }
});
