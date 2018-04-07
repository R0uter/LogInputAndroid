package im.lginput.sql

import android.content.Context
import im.lginput.app.database
import im.lginput.engine.Engine

class SqlEngine(ctx: Context) : Engine(SqlSuggestion(ctx.database))