package devlcc.io.kmmshowcaserealestate.core.database.di

import devlcc.io.kmmshowcaserealestate.core.database.FavoriteDao
import devlcc.io.kmmshowcaserealestate.core.database.KMMShowcaseRealEstateDb
import devlcc.io.kmmshowcaserealestate.core.database.PropertyDao
import devlcc.io.kmmshowcaserealestate.core.database.sqldelight.dao.FavoriteDaoImpl
import devlcc.io.kmmshowcaserealestate.core.database.sqldelight.dao.PropertyDaoImpl
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.jvm.JvmName

fun getCoreDatabaseModule() = module {

    single<KMMShowcaseRealEstateDb> {
        KMMShowcaseRealEstateDb(
            driver = get()
        )
    }

    // TODO:: List DAO here...
    single<PropertyDao> {
        PropertyDaoImpl(
            database = get(),
            json = get(),
            ioDispatcher = get()/*newFixedThreadPoolContext(20, "KMMRealEstateShowcase-CoroutineDispatcher")*/,
        )
    }
    single<FavoriteDao> {
        FavoriteDaoImpl(
            database = get(),
            json = get(),
            ioDispatcher = get()/*newFixedThreadPoolContext(20, "KMMRealEstateShowcase-CoroutineDispatcher")*/,
        )
    }
}
    .apply { includes(platformDatabaseModule) }

const val DB_NAME = "KMMShowcaseRealEstateDb"

internal expect val platformDatabaseModule: Module