var prompt = require("prompt");

prompt.message = "";
prompt.delimiter = "";
prompt.start();

prompt.get([{
      name: "mode",
      description: "What mode do you want to use? Static (1) or dynamic (2)?".yellow,
      message: "This is a required field and you should only use numbers (1 & 2) here!",
      type: "number",
      required: true,
      conform: function(mode) {
        if (mode < 1 || mode > 2)
          return false;
        return true;
      }}],
function (err, results) {
  console.log(results);
});
