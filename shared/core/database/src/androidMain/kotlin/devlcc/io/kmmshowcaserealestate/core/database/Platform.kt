@file:JvmName("PlatformJvmCoreDatabase")

package devlcc.io.kmmshowcaserealestate.core.database

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
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