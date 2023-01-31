@file:JvmName("KoinJvmCoreDatabase")

package devlcc.io.kmmshowcaserealestate.core.database.di

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import devlcc.io.kmmshowcaserealestate.core.database.KMMShowcaseRealEstateDb
import org.koin.dsl.module

actual val platformDatabaseModule = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            KMMShowcaseRealEstateDb.Schema,
            get<Context>(),
            DB_NAME
        )
    }
}