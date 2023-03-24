<%-- 
    Document   : test
    Created on : Mar 24, 2023, 8:01:36 PM
    Author     : User
--%>

<%@page import="DHTV.order.OrderDetailDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <title>Top 10 Products Dashboard</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <h1>Top 10 Products Dashboard</h1>
        <div style="width: 800px; height: 400px;">
            <canvas id="column-chart"></canvas>

        </div>
        <%
            List<OrderDetailDTO> top10Products = (List<OrderDetailDTO>) request.getAttribute("top10Products");
        %>

        <!-- Convert data into format suitable for Chart.js -->
        <script>
            const chartData = {
                labels: [<% for (OrderDetailDTO product : top10Products) {%>"<%= product.getProductName()%>",<% } %>],
                        datasets: [
                            {
                                label: "Total Sales",
                                data: [<% for (OrderDetailDTO product : top10Products) {%><%= product.getQuantity()%>,<% }%>],
                                backgroundColor: "rgba(54, 162, 235, 0.5)"
                            }
                        ]
            };

        // Create the column chart
            const ctx = document.getElementById('column-chart').getContext('2d');
            const chart = new Chart(ctx, {
                type: 'bar',
                data: chartData,
                options: {
                    scales: {
                        yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                    }
                }
            });
        </script>
    </body>

</html>
