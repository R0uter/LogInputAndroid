package im.lginput.sql

import im.lginput.engine.Suggestion
import im.lginput.engine.SuggestionProvider
import im.lginput.engine.WordComposer
import im.lginput.engine.join
import org.jetbrains.anko.db.*

class SqlSuggestion(val db: ManagedSQLiteOpenHelper) : Suggestion(), SuggestionProvider {
  var suggestions = ArrayList<String>(10)
  override fun toList(): List<String> {
    return suggestions
  }

  override fun clear() {
    suggestions.clear()
  }

  override fun suggest(x: WordComposer): Suggestion {
    suggestions.clear()
    val word = x.composing()
    val words = ArrayList<CharSequence>(4)
    var currentTable = x.words.size + 1
    db.use {
      val s = ArrayList<String>(4)
      words.forEachIndexed { i, e ->
        s.add("p${i + 1}=$e")
      }
//            val str = " AND ".join(words.toArray()) + " AND p${words.size+1} >= $word;}"
      val str = "p1 = 'wang'"
      val res = select("w$currentTable").whereArgs(str).exec {
        val res = parseSingle(object : RowParser<String> {
          override fun parseRow(columns: Array<Any?>): String {
            return columns.lastIndex as String
          }
        })
        res
      }
      suggestions.add(res)
      null
    }
    return this
  }
}

