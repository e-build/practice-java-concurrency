# Chapter 1
- Thread life cycle
  - `NEW`
    - Thread 인스턴스 생성 이후 실행되지 않은 상태
  - `RUNNABLE`
    - `start()` 이후 스레드가 실행 중이거나 실행 가능한 상태
  - `WAITING`
    - `wait()`와 `notify()` 메서드는 Object 클래스에 정의. Java의 동기화 메커니즘에서 중요한 역할 수행. 
    - 이 메서드들은 반드시 동기화된(synchronized) 컨텍스트에서만 사용할 수 있음. 즉, `wait()`, `notify()`, `notifyAll()` 메서드는 동기화 블록 또는 동기화 메서드 내에서만 호출가능.
      - synchronized 키워드를 사용하면, 해당 메서드나 블록을 실행하는 스레드는 해당 객체의 락을 획득함
      - `wait()` 메서드의 정의에 따르면, 스레드가 객체의 락을 소유한 상태에서만 대기 상태로 들어갈 수 있음. 이러한 규칙을 위반할 경우 IllegalMonitorStateException 예외 발생
      - `notify()`나 `notifyAll()` 메서드를 호출하는 다른 스레드가 있을 경우, 대기 상태에 있던 스레드 중 하나(또는 전부)가 깨어나 락을 다시 획득하고 계속 실행함. 이 과정에서 동기화는 락의 소유권을 안전하게 전달하기 위한 필수요소 
  - `TIMED_WAITING`
    - `Thread.sleep()` 에 의해 일시정지된 상태
  - `BLOCKED`
    - 다른 스레드가 사용 중인 객체의 모니터 락을 획득하려고 시도하며 대기하는 상태
  - `TERMINATED`
    - 스레드 실행이 종료된 상태
# Chapter 2
- Context switch
  - CPU가 한 스레드에서 다른 스레드로 전환하는 과정. 이 과정에서 현재 실행 중인 작업의 상태(컨텍스트)를 저장하고, 
    다음 작업의 상태를 불러와 CPU가 이전에 중단된 지점부터 작업을 재개할 수 있도록 합니다. 컨텍스트 스위칭은 다중 작업을 가능하게 하지만, 저장과 불러오기 과정에서 시간이 소요되므로 오버헤드가 발생함.
  - CPU 코어: CPU 내의 처리 장치로, 동시에 여러 작업을 처리할 수 있는 능력이 코어 수에 비례함.
    - 싱글 코어: 여러 스레드가 동시에 실행되는 것처럼 보이지만, 실제로는 매우 빠른 속도로 스레드 간 전환을 반복하며, 이 과정을 컨텍스트 스위치라고 함
    - 멀티 코어: 여러 스레드가 동시에 실행될 수 있음. 이 경우에 각 코어에서 스레드 간 전환은 발생  
  - 스레드: 프로그램 내에서 실행되는 작업의 단위로, 여러 스레드가 동시에 실행될 수 있습니다. 자바에서는 Thread 클래스나 Runnable 인터페이스를 사용하여 스레드를 구현함
- CPU bounded
  - CPU의 계산 능력에 의존하는 작업.
  - 기본적으로 CPU의 처리 속도가 전체 성능에 가장 큰 영향을 미침. 
    ex) 큰 데이터 세트에 대한 복잡한 수학적 계산, 암호화 작업, 이미지 또는 비디오 처리 등...
- IO bounded
  - 입력과 출력 작업, 즉 디스크 I/O나 네트워크 I/O에 의해 성능이 결정되는 작업.
  - CPU의 계산 속도보다 데이터를 읽고 쓰는 속도가 전체 성능에 더 큰 영향을 미침.
    ex) 데이터베이스 쿼리, 파일 시스템 작업, 웹 API 호출 등...
# Chapter 3
- 스레드 대기 :: join
- 스레드 중단 :: interrupt
# Chapter 4
# Chapter 5
## Example 3
- 키워드 `synchronized`
  - Java에서 동기화를 위해 사용
  - 메서드 수준에서의 Synchronized
    ```java
    public synchronized void method() {
    // 동기화된 메서드 내용
    }
    ```
    - 해당 메서드가 포함된 객체의 락(lock)을 획득해야만 해당 메서드를 실행할 수 있음을 의미
    - 메서드 수준의 동기화는 메서드가 시작될 때 자동으로 락을 획득하고, 메서드가 종료되면 락을 자동으로 해제
  - 블록 수준에서의 Synchronized
      ```java
      public void method() {
        synchronized(this) {
        // 동기화된 블록 내용
        }
      }
      ```
    - 동기화 블록은 더 세밀한 동기화 제어를 가능하게 함, 필요한 부분에만 동기화를 적용할 수 있어 메서드 전체를 동기화하는 것보다 성능상 이점이 있음
# Chapter 6
- Mutex
  - 동시에 여러 스레드나 프로세스가 같은 자원에 접근하는 것을 방지하기 위한 동기화 매커니즘
  - 한 번에 하나의 스레드만이 자원에 접근하도록 함으로써 경쟁 상태(Race Condition)으로 인한 문제들을 방지함
- Semaphore 
  - 카운터 기반의 동기화 메커니즘으로 동시에 리소스에 접근할 수 있는 스레드의 수를 제한 함
    - 카운터 값: 세마포어는 내부적으로 카운터 값을 보유함. 동시에 리소스에 접근할 수 있는 스레드의 최대 수를 의미함 
    - acquire(): 스레드가 리소스에 접근하려고 할 때 호출. 세마포어의 카운터 값을 감소시키고, 카운터 값이 0이면 리소스에 접근할 수 있는 권한이 없으므로 스레드는 대기 상태가 됨.
    - release(): 스레드가 리소스 사용을 완료하고 다른 스레드가 접근할 수 있도록 하기 위해 호출. 세마포어의 카운터 값을 증가시킴.
  - Binary Semaphore
    - 카운터 값으로 0 또는 1만 가질 수 있는 세마포어. 바이너리 세마포어는 뮤텍스와 유사하게 작동하며, 주로 리소스의 상호 배제를 위해 사용
    - 뮤텍스(Mutex)와 바이너리 세마포어(Binary Semaphore)는 모두 상호 배제(Mutual Exclusion)를 보장하기 위해 사용되지만, 몇 가지 차이점이 있음
      - 뮤텍스는 소유권 개념을 가지고 있음. 즉, 뮤텍스를 잠금한 스레드만이 해당 뮤텍스를 해제할 수 있음. 바이너리 세마포어는 소유권 개념이 없음. 어떤 스레드든 세마포어를 획득할 수 있으며, 다른 스레드가 세마포어를 해제할 수도 있습니다. 이는 뮤텍스보다 유연성을 제공하지만, 오용할 경우 문제를 일으킴.
      - 뮤텍스는 주로 데이터의 상호 배제를 보장하기 위해 사용하지만, 바이너리 세마포어는 상호 배제뿐만 아니라, 시그널링(signaling) 메커니즘으로도 사용할 수 있음. 즉, 하나의 스레드가 다른 스레드에게 특정 이벤트나 조건의 발생을 알리는 데 사용 가능
  - Counting Semaphore
    - 카운터 값으로 0 이상의 값을 가질 수 있는 세마포어. 카운팅 세마포어는 여러 스레드가 동시에 리소스에 접근할 수 있도록 허용하며, 리소스의 개수를 제한하는 데 사용
- Condition Synchronization (조건 동기화)
  - 조건 동기화(Condition Synchronization)는 멀티 스레딩 환경에서 특정 조건이 만족될 때까지 스레드를 대기시키고, 해당 조건이 만족되면 스레드를 깨우는 메커니즘.
  - 스레드가 특정 조건이 충족되기를 기다린 후 작업을 수행해야 할 때 유용. 자원의 낭비 없이 효율적인 작업 수행을 도모할 수 있음. 
  - 조건 변수는 주로 뮤텍스와 함께 사용되어 복잡한 동기화 조건을 처리함
  - 대기(Wait): 특정 조건이 만족되지 않았을 때, 스레드는 조건이 충족될 때까지 대기. 대기 중인 스레드는 CPU 시간을 소비하지 않음.
  - 알림(Notify): 조건이 충족되면, 대기 중인 스레드 중 하나 또는 모든 스레드에게 알림을 보내어 작업을 계속할 수 있도록 함.
- Spinlock (스핀락)
  - 바쁜 대기(Busy Waiting)를 하는 잠금 메커니즘. 
  - 락을 획득하려는 스레드는 잠금이 해제될 때까지 루프를 돌며 계속 획득 시도. 
  - 컨텍스트 스위치가 없기 때문에 잠금을 기다리는 시간이 짧을 경우 오버헤드가 낮음.
  - 단순 스핀락
    - 잠금이 해제될 때까지 루프를 돌며 계속 시도, 
  - 하드웨어 지원을 받는 스핀락
    - 조금 더 발전된 형태 
      - TAS(Test-And-Set) Lock, CAS(Compare-And-Swap) Lock 등 
      - 최적화된 스핀락 구현체는 스레드가 CPU 코어를 과도하게 점유하지 않도록 하며, 필요에 따라 백오프(Back-off) 전략을 사용하여 스핀 회수를 조절할 수 있음.
# Chapter 7
- 동기화
  - 인스턴스 메서드 동기화: 인스턴스 메서드에 synchronized 키워드를 사용하면, 메서드가 속한 객체(this)를 모니터로 사용. 객체의 인스턴스 수준에서 동기화를 제공. 
  - 정적 메서드 동기화: 정적 메서드에 synchronized 키워드를 사용하면, 메서드가 속한 클래스의 Class 객체를 모니터로 사용. 클래스 수준에서 동기화를 제공하며, 모든 인스턴스에 걸쳐서 동기화가 이루어짐. 
  - 인스턴스 블록 동기화: 특정 객체(this)의 블록에 synchronized 키워드를 사용하여, 해당 객체에 대한 동기화 블록을 만듬. 이는 인스턴스 메서드 동기화와 유사하지만, 코드의 특정 부분만을 동기화할 수 있는 유연성을 제공. 
  - 별도의 객체를 사용한 동기화: 특정 객체(lockObject)에 대한 동기화 블록을 만듬. 이 방식은 동기화를 위해 별도의 객체를 명시적으로 지정하며, 동기화의 범위를 해당 객체로 한정함. 
  - 클래스를 모니터로 사용한 동기화: 클래스 자체(MethodBlockSynchronizedExamples.class)를 모니터로 사용하는 동기화 블록을 구현함. 정적 메서드 동기화와 유사한 효과를 가지며, 클래스 수준에서의 동기화를 제공함. 
  - 별도의 클래스를 모니터로 사용한 동기화: 다른 클래스(MethodBlock.class)를 모니터로 사용하는 동기화 블록을 구현함. 이 방식은 동기화를 위해 외부 클래스를 사용하며, 해당 클래스의 Class 객체를 모니터로 활용.
- Reentrant Lock (재진입 가능한 잠금)
  - 한 스레드가 어떤 잠금을 획득한 상태에서 같은 잠금을 추가로 획득할 수 있는 속성
  - 스레드가 같은 잠금을 여러 번 획득하고 해제할 수 있음을 의미하며, 잠금을 소유한 스레드가 블록되지 않고, 해당 잠금에 의해 보호되는 임계 구역에 자유롭게 다시 접근할 수 있게 함
  - 재진입 가능한 잠금은 스레드가 이미 획득한 잠금을 기다리면서 발생할 수 있는 데드락 상황을 방지
  - Synchronized: Java에서 synchronized 키워드는 메서드나 코드 블록에 대한 동기화를 제공하며, 내장된 잠금(모니터 락)은 자동으로 재진입 가능.
  - ReentrantLock: ReentrantLock은 Java의 `java.util.concurrent.locks` 패키지에 있는 명시적 잠금 메커니즘이며, synchronized에 비해 더 고급 기능(예: 공정성 설정, 시도 타임아웃)을 제공
  - 예제
  
    outerMethod와 innerMethod 모두 synchronized 키워드로 동기화되어 있음. outerMethod가 호출되어 잠금을 획득한 후, 같은 객체의 innerMethod를 호출할 때 추가적인 잠금 없이 접근. 
    ```java
    public class SynchronizedExample {
      public synchronized void outerMethod() {
          System.out.println("외부 메서드 시작");
          innerMethod();
          System.out.println("외부 메서드 종료");
      }
  
      public synchronized void innerMethod() {
          System.out.println("내부 메서드");
      }
  
      public static void main(String[] args) {
          SynchronizedExample example = new SynchronizedExample();
          example.outerMethod();
      }
    }
    ```
- volatile
  - Java의 멀티 스레딩 환경에서 변수의 가시성을 보장하기 위해 사용되는 키워드 
    - 보통 멀티쓰레드 환경에서는 각 쓰레드가 자신의 CPU 캐시에 변수의 복사본을 가지고 작업하기 때문에, 변수의 변경 사항이 다른 쓰레드에게 즉시 반영되지 않을 수 있음. 
      컴파일러가 해당 변수에 대한 일부 최적화를 제한하고, 변수의 값을 읽거나 쓸 때 CPU 캐시가 아닌 메인 메모리에서 직접 작업하도록 함으로써 쓰레드 간 가시성을 확보하게 됨
  - volatile 키워드가 붙은 변수는 항상 메인 메모리에서 읽고 씀. 이로 인해, 여러 스레드가 동시에 해당 변수를 사용하더라도 항상 최신의 값을 보장받을 수 있음.
  - 주의사항
    - 원자적 연산 보장하지 않음: volatile은 변수의 가시성만을 보장하며, 원자적 연산을 보장하지 않음. 따라서 스레드 간의 상태 동기화를 위해 사용해서는 안됨. 변수의 읽기와 쓰기 사이에 원자적 연산이 필요한 경우 synchronized 키워드나 java.util.concurrent.atomic 패키지의 클래스들을 사용해야 함. 
    - 성능 저하 가능성: volatile 변수는 CPU 캐시 대신 메인 메모리에서 직접 작업하므로 성능에 영향을 줄 수 있음. 특히, 변수가 매우 빈번하게 읽기 또는 쓰기되는 경우 성능 저하가 발생함. 또한, volatile 변수가 많은 경우에는 캐시 일관성(cache coherence)을 유지하기 위해 오버헤드 발생.
  - 단순 상태 플래그와 같은 간단한 용도에만 사용하는 것이 안전함
# Chapter 8
- java lock API
  - java.util.concurrent.locks.Lock
    - lock: unlock() 메서드를 호출하기 전까지 다른 스레드가 lock() 메서드를 호출하면 대기 상태로 전환됨. 
    - tryLock: 현재 스레드가 lock을 획득할 수 있는지 여부를 반환. 획득할 수 있다면 true를 반환하고 lock 을 획득함.
- java ReadWriteLock API
  - java.util.concurrent.locks.ReadWriteLock
    - ReadLock: 읽기 락을 획득하기 위한 인터페이스. 읽기 락은 여러 스레드가 동시에 획득할 수 있음. 읽기 락을 획득한 스레드는 다른 스레드가 쓰기 락을 획득하지 못하게 함. 
    - WriteLock: 쓰기 락을 획득하기 위한 인터페이스. 쓰기 락은 하나의 스레드만 획득할 수 있음. 쓰기 락을 획득한 스레드는 다른 스레드가 읽기 락이나 쓰기 락을 획득하지 못하게 함.
- lock fairness
  - 공정한 락은 스레드가 락을 요청한 순서대로 락을 획득함. 대기 중인 스레드가 락을 얻기 위해 오래 기다리지 않도록 보장함. 하지만 이 공정성을 유지하기 위해 추가적인 관리가 필요하며, 이로 인해 성능이 저하될 수 있음.
  - 공정한 락은 스레드의 대기 시간을 최소화. 비공정한 락은 락 관리의 효율성을 최대화.
# Chapter 9
- CAS (Compare And Swap)
  - compareAndSet 메서드는 원자적 비교 및 설정 연산을 수행. 이 메서드는 주어진 예상 값과 현재 값이 일치하는지 비교하고, 일치하는 경우에만 새로운 값으로 설정하고 이 모든 작업은 원자적으로, 즉 중단되지 않고 실행
  - 동작 원리 
    - 자바의 Unsafe 클래스와 CPU의 CAS (Compare-And-Swap) 명령에 대한 이해 필요.  
    - Unsafe 클래스는 저수준 연산을 수행하는 메서드를 제공하며, 이 중 하나가 compareAndSwapInt임. 이 메서드는 원자적 비교 및 교환 연산을 수행하며, 이는 CPU의 CAS 명령을 사용하여 구현됨.  
    - CAS 명령은 메모리 위치의 현재 값이 예상 값과 일치하는지 확인하고, 일치하는 경우에만 새로운 값으로 메모리 위치를 업데이트. 이 모든 작업은 원자적으로 수행되므로, 다른 스레드가 동시에 해당 메모리 위치를 수정할 수 없음.
- CountDownLatch
  - CountDownLatch는 주어진 카운트가 0이 될 때까지 대기하는 동기화 매커니즘. 
  - CountDownLatch는 주로 한 스레드가 다른 여러 스레드가 수행하는 작업을 기다리는 데 사용됨.
- CyclicBarrier
  - 여러 스레드가 특정 지점에서 서로 기다리도록 하는 동기화 도구.
  - 여러 스레드가 동시에 시작하거나, 모든 스레드가 특정 계산을 완료할 때까지 기다리는 등의 동기화 작업을 수행할 수 있음
  - CyclicBarrier vs CountDownLatch
    - 유사하지만, 다음과 같은 차이점이 있음
      - CountDownLatch는 카운트가 0이 되면 재사용할 수 없지만, CyclicBarrier는 재사용 가능
      - CountDownLatch는 하나의 스레드가 다른 여러 스레드의 작업 완료를 기다리는 데 사용됨. 반면에 CyclicBarrier는 여러 스레드가 서로를 기다리는 데 사용됨. 즉, 모든 스레드가 특정 지점에 도달할 때까지 기다리는 것.
      - CountDownLatch의 카운트는 외부 이벤트(countDown())에 의해 감소되지만, CyclicBarrier의 경우는 스레드가 장벽에 도달(await())할 때마다 내부 카운트가 감소.
# Chapter 10
- 자바의 Thread pool 추상화
  - Executor 인터페이스
    - Executor 인터페이스는 스레드 풀을 추상화한 인터페이스로, 작업 실행에 대한 API 제공
    - ExecutorService 인터페이스
      - ExecutorService 인터페이스는 Executor 인터페이스를 확장한 인터페이스로, 스레드 풀을 관리하는 데 사용됨. 
      - 스레드 풀의 생성, 작업 스케줄링, 작업 완료 여부 확인 등의 기능을 제공함.
    - Executors 클래스
      - Executor 인터페이스의 구현체를 생성하는 팩토리 메서드 제공 
  - SingleThreadPool
    - 스레드 풀의 크기가 1인 스레드 풀.
  - FixedThreadPool
    - 스레드 풀의 크기가 고정된 스레드 풀.
  - ScheduledThreadPool
    - 스케줄링 기능을 제공하는 스레드 풀.
    - schedule(): 특정 시간 이후에 작업을 한번 실행하도록 예약할 수 있음
    - scheduleAtFixedRate(): 주기적으로 작업을 실행하며, 이전 작업의 **시작 시간**부터 일정 시간 간격으로 작업을 실행할 수 있음.
    - scheduleWithFixedDelay(): 주기적으로 작업을 실행하며, 이전 작업의 **종료 시간**부터 일정 시간 간격으로 작업을 실행할 수 있음.
  - CachedThreadPool
    - 필요에 따라 스레드를 생성하고 재사용하는 스레드 풀.
    - 스레드 풀의 크기가 유동적으로 변하며, 코어 스레드 수와 최대 스레드 수를 설정할 수 있음. 작업이 많은 경우에는 새로운 스레드를 생성하고, 작업이 적은 경우에는 스레드를 종료하여 자원을 절약할 수 있음.
- ThreadPoolExecutor
  - ThreadPoolExecutor 클래스는 ExecutorService 인터페이스를 구현한 스레드 풀 클래스로, 스레드 풀의 동작을 세부적으로 제어할 수 있음.
  - 제어 속성
    - policy
      - AbortPolicy: 스레드 풀이 작업을 처리할 수 없을 때 예외를 발생시키는 정책. 기본값
      - CallerRunsPolicy: 스레드 풀이 작업을 처리할 수 없을 때 현재 스레드에서 작업을 실행하는 정책.
      - DiscardOldestPolicy: 스레드 풀이 작업을 처리할 수 없을 때 가장 오래된 작업을 제거하는 정책.
      - DiscardPolicy: 스레드 풀이 작업을 처리할 수 없을 때 작업을 무시하는 정책.
      - CustomPolicy: 사용자 정의 정책을 구현할 수 있음.
    - keepAliveTime
      - 스레드 풀이 코어 스레드 수 이상의 스레드를 가지고 있을 때, keepAliveTime 시간 동안 작업이 없으면 코어 스레드가 아닌 스레드는 종료됨
    - prestartAllCoreThreads() 
      - 스레드 풀의 코어 스레드를 사전에 생성하여 시작시킴. 작업이 생성될 때 대기 시간 없이 작업을 처리하도록 하기 위해 사용될
    - hook
      - ThreadPoolExecutor 를 상속하여 hook을 정의할 수 있음 
      - beforeExecute(): 작업 실행 전에 호출되는 메서드
      - afterExecute(): 작업 실행 후에 호출되는 메서드
      - terminated(): 스레드 풀이 종료될 때 호출되는 메서드
# Chapter 11
- Sync/Async vs Block/Non-Block
  - Sync/Async
    - 작업을 요청받은 주체가 바로 작업을 수행하는지 여부에 따라 구분 
    - Sync: 작업을 요청한 후, 해당 작업이 완료될 때까지 기다리는 방식. 작업이 완료되기 전까지 다른 작업을 수행할 수 없음.
    - Async: 작업을 요청한 후, 해당 작업의 완료 여부와 상관없이 다른 작업을 계속 수행할 수 있는 방식. 작업이 완료되면 별도의 콜백 함수나 이벤트를 통해 알림을 받음.
  - Block/Non-Block
    - 작업 처리에 대한 제어권을 가지고 있는 지가 핵심
    - Block: 작업을 요청하면서 제어권을 넘김
    - Non-Block: 작업을 요청하면서 제어권을 유지함
  - Sync/Block
    - A 스레드가 B 스레드에게 작업을 요청하면서 제어권도 함께 넘김, A 스레드는 B 스레드가 작업을 완료할 때까지 대기하며, 다른 작업을 수행할 수 없음.
  - Sync/Non-Block
    - A 스레드가 B 스레드에게 작업을 요청하면서 제어권을 유지함. A 스레드는 B 스레드가 작업을 완료할 때까지 작업 완료 여부를 계속 확인함.
  - Async/Block
    - A 스레드가 B 스레드에게 작업을 요청하면서 제어권을 넘김, A 스레드는 B 스레드가 작업을 완료할 때까지 대기하지만, 대기 지점 전까지 다른 작업을 수행할 수 있음
  - Async/Non-Block
    - A 스레드가 B 스레드에게 작업을 요청하면서 제어권을 유지함. A 스레드는 B 스레드의 작업 완료를 대기하지 않고, 다른 작업을 수행함
- Future vs CompletableFuture
  - Future
    - Future 인터페이스는 비동기 작업의 결과를 나타내는 인터페이스로, 작업이 완료되면 결과를 가져올 수 있음.
    - get(): 작업이 완료될 때까지 대기하며, 작업이 완료되면 결과를 가져옴. 
    - cancel(): 작업을 취소하며, 취소 여부를 반환함.
    - isDone(): 작업이 완료되었는지 여부를 반환함.
    - 한계
      - isDone, isCanceled 와 같은 기본사항 체크를 할 수 있는 메서드를 제공하지만, 
        각기 다른 실행시간을 가지는 Future들을 조합해서 계산을 한다든지 다른 질의의 결과와 같이 계산을 한다든지 하는 
        복잡한 (현실세계의 문제를 해결하는데 꼭 필요한) 로직을 다루기 힘듬
  - CompletableFuture
    - Future 인터페이스를 확장한 CompletableFuture 클래스는 비동기 작업을 더 쉽게 처리할 수 있도록 도와주는 클래스.
    - get() vs join()
      - get(): 작업이 완료될 때까지 대기하며, 작업이 완료되면 결과를 가져옴.
      - join(): 작업이 완료될 때까지 대기하며, 작업이 완료되면 결과를 가져옴. get()과의 차이점은 join()은 checked exception을 던지지 않음.
    - thenApply(): 작업이 완료된 후, CompletableFuture가 완료된 스레드에서 결과를 가공하거나 변환하는 작업을 수행함.
    - thenApplyAsnyc(): 작업이 완료된 후, 새로운 메서드에서 결과를 가공하거나 변환하는 작업을 수행함.
    - thenAccept(): 작업이 완료된 후, 결과를 소비하는 작업을 수행함. 결과는 한번만 소비될 수 있음
    - thenRun(): 작업이 완료된 후, 결과를 사용하지 않고 다른 작업을 수행함.
    - thenCompose(): 두 개의 CompletableFuture를 조합하여 하나의 CompletableFuture로 만드는 작업 수행.
    - thenCombine(): 이전 CompletableFuture 작업의 결과를 사용 하여 다른 CompletableFuture 작업 실행
    - allOf(): 모든 CompletableFuture가 완료될 때까지 대기하며, 모든 CompletableFuture가 완료되면 결과를 반환함.
    - anyOf(): 모든 CompletableFuture 중 하나라도 완료되면 결과를 반환함.