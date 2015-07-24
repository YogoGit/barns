var selectedBarn;

$(document).ready(function() {

  // First thing we do when the page is ready is make a GET call to populate the Barns
  // in the barns <select> list.
    $.get( "barns/all", function( data ) {
        var numBarns = 0;
      $.each(data, function(item) {
        numBarns++;
          $("#barnsSelect").append($("<option />").val(this.barnId).text(this.name));
        });

        // Determine selected barnId immediately, since the <select> will default to the 0th
        // item in the list (ie. not picked up by the change function defined below.)
        determineSelectedBarnId();
        if(numBarns < 1){
          $("submitDiv").hide();
        }
        else{
          $("submitDiv").show();
        }
    });

    // Grabs the barnId from the selected <option> in the <select> list.
    // Then sets that value to the invisible input field used by the AnimalForm.
    function determineSelectedBarnId(){
        selectedBarn = $("#barnsSelect option:selected").val();
        $("#barnIdInput").val(selectedBarn);
      }

    // Every time a barn selection is made, we need to update our selectedBarn variable.
    $("#barnsSelect").change(function(){
      determineSelectedBarnId();
    });

});