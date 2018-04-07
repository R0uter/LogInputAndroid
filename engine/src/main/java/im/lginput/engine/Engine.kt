package im.lginput.engine

import java.lang.StringBuilder

abstract class WordComposer() {
  val words = ArrayList<String>(4)
  var offset = 0
  abstract fun composing(): String
  fun putWord(w: String): Unit {
    offset += w.length
    words.add(w)
  }

  fun currentComposing(): String {
    val temp = composing()
    return temp[offset..temp.lastIndex]
  }

  fun clear(): Unit {
    words.clear()
    offset = 0
  }
}

operator fun String.get(range: IntRange): String {
  return this.slice(range)
}

fun CharSequence.join(os: Array<Any>): String {
  var res = ""
  os.forEachIndexed { index, any ->
    if (index != 0) {
      res += this
    }
    res += any.toString()
  }
  return res
}

abstract class Suggestion() {
  abstract fun toList(): List<String>
  fun getPreferred(): String {
    assert(toList().isNotEmpty()) { "Suggestion List is empty" }
    return toList()[0]
  }

  abstract fun clear()
}

interface SuggestionProvider {
  fun suggest(x: WordComposer): Suggestion
}


abstract class Engine(var suggestionProvider: SuggestionProvider) : SuggestionProvider by suggestionProvider {
}

class CompWordComposer : WordComposer() {
  lateinit var buffer: String
  override fun composing(): String {
    return buffer
  }

  fun setComposingBuffer(src: StringBuilder) {
    buffer = src.toString()
  }
}



