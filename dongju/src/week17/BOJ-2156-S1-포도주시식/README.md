## DP

포도주를 최대 연속 2잔까지 마실수 있으므로 안 마시는 경우, 마시는 경우, 연속으로 2잔 마시는 경우의 3가지 경우의 동적 배열을 이용했다

안 마시는 경우: 이전 최대값
마시는 경우: 두번쨰 이전 최대값(이전에는 안마셔야 하므로) + 현재 포도주
연속으로 마시는 경우: 이전 최대값 + 현재 포도주
