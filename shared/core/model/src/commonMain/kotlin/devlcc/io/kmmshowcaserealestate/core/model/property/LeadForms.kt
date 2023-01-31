package devlcc.io.kmmshowcaserealestate.core.model.property

import devlcc.io.kmmshowcaserealestate.core.model.di.CommonParcelable
import devlcc.io.kmmshowcaserealestate.core.model.di.CommonParcelize

@CommonParcelize
data class LeadForms(
    val form: Fields? = null,
    val showAgent: Boolean? = null,
    val showBroker: Boolean? = null,
    val showBuilder: Boolean? = null,
    val showContactALender: Boolean? = null,
    val showVeteransUnited: Boolean? = null,
    val formType: FormType? = null,
    val leadType: LeadType? = null,
    val isLcmEnabled: Boolean? = null,
    val flipTheMarketEnabled: Boolean? = null,
    val localPhone: String? = null,
    val localPhones: LocalPhones? = null,
    val showTextLeads: Boolean? = null,
    val cashbackEnabled: Boolean? = null,
    val smarthomeEnabled: Boolean? = null
) : CommonParcelable {

    @CommonParcelize
    data class Fields(
        val name: Validation? = null,
        val email: Validation? = null,
        val phone: Validation? = null,
        val message: Validation? = null,
        val show: Boolean? = null,
    ) : CommonParcelable {

        @CommonParcelize
        data class Validation(
            val required: Boolean? = null,
            val minimumCharacterCount: Int? = null,   // "minimum_character_count"
            val maximumCharacterCount: Int? = null,
        ) : CommonParcelable

    }

    @CommonParcelize
    enum class FormType : CommonParcelable {
        Classic // "classic"
    }

    @CommonParcelize
    enum class LeadType : CommonParcelable {
        CoBroke // co_broke
    }

    @CommonParcelize
    data class LocalPhones(
        val commConsoleMweb: String? = null,  // comm_console_mweb (i.e. "(888)860-0947")
    ) : CommonParcelable

}
