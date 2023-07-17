const input = require("fs")
  .readFileSync(process.platform === "linux" ? "dev/stdin" : "./input.txt")
  .toString()
  .split("\n");

const n = Number(input[0]);
const men = input[1].split(" ").map(Number);
const women = input[2].split(" ").map(Number);
const minusMen = men.filter((e) => e < 0);
const plusMen = men.filter((e) => e > 0);
const minusWomen = women.filter((e) => e < 0);
const plusWomen = women.filter((e) => e > 0);
let ans = 0;

minusMen.sort((a, b) => b - a);
plusMen.sort((a, b) => a - b);
minusWomen.sort((a, b) => b - a);
plusWomen.sort((a, b) => a - b);

let idx = 0;
for (let m of minusMen) {
  m = Math.abs(m);

  if (idx > plusWomen.length) break;
  if (m > plusWomen[idx]) {
    ans++;
    idx++;
  }
}

idx = 0;
for (let m of minusWomen) {
  m = Math.abs(m);

  if (m > plusMen[idx]) {
    ans++;
    idx++;
  }
}

console.log(ans);
