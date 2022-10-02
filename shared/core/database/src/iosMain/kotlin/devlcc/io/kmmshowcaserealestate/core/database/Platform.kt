package devlcc.io.kmmshowcaserealestate.core.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.dsl.module

actual val platformDatabaseModule = module {
    single<SqlDriver> {
        NativeSqliteDriver(
            schema = KMMShowcaseRealEstateDb.Schema,
            name = DB_NAME,
        )
    }
}
