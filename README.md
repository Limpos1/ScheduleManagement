# ScheduleManagement

일정 등록 요청 API
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
  "date":"2024-08-15, 14:32",
  "fix_date":"2024-08-15, 14:32"
}

------------------------------------





