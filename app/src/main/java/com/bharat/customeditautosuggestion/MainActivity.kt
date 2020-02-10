package com.bharat.customeditautosuggestion

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.bharat.customeditautsuggestion.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var tag_firebase_list: ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    fun init(){
        tag_firebase_list.add("sleep")
        tag_firebase_list.add("Digestion")
        tag_firebase_list.add("Mood")
        tag_firebase_list.add("happy")
        tag_firebase_list.add("sad")
        tag_firebase_list.add("good")
        tag_firebase_list.add("well")
        tag_firebase_list.add("done")
        tag_firebase_list.add("Courtney Pittman")
        tag_firebase_list.add("Chad Greene")
        tag_firebase_list.add("Lamar Glover")
        tag_firebase_list.add("Christian Kelley")
        tag_firebase_list.add("Wallace Francis")
        tag_firebase_list.add("alejandro Bass")
        tag_firebase_list.add("Jeff Franklin")
        tag_firebase_list.add("raquel Owens")
        tag_firebase_list.add("Brad Watson")


        var is_textchange=true
        autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(str: CharSequence?, start: Int, before: Int, count: Int) {

                if(!is_textchange){
                    is_textchange=true
                    return
                }
                Log.e("kkkk","onTextChanged")
                var type = str.toString()
                if (Validation.isRequiredField(
                        type
                    )
                ) {
                    var serach_found = ""
                    var serach_list: ArrayList<String> = ArrayList()
                    for (tag in tag_firebase_list) {
                        if (tag!!.contains(type, ignoreCase = true)) {
                            Log.e("kk","tag"+tag)
                            serach_found = tag

                            serach_list.add(serach_found)
                            // break
                        }

                    }

                    /*   for (i in 0 until serach_list.size) {
                           //  Log.e("test","filter"+serach_list.get(0))
                       }*/
                    if (serach_list.size > 0) {

                        var test = serach_list.get(0)
                        Log.e("test", "filter==>" + test)

                        var match = true
                        for (i in 0 until type.length) {
                            if (type[i]!!.toString().equals(
                                    test[i].toString(),
                                    ignoreCase = true
                                )
                            ) {
                                match = true
                            } else {
                                match = false
                                break
                            }
                        }
                        if (match) {
                            edt_hint.setText(serach_list.get(0))
                            var cover_same_text=serach_list.get(0)!!.substring(0,type.length)
                            is_textchange=false
                            autoCompleteTextView.setText(cover_same_text)
                            autoCompleteTextView.setSelection(cover_same_text.length)
                        } else {
                            edt_hint.setText("")
                        }
                    } else {
                        edt_hint.setText("")
                        edt_hint.setHint("")
                    }

                } else {
                    edt_hint.setText("")
                    autoCompleteTextView.setHint(resources.getString(R.string.add_tags_eg_sleep_digestion))
                }

            }

        })

        autoCompleteTextView.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    var hint_text = edt_hint.text.toString()
                    Log.e("hhh","=-====hint_text===="+hint_text)
                    var write_str = autoCompleteTextView.text.toString()
                    if(!write_str.equals("")) {
                        hideKeyboard()
                        if(tag_firebase_list.contains(write_str)){
                            if (write_str.length > hint_text.length) {
                                //autoCompleteTextView.setText(hint_text)
                                autoCompleteTextView.text!!.clear()

                            } else {
                                autoCompleteTextView.text!!.clear()
                                //flexbox_layout.addTag(hint_text)
                               // tag_selected_list.add(hint_text)
                            }
                        }else{
                            Log.e("test","please enter tag");

                        }

                    }else{

                    }
                    Log.e("test", "IME_ACTION_DONE")
                }
                return true
            }


        })
    }
    fun hideKeyboard() {
        try {
            val view = currentFocus
            if (view != null) {
                view.clearFocus()
                val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
