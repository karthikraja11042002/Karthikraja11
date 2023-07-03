$(document).ready(function () {
  $.ajax({
    type: "get",
    datatype: "json",
    contenttype: "application/json",
    mimeType: "application/json",
    url: "/giveFeedback",
    success: function (data) {
      for (var i = 0; i < data.length; i++) {
        var questionIndex = i + 1;
        var question = data[i];
        var questionId = 'p' + questionIndex;

        document.getElementById(questionId).innerText = question.questionValue;

        for (var j = 1; j <= 4; j++) {
          var optionIndex = j;
          var optionId = 'op' + questionIndex + optionIndex;
          var optionTextId = 'p' + questionIndex + optionIndex;
          var optionField = 'option' + String.fromCharCode(64 + j);

          document.getElementById(optionId).value = question[optionField];
          document.getElementById(optionTextId).innerText = question[optionField];
        }
      }
    },
  });
});

$(document).ready(function () {
  $('#addRegister').click(function () {
    alert("message from js");
    var user = {
      userName: $("#userName").val(),
      userAge: $("#userAge").val(),
      userEmail: $("#userEmail").val(),
    };

    for (var i = 1; i <= 5; i++) {
      var answer = "";
      for (var j = 1; j <= 4; j++) {
        var optionId = 'op' + i + j;
        if (document.getElementById(optionId).checked) {
          answer = document.getElementById(optionId).value;
          break;
        }
      }

      if (answer === "") {
        alert("Please fill all the values");
        return;
      }

      user['answer' + i] = answer;
    }

    alert("My data " + JSON.stringify(user));

    $.ajax({
      type: "post",
      datatype: "text",
      contenttype: "application/json",
      data: { userData: JSON.stringify(user) },
      url: "/submitFeedback",
      success: function (data) {
        location.href = data;
      },
    });
  });
});
