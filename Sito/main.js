  var graph;

  $(document).ready(function () {
      //Creazione grafico di visualizzazione delle temperature
      graph = Morris.Line({
          element: 'grafico-temperatura',
          xkey: ['time'],
          ykeys: ['value'],
          labels: ['Temperatura']
      });
      //Update ogni 200 millisecondi dei dati
      updateData();
      setInterval(function () {
          updateData();
      }, 200);

  });

  function updateData() {
      //Ottenimento JSON temperature con visualizzazione delle ultime 10 graficamente
      $.getJSON('temperature.json', function (json) {
          var data = [];
          
          left = json.length - 10;
          if (left < 0){ left = 0 };
          
          for (var i = left ; i < json.length ; i++ ){
              data.push(json[i]);
          }
          //Setting dei dati
          graph.setData(data);
          lastTemp = json[json.length - 1].value;
          $('#title-row h1').text("Temperatura attuale: " + lastTemp);
      });
      //Ottenimento JSON degli accessi e creazione del panel che li contiene
      $.getJSON('log.json', function (json) {
          var container = $('#log-group');
          var type;
          var message;
          container.html("");
          for (var i = 0; i < json.length; i++) {
              if (json[i].alarm) {
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