
// Render Layout
$(() => {
  $("#header").load("../header.html");
  $("#footer").load("../footer.html");
})



//Geração dos charts nas paginas dos relatórios

$('#chartModal').on('shown.bs.modal', function () {
  chart.render();
});