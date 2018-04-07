package im.lginput.engine

abstract class WordLaxer() {
  abstract fun lax(compose: WordComposer): List<String>
}

enum class Word

enum class U(val value: String) {
  b("b"),
  p("P"),
  m("m"),
  f("f"),
  d("d"),
  t("t"),
  n("n"),
  l("l"),
  g("g"),
  k("k"),
  h("h"),
  j("j"),
  q("q"),
  x("x"),
  zh("zh"),
  ch("ch"),
  sh("sh"),
  r("r"),
  z("z"),
  c("c"),
  s("s"),
  y("y"),
  w("w")
}

enum class I(val value: String) {
  a("a"),
  o("o"),
  e("e"),
  i("i"),
  u("u"),
  v("v"),
  ai("ai"),
  ei("ei"),
  ui("ui"),
  ao("ao"),
  ou("ou"),
  iu("iu"),
  ie("ie"),
  ve("ve"),
  an("an"),
  en("en"),
  iin("in"),
  un("un"),
  ang("ang"),
  eng("eng"),
  ing("ing"),
  ong("ong")
}

enum class O(value: String) {
  er("er")
}