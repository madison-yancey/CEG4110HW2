# CEG4110 HW2
CEG 4110 Homework 2

Android app that displays either a digital or analog clock (or both) to the user and allows the user to change the time.

## Prerequisites
Any phone that can run an Android app

## Deployment
1. Download the zip file of repository
2. Open the bin file
3. Open the APK
4. Install 

## How to Use
At the top half of the screen are the controls that allow the user to modify the time, add a clock, or undo/redo a time change. First, the user can select which clocks to add to the screen by pressing either "+Analog" to add an analog clock or "+Digital" to add a digital clock. Then, the user is able to change the second, minute, hour, day, month, and year of the clock(s). Once the desired time is configured by pressing either (+) to incrememnt a time period or (-) to decrement a time period, the user can press "Update" to update any open clocks. This updated time will also be the time set for any additonal clocks added to the screen.

![ClockControls](https://github.com/madison-yancey/CEG4110HW2/blob/master/Resources/ClockControls.PNG)

The user can also undo any time changes made to the clocks. In additon, the user can redo any changes made.

![Update](https://github.com/madison-yancey/CEG4110HW2/blob/master/Resources/Update.PNG)
![Undo](https://github.com/madison-yancey/CEG4110HW2/blob/master/Resources/Undo.PNG)

Clocks were updated from October 11, 2018 to January 1, 2018. Above, the time change from October to January was undone.

The bottom half of the screen displays all the clocks in the order in which they were added. The user can have multiple clocks open at the same time, and multiple versions of the same clock. For example, the user can have multiple analog clocks open, multiple digital clocks open, or a combination of both, as seen in the screenshots below. The user can then scroll through and view all the clocks. 

![AnalogClocks](https://github.com/madison-yancey/CEG4110HW2/blob/master/Resources/AnalogClocks.PNG)
![DigitalClocks](https://github.com/madison-yancey/CEG4110HW2/blob/master/Resources/DigitalClocks.PNG)

## Built With
Android Studio

Vector Analog Clock

DigitalClock

## Authors
Madison Yancey

## Design & Features
This app features an analog clock library, called Vector Analog Clock (https://github.com/TurkiTAK/vector-analog-clock). The library provided the analog clock and the ability to set the time of the analog clock.

Another library, called DigitalClock, is implemented in the app and provides the foundation for the digital clocks (https://gist.github.com/derekstavis/3894287). This library was modified to add in seconds and a visual representation of the digital clock, both of which were not part of the original DigitalClock library.

![AddingClocks](https://github.com/madison-yancey/CEG4110HW2/blob/master/Resources/AddingClocks.PNG)
