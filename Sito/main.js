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
  }, 200);

 });

  function updateData() {

    $.getJSON('temperature.json', function(json) {
      graph.setData(json);
      lastTemp = json[json.length-1].value;
      $('#title-row h1').text("Temperatura attuale: "+ lastTemp);
    });

    $.getJSON('log.json', function(json) {
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

        var formattedTime = (json[i].time.split(" ")[1]);

        container.append('<li class="list-group-item ' + type + '">' +
         '<p class="info-time">' + formattedTime + '</p>' + 
         '<p class="info-message">' + message + '</p></li>');
      }
    });
  }