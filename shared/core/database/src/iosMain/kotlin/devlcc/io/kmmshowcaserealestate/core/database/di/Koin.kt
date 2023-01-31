package devlcc.io.kmmshowcaserealestate.core.database.di

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import devlcc.io.kmmshowcaserealestate.core.database.KMMShowcaseRealEstateDb
import org.koin.dsl.module

actual val platformDatabaseModule = module {
    single<SqlDriver> {
        NativeSqliteDriver(
            schema = KMMShowcaseRealEstateDb.Schema,
            name = DB_NAME,
        )
    }
}
