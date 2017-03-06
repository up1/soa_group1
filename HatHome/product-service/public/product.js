$(document).ready(function() {
    $.ajax({
        type: 'GET',
        url: "http://localhost:9004/a"
    }).then(function(data) {
        $('.pname').append(data.name);
        console.log('sadasdas');
    });
    });