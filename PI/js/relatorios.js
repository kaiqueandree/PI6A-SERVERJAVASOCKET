
// Render Layout
$(() => {
  $("#header").load("../header.html");
  $("#footer").load("../footer.html");
})



//Geração dos charts nas paginas dos relatórios

function loadXMLDoc() {
  var xmlhttp = new XMLHttpRequest();

  xmlhttp.onreadystatechange = function() {
      if (xmlhttp.readyState == XMLHttpRequest.DONE) {   // XMLHttpRequest.DONE == 4
         if (xmlhttp.status == 200) {
             document.getElementById("chartBarContainer").innerHTML = xmlhttp.responseText;
             document.getElementById("chartAreaContainer").innerHTML = xmlhttp.responseText;
             document.getElementById("chartLineContainer").innerHTML = xmlhttp.responseText;
             document.getElementById("chartPieContainer").innerHTML = xmlhttp.responseText;
         }
         else if (xmlhttp.status == 400) {
            alert('There was an error 400');
         }
         else if (xmlhttp.status == 500) {
             alert('Internal Server Error 500');
          }
         else {
             alert('something else other than 200 was returned');
         }
      }
  };

  xmlhttp.open("GET", "relatorio.java", true);
  xmlhttp.send();
}

window.onload = function chartBar() {

  // Grafico de Barras
  var chartBar = new CanvasJS.Chart("chartBarContainer", {
    animationEnabled: true,
    exportEnabled: true,
    theme: "light1", // "light1", "light2", "dark1", "dark2"
    title: {
      text: "Relatório de Barras"
    },
    data: [{
      type: "column", //change type to bar, line, area, pie, etc
      //indexLabel: "{y}", //Shows y value on all Data Points
      indexLabelFontColor: "#5A5757",
      indexLabelPlacement: "outside",
      dataPoints: [
        { x: 10, y: 71 },
        { x: 20, y: 55 },
        { x: 30, y: 50 },
        { x: 40, y: 65 },
        { x: 50, y: 92, indexLabel: "Mais alto" },
        { x: 60, y: 68 },
        { x: 70, y: 38 },
        { x: 80, y: 71 },
        { x: 90, y: 54 },
        { x: 100, y: 60 },
        { x: 110, y: 36 },
        { x: 120, y: 49 },
        { x: 130, y: 21, indexLabel: "Mais baixo" }
      ]
    }]
  });
  chartBar.render();

  // Grafico de Area
  var chartArea = new CanvasJS.Chart("chartAreaContainer", {
    animationEnabled: true,
    title: {
      text: "Gráfico de Área"
    },
    axisX: {
      minimum: new Date(2015, 01, 25),
      maximum: new Date(2019, 02, 15),
      valueFormatString: "MMM YY"
    },
    axisY: {
      title: "Gráfico de Linhas",
      titleFontColor: "#4F81BC",
      suffix: "mn"
    },
    data: [{
      indexLabelFontColor: "darkSlateGray",
      name: "views",
      type: "area",
      yValueFormatString: "#,##0.0mn",
      dataPoints: [
        { x: new Date(2015, 02, 1), y: 74.4, label: "Q1-2015" },
        { x: new Date(2015, 05, 1), y: 61.1, label: "Q2-2015" },
        { x: new Date(2015, 08, 1), y: 47.0, label: "Q3-2015" },
        { x: new Date(2015, 11, 1), y: 48.0, label: "Q4-2015" },
        { x: new Date(2016, 02, 1), y: 74.8, label: "Q1-2016" },
        { x: new Date(2016, 05, 1), y: 51.1, label: "Q2-2016" },
        { x: new Date(2016, 08, 1), y: 40.4, label: "Q3-2016" },
        { x: new Date(2016, 11, 1), y: 45.5, label: "Q4-2016" },
        { x: new Date(2017, 02, 1), y: 78.3, label: "Q1-2017", indexLabel: "Higher", markerColor: "red" }
      ]
    }]
  });
  chartArea.render();


   // Grafico de Pizza
   var chartPie = new CanvasJS.Chart("chartPieContainer", {
    animationEnabled: true,
    title: {
      text: "Gráfico de Pizza"
    },
    data: [{
      type: "pie",
      startAngle: 240,
      yValueFormatString: "##0.00\"%\"",
      indexLabel: "{label} {y}",
      dataPoints: [
        { y: 79.45, label: "Google" },
        { y: 7.31, label: "Bing" },
        { y: 7.06, label: "Baidu" },
        { y: 4.91, label: "Yahoo" },
        { y: 1.26, label: "Others" }
      ]
    }]
  });
  chartPie.render();

  // Grafico de Linhas
  var chartLine = new CanvasJS.Chart("chartLineContainer", {
    animationEnabled: true,
    title: {
      text: "Gráfico de Linha"
    },
    axisX: {
      valueFormatString: "DD MMM,YY"
    },
    axisY: {
   /*   title: "Temperature (in °C)", 
      includeZero: false,
      suffix: " °C"*/
    },
    legend: {
      cursor: "pointer",
      fontSize: 16,
      itemclick: toggleDataSeries
    },
    toolTip: {
      shared: true
    },
    data: [{
      name: "Myrtle Beach",
      type: "spline",
    //  yValueFormatString: "#0.## °C",
      showInLegend: true,
      dataPoints: [
        { x: new Date(2017, 6, 24), y: 31 },
        { x: new Date(2017, 6, 25), y: 31 },
        { x: new Date(2017, 6, 26), y: 29 },
        { x: new Date(2017, 6, 27), y: 29 },
        { x: new Date(2017, 6, 28), y: 31 },
        { x: new Date(2017, 6, 29), y: 30 },
        { x: new Date(2017, 6, 30), y: 29 }
      ]
    },
    {
      name: "Martha Vineyard",
      type: "spline",
     // yValueFormatString: "#0.## °C",
      showInLegend: true,
      dataPoints: [
        { x: new Date(2017, 6, 24), y: 20 },
        { x: new Date(2017, 6, 25), y: 20 },
        { x: new Date(2017, 6, 26), y: 25 },
        { x: new Date(2017, 6, 27), y: 25 },
        { x: new Date(2017, 6, 28), y: 25 },
        { x: new Date(2017, 6, 29), y: 25 },
        { x: new Date(2017, 6, 30), y: 25 }
      ]
    },
    {
      name: "Nantucket",
      type: "spline",
      //yValueFormatString: "#0.## °C",
      showInLegend: true,
      dataPoints: [
        { x: new Date(2017, 6, 24), y: 22 },
        { x: new Date(2017, 6, 25), y: 19 },
        { x: new Date(2017, 6, 26), y: 23 },
        { x: new Date(2017, 6, 27), y: 24 },
        { x: new Date(2017, 6, 28), y: 24 },
        { x: new Date(2017, 6, 29), y: 23 },
        { x: new Date(2017, 6, 30), y: 23 }
      ]
    }]
  });
  chartLine.render();

  function toggleDataSeries(e) {
    if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
      e.dataSeries.visible = false;
    }
    else {
      e.dataSeries.visible = true;
    }
    chartLine.render();

  }

 
}
