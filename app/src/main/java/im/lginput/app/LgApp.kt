package im.lginput.app

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.reflect.Constructor
import java.net.URI
import kotlin.concurrent.thread

class LgApp : Application() {
  override fun onCreate() {
    super.onCreate()
    val ctx = applicationContext
    val dbFile = File(ctx.applicationInfo.dataDir + "/databases", "main")
    thread {
      if (!dbFile.exists()) {
        if (!dbFile.parentFile.exists()) {
          dbFile.parentFile.mkdirs()
        }
        synchronized(dbFile) {
          ctx.assets.open("dag.db").copyTo(FileOutputStream(dbFile))
        }

      }
    }
    instance = this
  }

  companion object {
    lateinit var instance: Application
  }
}

class SampleDataBase(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "main") {
  override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    return
  }

  override fun onCreate(db: SQLiteDatabase?) {
    return
  }

  companion object {
    private var instance: SampleDataBase? = null

    @Synchronized
    fun getInstance(ctx: Context): SampleDataBase {
      if (instance == null) {
        instance = SampleDataBase(ctx.applicationContext)
      }
      return instance!!
    }
  }
}

val Context.database
  get() = SampleDataBase.getInstance(applicationContext)