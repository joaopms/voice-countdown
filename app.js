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
        description: "Total?",
        message: "This is a required field and you should use a number greater than 0!",
        type: "number",
        required: true,
        conform: function(total) {
          return total >= 1;
        }}],
  function (err, results) {
    console.log(results);
  });
}
