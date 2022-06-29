
function reporte(){
	
        var fechainicio = document.getElementById("fechainicio").value;
        
	var fechafin = document.getElementById("fechafin").value;
	
	var tip0 = document.getElementById("tip0").value;
	
	
	window.open("/report/ventas/download?fechainicio="+fechainicio+"&fechafin="+fechafin+"&tipo="+tip0);
	
}

function reporte2(){
	
        var fechainicio = document.getElementById("fechainicio").value;
        
	var fechafin = document.getElementById("fechafin").value;
	
	var tip0 = document.getElementById("tip0").value;
	
	
	window.open("/report/ventas/download2?fechainicio="+fechainicio+"&fechafin="+fechafin+"&tipo="+tip0);
	
}