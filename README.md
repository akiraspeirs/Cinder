# Cinder
![Build Status](https://travis-ci.org/akiraspeirs/Cinder.svg?branch=master)
[![codecov](https://codecov.io/gh/akiraspeirs/Cinder/branch/master/graph/badge.svg)](https://codecov.io/gh/akiraspeirs/Cinder)
[![Release](https://jitpack.io/v/akiraspeirs/Cinder.svg)](https://jitpack.io/#akiraspeirs/Cinder)

## What is Cinder?
Cinder helps you write concise and declarative code with Android’s data binding Observable classes.

It doesn't need any setup and you can start using it with your existing Observable code.

The library is in version zero so its interface and implementation might change or be unstable.

## What it does:
```java
//You can write this:
Class ReceiptItem{
	public final ObservableInt quantity = new ObservableInt();
	public final ObservableInt price = new ObservableInt();
	public final ObservableInt total = Cinder.computeInt(()-> quantity.get() * price.get(),
	    quantity, price);
}

//Instead of something this:
Class ReceiptItem{
	public final ObservableInt quantity = new ObservableInt();
	public final ObservableInt price = new ObservableInt();
	public final ObservableInt total = new ObservableInt();

    private OnPropertyChangedCallback totalObserver = new OnPropertyChangedCallback() {
       @Override
       public void onPropertyChanged(Observable sender, int propertyId) {
           total.set(quantity.get() * price.get());
       }
    };

	public ReceiptItem(){
        quantity.addOnPropertyChangedCallback(totalObserver);
        price.addOnPropertyChangedCallback(totalObserver);
	}
}
```
## Other Examples:
```java
//Observe properties of array elements
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
For more examples see the cinderexample app, the documentation below, or the test suite.
## Installing:

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

Strongly recommended to be used with [Retrolambda](https://github.com/evant/gradle-retrolambda)
## Reference:

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
// Returns an ObservableArrayList subclass that can be updated via the list argument of the
// computeCallback whenever any of the specified observables call notifyChange.
public static <T> CinderArrayList<T> computeArrayList(OnComputeArrayListCallback<T> computeCallback,
        BaseObservable... observables);

//Example:
ObservableArrayList<String> fruits = Cinder.<String>computeArrayList((list)-> {
		list.add(new newFruitName.get())
    },
    addFruitEvent);
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
// Runs onChangeCallback whenever the list is mutated, or when any specified BaseObservable fields
// of the list elements calls notifyChange
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
//Operators can be used to filter and control how callbacks are triggered.
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

//Sets a default value for the computed property.
public ObservableInt withDefault(int defaultValue);

//Runs the callback once immediately.
public Observable immediate();

//Debounces the callback by the specified milliseconds.
public Observable debounce(long debounce);
```
#### Operator precedence:
`filter > skipFilter > takeFilter > skip > take`

For example, if you call `filter(()->check()).skip(3).take(2)`, it will always check the filter
first, then skip 3 and then take 2.

#### Stopping:
```java
// Stops the callback triggering. Stopping removes all observers used to run the callback. It
// doesn't remove callbacks observing the obervable.
public void stop();
```

## Notes
#### Memory management:
- All callbacks and objects generated by Cinder will clean themselves up automatically, so long as their owning objects are not leaking.
- If you don't call `stop()`, a Cinder variable's callbacks cannot be garbage collected until its owner and all Observables it's computed from can be garbage collected.
- The owner of a Cinder callback cannot be garbage collected if it has any Cinder callbacks that cannot be garbage collected.

#### Thread safety:
- `debounce()` will run the callback in a Timer thread, unless used with `onUiThread()`. Make sure your debounced callbacks are threadsafe.

## Roadmap
- Add more operators.

## License
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