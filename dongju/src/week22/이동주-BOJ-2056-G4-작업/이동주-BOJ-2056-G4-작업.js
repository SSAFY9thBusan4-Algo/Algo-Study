const input = require("fs")
  .readFileSync(process.platform === "linux" ? "dev/stdin" : "./input.txt")
  .toString()
  .split("\n");

const n = +input[0];
let indegrees = new Array(n + 1).fill(0);
let arr = Array.from(Array(n + 1), () => Array());
let time = new Array(n + 1);

for (let i = 1; i <= n; i++) {
  let temp = input[i].split(" ").map(Number);
  time[i] = temp[0];

  if (temp[1] != 0) {
    for (let j = 2; j < temp.length; j++) {
      arr[temp[j]].push(i);
      indegrees[i]++;
    }
  }
}

const que = [];
const dp = new Array(n + 1).fill(0);

for (let i = 1; i <= n; i++) {
  if (indegrees[i] === 0) {
    que.push(i);
    dp[i] = time[i];
  }
}

while (que.length) {
  let temp = que.shift();
  for (let i of arr[temp]) {
    indegrees[i]--;
    dp[i] = Math.max(dp[i], dp[temp] + time[i]);
    if (indegrees[i] === 0) que.push(i);
  }
}

console.log(Math.max(...dp));
