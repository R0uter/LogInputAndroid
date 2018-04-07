package im.lginput.keyboard

import android.content.ComponentCallbacks2
import android.util.Log
import android.view.inputmethod.InputConnection
import com.blackcj.customkeyboard.SoftKeyboard
import im.lginput.app.LgApp
import im.lginput.app.database
import im.lginput.engine.*
import im.lginput.sql.SqlEngine
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.concurrent.thread

open class LgBaseSoftKeyboard(var engine: Engine) : SoftKeyboard() {
  private val composer = CompWordComposer()
  lateinit var suggestion: Suggestion
  override fun updateCandidates() {
    super.updateCandidates()
    if (!mCompletionOn) {
      if (mComposing.isNotEmpty()) {
        composer.setComposingBuffer(mComposing)
        suggestion = engine.suggest(composer)
        setSuggestion(suggestion, true, true)
      } else {
        super.setSuggestions(null, false, false)
      }
    }
  }

  override fun commitTyped(ic: InputConnection?) {
    if (mComposing.isNotEmpty()) {
      ic!!.commitText(suggestion.getPreferred(), 1)
      mComposing.setLength(0)
      composer.clear()
//            suggestion.clear()
      updateCandidates()
    }
  }

  private fun setSuggestion(suggestion: Suggestion, complete: Boolean, valid: Boolean) {
    super.setSuggestions(suggestion.toList(), complete, valid)
  }

}

class LgSoftKeyboard : LgBaseSoftKeyboard(SqlEngine(LgApp.instance.applicationContext))