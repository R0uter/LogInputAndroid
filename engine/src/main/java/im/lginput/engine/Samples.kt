package im.lginput.engine

class SampleSuggestion : Suggestion(), SuggestionProvider {
  var suggestions = ArrayList<String>(10)
  override fun toList(): List<String> {
    return suggestions
  }

  override fun clear() {
    suggestions.clear()
  }

  override fun suggest(x: WordComposer): Suggestion {
    suggestions.clear()
    suggestions.add("哈哈哈哈")
    return this
  }
}

class SampleEngine : Engine(SampleSuggestion())

class SampleLaxer : WordLaxer() {
  override fun lax(compose: WordComposer): List<String> {
    TODO()
    return listOf("df", "df")
  }
}