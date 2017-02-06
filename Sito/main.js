  var graph;

  $(document).ready(function() {

   graph = Morris.Line({
    element: 'grafico-temperatura',
    xkey: ['time'],
    ykeys: ['value'],
    labels: ['Temperatura']
  });

   updateData();
   setInterval(function(){
    updateData();
  }, 1000);

 });

  function updateData() {

    $.getJSON('temperature.json', function(json) {
      graph.setData(json);
    });

    $.getJSON('log.json', function(json) {
            console.log(json.length);
      var container = $('#log-group');
      var type;
      var message;
      container.html("");
      for (var i = 0 ; i < json.length ; i++) {
        if (json[i].alarm){
          type = "list-group-item-danger";
          message = "Allarme";
        } else {
          type = "list-group-item-success";
          message = "Accesso consentito";          
        }
        container.append('<li class="list-group-item ' + type + '">' +
         '<p class="info-time">' + json[i].time + '</p>' + 
         '<p class="info-message">' + message + '</p></li>');
      }
    });
  }