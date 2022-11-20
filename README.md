# EffectiveJava 정리 및 코드 추가 


## 1. 객체 생성과 파괴 


### 1.1 생성자대신 정적 팩터리 메서드를 고려하라 
-------

+ 정적 팩터리 메서드 장점

> 1. 이름을 가질 수 있다
> 2. 호출될 때 마다 인스턴스를 새로 생성하지는 않아도 된다.
> 3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.
> 4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
> 5. 정적 팩터리 메서드를 작성하는 시정에는 반환할 객체의 클래스가 존재하지 않아도 된다.

+ 정적 팩터리 메서드 단점

> 1. 상속을 하려면 public이나 protected 생성자가 필요하니 정적 팩터리 메서드만 제공하면 하위 클래스를 만들 수 없다.
> 2. 정적 팩터리 메서드는 프로그래머가 찾기 어렵다.



### 1.2 생성자에 매개변수가 많다면 빌더를 고려하라  

#### 해결책 1 : 생성자 
설정하고 싶은 매개변수를 최소한으로 사용하는 생성자를 사용해서 인스턴스를 만들 수 있다.
```java
NutritionFacts cocaCola = new NutritionFacts(6, 1, 3)
```
하지만 작성하기 어렵고, 읽기도 어려움

#### 해결책 2 : 자바빈
인스턴스 생성후 세터를 사용해서 필드만 설정할 수 있다.
```java
 NutritionFacts cocaCola = new NutritionFacts();
        cocaCola.setServingSize(240);
        cocaCola.setServings(8);
        cocaCola.setCalories(100);
        cocaCola.setSodium(35);
        cocaCola.setCarbohydrate(27);
```
자바빈즈 패턴에서는 객체 하나를 만들려면 메서드를 여러개 호출해야 하고, 객체가 완전히 생성되기 전까지는 일관성이 무너진상태가 된다. 이러한 문제 때문에 자바빈즈 패턴에서는 클래스를 분변으로 만들 수 없다. 

#### 해결책 3 : 빌더
생성자의 안정성과 자바빈을 사용할때 얻을 수 있었던 가독성을 모두 취할 수 있는 대안이다.
빌더 패턴은 만들려는 객체를 바로 만들지 않고 클라이언트는 빌더(생성자 또는 정적 팩토리)에 필수적인 
매개변수를 주변서 호출해 Builder객체를 얻은 다음 빌더 객체가 제공하는 세터와 비슷한 메소드를 사용해서 부가적인 필드를 채워넣고 최종적으로 build라는 메소드를 호출해서 만들려는 객체를 생성한다.

```java
NutritionFacts cocaCola = new NutritionFacts.Builder(240,8).calories(100).sodium(35).build();
```
