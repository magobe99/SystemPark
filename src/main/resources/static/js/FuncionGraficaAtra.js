window.onload = function () {

var chart = new CanvasJS.Chart("chartContainer2", {
    exportEnabled: true,
    animationEnabled: true,
    title:{
        text: "Grafica de Atraciones"
    },
    legend:{
        cursor: "pointer",
        itemclick: explodePie
    },
    data: [{
        type: "pie",
        showInLegend: true,
        toolTipContent: "{name}: <strong>{y}%</strong>",
        indexLabel: "{name} - {y}%",
        dataPoints: [
            { y: 26, name: "Muro de Escalado", exploded: true },
            { y: 20, name: "Piscina de pelotas" },
            { y: 5, name: "Arenera" },
            { y: 3, name: "Casa de juguetes" },
            { y: 7, name: "Avioneta didáctica" },
            { y: 17, name: "Mula Artisitica" },
            { y: 22, name: "Zona para bebes"}
            

        ]
    }]
});
chart.render();
}

function explodePie (e) {
    if(typeof (e.dataSeries.dataPoints[e.dataPointIndex].exploded) === "undefined" || !e.dataSeries.dataPoints[e.dataPointIndex].exploded) {
        e.dataSeries.dataPoints[e.dataPointIndex].exploded = true;
    } else {
        e.dataSeries.dataPoints[e.dataPointIndex].exploded = false;
    }
    e.chart.render();

}