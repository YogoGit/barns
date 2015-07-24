var selectedBarn;

$( document ).ready(function() {
    console.log( "ready!" );
    $.get( "barns/all", function( data ) {
        $.each(data, function(item) {
          $("#barnsSelect").append($("<option />").val(this.id).text(this.name));
        });
    });


    $("#barnsSelect").change(function(){
      selectedBarn = $("#barnsSelect").val();

    });
});

//<option value="volvo">v</option>

