# Cinder
![Build Status](https://travis-ci.org/akiraspeirs/Cinder.svg?branch=master)
[![codecov](https://codecov.io/gh/akiraspeirs/Cinder/branch/master/graph/badge.svg)](https://codecov.io/gh/akiraspeirs/Cinder)
[![Release](https://jitpack.io/v/akiraspeirs/Cinder.svg)](https://jitpack.io/#akiraspeirs/Cinder)

### What is Cinder?
Cinder helps you write concise and declarative code with Android’s databinding Observable classes.

It is unobtrusive and has no setup. You can start using it with your existing Observable code.

The library is in pre-release so its interface and implementation might change.

### Examples:
```java
//An example for working with arrays of observable classes.
Class ReceiptItem{
	public final ObservableInt quantity = new ObservableInt();
	public final ObservableInt price = new ObservableInt();

    //Recalculates when quantity or price changes.
	public final ObservableInt total = Cinder.computeInt(()->
	    quantity.get() * price.get(),
	    quantity, price);
}
Class Receipt{
	public final ObservableArrayList<RecepitItem> items = new ObservableArrayList<>();

	//Recalculates when items are added/removed or if their totals change.
	public final ObservableInt total = Cinder.computeInt(()->{
            int total = 0;
            for (RecepitItem item: items){
                total += item.total.get();
            }
            return total;
        }, Cinder.observable(items, RecepitItem.class, "total"));
}
```
```java
//This is a complete class from the cinderexample app.
public class FruitStall {
    final public ObservableField<String> newFruit = new ObservableField<>("");
    final public ObservableEvent addFruit = new ObservableEvent();

    //A list that adds newFruit each time addFruit fires, but only if newFruit has length > 0.
    final public ObservableArrayList<String> fruits = Cinder.<String>computeArrayList((list)->
            list.add(newFruit.get()),
        addFruit).filter(()->newFruit.get().length() > 0);

    //Remake a string of all fruits each time fruits is updated.
    final public ObservableField<String> fruitList = Cinder.<String>computeField(()-> {
            String fruitList = "";
            for (String fruit : fruits) {
                fruitList += fruit + " ";
            }
            return fruitList;
        }, Cinder.observable(fruits));

    //When fruits is updated, clear new fruit.
    final public Observable fruitsObserver = Cinder.observe(()->
        newFruit.set(""), Cinder.observable(fruits));
}
```
### Installing

You can install Cinder using JitPack.io:

```groovy
    repositories {
        maven {
            url "https://jitpack.io"
        }
    }
```

```groovy
    dependencies {
        compile 'com.github.akiraspeirs:Cinder:0.1.0'
    }
```
### Reference

#### ObservableEvent class:
```java
//A BaseObservable subclass that holds no data. Calling .fire() triggers notifyChange().
ObservableEvent addFruit = new ObservableEvent();

//XML:
<Button
	android:onClick="@{()->fruitshop.addFruit.fire()}" />
```

#### Basic compute methods:
```java
// Returns an ObservableInt subclass that is updated with the return value of the computeCallback
// whenever any of the specified observables call notifyChange.
public static CinderInt computeInt(OnComputeIntCallback computeCallback,
        BaseObservable... observables);

//Example:
ObservableInt fruitCount = Cinder.computeInt(()->
	appleCount.get() + bananaCount.get() + orangeCount.get(),
    appleCount, bananaCount, orangeCount);
```

#### List and Map compute methods:
```java
// Returns an ObservableArrayList subclass that can be updated via a list parameter of the
// computeCallback whenever any of the specified observables call notifyChange
public static <T> CinderArrayList<T> computeArrayList(OnComputeArrayListCallback<T> computeCallback,
        BaseObservable... observables);

//Example:
ObservableArrayList<String> fruits = Cinder.<String>computeArrayList((list)-> {
		list.add(new newFruitName.get())
    },
    addFruitEvent, newFruitName);
```

#### Basic observe methods:
```java
// When you need to just observe.
public static CinderObservable observe(OnChangeCallback onChangeCallback,
        BaseObservable... observables)

//Example:
Observable observable = Cinder.observe(()->{
		fruitService.recordTotalFruitCount(appleCount.get() + bananaCount.get();
	}, appleCount, bananaCount);
```

#### List and Map observe methods:
```java
// Runs onChangeCallback whenever the list is mutated, or when any specified BaseObservable field
// name calls notifyChange
public static <T> CinderObservable observe(ObservableList<T> list, Class c,
        OnChangeCallback onChangeCallback, String... fields)

//Example:
Observable fruitObserver = Cinder.observe(list, Fruit.class, ()->{
		fruitService.uploadAllFruits(list);
    }, "name", "tastiness"));
```

#### Observable list and map conversion methods:
```java
// Makes an Observable that notifies changes whenever the list is mutated. This is useful in
// conjection with observe and compute methods.
public static CinderComputable observable(ObservableList list);

//Example
ObservableInt length = Cinder.ComputeInt(()->list.get().length(), Cinder.observable(list));

// Makes an Observable that notifies changes whenever the list is mutated or an element's Observable
// field changes.
public static CinderComputable observable(ObservableList list, Class c, String... fields);

//Example
ObservableInt length = Cinder.ComputeInt(()->
	getAverageTastiness(list),
    Cinder.observable(list, Fruit.class, "tastiness"));
```

#### Operators:
```java
//Operators can be use to filter and control how callbacks are triggered.
Example:
final public ObservableArrayList<String> fruits = Cinder.<String>computeArrayList((list)->
            list.add(newFruit.get()),
        addFruit)
        .filter(()->newFruit.get().length() > 0) //Don't process fruits without a name.
        .skip(5) //Don't process the first five times a fruit is added
        .onUiThread(); //Run the callback on the UI thread.

//Triggers the callback once and then stops.
public Observable once();

//Trigers the callback the specified amount of times then stops.
public Observable take(int take);

//Skips triggering the callback the specified amount of times.
public Observable skip(int skip);

//Only triggers the callback if the filter returns true.
public Observable filter(CinderObservable.Filter filter);

//Triggers while the filter is true then stops.
public Observable takeWhile(CinderObservable.Filter filter);

//Skips triggering while the filter is true.
public Observable skipWhile(CinderObservable.Filter filter);

//Runs the callback on the UI thread.
public Observable onUiThread();

//Use a default value for the computed property.
public ObservableInt withDefault(int defaultValue);

//Run the callback once immediately.
public Observable immediate();

//Debounce the callback by the specified milliseconds. Throttles triggering the event
public Observable debounce(long debounce);
```
#### Operator precedence:
`filter > skipFilter > takeFilter > skip > take`

For example, if you call `filter(()->check()).skip(3).take(2)`, it will always check the filter
first, then skip 3 and then take 2.

#### Stopping:
```java
//Stops the callback triggering. Stopping removes all observers used to run the callback.
//It doesn't remove callbacks observing the obervable.
public void stop();
```

### Roadmap
- Add more operators.

### License
MIT License

Copyright (c) 2016 akiraspeirs

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.