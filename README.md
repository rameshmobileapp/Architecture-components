
## Application description:
 A sample app it based on MVVM + DAGGER + ROOM + RXJAVA + LIVE DATA with clean architecture.
## Application function:
User can store add, edit and delete the notes with quick manner.
### Application component:
1.	MVVM
2.	Room
3.	Rx-java, Rx-android
4.	Dagger
5.	Live data
6.	Constrain layout.
### App package details:
### 	di ->it describes the dependency injection
#### 1. component ->it describes the application and activity component.
#### 2. module -> it describes the application and activity module.
## 	model 
#### 1.	data    -> it describes data transaction between the component
#### 2.	local    -> it describes the data transaction with Room
#### 3.	Repository -> it describes operation of the Room
## 	utils -> it describes the common things.
## 	view 
#### 1.	base -> it describes the implementation of base activity, interface.
#### 2.	Callback -> it describes the callback implementation 
#### 3.	Adapter -> it describes the adapter of the recycler view
## Application specification [It covers the 100 % of the device]
	Min SDK -> 	15
	Max SDK ->	28
	Java version -> 8
