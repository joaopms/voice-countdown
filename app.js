var prompt = require("prompt");

prompt.message = "";
prompt.delimiter = "";
prompt.start();

prompt.get([{
        name: "mode",
        description: "What mode do you want to use? Static (1) or dynamic (2)?",
        message: "This is a required field and you should only use numbers (1 & 2) here!",
        type: "number",
        required: true,
        conform: function(mode) {
          return mode >= 1 && mode <= 2;
      }}],
function (err, results) {
  if (results.mode == 1) {
    console.log("Static mode activated!");
    staticMode();
  } else {
    console.log("Dynamic mode activated!");
    dynamicMode();
  }
});

function staticMode() {
  prompt.get([{
          name: "total",
          description: "How much time do you want to count?",
          message: "This is a required field and you should use a number greater than 0!",
          type: "number",
          required: true,
          conform: function(total) {
            return total >= 1;
          }
        },
        {
          name: "between",
          description: "How much time do you want between warnings?",
          message: "This is a required field and you should use a number greater than 0 and less than the total time!", // Only valid if total % between isn't greater than 0
          type: "number",
          required: true,
          conform: function(between) {
            var total = prompt.history("total").value;
            return between < total && total % between == 0;
          }
        }],
  function (err, results) {
    var total = results.total;
    var between = results.between;
    var left = total;

    console.log("Countdown started.");
    function tick() {
      if (left <= 0) {
        clearInterval(interval);
        // voice thingy - end
        console.log("Countdown ended.");
        return;
      }

      // voice thingy - tick
      console.log(left + " minute(s) left!");
      left -= between;
    }

    tick();
    var interval = setInterval(tick, between * /*60000*/ 1000);
  });
}
