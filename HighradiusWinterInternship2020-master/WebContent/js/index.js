(function () {
  $("form > input").keyup(function () {
    var empty = false;
    $("form > input").each(function () {
      if ($(this).val() == "") {
        empty = true;
      }
    });

    if (empty) {
      $("#signin").attr("disabled", "disabled");
    } else {
      $("#signin").removeAttr("disabled");
    }
  });
})();
