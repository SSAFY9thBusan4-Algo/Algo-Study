const input = require("fs")
  .readFileSync(process.platform === "linux" ? "dev/stdin" : "./input.txt")
  .toString();

const n = Number(input);
const v = Array.from({ length: 1001 }, () => Array(1001).fill(0));

const que = [];
que.push([1, 0]);
v[1][0] = 1;
let time = 0;

while (que.length) {
  const size = que.length;
  time++;
  for (let i = 0; i < size; i++) {
    const [emoji, clip] = que.shift();
    if (emoji > 1000 || emoji <= 0) continue;

    if (emoji === n) {
      que.length = 0;
      break;
    }

    if (!v[emoji][emoji]) {
      que.push([emoji, emoji]);
      v[emoji][emoji] = 1;
    }
    if (emoji + clip <= 1000 && !v[emoji + clip][clip]) {
      que.push([emoji + clip, clip]);
      v[emoji + clip][clip] = 1;
    }
    if (!v[emoji - 1][clip]) {
      que.push([emoji - 1, clip]);
      v[emoji - 1][clip] = 1;
    }
  }
}

console.log(time - 1);
