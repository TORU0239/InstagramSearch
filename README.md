# InstagramSearch
Image Searching Test App using Instagram API (Unofficial)

Author: Wonyoung Choi, 최원영

## 개발 환경
1. OS: macOS Sierra 
2. IDE: Android Studio 2.3.2
3. 사용한 오픈소스 정보
```
- Glide
- Okhttp3
- Retrofit
- Gson
```
4. 사용한 Architecture
```
- MVP
- DataBinding
```

## 실행 환경 및 방법
1. Fork 받아서 Android Studio에 import한 후 Build.
2. InstagramSeach 앱 선택 후 실행
3. 분홍색 Toolbar 의 돋보기 버튼을 누른 후, Search 모드에서 검색할 ID를 입력하고 소프트키의 돋보기 아이콘을 터치, 아이디에 해당하는 이미지를 받아온다.

## Homework 회고 (개발 진행 과정, 결과물, 환경 등에 대한 회고)
1. 개발 프로세스는 다음과 같이 진행됨.

```
a. Github에 Issue 등록
b. master 에서 branch 만듬
c. 작업 후 PR 및 Comment 후 Conflict Check
d. Merge
e. Issue Close
```

2. 결과물에 대한 회고
```
a. 현재 요구사항에 대해 구현 완료했으나 MVP 각 element에 대한 추상화 고민이 좀더 필요할 것 같음.
b. Material Design 을 표방하면서 만들었지만 조금 더 UI에 디테일함이 필요할 것 같음.
```

3. 개발환경에 대한 회고
```
a. 혼자서 이슈 등록, branch 만들기, PR 및 Merge 를 다 하다 보니 가끔은 브랜치명과 다른 작업을 한 경우도 있었음.
b. 실제 현업에서 하는 프로세스와 거의 가깝게 진행했다는 점에서 의미가 있는 작업이라 생각됨.
```
## Homework 수행에 대한 회고 
1. 새로운 아키텍쳐 도입에 대한 아쉬움
```
a. Homework 중에 Google I/O 시작, 구글이 새로운 Architecture 를 발표했으나 러닝커브로 인해 바로 적용할 수 없었던 점.
b. RxJava 등의 미완성
```
    
2. 다음으로 해 볼만한 것
```
a. 전체 Architecture에 RxJava 를 이용한 Refactoring의 가능성
b. 코틀린으로 애플리케이션 재작성 (or Module화)
```

## Homework 진행에 대한 제안
- 과제 제출 후 소스코드에 대한 Issue 제기를 같이 해 주셔도 좋을 것 같음. (코드는 계속 바뀌어야 함)
- API 호출 시 이미지가 오는 경우도 있고, Carousel 형태의 데이터가 오는 경우도 있고, 영상이 있는 경우도 있음. 상세화면에서는 그런 다양한 데이터 타입에 대응하는 Task 를 해 보아도 좋을 겉 같음.

## 기타 (위에 나열된 내용 이외에 Homework 과 관련된 추가적인 어떠한 정보든 괜찮습니다.)
- 
