#  💡 알고리즘 스터디 💡

SSAFY 9기 부울경 4반 알고리즘 스터디 기록

## 🙋🏻‍♂️ Contributors
<table>
  <tr>
   <td align="center"><a href="https://github.com/kjh95044"><img src="https://avatars.githubusercontent.com/u/48514956?v=4" width="120px;" alt=""/><br /><sub><b>김지홍</b></sub></a><br /></td>
    <td align="center"><a href="https://github.com/da010228"><img src="https://avatars.githubusercontent.com/u/69593799?v=4" width="120px;" alt=""/><br /><sub><b>강다은</b></sub></a><br /></td>
    <td align="center"><a href="https://github.com/Ksohyeon"><img src="https://avatars.githubusercontent.com/u/110150957?s=96&v=4" width="120px;" alt=""/><br /><sub><b>강소현</b></sub></a><br /></td>
    <td align="center"><a href="https://github.com/YoungEun-Choi930"><img src="https://avatars.githubusercontent.com/u/69569809?s=96&v=4" width="120px;" alt=""/><br /><sub><b>최영은</b></sub></a><br /></td>
    <td align="center"><a href="https://github.com/HyoinJeong"><img src="https://avatars.githubusercontent.com/u/52451420?s=64&v=4" width="120px;" alt=""/><br /><sub><b>정효인</b></sub></a><br /></td>
    <td align="center"><a href="https://github.com/emoving"><img src="https://avatars.githubusercontent.com/u/82428892?v=4" width="120px;" alt=""/><br /><sub><b>이동주</b></sub></a><br /></td>
  </tr>
</table>

<br><br>
[![Solved.ac 프로필](http://mazassumnida.wtf/api/v2/generate_badge?boj=kjh9504)](https://solved.ac/kjh9504)
[![Solved.ac 프로필](http://mazassumnida.wtf/api/v2/generate_badge?boj=da010228)](https://solved.ac/da010228)
[![Solved.ac 프로필](http://mazassumnida.wtf/api/v2/generate_badge?boj=thgusrkd0120)](https://solved.ac/thgusrkd0120)
[![Solved.ac 프로필](http://mazassumnida.wtf/api/v2/generate_badge?boj=duddms0190)](https://solved.ac/duddms0190)
[![Solved.ac 프로필](http://mazassumnida.wtf/api/v2/generate_badge?boj=hiamy0107)](https://solved.ac/hiamy0107)
[![Solved.ac 프로필](http://mazassumnida.wtf/api/v2/generate_badge?boj=leednj0113)](https://solved.ac/leednj0113)
<br><br>

## 📌 Rule
각자 문제를 선정하여 매주 4~12문제를 풉니다.
매주 월요일 카카오톡 공지방에 댓글로 문제를 등록합니다.
* ❗❗ 문제풀이 마감 : 매주 월요일 23:59 까지
* ❕❕ 리뷰 마감 : 매주 화요일 23:59 까지
<br><br>

## 📌 Convention
###  1️⃣ Code Convention
각 코드 별 목적을 주석으로 작성합니다.
변수와 함수 이름 또한 역할을 알 수 있도록 간단한 주석을 덧붙입니다.

<br>

### 2️⃣ Project Convention

각 멤버별 프로젝트 구조는 다음과 같습니다
**프로젝트이름/week번호/플랫폼_문제번호_레벨_문제이름/...**

    kjh95044/week15/BOJ_1051_S3_숫자정사각형/...

<br>

### 3️⃣ Commit Convention
한번에 모든 파일을 add하지 않고 type별로 분리하여 commit 합니다.

    docs : README.md 등 문서 작성 및 수정
    code : 코드 작성
    fix : 코드 수정
    add : 기존에 푼 문제에 대한 추가
    remove : 코드 및 문서 삭제
    merge : pr(pull request)을 통해 자신의 repo에서 원본 repo로 merge하기
  <br>

적용 예시 ::
1. BOJ의 1051번 숫자 정사각형 (silver3) 문제를 풀었다면
해당 코드를 하나의 commit으로 분리합니다.  
이 때의 commit message는 다음과 같이 통일합니다
		
		 git commit -m "code : BOJ 1051 S3 숫자정사각형"

	해당 코드를 수정할 때의 commit message는 다음과 같이 통일합니다.
		
		 git commit -m "fix : BOJ 1051 S3 숫자정사각형"

2.  코드에 대한 설명을 작성하고
해당 문서를 하나의 commit으로 분리합니다.  
이 때의 commit message는 다음과 같습니다.
		
		 git commit -m "docs : BOJ 1051 S3 숫자정사각형"

3. main README.md 파일을 수정할 때의 commit message는 다음과 같습니다.
		
		 git commit -m "docs : main README update"

5. 파일을 삭제할 경우 commit message는 다음과 같습니다
		
		 git commit -m "remove : 삭제파일"
		
<br>

### 4️⃣ Review Convention
1. Pull Request의 제목은 다음과 같이 통일합니다.
**이름 : 문제플랫폼 문제번호 문제등급 문제제목** 
		
		 JIHONG KIM : BOJ 1051 S3 숫자정사각형
		
2. Pull Request의 comment에는 본인이 작성한 README.md의 내용을 추가합니다. 

3. 문제에 해당하는 유형을 선택하여 PR에 label을 attach하고,   
 자신의 PR의 assignee에 자신을 추가 후 문제풀이 week에 해당하는 Milestones을 선택합니다.

4. 기존에 PR을 작성 후 새로운 문제를 풀었을 경우, 새로운 문제에 대한 commit을 하기 전 다음 과정을 수행합니다.

	- ❓ 코드리뷰가 완료 되었을 경우  
		자신의 PR에서 merge 버튼을 눌러 merge 합니다. 
		
	- ❓ 리뷰 완료 전 새로운 문제를 풀 경우
		1. 자신의 local에서 새로 푼 문제에 대한 branch를 생성합니다.  
		이 때 branch의 이름을 **문제플랫폼-문제번호**과 같이 생성하는 것을 권장합니다.
		
			    boj-1051
		
		2. 새로운 문제에 대한 code와 README.md에 대한 commit을 추가 후 push합니다.   
		이 때의 commit message는 2️⃣ Commit Convention에서 언급한 규칙에 맞게 설정합니다.
		3. 이 때 반드시 (a)에서 생성한 branch로 push 되는지 확인합니다.
		4. 본인 계정의 fork된 repo에서 Pull Request을 작성할 때,   
		코드가 push된 브랜치(a에서 생성한 jihong/boj-1051)에서   
		organization repo의 main 브랜치로 Pull Request를 보냅니다.


<br><br>

## 📌 Solved Problems
### 🚩 week 1
| Type | 문제 | 제목 | 유형 | rank |
| -- |--| -- |--|--|
| BOJ | 3085 | [사탕 게임](https://www.acmicpc.net/problem/3085) | 브루트포스 | silver3 |
| BOJ | 1476 | [날짜 계산](https://www.acmicpc.net/problem/1476) | 브루트포스 | silver5 |
| BOJ | 1107 | [리모컨](https://www.acmicpc.net/problem/1107) | 브루트포스 | gold5 |
| BOJ | 6064 | [카잉 달력](https://www.acmicpc.net/problem/6064) | 브루트포스| silver1 |

### 🚩 week 2
| Type | 문제 | 제목 | 유형 | rank |
| -- |--| -- |--|--|
| BOJ | 14501 | [퇴사](https://www.acmicpc.net/problem/14501) | DP, 브루트포스 | silver3 |
| BOJ | 10972 | [다음 순열](https://www.acmicpc.net/problem/10972) | 재귀 | silver3 |
| BOJ | 11727 | [2×n 타일링 2](https://www.acmicpc.net/problem/11727) | DP | silver3 |
| BOJ | 10866 | [덱](https://www.acmicpc.net/problem/10866) | 덱 | silver4 |
| BOJ | 7576 | [토마토](https://www.acmicpc.net/problem/7576) | 그래프 | gold5 |

### 🚩 week 3
| Type | 문제 | 제목 | 유형 | rank |
| -- |--| -- |--|--|
| BOJ | 1992 | [쿼드트리](https://www.acmicpc.net/problem/1992) |분할 정복, 재귀 | silver1 |
| BOJ | 1991 | [트리 순회](https://www.acmicpc.net/problem/1991) | 트리, 재귀 | silver1 |
| BOJ | 9084 | [동전](https://www.acmicpc.net/problem/9084) | DP | gold5 |
| PRG | 60058 | [괄호 변환](https://school.programmers.co.kr/learn/courses/30/lessons/60058) | 구현 | LV.2 |

### 🚩 week 4
| Type | 문제 | 제목 | 유형 | rank |
| -- |--| -- |--|--|
| BOJ | 11052 | [카드 구매하기](https://www.acmicpc.net/problem/11052) | DP | silver1 |
| BOJ | 2096 | [내려가기](https://www.acmicpc.net/problem/2096) | DP | gold5 |
| BOJ | 1495 | [기타리스트](https://www.acmicpc.net/problem/1495) | DP | silver1 |
| BOJ | 11057 | [오르막 수](https://www.acmicpc.net/problem/11057) | DP | silver1 |
| PRG | 118666 | [성격 유형 검사하기](https://school.programmers.co.kr/learn/courses/30/lessons/118666) | 구현 | LV.1 |

### 🚩 week 5
| Type | 문제 | 제목 | 유형 | rank |
| -- |--| -- |--|--|
| BOJ | 21608 | [상어 초등학교](https://www.acmicpc.net/problem/21608) | 구현 | gold5 |
| SWEA | 1953 | [탈주범 검거](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpLlKAQ4DFAUq&categoryId=AV5PpLlKAQ4DFAUq&categoryType=CODE&problemTitle=%EB%AA%A8%EC%9D%98&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=2) | 구현 | 역량테스트 |
| SWEA | 2383 | [점심 식사시간](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5-BEE6AK0DFAVl&categoryId=AV5-BEE6AK0DFAVl&categoryType=CODE&problemTitle=%EB%AA%A8%EC%9D%98&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=2) | 구현 | 역량테스트 |
| SWEA | 4014 | [활주로 건설](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeW7FakkUDFAVH&categoryId=AWIeW7FakkUDFAVH&categoryType=CODE&problemTitle=%EB%AA%A8%EC%9D%98&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1) | 구현 | 역량테스트 |

### 🚩 week 6
| Type | 문제 | 제목 | 유형 | rank |
| -- |--| -- |--|--|
| BOJ | 2252 | [줄세우기](https://www.acmicpc.net/problem/2252) | 위상 정렬 | gold3 |
| BOJ | 9205 | [맥주 마시면서 걸어가기](https://www.acmicpc.net/problem/9205) | BFS | gold5 |
| BOJ | 10282 | [해킹](https://www.acmicpc.net/problem/10282) | Dijkstra | gold4 |
| BOJ | 13305 | [주유소](https://www.acmicpc.net/problem/13305) | 그리디 | silver3 |

### 🚩 week 7
| Type | 문제 | 제목 | 유형 | rank |
| -- |--| -- |--|--|
| BOJ | 10775 | [공항](https://www.acmicpc.net/problem/10775) | bfs | gold2 |
| BOJ | 2206 | [벽 부수고 이동하기](https://www.acmicpc.net/problem/2206) | bfs | gold3 |
| BOJ | 2665 | [미로만들기](https://www.acmicpc.net/problem/2665) | bfs | gold4 |
| BOJ | 2174 | [로봇 시뮬레이션](https://www.acmicpc.net/problem/2174) | 구현 | gold5 |
| BOJ | 1743 | [음식물 피하기](https://www.acmicpc.net/problem/1743) | bfs | silver1 |

### 🚩 week 8
| Type | 문제 | 제목 | 유형 | rank |
| -- |--| -- |--|--|
| BOJ | 6593 | [상범 빌딩](https://www.acmicpc.net/problem/6593) | bfs | gold5 |
| BOJ | 9328 | [열쇠](https://www.acmicpc.net/problem/9328) | 구현 bfs | gold1 |
| BOJ | 20055 | [컨베이어 벨트 위의 로봇](https://www.acmicpc.net/problem/20055) | 구현 | gold5 |
| BOJ | 2638 | [치즈](https://www.acmicpc.net/problem/2638) | bfs | gold3 |
| BOJ | 14719 | [빗물](https://www.acmicpc.net/problem/14719) | 구현 | gold5 |

### 🚩 week 9
| Type | 문제 | 제목 | 유형 | rank |
| -- |--| -- |--|--|
| BOJ | 11559 | [Puyo Puyo](https://www.acmicpc.net/problem/11559) |  | gold4|
| BOJ | 2632 | [피자판매(https://www.acmicpc.net/problem/2632) |   | gold2|
| BOJ | 1005 | [ACM Craft](https://www.acmicpc.net/problem/1005) |  | gold3|
| BOJ | 1043 | [거짓말(https://www.acmicpc.net/problem/1043) |  | gold4|
| BOJ | 21318 | [피아노 체조](https://www.acmicpc.net/problem/21318) |  | silver1|


