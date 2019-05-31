package br.com.pi.model.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.pi.model.LogAcesso;
import br.com.pi.service.ClienteService;

public class ResponseJS {
	
	public ResponseJS() {
		
	}

	public void generateJS(String s) {
		try {
			File file = new File("PI/js/teste.js");
			FileWriter fw = new FileWriter(file);
			System.out.println(file.getAbsolutePath());
			try {
				ClienteService cli = new ClienteService();
				ArrayList<LogAcesso> lista = cli.nRequisicoes(s);
				ArrayList<LogAcesso> dlista = cli.nRequisicoesData(s); 
			} catch (SQLException e) {
			System.out.println("Banco de dados não conectado.");
			}
			
			
			fw.write("\r\n" + 
					"// Render Layout\r\n" + 
					"$(() => {\r\n" + 
					"  $(\"#header\").load(\"../header.html\");\r\n" + 
					"  $(\"#footer\").load(\"../footer.html\");\r\n" + 
					"})\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"//Geração dos charts nas paginas dos relatórios\r\n" + 
					"\r\n" + 
					"function loadXMLDoc() {\r\n" + 
					"  var xmlhttp = new XMLHttpRequest();\r\n" + 
					"\r\n" + 
					"  xmlhttp.onreadystatechange = function() {\r\n" + 
					"      if (xmlhttp.readyState == XMLHttpRequest.DONE) {   // XMLHttpRequest.DONE == 4\r\n" + 
					"         if (xmlhttp.status == 200) {\r\n" + 
					"             document.getElementById(\"chartBarContainer\").innerHTML = xmlhttp.responseText;\r\n" + 
					"             document.getElementById(\"chartAreaContainer\").innerHTML = xmlhttp.responseText;\r\n" + 
					"             document.getElementById(\"chartLineContainer\").innerHTML = xmlhttp.responseText;\r\n" + 
					"             document.getElementById(\"chartPieContainer\").innerHTML = xmlhttp.responseText;\r\n" + 
					"         }\r\n" + 
					"         else if (xmlhttp.status == 400) {\r\n" + 
					"            alert('There was an error 400');\r\n" + 
					"         }\r\n" + 
					"         else if (xmlhttp.status == 500) {\r\n" + 
					"             alert('Internal Server Error 500');\r\n" + 
					"          }\r\n" + 
					"         else {\r\n" + 
					"             alert('something else other than 200 was returned');\r\n" + 
					"         }\r\n" + 
					"      }\r\n" + 
					"  };\r\n" + 
					"\r\n" + 
					"  xmlhttp.open(\"GET\", \"relatorio.java\", true);\r\n" + 
					"  xmlhttp.send();\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"window.onload = function chartBar() {\r\n" + 
					"\r\n" + 
					"  // Grafico de Barras\r\n" + 
					"  var chartBar = new CanvasJS.Chart(\"chartBarContainer\", {\r\n" + 
					"    animationEnabled: true,\r\n" + 
					"    exportEnabled: true,\r\n" + 
					"    theme: \"light1\", // \"light1\", \"light2\", \"dark1\", \"dark2\"\r\n" + 
					"    title: {\r\n" + 
					"      text: \"Relatório de Barras\"\r\n" + 
					"    },\r\n" + 
					"    data: [{\r\n" + 
					"      type: \"column\", //change type to bar, line, area, pie, etc\r\n" + 
					"      //indexLabel: \"{y}\", //Shows y value on all Data Points\r\n" + 
					"      indexLabelFontColor: \"#5A5757\",\r\n" + 
					"      indexLabelPlacement: \"outside\",\r\n" + 
					"      dataPoints: [\r\n" + 
					"        { x: "+ 10 + ", y: "+ 71 + " },\r\n" + 
					"        { x: 20, y: 55 },\r\n" + 
					"        { x: 30, y: 50 },\r\n" + 
					"        { x: 40, y: 65 },\r\n" + 
					"        { x: 50, y: 92, indexLabel: \"Mais alto\" },\r\n" + 
					"        { x: 60, y: 68 },\r\n" + 
					"        { x: 70, y: 38 },\r\n" + 
					"        { x: 80, y: 71 },\r\n" + 
					"        { x: 90, y: 54 },\r\n" + 
					"        { x: 100, y: 60 },\r\n" + 
					"        { x: 110, y: 36 },\r\n" + 
					"        { x: 120, y: 49 },\r\n" + 
					"        { x: 130, y: 21, indexLabel: \"Mais baixo\" }\r\n" + 
					"      ]\r\n" + 
					"    }]\r\n" + 
					"  });\r\n" + 
					"  chartBar.render();\r\n" + 
					"\r\n" + 
					"  // Grafico de Area\r\n" + 
					"  var chartArea = new CanvasJS.Chart(\"chartAreaContainer\", {\r\n" + 
					"    animationEnabled: true,\r\n" + 
					"    title: {\r\n" + 
					"      text: \"Gráfico de Área\"\r\n" + 
					"    },\r\n" + 
					"    axisX: {\r\n" + 
					"      minimum: new Date(2015, 01, 25),\r\n" + 
					"      maximum: new Date(2019, 02, 15),\r\n" + 
					"      valueFormatString: \"MMM YY\"\r\n" + 
					"    },\r\n" + 
					"    axisY: {\r\n" + 
					"      title: \"Gráfico de Linhas\",\r\n" + 
					"      titleFontColor: \"#4F81BC\",\r\n" + 
					"      suffix: \"mn\"\r\n" + 
					"    },\r\n" + 
					"    data: [{\r\n" + 
					"      indexLabelFontColor: \"darkSlateGray\",\r\n" + 
					"      name: \"views\",\r\n" + 
					"      type: \"area\",\r\n" + 
					"      yValueFormatString: \"#,##0.0mn\",\r\n" + 
					"      dataPoints: [\r\n" + 
					"        { x: new Date(2015, 02, 1), y: 74.4, label: \"Q1-2015\" },\r\n" + 
					"        { x: new Date(2015, 05, 1), y: 61.1, label: \"Q2-2015\" },\r\n" + 
					"        { x: new Date(2015, 08, 1), y: 47.0, label: \"Q3-2015\" },\r\n" + 
					"        { x: new Date(2015, 11, 1), y: 48.0, label: \"Q4-2015\" },\r\n" + 
					"        { x: new Date(2016, 02, 1), y: 74.8, label: \"Q1-2016\" },\r\n" + 
					"        { x: new Date(2016, 05, 1), y: 51.1, label: \"Q2-2016\" },\r\n" + 
					"        { x: new Date(2016, 08, 1), y: 40.4, label: \"Q3-2016\" },\r\n" + 
					"        { x: new Date(2016, 11, 1), y: 45.5, label: \"Q4-2016\" },\r\n" + 
					"        { x: new Date(2017, 02, 1), y: 78.3, label: \"Q1-2017\", indexLabel: \"Higher\", markerColor: \"red\" }\r\n" + 
					"      ]\r\n" + 
					"    }]\r\n" + 
					"  });\r\n" + 
					"  chartArea.render();\r\n" + 
					"\r\n" + 
					"  // Grafico de Linhas\r\n" + 
					"  var chartLine = new CanvasJS.Chart(\"chartLineContainer\", {\r\n" + 
					"    animationEnabled: true,\r\n" + 
					"    title: {\r\n" + 
					"      text: \"Gráfico de Linha\"\r\n" + 
					"    },\r\n" + 
					"    axisX: {\r\n" + 
					"      valueFormatString: \"DD MMM,YY\"\r\n" + 
					"    },\r\n" + 
					"    axisY: {\r\n" + 
					"   /*   title: \"Temperature (in °C)\", \r\n" + 
					"      includeZero: false,\r\n" + 
					"      suffix: \" °C\"*/\r\n" + 
					"    },\r\n" + 
					"    legend: {\r\n" + 
					"      cursor: \"pointer\",\r\n" + 
					"      fontSize: 16,\r\n" + 
					"      itemclick: toggleDataSeries\r\n" + 
					"    },\r\n" + 
					"    toolTip: {\r\n" + 
					"      shared: true\r\n" + 
					"    },\r\n" + 
					"    data: [{\r\n" + 
					"      name: \"Myrtle Beach\",\r\n" + 
					"      type: \"spline\",\r\n" + 
					"    //  yValueFormatString: \"#0.## °C\",\r\n" + 
					"      showInLegend: true,\r\n" + 
					"      dataPoints: [\r\n" + 
					"        { x: new Date(2017, 6, 24), y: 31 },\r\n" + 
					"        { x: new Date(2017, 6, 25), y: 31 },\r\n" + 
					"        { x: new Date(2017, 6, 26), y: 29 },\r\n" + 
					"        { x: new Date(2017, 6, 27), y: 29 },\r\n" + 
					"        { x: new Date(2017, 6, 28), y: 31 },\r\n" + 
					"        { x: new Date(2017, 6, 29), y: 30 },\r\n" + 
					"        { x: new Date(2017, 6, 30), y: 29 }\r\n" + 
					"      ]\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      name: \"Martha Vineyard\",\r\n" + 
					"      type: \"spline\",\r\n" + 
					"     // yValueFormatString: \"#0.## °C\",\r\n" + 
					"      showInLegend: true,\r\n" + 
					"      dataPoints: [\r\n" + 
					"        { x: new Date(2017, 6, 24), y: 20 },\r\n" + 
					"        { x: new Date(2017, 6, 25), y: 20 },\r\n" + 
					"        { x: new Date(2017, 6, 26), y: 25 },\r\n" + 
					"        { x: new Date(2017, 6, 27), y: 25 },\r\n" + 
					"        { x: new Date(2017, 6, 28), y: 25 },\r\n" + 
					"        { x: new Date(2017, 6, 29), y: 25 },\r\n" + 
					"        { x: new Date(2017, 6, 30), y: 25 }\r\n" + 
					"      ]\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      name: \"Nantucket\",\r\n" + 
					"      type: \"spline\",\r\n" + 
					"      //yValueFormatString: \"#0.## °C\",\r\n" + 
					"      showInLegend: true,\r\n" + 
					"      dataPoints: [\r\n" + 
					"        { x: new Date(2017, 6, 24), y: 22 },\r\n" + 
					"        { x: new Date(2017, 6, 25), y: 19 },\r\n" + 
					"        { x: new Date(2017, 6, 26), y: 23 },\r\n" + 
					"        { x: new Date(2017, 6, 27), y: 24 },\r\n" + 
					"        { x: new Date(2017, 6, 28), y: 24 },\r\n" + 
					"        { x: new Date(2017, 6, 29), y: 23 },\r\n" + 
					"        { x: new Date(2017, 6, 30), y: 23 }\r\n" + 
					"      ]\r\n" + 
					"    }]\r\n" + 
					"  });\r\n" + 
					"  chartLine.render();\r\n" + 
					"\r\n" + 
					"  function toggleDataSeries(e) {\r\n" + 
					"    if (typeof (e.dataSeries.visible) === \"undefined\" || e.dataSeries.visible) {\r\n" + 
					"      e.dataSeries.visible = false;\r\n" + 
					"    }\r\n" + 
					"    else {\r\n" + 
					"      e.dataSeries.visible = true;\r\n" + 
					"    }\r\n" + 
					"    chartLine.render();\r\n" + 
					"\r\n" + 
					"  }\r\n" + 
					"\r\n" + 
					"  // Grafico de Pizza\r\n" + 
					"  var chartPie = new CanvasJS.Chart(\"chartPieContainer\", {\r\n" + 
					"    animationEnabled: true,\r\n" + 
					"    title: {\r\n" + 
					"      text: \"Gráfico de Pizza\"\r\n" + 
					"    },\r\n" + 
					"    data: [{\r\n" + 
					"      type: \"pie\",\r\n" + 
					"      startAngle: 240,\r\n" + 
					"      yValueFormatString: \"##0.00\\\"%\\\"\",\r\n" + 
					"      indexLabel: \"{label} {y}\",\r\n" + 
					"      dataPoints: [\r\n" + 
					"        { y: 79.45, label: \"Google\" },\r\n" + 
					"        { y: 7.31, label: \"Bing\" },\r\n" + 
					"        { y: 7.06, label: \"Baidu\" },\r\n" + 
					"        { y: 4.91, label: \"Yahoo\" },\r\n" + 
					"        { y: 1.26, label: \"Others\" }\r\n" + 
					"      ]\r\n" + 
					"    }]\r\n" + 
					"  });\r\n" + 
					"  chartPie.render();\r\n" + 
					"}\r\n");
		   
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}

