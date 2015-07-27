var selectedBarn;
var validNumbers = "0123456789";
var validAlpha = "abcdefghijklmnopqrstuvwxyz";
validAlpha += validAlpha.toUpperCase();

$(document).ready(function() {

  $('input[id=nameInput]').focus();

  // First thing we do when the page is ready is make a GET call to populate the Barns
  // in the barns <select> list.
    $.get( "barns/all", function( data ) {
      $.each(data, function(item) {
          $("#barnsSelect").append($("<option />").val(this.barnId).text(this.name));
        });

        // Determine selected barnId immediately, since the <select> will default to the 0th
        // item in the list (ie. not picked up by the change function defined below.)
        determineSelectedBarnId();
    });

    // Grabs the barnId from the selected <option> in the <select> list.
    // Then sets that value to the invisible input field used by the AnimalForm.
    function determineSelectedBarnId(){
        selectedBarn = $("#barnsSelect option:selected").val();
        $("#barnIdInput").val(selectedBarn);
      }

    // Always know which Barn ID is selected.
    $("#barnsSelect").change(function(){
      determineSelectedBarnId();
    });

    // The following methods enforce appropriate input for the name field (alpha only)
    // and for the quantity field (numeric only). A focusout listener is also used in
    // case the user attempt to copy/paste invalid content into the field.
    $("input[id=nameInput]").keyup(function(){
        enforceValidInput($("#nameInput"), validAlpha);
      });

    $("input[id=nameInput]").focusout(function(){
      enforceValidInput($("#nameInput"), validAlpha);
    });

    $("input[id=quantityInput]").keyup(function(){
      enforceValidInput($("#quantityInput"), validNumbers);
    });

    $("input[id=quantityInput]").focusout(function(){
      enforceValidInput($("#quantityInput"), validNumbers);
    });

    function enforceValidInput(field, validCharacters){
      var input = field.val();
      if(input.length === 0) return;
      var scrubbed = "";
      var strArr = input.split("");
      for(var i=0; i<strArr.length; i++){
        if(validCharacters.indexOf(strArr[i]) >= 0){
          scrubbed += strArr[i];
        }
      }
      if(scrubbed === "0") scrubbed = "";
      field.val(scrubbed);
    }
});