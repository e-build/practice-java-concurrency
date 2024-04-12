# Chapter 1
## Example 1
## Example 2
## Example 3
- Thread life cycle
  - `NEW`
  - `RUNNABLE`
  - `WAITING`
    - wait()와 notify() 메서드는 Object 클래스에 정의 Java의 동기화 메커니즘에서 중요한 역할 수행. 
    - 이 메서드들은 반드시 동기화된(synchronized) 컨텍스트에서만 사용할 수 있음. 즉, wait(), notify(), notifyAll() 메서드는 동기화 블록 또는 동기화 메서드 내에서만 호출가능.
      - synchronized 키워드를 사용하면, 메서드나 블록을 실행하는 스레드는 해당 객체의 락을 획득함
      - wait() 메서드의 정의에 따르면, 스레드가 객체의 락을 소유한 상태에서만 대기 상태로 들어갈 수 있음. 이러한 규칙을 위반할 경우 IllegalMonitorStateException 예외 발생
      - notify()나 notifyAll() 메서드를 호출하는 다른 스레드가 있을 경우, 대기 상태에 있던 스레드 중 하나(또는 전부)가 깨어나 락을 다시 획득하고 계속 실행함. 이 과정에서 동기화는 락의 소유권을 안전하게 전달하기 위한 필수요소 
  - `TIMED_WAITING`
  - `BLOCKED`
  - `TERMINATED`

# Chapter 2
# Chapter 3
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
# Chapter 8
# Chapter 9
# Chapter 10
# Chapter 11