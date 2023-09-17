const input = require("fs")
  .readFileSync(process.platform == "linux" ? 0 : "input.txt")
  .toString()
  .trim()
  .split("\n");

const MOD = 1000000003;
const n = +input[0];
const k = +input[1];

const d = Array.from(Array(n + 1), () => Array(k + 1).fill(0));

for (let i = 1; i <= n; i++) {
  d[i][0] = 1;
  d[i][1] = i;
}

for (let i = 3; i <= n; i++) {
  for (let j = 2; j <= ~~((i + 1) / 2); j++) {
    d[i][j] = (d[i - 1][j] + d[i - 2][j - 1]) % MOD;
  }
}

console.log((d[n - 3][k - 1] + d[n - 1][k]) % MOD);
