var selectedBarn;
var validNumbers = "0123456789";
var validAlpha = "abcdefghijklmnopqrstuvwxyz";
validAlpha += validAlpha.toUpperCase();

$(document).ready(function() {

    // Put focus in the first input field.
    $('input[id=nameInput]').focus();

    // The following methods enforce appropriate input for the name field (alpha only)
    // and for the quantity field (numeric only). A focusout listener is also used in
    // case the user attempt to copy/paste invalid content into the field.
    $("input[id=nameInput]").keyup(function() {
        enforceValidInput($("#nameInput"), validAlpha);
    });

    $("input[id=nameInput]").focusout(function() {
      enforceValidInput($("#nameInput"), validAlpha);
    });

    $("input[id=quantityInput]").keyup(function() {
      enforceValidInput($("#quantityInput"), validNumbers);
    });

    $("input[id=quantityInput]").focusout(function() {
      enforceValidInput($("#quantityInput"), validNumbers);
    });

    function enforceValidInput(field, validCharacters) {
      var input = field.val();
      if(input.length === 0) {
      return;
      }
      var scrubbed = "";
      var strArr = input.split("");
      for(var i=0; i<strArr.length; i++) {
        if(validCharacters.indexOf(strArr[i]) >= 0) {
          scrubbed += strArr[i];
        }
      }
      if(scrubbed === "0") {
        scrubbed = "";
      }
      field.val(scrubbed);
    }
});