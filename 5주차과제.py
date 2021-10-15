#ㅖ언어 : Pyhon
# 
import time
import random

#중복 없는 랜덤 리스트트 생성하는 함수
def randomList(num):
    # sample() 함수를 이용해 중복 없는 요소를 num개 반환 
    num_list = random.sample(range(1, num+1), num) 
    return num_list

#각 정렬 함수 시작과 끝에는 시간 측정을 실행
#선택정렬 함수
def selectionSort(a, n):
    start = time.time()
    
    for i in range(n-1, 0, -1): #배열의 끝인 n-1부터 1까지 (0 포함X) 1씩 감소
        largest=0 #기준이 되는 0번째 인덱스를 사용
        for j in range(1, i+1): # 1부터 i 번째 요소까지 최대값이 있는 인덱스 찾기
            if a[j] > a[largest]: #최대값이 존재하면
                largest=j #그 값의 인덱스를 저장
        a[i], a[largest]=a[largest], a[i] # 한 사이클 비교가 끝나면 제일 뒤에 있는 값과 최대값의 자리를교환

    print("실행시간: ", time.time() - start, "sec")

#버블정렬 함수
def bubbleSort(a, n):
    start = time.time()

    for i in range(n-1, 0, -1): #배열의 끝인 n-1부터 1까지 (0 포함X) 1씩 감소
        for j in range(0, i): #0번 째부터 i-1번 째까지 두 값씩 비교
            if a[j] > a[j+1]: # 앞 요소가 뒷 요소보다 크면 
                a[j], a[j+1]=a[j+1], a[j] # 자리를 교환

    print("실행시간: ", time.time() - start, "sec")

#삽입정렬 함수
def insertionSort(a, n):
    start = time.time()
    
    for i in range(1, n): # 1부터 배열의 끝인 n-1까지 1씩 증가
        index=i-1 # 앞에서 정렬된 배열의 최댓값을 저장
        item=a[i] #a에 저장된 요소를 하나씩 비교하기 위해 item에 저장
        #a[0]부터 a[i-1]까지는 정렬이 되어있다.
        while index>=0 and item<a[index]: #index가 0이상이고 item(a[i])이 a[i-1]보다 크다면 들어갈 자리를 탐색
            a[index+1]=a[index] #이미 정렬된 배열에다가 최댓값을 제일 끝에 저장
            index-=1 # index를 하나씩 줄여나가면서 뒤에서부터 앞까지 비교 
            #최종적으로 index는 자신이 들어갈 자리보다 하나 앞이 된다.
        a[index+1]=item #index+1 자리에 item을 삽입

    print("실행시간: ", time.time() - start, "sec")

#데이터의 개수를 입력 받고 랜덤 리스트를 만들어서 호출
s = int(input("데이터의 개수: "))
list= randomList(s)

print("<<< 선택 정렬 >>>")
selectionSort(list, s)

print("<<< 버블 정렬 >>>")
bubbleSort(list, s)

print("<<< 삽입 정렬 >>>")
insertionSort(list, s)

