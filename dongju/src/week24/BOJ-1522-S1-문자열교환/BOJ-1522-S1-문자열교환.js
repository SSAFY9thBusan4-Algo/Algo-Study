const input = require("fs")
  .readFileSync(process.platform === "linux" ? "dev/stdin" : "./input.txt")
  .toString()
  .split("\n");

let arr = input.shift();

let aCnt = 0;
for (const s of arr) {
  if (s === "a") aCnt++;
}

let min = 1000;
for (let i = 0; i < arr.length; i++) {
  let bCnt = 0;
  for (let j = i; j < aCnt + i; j++) {
    if (arr[j % arr.length] === "b") bCnt++;
  }

  min = Math.min(min, bCnt);
}

console.log(min);
