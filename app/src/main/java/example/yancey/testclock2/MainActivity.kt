package example.yancey.testclock2

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.util.Log
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import com.turki.vectoranalogclockview.VectorAnalogClock
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener



class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val monthSpinner = findViewById(R.id.spMonth) as Spinner
        val monthAdapter = ArrayAdapter.createFromResource(this, R.array.month_array,
                android.R.layout.simple_spinner_item)
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        monthSpinner.setAdapter(monthAdapter)

        monthSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                tvDays.text = "1"
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }

        }

        val myRoot = findViewById(R.id.mainLayout) as LinearLayout
        var tCalendar = Calendar.getInstance()
        var firstAnalogClock = true
        var firstDigitalClock = true

        var handler1 = Handler()
        var runnable1: Runnable = object : Runnable {
            override fun run() {
                if(firstAnalogClock == false) {
                    tCalendar.roll(Calendar.SECOND, 1)
                    handler1.postDelayed(this, 1000)
                }
            }
        }

        bAddAnalog.setOnClickListener{
            var analogClock = MyVectorClock(this)
            var a = example.yancey.testclock2.WordClock(this)
            if(firstAnalogClock == false){
                a.setTime(tCalendar.get(Calendar.SECOND), tCalendar.get(Calendar.MINUTE),
                        tCalendar.get(Calendar.HOUR), tCalendar.get(Calendar.DAY_OF_MONTH),
                        tCalendar.get(Calendar.MONTH), tCalendar.get(Calendar.YEAR))
                analogClock.setCalendar(tCalendar)
                Log.d("CALENDAR MON: ", tCalendar.get(Calendar.MONTH).toString())
                //count1 = 0
            } else{
                //don't set calendar
                if(firstDigitalClock == false){
                    firstAnalogClock = false
                } else {
                    Log.d("here", "here")
                    handler1.post(runnable1)
                    firstAnalogClock = false
                }
            }
            myRoot.addView(analogClock)
            myRoot.addView(a)
        }

        bAddDigital.setOnClickListener{
            var a = example.yancey.testclock2.DigitalClock(this)
            if(firstDigitalClock != true){
                a.setTime(tCalendar.get(Calendar.SECOND), tCalendar.get(Calendar.MINUTE),
                        tCalendar.get(Calendar.HOUR), tCalendar.get(Calendar.DAY_OF_MONTH),
                        tCalendar.get(Calendar.MONTH), tCalendar.get(Calendar.YEAR))
            } else{
                //don't set calendar
                if(firstAnalogClock == false){
                    //don't start thread
                    firstDigitalClock = false
                } else {
                    handler1.post(runnable1)
                    firstDigitalClock = false
                }
            }
            myRoot.addView(a)
        }

        bPlusSeconds.setOnClickListener {
            var currentSeconds = Integer.parseInt(tvSeconds.text.toString())
            if(currentSeconds == 59){
                tvSeconds.text = "0"
            }else{
                var seconds = currentSeconds + 1
                tvSeconds.text = seconds.toString()
            }
        }

        bMinusSeconds.setOnClickListener {
            var currentSeconds = Integer.parseInt(tvSeconds.text.toString())
            if(currentSeconds == 0){
                tvSeconds.text = "59"
            }else{
                var seconds = currentSeconds - 1
                tvSeconds.text = seconds.toString()
            }
        }

        bPlusMinutes.setOnClickListener {
            var currentMinutes = Integer.parseInt(tvMinutes.text.toString())
            if(currentMinutes == 59){
                tvMinutes.text = "0"
            }else{
                var minutes = currentMinutes + 1
                tvMinutes.text = minutes.toString()
            }
        }

        bMinusMinutes.setOnClickListener {
            var currentMinutes = Integer.parseInt(tvMinutes.text.toString())
            if(currentMinutes == 0){
                tvMinutes.text = "59"
            }else{
                var minutes = currentMinutes - 1
                tvMinutes.text = minutes.toString()
            }
        }

        //12 hour analog clock???????????????
        bPlusHours.setOnClickListener {
            var currentHours = Integer.parseInt(tvHours.text.toString())
            if(currentHours == 23){
                tvHours.text = "0"
            }else{
                var hours = currentHours + 1
                tvHours.text = hours.toString()
            }
        }

        bMinusHours.setOnClickListener {
            var currentHours = Integer.parseInt(tvHours.text.toString())
            if(currentHours == 0){
                tvHours.text = "23"
            }else{
                var hours = currentHours - 1
                tvHours.text = hours.toString()
            }
        }

        bPlusDays.setOnClickListener {
            var currentDay = Integer.parseInt(tvDays.text.toString())
            var month = monthSpinner.selectedItemPosition
            if(currentDay == 28 && month == 1 ){
                tvDays.text = "1"
            }else if (currentDay == 30 && (month == 3 || month == 5 || month == 8 || month == 10)){
                tvDays.text = "1"
            } else if(currentDay == 31 && (month == 0 || month == 2 || month == 4 || month == 6
                            || month == 7 || month == 9 || month == 11)){
                tvDays.text = "1"
            } else{
                var days = currentDay + 1
                tvDays.text = days.toString()
            }
        }

        bMinusDays.setOnClickListener {
            var currentDay = Integer.parseInt(tvDays.text.toString())
            var month = monthSpinner.selectedItemPosition
            if(currentDay == 1 && month == 1 ){
                tvDays.text = "28"
            }else if (currentDay == 1 && (month == 3 || month == 5 || month == 8 || month == 10)){
                tvDays.text = "30"
            } else if(currentDay == 1 && (month == 0 || month == 2 || month == 4 || month == 6
                            || month == 7 || month == 9 || month == 11)){
                tvDays.text = "31"
            } else{
                var days = currentDay - 1
                tvDays.text = days.toString()
            }
        }

        bPlusYears.setOnClickListener {
            var currentYear = Integer.parseInt(tvYears.text.toString())
            var years = currentYear + 1
            tvYears.text = years.toString()
        }

        bMinusYears.setOnClickListener {
            var currentYear = Integer.parseInt(tvYears.text.toString())
            if(currentYear == 0){
                tvYears.text = "0"
            } else{
                var years = currentYear - 1
                tvYears.text = years.toString()
            }
        }


        var CmdQ = CommandQ()
        bUpdate.setOnClickListener{
            val prevSec = tCalendar.get(Calendar.SECOND)
            val prevMin = tCalendar.get(Calendar.MINUTE)
            val prevHour = tCalendar.get(Calendar.HOUR)
            val prevDay = tCalendar.get(Calendar.DAY_OF_MONTH)
            val prevMonth = tCalendar.get(Calendar.MONTH)
            val prevYear = tCalendar.get(Calendar.YEAR)

            Log.d("prev month!", tCalendar.get(Calendar.MONTH).toString())

            val previousTime = Time(prevSec, prevMin, prevHour, prevDay, prevMonth, prevYear)

            Log.d("prev month", previousTime._month.toString())

            var seconds = Integer.parseInt(tvSeconds.text.toString())
            var minutes = Integer.parseInt(tvMinutes.text.toString())
            var hours = Integer.parseInt(tvHours.text.toString())
            var day  = Integer.parseInt(tvDays.text.toString())
            var month = monthSpinner.selectedItemPosition
            var year  = Integer.parseInt(tvYears.text.toString())

            var currentTime = Time(seconds, minutes, hours, day, month, year)

            tCalendar.set(year, month, day, hours, minutes, seconds)

            var cmdDone = UpdateTimeCommand(previousTime, currentTime, myRoot, tCalendar)
            CmdQ.push(cmdDone)
            updateClocks(myRoot, seconds, minutes, hours, day, month, year, tCalendar)
        }

        bUndo.setOnClickListener{
            CmdQ.undo()
        }

        bRedo.setOnClickListener{
            CmdQ.redo()
        }
    }
}

fun updateClocks(myRoot: LinearLayout, seconds: Int, minutes: Int, hours: Int,
                 day: Int, month: Int, year : Int, calendar: Calendar){
    calendar.set(year, month, day, hours, minutes, seconds)
    var i = 0
    while(i < myRoot.childCount){
        var v = myRoot.getChildAt(i)
        if(v is DigitalClock){
            v.setTime(seconds, minutes, hours, day, month, year)
        } else if(v is WordClock){
            v.setTime(seconds, minutes, hours, day, month, year)
        } else if (v is MyVectorClock){
            Log.d("HOUR SET INNER", calendar.get(Calendar.HOUR).toString())
            v.setCalendar(calendar)
        }
        i++
    }
    Log.d("IDK:", calendar.get(Calendar.MONTH).toString())
}

/*
    Code revised from GitHub library located at: https://gist.github.com/derekstavis/3894287
*/
class DigitalClock: TextView {

    private var hours: Int = 0
        set(value) {field = value}
    private var minutes: Int = 0
        set(value) {field = value}
    private var seconds: Int = 0
        set(value) {field = value}
    private var years: Int = 2018
        set(value) {field = value}
    private var months: Int = 0
        set(value) {field = value}
    private var days: Int = 1
        set(value) {field = value}
    private var ID: Int = 0
        set(value) {field = value}

    private var clockTimer: Timer? = null
    private val clockTask = object : TimerTask() {
        override fun run() {
            mHandler.post(mUpdateResults)
        }
    }

    internal val mHandler = Handler()
    internal val mUpdateResults: Runnable = Runnable { update() }

    constructor(context: Context) : super(context) {
        init()
    }

    private fun update() {
        seconds++

        if (seconds >= 60) { //time to update
            seconds = 0
            if (minutes < 59) {
                minutes++
            } else if (hours < 23 && minutes >= 59) { //minutes = 59 but hours less than 24
                minutes = 0
                hours++
            } else if(hours >= 23 && minutes >= 59){ //minutes = 59 and hours = 24
                minutes = 0
                hours = 0
                if((months == 0 || months == 2 || months == 4 || months == 6 || months == 7 || months == 9 || months == 11)
                        && days < 31)
                {
                    days++
                } else if (months == 1 && days < 28){
                    days++
                } else if((months == 3 || months == 5 || months == 8 || months == 10) && days < 30){
                    days++
                } else{
                    days = 1
                    if(months < 11){
                        months++
                    } else {
                        Log.d("here", "Here")
                        months = 0
                        years++
                    }
                }
            }
        }

        var displayMonth = if(months == 0){
            "January "
        } else if(months == 1){
            "February "
        } else if(months == 2){
            "March "
        } else if(months == 3){
            "April "
        } else if(months == 4){
            "May "
        } else if(months == 5){
            "June "
        } else if(months == 6){
            "July "
        } else if(months == 7){
            "August "
        } else if(months == 8){
            "September "
        } else if(months == 9){
            "October "
        } else if(months == 10){
            "November "
        } else {
            "December "
        }
        textSize = 20F
        setTextColor(Color.parseColor("#9932CC"))
        text = String.format("%s %02d, %04d %02d:%02d:%02d", displayMonth, days, years, hours, minutes, seconds)
    }

    private fun init() {
        clockTimer = Timer()

        val mCalendar = Calendar.getInstance()
        hours = mCalendar.get(Calendar.HOUR_OF_DAY)
        minutes = mCalendar.get(Calendar.MINUTE)
        seconds = mCalendar.get(Calendar.SECOND)
        years = mCalendar.get(Calendar.YEAR)
        months = mCalendar.get(Calendar.MONTH)
        days = mCalendar.get(Calendar.DAY_OF_MONTH)

        clockTimer!!.scheduleAtFixedRate(clockTask, 0, 1000)
    }

    fun setTime(second: Int, minute: Int, hour: Int, day: Int, month: Int, year: Int){
        seconds = second
        minutes = minute
        hours = hour
        days = day
        months = month
        years = year
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }
}

class Time(seconds: Int, minutes: Int, hours: Int, days: Int, month: Int, years: Int){
    var _seconds = seconds
        get() = field
        set(value) {field}
    var _minutes = minutes
        get() = field
        set(value) {field}
    var _hours = hours
        get() = field
        set(value) {field}
    var _days = days
        get() = field
        set(value) {field}
    var _month = month
        get() = field
        set(value) {field}
    var _years = years
        get() = field
        set(value) {field}

    public fun updateTime(seconds: Int, minutes: Int, hours: Int, days: Int, month: Int, years: Int){
        _seconds = seconds
        _minutes = minutes
        _hours = hours
        _days = days
        _month = month
        _years = years
    }
}

/*
    Code revised from GitHub library located at: https://gist.github.com/derekstavis/3894287
*/
class WordClock: TextView {

    private var hours: Int = 0
        get() = field
        set(value) {field = value}
    private var minutes: Int = 0
        get() = field
        set(value) {field = value}
    private var seconds: Int = 0
        get() = field
        set(value) {field = value}
    private var years: Int = 2018
        get() = field
        set(value) {field = value}
    private var months: Int = 0
        get() = field
        set(value) {field = value}
    private var days: Int = 1
        get() = field
        set(value) {field = value}
    private var ID: Int = 0
        get() = field
        set(value) {field = value}

    private var clockTimer: Timer? = null
    private val clockTask = object : TimerTask() {
        override fun run() {
            mHandler.post(mUpdateResults)
        }
    }

    internal val mHandler = Handler()
    internal val mUpdateResults: Runnable = Runnable { update() }

    constructor(context: Context) : super(context) {
        init()
    }

    private fun update() {
        seconds++

        if (seconds >= 60) { //time to update
            seconds = 0
            if (minutes < 59) {
                minutes++
            } else if (hours < 23 && minutes >= 59) { //minutes = 59 but hours less than 24
                minutes = 0
                hours++
            } else if(hours >= 23 && minutes >= 59){ //minutes = 59 and hours = 24
                minutes = 0
                hours = 0
                if((months == 0 || months == 2 || months == 4 || months == 6 || months == 7 || months == 9 || months == 11)
                        && days < 31)
                {
                    days++
                } else if (months == 1 && days < 28){
                    days++
                } else if((months == 3 || months == 5 || months == 8 || months == 10) && days < 30){
                    days++
                } else{
                    days = 1
                    if(months < 11){
                        months++
                    } else {
                        months = 0
                        years++
                    }
                }
            }
        }

        var displayMonth = if(months == 0){
            "January"
        } else if(months == 1){
            "February"
        } else if(months == 2){
            "March"
        } else if(months == 3){
            "April"
        } else if(months == 4){
            "May"
        } else if(months == 5){
            "June"
        } else if(months == 6){
            "July"
        } else if(months == 7){
            "August"
        } else if(months == 8){
            "September"
        } else if(months == 9){
            "October"
        } else if(months == 10){
            "November"
        } else {
            "December"
        }
        textSize = 20F
        setTextColor(Color.BLACK)
        text = String.format("                      %s %d, %d", displayMonth, days, years)
    }

    private fun init() {
        clockTimer = Timer()

        val mCalendar = Calendar.getInstance()
        hours = mCalendar.get(Calendar.HOUR_OF_DAY)
        minutes = mCalendar.get(Calendar.MINUTE)
        seconds = mCalendar.get(Calendar.SECOND)
        years = mCalendar.get(Calendar.YEAR)
        months = mCalendar.get(Calendar.MONTH)
        days = mCalendar.get(Calendar.DAY_OF_MONTH)

        clockTimer!!.scheduleAtFixedRate(clockTask, 0, 1000)
    }

    fun setTime(second: Int, minute: Int, hour: Int, day: Int, month: Int, year: Int){
        seconds = second
        minutes = minute
        hours = hour
        days = day
        months = month
        years = year
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }
}

class MyVectorClock : VectorAnalogClock {

    private fun init() {
        //use this for the default Analog Clock (recommended)
        initializeSimple()

        //or use this if you want to use your own vector assets (not recommended)
        //initializeCustom(faceResourceId, hourResourceId, minuteResourceId, secondResourceId);
    }

    //mandatory constructor
    constructor(ctx: Context) : super(ctx) {
        init()
    }

    // the other constructors are in case you want to add the view in XML

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }
}

/*
    Implementation of Command Design Pattern
 */
interface Command{
    fun doIt()
    fun undoIt()
}

class UpdateTimeCommand(prevTime: Time, currTime: Time, layout: LinearLayout, calendar: Calendar): Command{
    private var previousTime = prevTime
    private var currentTime = currTime
    private var layout = layout
    private var theCalendar = calendar

    var currentSeconds = currentTime._seconds
    var currentMinutes = currentTime._minutes
    var currentHours = currentTime._hours
    var currentDay = currentTime._days
    var currentMonth = currentTime._month
    var currentYear = currentTime._years

    var previousSeconds = previousTime._seconds
    var previousMinutes = previousTime._minutes
    var previoustHours = previousTime._hours
    var previousDay = previousTime._days
    var previousMonth = previousTime._month
    var previousYear = previousTime._years

    override fun doIt() {
        updateClocks(layout, currentSeconds, currentMinutes, currentHours, currentDay,
                currentMonth, currentYear, theCalendar)
    }

    override fun undoIt() {
        Log.d("month to go back to", previousMonth.toString())
        updateClocks(layout, previousSeconds, previousMinutes, previoustHours, previousDay,
                previousMonth, previousYear, theCalendar)
    }
}

class CommandQ{
    private var cmdsDone = ArrayDeque<UpdateTimeCommand>()
    private var cmdsUndone = ArrayDeque<UpdateTimeCommand>()

    fun push(command: UpdateTimeCommand){
        cmdsDone.push(command)
    }

    fun undo(){
        if(cmdsDone.isNotEmpty()) {
            var command = cmdsDone.pop()
            command.undoIt()
            cmdsUndone.push(command)
        }
    }

    fun redo(){
        if(cmdsUndone.isNotEmpty()) {
            var command = cmdsUndone.pop()
            command.doIt()
            cmdsDone.push(command)
        }
    }
}