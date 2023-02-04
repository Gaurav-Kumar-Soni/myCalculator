package sonigaurav950.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    private var lastNumeric: Boolean = false             // for checking input last character is numeric or not
    private var lastDot: Boolean = false                  // for checking there is  decimal in input or not
    private var resultDone: Boolean = false                //  after equal sign press, resultDone = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)

    }

    fun checkLength() {
        if (tvInput?.length() == 0)                                   // checking input length is zero or not
            lastNumeric = false
    }

    fun onDigit(view: View) {
        if (!resultDone) {
            tvInput?.append((view as Button).text)
            lastNumeric = true
            resultDone = false
        }
        if (resultDone) {
            tvInput?.text = ((view as Button).text).toString()
            lastNumeric = true
            resultDone = false
        }

    }

    fun onClear(view: View) {
        tvInput?.text = ""
        lastDot = false
        lastNumeric = false
        resultDone = false
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            if (!resultDone) {
                tvInput?.append(".")
                lastDot = true
                lastNumeric = false
            }
            if (resultDone) {
                tvInput?.text = ""
            }
        }

    }

    fun onOperator(view: View) {
//        val length = (tvInput?.text?.length)
//        val lastChar: Char? = ((tvInput?.text)?.last())
//        if ((lastChar == '+' || lastChar == '-' || lastChar == 'x' || lastChar == '/')&&(!isOperatorAdded(
//                tvInput?.text as String
//            )) && (length != 0)) {
//            tvInput?.text = (tvInput?.text)?.substring(0, (tvInput?.length()?.minus(1)!!))
//            tvInput?.append((view as Button).text)
//            lastNumeric = false
//            lastDot = false
//            resultDone = false
//            return
//        }

        checkLength()
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString()) && tvInput?.length() != 0) {
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
                resultDone = false
            }

        }
    }


    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            (value.contains("/") || value.contains("x") || value.contains("+") || value.contains(
                "-"
            ))
        }

    }

    fun isEqual(view: View) {
        if (lastNumeric) {
            var tvValue = (tvInput?.text).toString()
            try {

                var prefix = ""
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }


                    val result = one.toDouble() - two.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())
                }
                if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }


                    val result = one.toDouble() + two.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())
                }
                if (tvValue.contains("x")) {
                    val splitValue = tvValue.split("x")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }


                    val result = one.toDouble() * two.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())
                }
                if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }


                    val result = one.toDouble() / two.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
        resultDone = true
    }

    private fun removeZeroAfterDot(result: String): String {
        var value = result
        if (result.endsWith(".0")) {
            value = result.substring(0, result.length - 2)
        }
        return value
    }

    fun deleteByOne(view: View) {
        if ((tvInput?.length()) != 0)
            tvInput?.text = (tvInput?.text)?.substring(0, (tvInput?.length()?.minus(1)!!))

    }


}




