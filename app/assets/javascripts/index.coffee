$ ->
  $.get "/barns/all", (data) ->
    $.each data, (index, item) ->
      $("#barns").append "<li>" + item.name + "</li>"