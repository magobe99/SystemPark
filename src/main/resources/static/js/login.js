function usrpas(){
	if (document.form1.txt.value=="JuanF@gmail.com" && document.form1.num.value=="20136"){window.location="dashboardAdmin.html"}
	if (document.form1.txt.value=="MariaAleja@hotmail.com" && document.form1.num.value=="95031"){window.location="DashboardCliente.html"}
	else {alert("Error en Usuario o Contraseña. Intenta de nuevo.")}
}
document.oncontextmenu=new Function("return false");