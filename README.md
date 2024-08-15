# ScheduleManagement

ERD : https://www.erdcloud.com/d/5RTAP4Tm9PwNuRhe4

-------------------------------------------------------------

일정 등록 요청
POST /schedule/regist
{
  "task":"할일",
  "managername":"담당자이름",
  "password":"password1234"
}

일정 등록 응답
id값은 일정의 고유값이며 일정이 추가될때마다 자동으로으로 1씩 올라가는 값이다.
{
  "id":1,
  "task":"할일",
  "managername":"담당자이름",
  "date":"2024-08-15 14:32",
  "fix_date":"2024-08-15 14:32"
}

------------------------------------

일정 단건 조회 요청
GET /schedule/inquiry
{
  "id":1
}


일정 단건 조회 응답
{
  "id":1,
  "task":"할일"
  "managername":"담당자이름"
  "date":"2024-08-15 14:32"
  "fix_date":"2024-08-15 14:32"
}

--------------------------------------
받은 조건을 기반으로 수정일 기준 내림차순 조회하기 (조건을 받지 않았다면 전체 조회) (둘 중 하나만 받으면 그걸 기준으로 조회)

GET /schedule/condition
{
  "fix_date":"2024-08-15 14:32"
  "managername":"담당자이름"
}

응답
{
  "id":1,
  "task":"할일"
  "managername":"담당자이름"
  "date":"2024-08-15 14:32"
  "fix_date":"2024-08-15 14:32"
}

------------------------------------------

일정 수정하기

PUT /schedule/modify
{
  "task":"수정할 일정 쓰기"
  "managername":"변경할 매니저 이름"
  "id":"변경할 일정 번호"
  "password":"일정의 비밀번호와 일치해야 변경가능"
}




