#ㅖ언어 : Pyhon
from math import floor
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

#병합정렬 함수
def mergeSort(a, p, r): #배열, 시작 인덱스, 끝 인덱스  
    if p<r: #원소가 2개 이상일 때 병합정렬을 수행한다.
        q = floor((p+r)/2) #두 원소를 반으로 나누고 내림한다.
        mergeSort(a, p, q) # 전반부 정렬을 재귀적으로 진행
        mergeSort(a, q+1, r) # 후반부 정렬을 재귀적으로 진행
        merge(a, p, q, r) # 정렬된 전, 후반부를 합쳐서 하나의 배열로 만든다.
#병합 함수
def merge(a, p, q, r):
    tmp=[] # 정렬하기위해 임시 배열을 만든다.
    i=p #전반부의 첫 인덱스
    j=q+1 #후반부의 첫 인덱스
    while i<=q and j<=r: #전, 후반 배열의 인덱스가 아직 남아있을 때
        if a[i] <= a[j]: #전반부 원소가 후반부 원소보다 작으면
            tmp.append(a[i]) #전반부 원소를 임시 배열에 하나 넣는다.
            i+=1 # 다음 원소를 탐색하기 위해 +1을 한다.
        else: #후반부 원소가 전반부 원소보다 작으면
            tmp.append(a[j]) # 후반부 원소를 임시 배열에 하나 넣는다.
            j+=1 #다음 원소를 탐색하기 위해 +1을 한다.

    while i<=q: # 전반부 배열만 남아있다면
        tmp.append(a[i]) #남은 전반부 원소를 전부 임시배열에 넣는다.
        i+=1 #+1을 하면서 전부 넣는다.

    while j<=r: # 후반부 배열만 남아있다면 
        tmp.append(a[j]) #남은 후반부 원소를 전부 임시배열에 넣는다.
        j+=1 #+1을 하면서 전부 넣는다.

    i=p; t=0; # 완성된 임시 배열을 원 배열에 넣기 위해 i을 시작 인덱스로 t을 0으로 세트
    while i<=r: #전부 넣어주기 위해 끝 인덱스 r까지 반복한다.
        a[i]=tmp[t] 
        i+=1
        t+=1

#퀵 정렬 함수
def quickSort(a, p, r):
    if (p<r): #원소가 2개 이상일 때 퀵 정렬을 수행한다.
        q=partition(a, p, r) #기준원소를 기준으로 분할하고 기준원소의 인덱스를 반환한다.
        quickSort(a, p, q-1) #기준원소를 제외한 기준원소의 왼쪽 부분을 퀵 정렬을 한다.
        quickSort(a, q+1, r) #기준원소를 제외한 기준원소의 오른쪽 부분을 퀵 정렬을 한다.
#분할 함수 
def partition(a, p, r):
    #1구역 : 기준원소보다 작은 원소의 구역 , p ~ i
    #2구역 : 기준원소보다 큰 원소의 구역, i+1 ~ j-1
    #3구역 : 아직 분할되지 않은 원소의 구역, j ~ r-1
    #기준원소의 인덱스는 r
    x=a[r]; #제일 오른쪽 원소를 기준원소로 잡는다.
    i=p-1; #1구역의 인덱스 
    for j in range(p, r): # 3구역을 반복해서 돌면서 분할한다.
        if a[j]<=x: #기준원소보다 작거나 같으면
            i+=1 #i+1을 함으로써 2구역의 첫 원소를 가리키게 된다.
            a[i], a[j] = a[j], a[i] #2구역의 첫 원소와 3구역의 원소의 자리를 바꾼다.

    a[i+1], a[r] = a[r], a[i+1] #2구역의 첫 원소와 기준원소의 자리를 바꾼다.

    return i+1 #기준원소의 인덱스를 리턴한다.

#데이터의 개수를 입력 받고 랜덤 리스트를 만들어서 호출
s = int(input("데이터의 개수: "))

list= randomList(s)
print("<<< 선택 정렬 >>>")
selectionSort(list, s)


list= randomList(s)
print("<<< 버블 정렬 >>>")
bubbleSort(list, s)

list= randomList(s)
print("<<< 삽입 정렬 >>>")
insertionSort(list, s)

list= randomList(s)
print("<<< 병합 정렬 >>>")
start = time.time()
mergeSort(list, 0, s-1)
print("실행시간: ", time.time() - start, "sec")

list= randomList(s)
print("<<< 퀵 정렬 >>>")
start = time.time()
quickSort(list, 0, s-1)
print("실행시간: ", time.time() - start, "sec")


