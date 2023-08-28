const input = require("fs")
  .readFileSync(process.platform == "linux" ? 0 : "input.txt")
  .toString()
  .trim()
  .split("\n");

const [n, m] = input[0].split(" ").map(Number);

const arr = [];
for (let i = 1; i <= n; i++) {
  const [a, b] = input[i].split(" ");
  arr.push([a, +b]);
}

let ans = "";
for (let i = 1; i <= m; i++) {
  const power = +input[n + i];

  let left = 0;
  let right = arr.length - 1;
  while (left <= right) {
    const mid = ~~((left + right) / 2);

    if (power <= arr[mid][1]) right = mid - 1;
    else left = mid + 1;
  }

  ans += arr[left][0] + "\n";
}

console.log(ans);
