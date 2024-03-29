package devlcc.io.kmmshowcaserealestate.core.model.di

/**
 *
 * Common parcelable implementation for androis
 */

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
expect annotation class CommonParcelize()

expect interface CommonParcelable
