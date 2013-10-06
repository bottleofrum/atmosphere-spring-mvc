$(function(){
    var socket = $.atmosphere;
    var request = new $.atmosphere.AtmosphereRequest();
    request.url = '/echo/message';
    request.transport = 'websocket';
    request.fallbackTransport = 'long-polling';

    request.onOpen = function() { $.atmosphere.log('info', ['socket open']); };
    request.onError =  function() { $.atmosphere.log('info', ['socket error']); };
    request.onReconnect =  function() { $.atmosphere.log('info', ['socket reconnect']); };

    request.onMessage = function(message) {
        $('#messages').append('<div>'+message.responseBody+'</div>')
    }

    var subSocket = socket.subscribe(request);
    //subSocket.push("Hello");
})