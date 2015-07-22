$ ->
  $.get "/barns", (data) ->
    $.each data, (index, item) ->
      $("#barns").append "<li>" + item.name + "</li>"